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
 *
 * Предусловия:
 * 1. Запись в SA должна существовать.
 * У нее: srcSysId =1, tryCnt = MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
 * Действия:
 * 1. Вставить в SA запись с tryCnt = MaxTryCount
 * 2. Запустить пакет загрузки хаба
 * 3. Запустить тест
 * 4. После анализа результатов теста зачистить тестовые данные
 */
public class TryCntMoreMax {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableForTestDataInDWH = "hub.hubFileLiner";
    private String tableForTestData = "stg.UNITY_Sag";
    private String viewForDWH = "stg.v_Sag";


    @Test
    public void tryCntMoreMax() throws SQLException, IOException {

        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String dwhSQL = SQL.getSelectFromDWH(tableForTestDataInDWH);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus != 0) {
            System.err.println("HubStatus have not valid values! HubStatus [" + hubStatus + "]");
        } else {
            System.out.println("HubStatus have expected values! HubStatus [" + hubStatus + "]");
        }
        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        System.out.println("Check this tryCnt. It not can be update. TryCtn [" + tryCtn + "]");
        if (dh.getCountRowOfHub(dwhSQL) == 1) {
            System.err.println("In DWH present record! It's not valid!");
        } else {
            System.out.println("Record in DWH doesn't created. It's expected result! Check package log!");
        }
    }

    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableForTestData);
        dh.deleteTestRowFromDWH(tableForTestDataInDWH);
    }
}