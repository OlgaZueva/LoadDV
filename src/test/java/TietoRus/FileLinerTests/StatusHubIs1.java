package TietoRus.FileLinerTests;

import TietoRus.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Тест проверяет поведение системы в случае, когда  записьв SA имеет statusHub = 1
 */

/**
 * Тест проверяет поведение системы в случае, когда в SA statusHub = 1
 * Обрабатывать должны только записи, у которых statusHub = 0
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: PartitionId = 0, tryCnt < MaxTryCount, srcSysId =1, statusHub = 1  остальные значения любые
 * Действия:
 * 1. Вставить в SA запись с statusHub = 1
 * 2. Запустить пакет загрузки хаба
 * 3. Запустить тест
 * 4. После анализа результатов теста зачистить тестовые данные
 */
public class StatusHubIs1 {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableForTestDataInDWH = "hub.hubFileLiner";
    private String tableForTestData = "stg.UNITY_Sag";
    private String viewForDWH = "stg.v_Sag";


    @Test
    public void statusHubIs1() throws SQLException, IOException {

        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String dwhSQL = SQL.getSelectFromDWH(tableForTestDataInDWH);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        dh.deleteTestRowFromDWH(tableForTestDataInDWH);
        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else {
            if (dh.getCountRowOfHub(dwhSQL) == 1) {
                System.err.println("In DWH present record! It's not valid!");
            } else {
                System.out.println("Record in DWH doesn't created. It's expected result! Check package log!");
            }
        }
        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        System.out.println("Check this tryCnt. It not can be update. TryCtn [" + tryCtn + "]");
    }


    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableForTestData);
        dh.deleteTestRowFromDWH(tableForTestDataInDWH);
    }
}