package TietoRus.FileLinerTests;

import TietoRus.system.helpers.helpers.Asserts;
import TietoRus.system.helpers.helpers.GetDataHelper;
import TietoRus.system.helpers.models.FileLinerSat;
import TietoRus.system.helpers.objects.FileLinerSatObjects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Тест проверяет поведение системы в случае, когда запись в SA имеет statusHub = 0 и при этом существует запись в DWH
 */

/**
 * Тест проверяет поведение системы в случае, когда в SA statusHub = 0 и при этом в DWH существует запись
 * В результате в SA_table у записи должны выставить statusHub = 1, в таблице satHubStatus ничего менять не должны (в данном случае записи там быть не должно, т.к. руками добавили hub)
 * (В общем случае, если хаб создавался бы системой, то в satHubStatus-таблице должна быть запись со статусом = 1 и он измениться не должен)
 * Предусловия:
 * 1. Запись в SA должна существовать.
 * У нее: srcSysId =1, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
 * 2. В DWH существует запись с этими же ключами
 * Действия:
 * 1. Вставить в SA запись с statusHub = 0 и  cdcOperation = null
 * 2. Вставить в DWH запись с теми же ключами
 * 3. Запустить пакет загрузки хаба
 * 4. Запустить тест
 * 5. После анализа результатов теста зачистить тестовые данные
 */
public class RowInDWHcdcOpNotD {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();
    private FileLinerSatObjects satObjects = new FileLinerSatObjects();
    private Asserts asserts = new Asserts();
    private String tableInSA;
    private String tableHub;
    private String tableSat;
    private String tableSatHubStatus;
    private String fieldNameForHubId;
    private Integer dwhHubId;


    @Test
    public void rowInDWHcdcOpIsNull() throws SQLException, IOException {


        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        tableSat = properties.getProperty("fileLiner.sat.table");
        tableSatHubStatus = properties.getProperty("fileLiner.SatStatus.table");
        fieldNameForHubId = properties.getProperty("fileLiner.fieldNameForHubIdInSatHubStatus");
        String satHubStatusTable = properties.getProperty("fileLiner.SatStatus.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String fieldNameForHubId = properties.getProperty("fileLiner.fieldNameForHubIdInSatHubStatus");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String hubSQL = SQL.getSelectHub(tableHub);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else {
            if (hubStatus == 1) {
                System.out.println("HubStatus set valid values! It's expected. HubStatus [" + hubStatus + "]");
            } else {
                System.err.println("HubStatus have not valid values! It'fail! HubStatus [" + hubStatus + "]");
            }

            Integer tryCtn = dh.getTryCtnFromSA(saSQL);
            System.out.println("Check this tryCnt. It not can be update. TryCtn [" + tryCtn + "]");

            int countRowOfHub = dh.getCountRowOfHub(hubSQL);
            if (countRowOfHub == 1) {
                System.out.println("Record in DWH is present! It's expected. Check package log! See row details in table for true.");
            } else if (countRowOfHub == 0) {
                System.err.println("Record in DWH doesn't found! It'fail!");
            } else if (countRowOfHub > 1) {
                System.err.println("In DWH more then one record for these keys It's fail!");
            }

            Integer dwhHubId = dh.getDWHHubId(hubSQL, fieldNameForHubId);
            if (dwhHubId == null) {
                System.err.println("HubId in DWH not found! It's fail!");
            } else {
                Integer satHubStatus = dh.getSatHubStatus(satHubStatusTable, fieldNameForHubId, dwhHubId);
                if (satHubStatus != null) {
                    System.err.println("Record for HubId in staHubStatus found! It's fail");
                } else {
                    System.out.println("Record for HubId in staHubStatus  not found! It's expected!");
                }
            }
        }
        // Проверка Sat'а
        dwhHubId = dh.getDWHHubId(hubSQL, fieldNameForHubId);
        if (dwhHubId == null) {
            System.err.println("HubId in DWH not found! It's fail!");
        } else {
            String satSQL = SQL.getSelectSat(tableSat, fieldNameForHubId, dwhHubId);
            FileLinerSat satfromSA = satObjects.getSatFromSA(saSQL);
            FileLinerSat satFromDWH = satObjects.getSatFromDWH(satSQL);
            asserts.assertFileLinerSat(satfromSA, satFromDWH);
// Проверка SatHubStatus'а
            Integer satHubStatus = dh.getSatHubStatus(tableSatHubStatus, fieldNameForHubId, dwhHubId);
            if (satHubStatus == null) {
                System.err.println("Record for HubId in satHubStatus not found or more one! It's fail");
            } else {
                if (satHubStatus != 1) {
                    System.err.println("SatHubStatus is not valid! SatHubStatus: [" + satHubStatus + "]");
                } else {
                    System.out.println("SatHubStatus is valid! SatHubStatus: [" + satHubStatus + "]");
                }
            }
        }

    }


    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableInSA);
        dh.deleteHub(tableHub);
        if (dwhHubId == null) {
            System.out.println("dwhHubId is null. No rows for delete in SatHub Status and Sat. Return.");
            return;
        } else {
            dh.deleteSat(tableSat, fieldNameForHubId, dwhHubId);
            dh.deleteSatHubStatus(tableSatHubStatus, fieldNameForHubId, dwhHubId);
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}