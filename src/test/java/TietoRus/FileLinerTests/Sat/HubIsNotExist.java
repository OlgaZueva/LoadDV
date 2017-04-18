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

/*
 * Тест проверяет поведение системы при загрузке sat'ов в случае, когда записи, с ключами записи в SA-таблице в hub'ах нет
 */

/**
 * Тест проверяет поведение системы при загрузке sat'ов в случае, когда в SA statusHub = 1 и hab загружен не будет, что порождает отсутсвие записи в hub при загрузке sat'a
 * В данной ситуации sat'a создано быть не должно, а в таблице [logDataLoadError] зафиксирована ошибка.
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: srcSysId =ID_SA-источника, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 1, statusSat = 0,  остальные значения любые
 * 2. В в таблице hub'ов записи с этими же ключами быть не должно
 * Действия:
 * 1. Вставить в SA запись с statusHub = 1
 * 2. Запустить пакет загрузки хаба (при выполненых предусловиях шаг можно пропустить)
 * 3. Запустить только пакет загрузки sat'a
 * 4. Запустить тест
 * 5. После анализа результатов теста зачистить тестовые данные
 */
public class HubIsNotExist {
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
        Integer satStatus = dh.getSatStatusFromSA(saSQL);

        if (dh.getCountRowOfHub(hubSQL) >= 1) {
            System.err.println("In DWH present record! It's not valid!");
        } else {
            System.out.println("Record in DWH doesn't created. It's expected result!");
            if (hubStatus != 0) {
                System.err.println("HubStatus have not valid values! It'fail! HubStatus [" + hubStatus + "]");
            } else {
                System.out.println("HubStatus does'not uptaded! It's expected. HubStatus [" + hubStatus + "]");
            }
            if (satStatus != 0) {
                System.err.println("SatStatus have not valid values! it was update, it's fail! SatStatus [" + satStatus + "]");
            } else {
                System.out.println("SatStatus was not update! It's expected. SatStatus [" + satStatus + "]");
            }
            System.err.println("Check TryCtn! It must been not update! TryCtn [" + dh.getTryCtnFromSA(saSQL) + "]");
            System.err.println("Check all log Tables! In [logDataLoadError] must be fixed error!");
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
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}