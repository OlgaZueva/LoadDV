package TietoRus.FileLinerTests.Hub;

import TietoRus.FileLinerTests.zSQLforTestData;
import TietoRus.system.helpers.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Тест проверяет поведение системы в случае, если у записи в SA параметр PartitionId !=0.
 * Обрабатывать должны только записи, у которых PartitionId = 0
 * Записи с PartitionId = 0 во вьюху сущности попасть не должны
 * <p>
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: PartitionId != 0, остальные значения любые
 * Действия:
 * ТЕСТ ВСЕ ПЕРЕЧИСЛЕННЫЕ ДЕЙСТВИЯ ВЫПОНЯЕТ САМ! Перед запуском установить правильные тестовые данные!
 * 1. Вставить в SA запись с PartitionId != 0
 * 2. Запустить тест
 * 3. После анализа результатов теста зачистить тестовые данные
 */
public class PartitionIdNot0 {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();

    private String tableInSA;


    @BeforeMethod
    public void insertTestData() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        dh.insertTestRowInSA(tableInSA);
    }

    @Test
    public void partitionIdNot0() throws SQLException, IOException {
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        int countRowInSA = dh.getCountRowInSA(saSQL);
        if (countRowInSA > 0) {
            System.err.println("Record is present in SA view, but it's wrong!");
        } else {
            System.out.println("Record is not present in SA view. It's valid flow!");
        }
    }

    @AfterMethod
    public void deleteTestData() throws SQLException {
        String deleteFromSA = SQL.getDeleteFromSA(tableInSA);
        dh.deleteTestRowFromSA(deleteFromSA);

    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
