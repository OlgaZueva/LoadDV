package TietoRus.FileLinerTests;

import TietoRus.system.helpers.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * По идее ситуация невозсожная -  в разных источниках не может быть одинаковых записей
 * Тест проверяет поведение системы в случае, если в SA и DWH существуют записи с одинаковыми бизнес-ключами, но разными systemId
 * Обрабатывать должны только записи соотвествующего srcSysId = 1
 * Предусловия:
 * 1. Запись_1 в SA должна существовать.
 * У нее: srcSysId = 1, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
 * 2. Запись_2 в SA должна существовать.
 * У нее: srcSysId = 2, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
 * 3. Соотвествущих записей в DWH не должно существовать
 * Действия:
 * 1. Вставить в SA запись_1 и запись_2 предусловия
 * 2. Запустить пакет загрузки хаба
 * 3. После анализа результатов теста зачистить тестовые данные
 */
public class SrcSysIdMore1WithKeys {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();
    private String tableForTestDataInSA;
    private String tableForTestDataInDWH;

    @Test
    public void srcSysIdNot1Test() throws SQLException, IOException {
        getPropertiesFile();
        tableForTestDataInSA = properties.getProperty("fileLiner.UNITY.table");
        tableForTestDataInDWH = properties.getProperty("fileLiner.hub.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String dwhSQL = SQL.getSelectHub(tableForTestDataInDWH);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        int contRowInDWH = dh.getCountRowOfHub(dwhSQL);
        if (contRowInDWH == 1) {
            System.out.println("Record in DWH is present! It's expected. Check package log! See row details in table for true.");
        } else if (contRowInDWH == 0) {
            System.err.println("Record in DWH doesn't found! It'fail!");
        } else if (contRowInDWH > 1) {
            System.err.println("In DWH more then one record for these keys It's fail!");
        }
    }

    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableForTestDataInSA);
        dh.deleteHub(tableForTestDataInDWH);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}

