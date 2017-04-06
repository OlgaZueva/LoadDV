package TietoRus.FileLinerTests;

import TietoRus.helpers.Asserts;
import TietoRus.helpers.GetDataHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Тест проверяет поведение системы в случае, если у записи в SA параметр srcSysId !=1.
 * Обрабатывать должны только записи, у которых srcSysId = 1
 *
 * Предусловия:
 * 1. Запись в SA должна существовать. У нее: PartitionId = 0, srcSysId !=1, TryCnt < maxTryCount и валидные значения бизнес-ключей
 * 2. Запись в DWH не должна существовать
 * 3. Зафиксировать значение tryCtn в SA-таблице, присвоить это значение переменной tryCtnPrecondition
 * Действия:
 * 1. Вставить в SA запись с srcSysId !=1
 * Insert into BIFROST.stg.UNITY_Sag (SELSKAB, SAGSNR, AFDELING, SrcSystemId, TryCnt,  PartitionId) Values (99, 999001, '98', 1, 0, 0)
 * 2. Запустить пакет загрузки хаба
 * 3. После анализа результатов теста зачистить тестовые данные
 */
public class SrcSysIdNot1 {
    private GetDataHelper dh = new GetDataHelper();
    private Properties properties = new Properties();
    private Asserts asserts = new Asserts();
    private zSQLforTestData SQL =  new zSQLforTestData();

    @Test
    public void srcSysIdNot1Test() throws SQLException, IOException {
        getPropertiesFile();
        int tryCtnPrecondition = 0;
        String saSQL = SQL.getSelectFromSA("stg.v_Sag");
        String dwhSQL = "select * from hub.hubFileLiner where accessCompanyId = 99 and fileLinerNr = 999777 and serviceCode = '98' and SrcSystemId =1";
        String satHubStatusTable = "SELECT *  FROM sat.satFileLinerStatus where dwhIdHubFileLiner = ";

        Integer tryCtn = dh.getTryCtnFromSA(saSQL);
        Integer hubStatus = dh.getHubStatusFromSA(saSQL);

    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
    }

