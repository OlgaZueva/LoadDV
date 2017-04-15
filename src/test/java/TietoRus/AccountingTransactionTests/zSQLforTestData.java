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
        String[] keys = new String[9];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //BILAGSNR
        keys[2] = String.valueOf(999001); //LOBE_NR
        keys[3] = String.valueOf(98); //SELSKAB
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = null;// cdcOperation
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
        System.out.println(getInsertHubIntoDWH(properties.getProperty("accountingTransaction.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHubFromDWH(properties.getProperty("accountingTransaction.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHubFromDWH(properties.getProperty("accountingTransaction.hub.table")));
        System.out.println("---------------------------");
    }



    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (BILAGSNR, LOBE_NR, SELSKAB, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHubIntoDWH(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (itemNr, sequenceNr, accessCompanyId, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[6] + ")";
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

    public String getDeleteHubFromDWH(String tableName) {
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


    public String getSelectHubFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE itemNr = " + keys[1] + " and sequenceNr = " + keys[2]
                + " and accessCompanyId = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
