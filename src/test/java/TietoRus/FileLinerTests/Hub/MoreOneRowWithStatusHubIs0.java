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

/*
 * Тест проверяет поведение системы в случае, когда в SA существует более одной записи с одними и теми же ключами и со statusHub = 0
 */

/**
 * Тест проверяет поведение системы в случае, когда в SA существует более одной записи с одними и теми же ключами и со statusHub = 0
 * Для такой ситуации в hub'ы должна попасть только одна запись
 * Предусловия:
 * 1. В SA должны существовать 2 записи
 * Пусть одна с:  srcSysId =ID_SA-источника, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = 'I ',  остальные значения любые
 * Вторая с:  srcSysId =ID_SA-источника, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = 'UN',  остальные значения любые
 * 2. В hub'ах записи с этими же ключами быть не должно
 * Действия:
 * 1. Вставить в SA записи предусловия
 * 2. Запустить только пакет загрузки хаба
 * 3. Запустить тест
 * 4. После анализа результатов теста зачистить тестовые данные
 */
public class MoreOneRowWithStatusHubIs0 {
    private GetDataHelper dh = new GetDataHelper();
    private zSQLforTestData SQL = new zSQLforTestData();
    private Properties properties = new Properties();
    private String tableInSA;
    private String tableHub;


    @Test
    public void MoreOneRowInSAWihtStatusHubIs0() throws SQLException, IOException {
        getPropertiesFile();
        tableInSA = properties.getProperty("fileLiner.UNITY.table");
        tableHub = properties.getProperty("fileLiner.hub.table");
        String viewForDWH = properties.getProperty("fileLiner.hub.view");
        String saSQL = SQL.getSelectFromSA(viewForDWH);
        String hubSQL = SQL.getSelectHub(tableHub);
        int countRowInHub = dh.getCountRowOfHub(hubSQL);


        if (countRowInHub > 1) {
            System.err.println("In DWH more one row of hub! It's not valid!");
        } else {
            if (countRowInHub == 0) {
                System.err.println("Record in hub doesn't created. It's not valid!");
            } else {
                System.out.println("Record in DWH was created! Must be one row! Row count: [" + countRowInHub + "]");
            }
        }
        System.err.println("Check HubStatus in all test rows (from precondition) in SA! It must been  = 1!");
        System.err.println("Check TryCtn! It must been not update! TryCtn [" + dh.getTryCtnFromSA(saSQL) + "]");
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
