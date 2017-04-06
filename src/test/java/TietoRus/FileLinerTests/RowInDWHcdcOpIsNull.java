package TietoRus.FileLinerTests;

import TietoRus.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Тест проверяет поведение системы в случае, когда запись в SA имеет statusHub = 0 и существует запись в DWH
 */

/**
 * Тест проверяет поведение системы в случае, когда в SA statusHub = 0 и при этом в DWH существует запись
 * В результате должны установить в SA имеет statusHub = 1 и ничего не делать с записью в DWH
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: PartitionId = 0, tryCnt < MaxTryCount, srcSysId =1, statusHub = 0  остальные значения любые
 * 2. В DWH существует запись с этими же клчами
 * Действия:
 * 1. Вставить в SA запись с statusHub = 1
 * 2. Вставить в DWH запись с теми же ключами
 * 3. Запустить пакет загрузки хаба
 * 4. Запустить тест
 * 5. После анализа результатов теста зачистить тестовые данные
 */
public class RowInDWHcdcOpIsNull {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableForTestDataInDWH = "hub.hubFileLiner";
    private String tableForTestData = "stg.UNITY_Sag";
    private String viewForDWH = "stg.v_Sag";


    @Test
    public void rowInDWHcdcOpIsNull() throws SQLException, IOException {

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