package TietoRus.Entity.BookingChargesTests;

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
        String[] keys = new String[15];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = String.valueOf(999001); //BOOK_NR bigint
        keys[3] = String.valueOf(98); //FRG_BT_NR smallint
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = String.valueOf(0);// statusSat
        keys[9] = String.valueOf(0);// statusLnk
        keys[10] = null;// cdcOperation
        keys[11] = "Q"; //DK_KODE nvarchar(1 CHAR)
        keys[12] = String.valueOf(12345678); //KUNDE decimal(10,0)
        keys[13] = "W"; //REL_TYPE nvarchar(1 CHAR)
        keys[14] = "E"; //STAT nvarchar(1 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("bookingCharges.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("bookingCharges.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("bookingCharges.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("bookingCharges.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("bookingCharges.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("bookingCharges.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, BOOK_NR, FRG_BT_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                " DK_KODE, KUNDE, " + "REL_TYPE, STAT) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", '" + keys[11] + "', " + keys[12] + ", '" + keys[13] + "', '" + keys[14] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, bookingNr, positionNr, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[6] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and BOOK_NR = " + keys[2]
                + " and FRG_BT_NR = '" + keys[3]  + "' and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNr = " + keys[2]
                + " and positionNr = " + keys[3]  + " and SrcSystemId = " + keys[4];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and BOOK_NR = " + keys[2]
                + " and FRG_BT_NR = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNr = " + keys[2]
                + " and positionNr = " + keys[3] + " and SrcSystemId = " + keys[4];
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
