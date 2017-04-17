package TietoRus.FileLinerTests.Sat;

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
 * Тест проверяет поведение системы при загрузке sat'ов в случае, когда satStatus = 0 и число попыток загрузки >= MaxTryCount
 */

/**
 * Тест проверяет поведение системы при sat'ов в случае, когда satStatus = 0 и число попыток загрузки >= MaxTryCount
 * Обрабатывать должны только записи, у которых satStatus = 0 и tryCnt <= MaxTryCount
 * Значение MaxTryCount в системе задается у пакета загрузки. Для тестов дублируется в system.properties
 *
 * Предусловия:
 * 1. Запись в SA должна существовать.
 * У нее: srcSysId =1, tryCnt = MaxTryCount, PartitionId = 0, statusHub = 0, statusSat = 0, cdcOperation = null,  остальные значения любые
 * Действия:
 * 1. Вставить в SA запись с tryCnt = MaxTryCount
 * 2. Запустить пакет загрузки хаба (при выполненых предусловиях шаг можно пропустить)
 * 3. Запустить пакет загрузки sat'a
 * 4. Запустить тест
 * 5. После анализа результатов теста зачистить тестовые данные
 */
public class TryCntMoreMax {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();
    private String tableInSA;
    private String tableHub;


    @Test
    public void tryCntMoreMax() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String dwhSQL = SQL.getSelectHub(tableHub);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);
        Integer satStatus = dh.getSatStatusFromSA(saSQL);

        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus != 0) {
            System.err.println("HubStatus have not valid values! HubStatus [" + hubStatus + "]");
        } else {
            System.out.println("HubStatus have expected values! HubStatus [" + hubStatus + "]");
        }

        if (satStatus != 0) {
            System.err.println("SatStatus have not valid values! it was update, it's fail! SatStatus [" + satStatus + "]");
        } else {
            System.out.println("SatStatus was not update! It's expected. SatStatus [" + satStatus + "]");
        }

        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        System.out.println("Check this tryCnt. It not can be update. TryCtn [" + tryCtn + "]");
        System.err.println("Check all log Tables! They are not have an error!");
    }

    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableInSA);
        dh.deleteHub(tableHub);
    }
    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
