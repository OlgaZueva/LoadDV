package TietoRus.FileLinerTests.Links;

import TietoRus.FileLinerTests.zSQLforTestData;
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

/*
 * Тест проверяет поведение системы при загрузке link'ов в случае, когда lnkStatus = 0 и число попыток загрузки >= MaxTryCount
 */

/**
 * Тест проверяет поведение системы при загрузке link'ов в случае, когда lnkStatus = 0 и число попыток загрузки >= MaxTryCount
 * Обрабатывать должны только записи, у которых lnkStatus = 0 и tryCnt <= MaxTryCount
 * Значение MaxTryCount в системе задается у пакета загрузки. Для тестов дублируется в systemSQL.properties
 *
 * Предусловия:
 * 1. Запись в SA должна существовать.
 * У нее: srcSysId =ID_SA-источника, tryCnt = MaxTryCount, PartitionId = 0, statusHub = 0, statusSat = 0, statusLnk = 1, cdcOperation = null,  остальные значения любые
 * Действия:
 * 1. Вставить в SA запись с tryCnt = MaxTryCount и statusLnk = 0
 * 2. Вставить в DWH запись Hub'а с соотвествующими ключами
 * 3. Запустить только пакет загрузки link'a
 * 4. Запустить тест
 * 5. После анализа результатов теста зачистить тестовые данные
 */
public class TryCntMoreMax {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private TietoRus.CompanyTests.zSQLforTestData companySQL = new TietoRus.CompanyTests.zSQLforTestData();
    private FileLinerSatObjects satObjects = new FileLinerSatObjects();
    private Properties properties = new Properties();
    private String tableInSA;
    private String tableCompanyHub;
    private String tableHub;
    private String tableSat;
    private String tableLink;
    private String fieldNameForHubId;
    private Integer dwhHubId;
    private String fieldNameForCompanyHubId;
    private Integer dwhHubIdInCompany;


    @Test
    public void tryCntMoreMax() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        tableCompanyHub = properties.getProperty("company.hub.table");
        tableLink = properties.getProperty("fileLiner.lnkCompany.table");
        fieldNameForHubId = properties.getProperty("fileLiner.fieldNameForHubIdInSatHubStatus");
        fieldNameForCompanyHubId = properties.getProperty("company.fieldNameForHubIdInSatHubStatus");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String hubSQL = SQL.getSelectHub(tableHub);
        String companyHubSQL = companySQL.getSelectHub(tableCompanyHub);
        Integer lnkStatus = dh.getLnkStatusFromSA(saSQL);
        //Integer hubStatus = dh.getHubStatusFromSA(saSQL);
        Integer satStatus = dh.getSatStatusFromSA(saSQL);


        if (lnkStatus == null) {
            System.err.println("LnkStatus is null! Maybe record in SA not found or more then one record with identical keys. ");
        } else {
// Проверка Link'а
            dwhHubId = dh.getDWHHubId(hubSQL, fieldNameForHubId);
            if (dwhHubId == null) {
                System.err.println("HubId in DWH not found! It's fail! May be record in hub not found!");
            } else {
                dwhHubIdInCompany = dh.getDWHHubId(companyHubSQL, fieldNameForCompanyHubId);
                String satSQL = SQL.getSelectSat(tableSat, fieldNameForHubId, dwhHubId);
                FileLinerSat satFromDWH = satObjects.getSatFromDWH(satSQL);
                if (satFromDWH != null) {
                    System.err.println("In Sat present record! It's not valid!");
                } else {
                    if (lnkStatus != 1) {
                        System.err.println("HubStatus have not valid values! It'fail! HubStatus [" + lnkStatus + "]");
                    } else {
                        System.out.println("HubStatus does'not uptaded! It's expected. HubStatus [" + lnkStatus + "]");
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
            System.out.println("dwhHubId is null. No rows for delete in Sat. Return.");
            return;
        } else {
            dh.deleteSat(tableSat, fieldNameForHubId, dwhHubId);
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
