package TietoRus.PaymentsTests;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение готовых запросов для вставки, редактирования и удаления тестовых записей в БД
 */
public class zSQLforTestData {
    private Properties properties = new Properties();

    private String[] getValues(String tableName) {
        String[] keys = new String[20];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = "Q"; //F_TYPE nvarchar(1 CHAR)
        keys[3] = "FAKTURANR"; //FAKTURANR nvarchar(20 CHAR)
        keys[4] = "W"; //K_TYPE nvarchar(1 CHAR)
        keys[5] = String.valueOf(98); //KUNDE decimal(10,0)
        keys[6] = String.valueOf(97); //LOBE_NR int
        keys[7] = String.valueOf(1); //SrcSystemId
        keys[8] = String.valueOf(0); //TryCnt
        keys[9] = String.valueOf(0); //PartitionId
        keys[10] = String.valueOf(0);// statusHub
        keys[11] = String.valueOf(0);// statusSat
        keys[12] = String.valueOf(0);// statusLnk
        keys[13] = null;// cdcOperation
        keys[14] = String.valueOf(10); //BELOBDKK decimal(18,2)
        keys[15] = String.valueOf(11); //BELOBVAL decimal(18,2)
        keys[16] = String.valueOf(12); //BILAGSNR bigint
        keys[17] = "03-10-1999"; //DATO datetime
        keys[18] = "TEST text"; //TEKST nvarchar(30 CHAR)
        keys[19] = "VALUTA"; //VALUTA nvarchar(6 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("payments.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("payments.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("payments.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("payments.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("payments.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("payments.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, F_TYPE, FAKTURANR, K_TYPE, KUNDE,  LOBE_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation, " +
                "BELOBDKK, BELOBVAL, BILAGSNR, DATO, TEKST, VALUTA) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", " + keys[12] + ", " + keys[13] + ", " + keys[14] + ", " + keys[15] + ", " + keys[16] + ", '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19]  + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, invoiceType, invoiceNr, debitCredit, customerCode, sequenceNr, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', " + keys[5]+ ", " + keys[6] + ", " + keys[7]+ ", " + keys[9] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and F_TYPE =' " + keys[2]
                + "' and FAKTURANR = '" + keys[3]   + "' and K_TYPE = '" + keys[4]   + "' and KUNDE = " + keys[5]   + " and LOBE_NR = " + keys[6]  + " and SrcSystemId = " + keys[7];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and invoiceType = '" + keys[2]
                + "' and invoiceNr = '" + keys[3]  + "' and debitCredit = '" + keys[4]  + "' " + " and customerCode = " + keys[5]   + " and sequenceNr = " + keys[6] + " and SrcSystemId = " + keys[7];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteSat(String tableName, String fieldNameForHubId, int dwhHubId) {
        String delete = "DELETE FROM " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and F_TYPE = '" + keys[2]
                + "' and FAKTURANR = '" + keys[3]   + "' and K_TYPE = '" + keys[4]   + "' and KUNDE = " + keys[5]   + " and LOBE_NR = " + keys[6]  + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and fileLinerNr = '" + keys[2]
                + "' and invoiceNr = '" + keys[3]  + "' and debitCredit = '" + keys[4]  + "' " + " and customerCode = " + keys[5]   + " and sequenceNr = " + keys[6] + " and SrcSystemId = " + keys[7];
        //System.out.println(select);
        return select;
    }

    public String getSelectSat(String tableName, String fieldNameForHubId, int dwhHubId) {
        String select = "SELECT * from " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println(select);
        return select;
    }

    public String getSelectLnk(String tableName, String fieldNameForHubId, int dwhHubId) {
        String select = "SELECT * from " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println(select);
        return select;
    }
    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
