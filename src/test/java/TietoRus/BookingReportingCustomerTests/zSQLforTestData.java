package TietoRus.BookingReportingCustomerTests;

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
        String[] keys = new String[11];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //BL_NUMBER smallint
        keys[2] = String.valueOf(4); //SrcSystemId
        //Внимание! Хаб формируется из EXCEL-файла (CleanUp.xls). srcSystemId = 4? не менять!
        keys[3] = String.valueOf(0); //TryCnt
        keys[4] = String.valueOf(0); //PartitionId
        keys[5] = String.valueOf(0);// statusHub
        keys[6] = String.valueOf(0);// statusSat
        keys[7] = String.valueOf(0);// statusLnk
        keys[8] = "CUSTOMER"; //Customer nvarchar(100)
        keys[9] = "QWE"; //NvoBcoFrw nvarchar(3)
        keys[10] = "02-10-1999"; //VALID_FROM datetime
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("bookingReportingCustomer.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("bookingReportingCustomer.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("bookingReportingCustomer.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("bookingReportingCustomer.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("bookingReportingCustomer.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("bookingReportingCustomer.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (BL_NUMBER, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation,"+
                "Customer, NvoBcoFrw, VALID_FROM) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", '" + keys[8] + "', '" + keys[9]
                + "', '" + keys[10] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (blNr,  SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[4] +")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE BL_NUMBER = " + keys[1] + " and SrcSystemId = " + keys[2];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE blNr = " + keys[1]  + " and SrcSystemId = " + keys[2];
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
        String select = "SELECT * from " + keys[0] + " WHERE BL_NUMBER = " + keys[1] + " and SrcSystemId = " + keys[2];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE blNr = " + keys[1]  + " and SrcSystemId = " + keys[2];
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
