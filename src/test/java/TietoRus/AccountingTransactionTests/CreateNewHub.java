package TietoRus.AccountingTransactionTests;

import TietoRus.system.helpers.helpers.Asserts;
import TietoRus.system.helpers.helpers.GetDataHelper;
import TietoRus.system.helpers.models.AccountingTransactionHub;
import TietoRus.system.helpers.objects.AccountingTransactionHubObjects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Проверка правильного формирования HUB'ов, SAT'ов и LINK'ов после первоначальной загрузки данных
 */
public class CreateNewHub {
    /**
     * тест проверяет общий положительный сценарий по добавлению записи
     * Предусловия:
     * 1. Запись в SA должна существовать.
     * У нее: srcSysId =1, tryCnt < MaxTryCount, PartitionId = 0, statusHub = 0, cdcOperation = null,  остальные значения любые
     * 2. Запись в DWH не должна существовать
     * 3. Зафиксировать значение tryCtn в SA-таблице, присвоить это значение переменной tryCtnPrecondition
     * Действия:
     * 1. Добавить запись в SA таблицу
     * 2. Запустить пакет загрузки хаба
     * 3. Запустить тест
     */

    private GetDataHelper dh = new GetDataHelper();
    private AccountingTransactionHubObjects objects = new AccountingTransactionHubObjects();
    private Properties properties = new Properties();
    private Asserts asserts = new Asserts();
    private zSQLforTestData SQL = new zSQLforTestData();
    private String tableForTestDataInSA;
    private String tableForTestDataInDWH;

    //Подготовь данные в SQLforTestData -> получи запрос на добавление записи -> используй его для добавления тестовой записи!
    // Добавь запись в SA перед выполнением теста!
    @Test
    public void HubCreatedValidFlow() throws SQLException, IOException {
        getPropertiesFile();
        tableForTestDataInSA = properties.getProperty("accountingTransaction.UNITY.table");
        tableForTestDataInDWH = properties.getProperty("accountingTransaction.hub.table");
        int tryCtnPrecondition = 0;
        String saSQL = SQL.getSelectFromSA(tableForTestDataInSA);
        String dwhSQL = SQL.getSelectHubFromDWH(tableForTestDataInDWH);

        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

        if (tryCtn == null) {
            System.err.println("TryCtn is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus == null) {
            System.err.println("HubStatus is null! Maybe record not found or more then one record in SA with identical keys. ");
        } else if (hubStatus != 0 || (tryCtn <= Integer.parseInt(properties.getProperty("system.MaxTryCount")))) {
            if (dh.getCountRowOfHub(dwhSQL) == 1) {
                AccountingTransactionHub hubfromSA = objects.getHubFromSA(saSQL);
                AccountingTransactionHub hubfromDWH = objects.getHubFromDWH(dwhSQL);
                asserts.assertAccountingTransactionHubs(hubfromSA, hubfromDWH);

                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                if (tryCtnAfter == tryCtnPrecondition) {
                    System.out.println("TryCount not update. It's valid. TryCtn [" + tryCtnAfter + "]");
                } else {
                    System.err.println("TryCtn was updated,  but it's wrong! TryCtn [" + tryCtnAfter + "]");
                }

                Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                if (hubStatusAfter == 1) {
                    System.out.println("HubStatus set valid values! HubStatus [" + hubStatusAfter + "]");
                } else {
                    System.err.println("HubStatus have not valid values! HubStatus [" + hubStatusAfter + "]");
                }
            } else {
                System.err.println("Record in DWH not found or they are more one!");
            }
        } else {
            AccountingTransactionHub hubfromDWH = objects.getHubFromDWH(dwhSQL);
            if (hubfromDWH != null) {
                System.err.println("Hub was created, but hubStatus or tryCtn out of valid values!");
                Integer tryCtnAfter = dh.getTryCtnFromSA(saSQL);
                Integer hubStatusAfter = dh.getHubStatusFromSA(saSQL);
                if (tryCtnAfter.equals(tryCtn) || hubStatusAfter.equals(hubStatus)) {
                    System.err.println("Package change tryCtn or nubStatus in SA! It's wrong!");
                }
            } else {
                System.out.println("HubStatus in SA =0. Do nothing. Check package log");
            }
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
