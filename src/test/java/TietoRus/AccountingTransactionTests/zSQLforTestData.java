package TietoRus.AccountingTransactionTests;

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
        String[] keys = new String[26];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(90); //BILAGSNR
        keys[2] = String.valueOf(999001); //LOBE_NR
        keys[3] = String.valueOf(99); //SELSKAB
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = String.valueOf(0);// statusSat
        keys[9] = String.valueOf(0);// statusLnk
        keys[10] = null;// cdcOperation
        keys[11] = "qwe";// AFDELING nvarchar(3 CHAR)
        keys[12] = "asd";// AKTIVITET nvarchar(3 CHAR)
        keys[13] = String.valueOf(1234567.09);// BELOBDKK decimal(18,2)
        keys[14] = String.valueOf(12345.01);// BELOBVAL decimal(18,2)
        keys[15] = String.valueOf(5);// DAGBOG int
        keys[16] = String.valueOf(4);// FP_ID int
        keys[17] = "T";// GRUPPE nvarchar(1 CHAR)
        keys[18] = "TEst";// INIT nvarchar(6 CHAR)
        keys[19] = String.valueOf(12345678);// KONTONR decimal(8,0)
        keys[20] = String.valueOf(3);// KONTOR smallint
        keys[21] = String.valueOf(2);// PERIODE int
        keys[22] = String.valueOf(98);// REF_NR bigint
        keys[23] = String.valueOf(0);// SAGSNR bigint
        keys[24] = "Test text in field TEKST";// TEKST nvarchar(240 CHAR)
        keys[25] = "Y";// TRANSTYPE nvarchar(1 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("accountingTransaction.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("accountingTransaction.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("accountingTransaction.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("accountingTransaction.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("accountingTransaction.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("accountingTransaction.hub.table")));
        System.out.println("---------------------------");
    }



    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (BILAGSNR, LOBE_NR, SELSKAB, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                "AFDELING, AKTIVITET, BELOBDKK, BELOBVAL, DAGBOG, FP_ID, GRUPPE, INIT, KONTONR, KONTOR, PERIODE, REF_NR, SAGSNR, TEKST, TRANSTYPE) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8]
                + ", " + keys[9] + ", " + keys[10] + ", '" + keys[11] + "', '" + keys[12] + "', " + keys[13] + ", " + keys[14] + ", " + keys[15] + ", " + keys[16]
                + ", '" + keys[17] + "', '" + keys[18] + "', " + keys[19] + ", " + keys[20] + ", " + keys[21] + ", " + keys[22] + ", " + keys[23]
                + ", '" + keys[24] + "', '" + keys[25]+  "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (itemNr, sequenceNr, accessCompanyId, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + "," + keys[3] + ", " + keys[4] + ", " + keys[6] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE BILAGSNR = " + keys[1] + " and LOBE_NR = " + keys[2]
                 + " and SELSKAB = " + keys[3] +  " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE itemNr = " + keys[1] + " and sequenceNr = " + keys[2]
               + " and accessCompanyId = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE BILAGSNR = " + keys[1] + " and LOBE_NR = " + keys[2]
               + " and SELSKAB = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE itemNr = " + keys[1] + " and sequenceNr = " + keys[2]
                + " and accessCompanyId = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }



    public String getSelectSat(String tableName, String fieldNameForHubId, int dwhHubId) {
        String select = "SELECT * from " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
