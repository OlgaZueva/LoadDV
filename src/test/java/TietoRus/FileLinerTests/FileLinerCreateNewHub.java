package TietoRus.FileLinerTests;

import TietoRus.system.helpers.helpers.Asserts;
import TietoRus.system.helpers.helpers.GetDataHelper;
import TietoRus.system.helpers.models.FileLinerHub;
import TietoRus.system.helpers.models.FileLinerSat;
import TietoRus.system.helpers.objects.FileLinerHubObjects;
import TietoRus.system.helpers.objects.FileLinerSatObjects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Проверка правильного формирования HUB'ов, SAT'ов и LINK'ов после первоначальной загрузки данных
 */
public class FileLinerCreateNewHub {
    /**
     * тест проверяет общий положительный сценарий по добавлению записи
     * Предусловия:
     * 1. Запись в SA должна существовать.
     * У нее: srcSysId =1, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
     * 2. Запись в DWH не должна существовать
     * 3. Зафиксировать значение tryCtn в SA-таблице, присвоить это значение переменной tryCtnPrecondition
     * Действия:
     * 1. Добавить запись в SA таблицу
     * 2. Запустить пакеты загрузки hub'а, sat'а и satHubStatus'а
     * 3. Запустить тест
     */

    private GetDataHelper dh = new GetDataHelper();
    private FileLinerHubObjects hubObjects = new FileLinerHubObjects();
    private FileLinerSatObjects satObjects = new FileLinerSatObjects();
    private Properties properties = new Properties();
    private Asserts asserts = new Asserts();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableInSA;
    private String tableHub;
    private String tableSat;
    private String tableSatHubStatus;
    private String fieldNameForHubId;
    private Integer dwhHubId;

    //Подготовь данные в SQLforTestData -> получи запрос на добавление записи -> используй его для добавления тестовой записи!
    // Добавь запись в SA перед выполнением теста!
    @Test
    public void HubCreatedValidFlow() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        tableSat = properties.getProperty("fileLiner.sat.table");
        fieldNameForHubId = properties.getProperty("fileLiner.fieldNameForHubIdInSatHubStatus");
        tableSatHubStatus = properties.getProperty("fileLiner.SatStatus.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        int tryCtnPrecondition = 0;
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        //String saSQL = SQL.getSelectFromSA(tableInSA);
        String hubSQL = SQL.getSelectHub(tableHub);

        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        if (tryCtn == null) {
            System.err.println("TryCtn is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus != 0 || (tryCtn <= Integer.parseInt(properties.getProperty("system.MaxTryCount")))) {
            if (dh.getCountRowOfHub(hubSQL) == 1) {
                FileLinerHub hubfromSA = hubObjects.getHubFromSA(saSQL);
                FileLinerHub hubfromDWH = hubObjects.getHubFromDWH(hubSQL);
                asserts.assertFileLinerHubs(hubfromSA, hubfromDWH);

                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                if (tryCtnAfter == tryCtnPrecondition) {
                    System.out.println("TryCount not update. It's valid. TryCtn [" + tryCtnAfter + "]");
                } else {
                    System.err.println("TryCtn was updated,  but it's wrong! TryCtn [" + tryCtnAfter + "]");
                }

                Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                if (hubStatusAfter == 1) {
                    System.out.println("HubStatus set valid values! HubStatus [" + hubStatusAfter + "]");
                } else {
                    System.err.println("HubStatus have not valid values! HubStatus [" + hubStatusAfter + "]");
                }
            } else {
                System.err.println("Record in DWH not found or they are more one!");
            }
        } else {
            FileLinerHub hubfromDWH = hubObjects.getHubFromDWH(hubSQL);
            if (hubfromDWH != null) {
                System.err.println("Hub was created, but hubStatus or tryCtn out of valid values!");
                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                if (tryCtnAfter.equals(tryCtn) || hubStatusAfter.equals(hubStatus)) {
                    System.err.println("Package change tryCtn or nubStatus in SA! It's wrong!");
                }
            } else {
                System.out.println("HubStatus in SA =0. Do nothing. Check package log");
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
        String deleteFromSA = SQL.getDeleteFromSA(tableInSA);
        String deleteFromHub = SQL.getDeleteHub(tableHub);
        dh.deleteTestRowFromSA(deleteFromSA);
        dh.deleteHub(deleteFromHub);
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
