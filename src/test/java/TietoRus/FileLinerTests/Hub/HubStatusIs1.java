package TietoRus.FileLinerTests.Hub;

import TietoRus.FileLinerTests.zSQLforTestData;
import TietoRus.system.helpers.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Тест проверяет поведение системы в случае, когда  записьв SA имеет statusHub = 1
 */

/**
 * Тест проверяет поведение системы в случае, когда в SA statusHub = 1
 * Обрабатывать должны только записи, у которых statusHub = 0, если иначе, то ничего делать не должны
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: srcSysId =ID_SA-источника, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 1, cdcOperation = null,  остальные значения любые
 * 2. В DWH записи с этими же ключами быть не должно
 * Действия:
 * 1. Вставить в SA запись с statusHub = 1
 * 2. Запустить только пакет загрузки хаба
 * 3. Запустить тест
 * 4. После анализа результатов теста зачистить тестовые данные
 */
public class HubStatusIs1 {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();
    private String tableInSA;
    private String tableHub;


    @Test
    public void HubStatusIs1() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String hubSQL = SQL.getSelectHub(tableHub);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else {
            if (dh.getCountRowOfHub(hubSQL) == 1) {
                System.err.println("In DWH present record! It's not valid!");
            } else {
                System.out.println("Record in DWH doesn't created. It's expected result! Check package log!");
            }
            if (hubStatus == 1) {
                System.out.println("HubStatus set valid values! It's expected. HubStatus [" + hubStatus + "]");
            } else {
                System.err.println("HubStatus have not valid values! It'fail! HubStatus [" + hubStatus + "]");
            }
            System.err.println("Check TryCtn! It must been not update! TryCtn [" + dh.getTryCtnFromSA(saSQL) + "]");
        }

    }


    @AfterMethod
    public void deleteTestData() throws SQLException {
        String deleteFromSA = SQL.getDeleteFromSA(tableInSA);
        String deleteFromHub = SQL.getDeleteHub(tableHub);
        dh.deleteTestRowFromSA(deleteFromSA);
        dh.deleteHub(deleteFromHub);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}