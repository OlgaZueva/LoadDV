package TietoRus.CustomersTests;

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
        keys[1] = String.valueOf(99); //NR
        keys[2] = String.valueOf(999001); //REF_NR
        keys[3] = String.valueOf(9); //REF_TYPE
        keys[4] = String.valueOf(98); //SELSKAB
        keys[5] = String.valueOf(1); //SrcSystemId
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = null;// cdcOperation
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("fileLiner.UNITY.table")));
        System.out.println("------");
        /*
        System.out.println("UPDATE " + keys[0] + " SET SrcSystemId = " + keys[4] + ", " + "TryCnt = " + keys[5] + ", "
                + "PartitionId = " + keys[6] + ", " + "statusHub = " + keys[7] + ", " + "cdcOperation = " + keys[8]  + " WHERE SELSKAB = " + keys[1] + " and SAGSNR = " + keys[2]
                + " and AFDELING = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4]);
        System.out.println("------");
        */
        System.out.println(getDeleteFromSA(properties.getProperty("fileLiner.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("fileLiner.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getDWHSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoDWH(properties.getProperty("fileLiner.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteFromDWH(properties.getProperty("fileLiner.hub.table")));
        System.out.println("------");
        System.out.println(getSelectFromDWH(properties.getProperty("fileLiner.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, SAGSNR, AFDELING, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertIntoDWH(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, fileLinerNr, serviceCode, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[6] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and SAGSNR = " + keys[2]
                + " and AFDELING = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and fileLinerNr = " + keys[2]
                + " and serviceCode = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and SAGSNR = " + keys[2]
                + " and AFDELING = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and fileLinerNr = " + keys[2]
                + " and serviceCode = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
