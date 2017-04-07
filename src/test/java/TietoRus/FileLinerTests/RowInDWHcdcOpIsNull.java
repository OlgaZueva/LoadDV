package TietoRus.FileLinerTests;

import TietoRus.helpers.GetDataHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Тест проверяет поведение системы в случае, когда запись в SA имеет statusHub = 0 и при этом существует запись в DWH
 */

/**
 * Тест проверяет поведение системы в случае, когда в SA statusHub = 0 и при этом в DWH существует запись
 * В результате должны установить в SA statusHub = 1 и ничего не делать с записью в DWH
 * Предусловия:
 * 1. Запись в SA должна существовать.
 * У нее: srcSysId =1, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
 * 2. В DWH существует запись с этими же ключами
 * Действия:
 * 1. Вставить в SA запись с statusHub = 0 и  cdcOperation = null
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

        if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else {
            if (hubStatus == 1) {
                System.out.println("HubStatus set valid values! It's expected. HubStatus [" + hubStatus + "]");
            } else {
                System.err.println("HubStatus have not valid values! It'fail! HubStatus [" + hubStatus + "]");
            }

            Integer tryCtn = dh.getTryCtnFromSA(saSQL);
            System.out.println("Check this tryCnt. It not can be update. TryCtn [" + tryCtn + "]");

            int contRowInDWH = dh.getCountRowOfHub(dwhSQL);
            if (contRowInDWH == 1) {
                System.out.println("Record in DWH is present! It's expected. Check package log! See row details in table for true.");
            } else if  (contRowInDWH == 0){
                System.err.println("Record in DWH doesn't found! It'fail!");
            } else if (contRowInDWH > 1) {
                System.err.println("In DWH more then one record for these keys It's fail!");
            }
        }
    }


    @AfterMethod
    public void deleteTestData() throws SQLException {
        dh.deleteTestRowFromSA(tableForTestData);
        dh.deleteTestRowFromDWH(tableForTestDataInDWH);
    }
}