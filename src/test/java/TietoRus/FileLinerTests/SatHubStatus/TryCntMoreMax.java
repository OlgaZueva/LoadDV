package TietoRus.FileLinerTests.SatHubStatus;

import TietoRus.FileLinerTests.zSQLforTestData;
import TietoRus.system.helpers.helpers.GetDataHelper;
import TietoRus.system.helpers.objects.FileLinerSatObjects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Тест проверяет поведение системы при загрузке sat'ов в случае, когда satStatus = 0 и число попыток загрузки >= MaxTryCount
 */

/**
 * Тест проверяет поведение системы при загрузке satHubStatus'ов в случае, когда hubStatus = 1 и число попыток загрузки >= MaxTryCount
 * Обрабатывать должны только записи, у которых hubStatus = 1 и tryCnt <= MaxTryCount
 * Значение MaxTryCount в системе задается у пакета загрузки. Для тестов дублируется в system.properties
 *
 * Предусловия:
 * 1. Запись в SA должна существовать.
 * У нее: srcSysId =ID_SA-источника, tryCnt = MaxTryCount, PartitionId = 0, statusHub = 1, statusSat = 0, cdcOperation = null,  остальные значения любые
 * Действия:
 * 1. Вставить в SA запись с tryCnt = MaxTryCount и statusHub = 1
 * 2. Вставить в DWH запись Hub'а с соотвествующими ключами
 * 3. Запустить только пакет загрузки satHubStatus'a
 * 4. Запустить тест
 * 5. После анализа результатов теста зачистить тестовые данные
 */
public class TryCntMoreMax {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();
    private String tableInSA;
    private String tableHub;
    private String tableSat;
    private String tableSatHubStatus;
    private String fieldNameForHubId;
    private Integer dwhHubId;


    @Test
    public void tryCntMoreMax() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        tableSatHubStatus = properties.getProperty("fileLiner.SatStatus.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String hubSQL = SQL.getSelectHub(tableHub);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);
        Integer satStatus = dh.getSatStatusFromSA(saSQL);


        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record in SA not found or more then one record with identical keys. ");
        } else {
            dwhHubId = dh.getDWHHubId(hubSQL, fieldNameForHubId);
            if (dwhHubId == null) {
                System.err.println("HubId in DWH not found! It's fail! May be record in hub not found!");
            } else {
                Integer satHubStatus = dh.getSatHubStatus(tableSatHubStatus, fieldNameForHubId, dwhHubId);
                if (satHubStatus != null) {
                    System.err.println("Record for HubId in satHubStatus was found or more one! It's fail");
                } else {
                    if (hubStatus != 1) {
                        System.err.println("HubStatus have not valid values! It'fail! HubStatus [" + hubStatus + "]");
                    } else {
                        System.out.println("HubStatus does'not uptaded! It's expected. HubStatus [" + hubStatus + "]");
                    }
                    if (satStatus != 0) {
                        System.err.println("SatStatus have not valid values! it was update, it's fail! SatStatus [" + satStatus + "]");
                    } else {
                        System.out.println("SatStatus was not update! It's expected. SatStatus [" + satStatus + "]");
                    }
                    System.err.println("Check TryCtn! It must been not update! TryCtn [" + dh.getTryCtnFromSA(saSQL) + "]");
                    System.err.println("Check all log Tables! They are not have an error!");
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
            System.out.println("dwhHubId is null. No rows for delete in SatHubStatus. Return.");
            return;
        } else {
            dh.deleteSatHubStatus(tableSatHubStatus, fieldNameForHubId, dwhHubId);
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
