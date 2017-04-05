package TietoRus;

import TietoRus.helpers.Asserts;
import TietoRus.helpers.DBHelper;
import TietoRus.helpers.GetDataHelper;
import TietoRus.models.FileLiner;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zuevaolg on 05.04.2017.
 * Проверка правильного формирования HUB'ов, SAT'ов и LINK'ов после первоначальной загрузки данных
 */
public class FileLinerDWHTests {
    private GetDataHelper dh = new GetDataHelper();
    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();
    private Asserts asserts = new Asserts();
    private Map<String, Object> mapAllFromSA = new HashMap<String, Object>();
    private Map<String, Object> mapHubFromDWH = new HashMap<String, Object>();

    /**
     * Предусловия:
     * 1. Запись в SA должна существовать.
     * 2. Запись в DWH не должна существовать
     * 2. Зафиксировать значение tryCtn в SA-таблице, присвоить это значение переменной tryCtnPrecondition
     * Действия:
     * 1. Запустить пакет загрузки хаба
     * 2. Запустить тест
     */
    @Test
    public void HubCreated() throws SQLException, IOException {
        getPropertiesFile();
        int tryCtnPrecondition = 0;
        String saSQL = "select * from stg.UNITY_Sag where SELSKAB = 13 and SAGSNR = 21677777 and AFDELING = '020' and SrcSystemId =1";
        String dwhSQL = "select * from hub.hubFileLiner where accessCompanyId = 13 and fileLinerNr = 21677777 and serviceCode = '020' and SrcSystemId =1";
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
                Integer dwhIdHub = dh.getdwhIdHub(dwhSQL);
                Integer satHubStatus = dh.getSatHubStatus(satHubStatusTable + dwhIdHub);
                if (satHubStatus == null) {
                    System.err.println("SatHubStatus is null! Maybe record not found or more then one record in SA with identical keys.");
                } else {
                    System.out.println("SatHubStatus = " + satHubStatus);
                    Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                    Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                    if ((tryCtnAfter == (tryCtn+1)) & (hubStatusAfter == 1)) {
                        System.out.println("TryCount & HubStatus set valid values! TryCtn [" + tryCtnAfter + "], "
                                + "HubStatus [" + hubStatusAfter + "]");
                    }else {
                        System.err.println("HubStatus or TryCtn have not valid values! TryCtn [" + tryCtnAfter + "], "
                                + "HubStatus [" + hubStatusAfter + "]");
                    }
                }
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

    @Test
    public void MainScenario() throws SQLException, IOException {
        int tryCtnPrecondition = 0;
        String saSQL = "select * from stg.UNITY_Sag where SELSKAB = 13 and SAGSNR = 21677777 and AFDELING = '020' and SrcSystemId =1";
        String dwhSQL = "select * from hub.hubFileLiner where accessCompanyId = 13 and fileLinerNr = 21677777 and serviceCode = '020' and SrcSystemId =1";
        String satHubStatusTable = "SELECT *  FROM sat.satFileLinerStatus where dwhIdHubFileLiner = ";

        FileLiner hubfromSA = dh.getHubFromSA(saSQL);
        FileLiner hubfromDWH = dh.getHubFromDWH(dwhSQL);
        asserts.assertHubs(hubfromSA, hubfromDWH);
        int satHubStatus = dh.getSatHubStatus(satHubStatusTable + dh.getdwhIdHub(dwhSQL));
        System.out.println("SatHubStatus = " + satHubStatus);
        int tryCountAfter = dh.getTryCtnFromSA(saSQL);
        System.out.println("TryCount в SA после добавления хаба: " + tryCountAfter);
        int hubStatusAfter = dh.getHubStatusFromSA(saSQL);
        System.out.println("HubStatus в SA после добавления хаба:" + hubStatusAfter);


    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
