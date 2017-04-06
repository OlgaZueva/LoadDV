package TietoRus.FileLinerTests;

import TietoRus.helpers.Asserts;
import TietoRus.helpers.GetDataHelper;
import TietoRus.models.FileLiner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by zuevaolg on 05.04.2017.
 * Проверка правильного формирования HUB'ов, SAT'ов и LINK'ов после первоначальной загрузки данных
 */
public class FileLinerDWHTests {
    /** тест проверяет общий положительный сценарий по добавлению записи
     * Предусловия:
     * 1. Запись в SA должна существовать. У нее: PartitionId = 0, srcSysID =1, TryCnt < maxTryCount и валидные значения бизнес-ключей
     * 2. Запись в DWH не должна существовать
     * 3. Зафиксировать значение tryCtn в SA-таблице, присвоить это значение переменной tryCtnPrecondition
     * Действия:
     * 1. Добавить запись в SA таблицу
     * 2. Запустить пакет загрузки хаба
     * 3. Запустить тест
     */

    private GetDataHelper dh = new GetDataHelper();
    private Properties properties = new Properties();
    private Asserts asserts = new Asserts();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableForTestDataInSA = "stg.UNITY_Sag";
    private String tableForTestDataInDWH = "hub.hubFileLiner";

    //Подготовь данные в SQLforTestData -> получи запрос на добавление записи -> используй его для добавления тестовой записи!
    // Добавь запись в SA перед выполнением теста!
    @Test
    public void HubCreatedValidFlow() throws SQLException, IOException {
        getPropertiesFile();
        int tryCtnPrecondition = 0;
        String saSQL = SQL.getSelectFromSA("stg.v_Sag");
        String dwhSQL = SQL.getSelectFromDWH(tableForTestDataInDWH);
        String satHubStatusTable = "SELECT *  FROM sat.satFileLinerStatus where dwhIdHubFileLiner = ";

        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        if (tryCtn == null) {
            System.err.println("TryCtn is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus != 0 || (tryCtn <= Integer.parseInt(properties.getProperty("system.MaxTryCount")))) {
            if (dh.getCountRowOfHub(dwhSQL) == 1) {
                FileLiner hubfromSA = dh.getHubFromSA(saSQL);
                FileLiner hubfromDWH = dh.getHubFromDWH(dwhSQL);
                asserts.assertHubs(hubfromSA, hubfromDWH);

                /* Запись в HubStaStatus statellit table появиться только если загружаем сат.
                * //Когда только хаб, без сата -  ее не будет, поэтому проверку отключила

                Integer dwhIdHub = dh.getDWHidHub(dwhSQL);
                Integer satHubStatus = dh.getSatHubStatus(satHubStatusTable + dwhIdHub);
                if (satHubStatus == null) {
                    System.err.println("SatHubStatus is null! Maybe record not found or more then one record in SA with identical keys.");
                } else {
                    if (satHubStatus == 1) {
                        System.out.println("SatHubStatus have valid value! SatHubStatus [" + satHubStatus + "]");
                    } else {
                        System.err.println("SatHubStatus have not valid value! SatHubStatus [" + satHubStatus + "]");
                    }
                }
               */

                Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                if (hubStatusAfter == 1) {
                    System.out.println("HubStatus set valid values! HubStatus [" + hubStatusAfter + "]");
                } else {
                    System.err.println("HubStatus have not valid values! HubStatus [" + hubStatusAfter + "]");
                }
                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                if (tryCtnAfter == tryCtnPrecondition) {
                    System.out.println("TryCount not update. It's valid. TryCtn [" + tryCtnAfter + "]");
                } else {
                    System.err.println("TryCtn was updated,  but it's wrong! TryCtn [" + tryCtnAfter + "]");
                }

/* Проверка накручивания счетчика tryCnt. Счетчик увеличивается только при неудачных попытках загразки.
* При успешной счетчик не должен увеличиваться.
* Отключена проверка пока тут
                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                if ((tryCtnAfter == (tryCtnPrecondition + 1))) {
                    System.out.println("TryCount set valid values! TryCtn [" + tryCtnAfter + "]");
                } else {
                    System.err.println("TryCtn have not valid values! TryCtn [" + tryCtnAfter + "]");
                }
                */

            } else {
                System.err.println("Record in DWH not found or they are more one!");
            }
        } else {
            FileLiner hubfromDWH = dh.getHubFromDWH(dwhSQL);
            if (hubfromDWH != null) {
                System.err.println("Hub was created, but hubStatus or tryCtn out of valid values!");
                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                if (tryCtnAfter.equals(tryCtn) || hubStatusAfter.equals(hubStatus)) {
                    System.err.println("Package change tryCtn or nubStatus in SA!");
                }
            } else {
                System.out.println("HubStatus in SA =0. Do nothing. Check package log");
            }
        }
    }

    @Test(enabled = false)
    public void MainScenario() throws SQLException, IOException {
        int tryCtnPrecondition = 0;
        String saSQL = "select * from stg.UNITY_Sag where SELSKAB = 13 and SAGSNR = 21677777 and AFDELING = '020' and SrcSystemId =1";
        String dwhSQL = "select * from hub.hubFileLiner where accessCompanyId = 13 and fileLinerNr = 21677777 and serviceCode = '020' and SrcSystemId =1";
        String satHubStatusTable = "SELECT *  FROM sat.satFileLinerStatus where dwhIdHubFileLiner = ";

        FileLiner hubfromSA = dh.getHubFromSA(saSQL);
        FileLiner hubfromDWH = dh.getHubFromDWH(dwhSQL);
        asserts.assertHubs(hubfromSA, hubfromDWH);
        int satHubStatus = dh.getSatHubStatus(satHubStatusTable + dh.getDWHidHub(dwhSQL));
        System.out.println("SatHubStatus = " + satHubStatus);
        int tryCountAfter = dh.getTryCtnFromSA(saSQL);
        System.out.println("TryCount в SA после добавления хаба: " + tryCountAfter);
        int hubStatusAfter = dh.getHubStatusFromSA(saSQL);
        System.out.println("HubStatus в SA после добавления хаба:" + hubStatusAfter);


    }

    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableForTestDataInSA);
        dh.deleteTestRowFromDWH(tableForTestDataInDWH);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
