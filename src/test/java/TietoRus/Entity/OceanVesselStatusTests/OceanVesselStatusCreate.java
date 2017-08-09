package TietoRus.Entity.OceanVesselStatusTests;


import TietoRus.system.helpers.helpers.Asserts;
import TietoRus.system.helpers.helpers.GetDataHelper;
import TietoRus.system.helpers.models.OceanVesselStatusHub;
import TietoRus.system.helpers.objects.OceanVesselStatusHubObjects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Проверка правильного формирования HUB'ов, SatHubStatus'ов, SAT'ов и LINK'ов после первоначальной загрузки данных
 */
public class OceanVesselStatusCreate {
    /**
     * Тест проверяет общий положительный сценарий по добавлению записей в hub, sat и hubSatStatus
     * Предусловия:
     * 1. Запись в SA должна существовать.
     * У нее: srcSysId = ID_SA-источника, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, statusSat = 0, cdcOperation = null,  остальные значения любые
     * 2. Записей в DWH не должно существовать
     * 3. Зафиксировать значение tryCtn в SA-таблице, присвоить это значение переменной tryCtnPrecondition
     * Действия:
     * 1. Добавить запись в SA таблицу
     * 2. Запустить пакеты загрузки hub'а, sat'а и satHubStatus'а
     * 3. Запустить тест
     */

    private GetDataHelper dh = new GetDataHelper();
    private OceanVesselStatusHubObjects hubObjects = new OceanVesselStatusHubObjects();
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
        tableInSA = properties.getProperty("oceanVesselStatus.UNITY.table");
        tableHub = properties.getProperty("oceanVesselStatus.hub.table");
        tableSat = properties.getProperty("oceanVesselStatus.sat.table");
        fieldNameForHubId = properties.getProperty("oceanVesselStatus.fieldNameForHubIdInSatHubStatus");
        tableSatHubStatus = properties.getProperty("oceanVesselStatus.SatStatus.table");
        String viewForDWH = properties.getProperty("oceanVesselStatus.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String hubSQL = SQL.getSelectHub(tableHub);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);
        Integer satStatus = dh.getSatStatusFromSA(saSQL);

        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record in SA not found or more then one record with identical keys. ");
        } else
//Проверка hub'а
            if (dh.getCountRowOfHub(hubSQL) == 1) {
                OceanVesselStatusHub hubfromSA = hubObjects.getHubFromSA(saSQL);
                OceanVesselStatusHub hubfromDWH = hubObjects.getHubFromDWH(hubSQL);
                asserts.assertOceanVesselStatusHub(hubfromSA, hubfromDWH);

                System.err.println("Check TryCtn! It must been not update! TryCtn [" + dh.getTryCtnFromSA(saSQL) + "]");

                if (hubStatus == 1) {
                    System.out.println("HubStatus set valid values! HubStatus [" + hubStatus + "]");
                } else {
                    System.err.println("HubStatus have not valid values! HubStatus [" + hubStatus + "]");
                }

                if (satStatus == 3) {
                    System.out.println("SatStatus set valid values! SatStatus [" + satStatus + "]");
                } else {
                    System.err.println("SatStatus have not valid values! SatStatus [" + satStatus + "]");
                }


// Проверка SatHubStatus'а
                dwhHubId = dh.getDWHHubId(hubSQL, fieldNameForHubId);
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

                System.err.println("Check package log!");
            } else {
                System.err.println("Record in DWH not found or they are more one!");
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
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
