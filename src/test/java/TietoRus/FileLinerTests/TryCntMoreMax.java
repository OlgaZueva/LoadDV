package TietoRus.FileLinerTests;

import TietoRus.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Тест проверяет поведение системы в случае, когда число попыток загрузки >= MaxTryCount
 */
/**
 * Тест проверяет поведение системы в случае, когда число попыток загрузки >= MaxTryCount
 * Обрабатывать должны только записи, у которых tryCnt <= MaxTryCount
 * Значение MaxTryCount в системе задается у пакета загрузки. Для тестов дублируется в system.properties
 * Записи с tryCnt >= MaxTryCount во вьюху сущности попасть не должны
 *
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: PartitionId = 0, tryCnt = MaxTryCount, srcSysId =1,  остальные значения любые
 * Действия:
 * тест все пречисленные действия выполняет сам! Перед запуском установить правильные тестовые данные!
 * 1. Вставить в SA запись с tryCnt = MaxTryCount
 * 2. Запустить тест
 * 3. После анализа результатов теста зачистить тестовые данные
 */
public class TryCntMoreMax {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableForTestData = "stg.UNITY_Sag";
    private String viewForDWH = "stg.v_Sag";

    @BeforeMethod
    public void insertTestData() throws SQLException {
        dh.insertTestRowInSA(tableForTestData);
    }
    @Test
    public void partitionIdNot0() throws SQLException, IOException {
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        int countRowInSA = dh.getCountRowInSA(saSQL);
        if (countRowInSA > 0){
            System.err.println("Record(s) is present in SA view, but it's wrong!");
        } else {
            System.out.println("Record(s) is not present in SA view. It's valid flow!");
        }
    }
    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableForTestData);
    }
}
