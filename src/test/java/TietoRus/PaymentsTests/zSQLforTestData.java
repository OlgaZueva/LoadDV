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
        String[] keys = new String[12];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(9); //F_TYPE
        keys[2] = String.valueOf(999001); //FAKTURANR
        keys[3] = String.valueOf(8); //K_TYPE
        keys[4] = String.valueOf(900); //KUNDE
        keys[5] = String.valueOf(999001); //LOBE_NR
        keys[6] = String.valueOf(99); //SELSKAB
        keys[7] = String.valueOf(1); //SrcSystemId
        keys[8] = String.valueOf(0); //TryCnt
        keys[9] = String.valueOf(0); //PartitionId
        keys[10] = String.valueOf(0);// statusHub
        keys[11] = null;// cdcOperation
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
    public void getDWHSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoDWH(properties.getProperty("payments.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteFromDWH(properties.getProperty("payments.hub.table")));
        System.out.println("------");
        System.out.println(getSelectFromDWH(properties.getProperty("payments.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (F_TYPE, FAKTURANR, K_TYPE,  KUNDE,  LOBE_NR,  SELSKAB, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] +  ", " + keys[9] + ", " + keys[10] + ", " + keys[11] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertIntoDWH(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (invoiceType, invoiceNr, debitCredit, customerCode, sequenceNr, accessCompanyId, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[9] +")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE F_TYPE = " + keys[1] + " and FAKTURANR = " + keys[2] + " and K_TYPE = " + keys[3] + " and KUNDE = " + keys[4]
                + " and LOBE_NR = " + keys[5] + " and SELSKAB = " + keys[6] + " and SrcSystemId = " + keys[7];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE invoiceType = " + keys[1] + " and invoiceNr = " + keys[2] + " and debitCredit = " + keys[3]
                + " and customerCode = " + keys[4] + " and sequenceNr = " + keys[5] + " and accessCompanyId = " + keys[6] + " and SrcSystemId = " + keys[7];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE F_TYPE = " + keys[1] + " and FAKTURANR = " + keys[2] + " and K_TYPE = " + keys[3] + " and KUNDE = " + keys[4]
                + " and LOBE_NR = " + keys[5] + " and SELSKAB = " + keys[6] + " and SrcSystemId = " + keys[7];
        //System.out.println(select);
        return select;
    }


    public String getSelectFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE invoiceType = " + keys[1] + " and invoiceNr = " + keys[2] + " and debitCredit = " + keys[3]
                + " and customerCode = " + keys[4] + " and sequenceNr = " + keys[5] + " and accessCompanyId = " + keys[6] + " and SrcSystemId = " + keys[7];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
