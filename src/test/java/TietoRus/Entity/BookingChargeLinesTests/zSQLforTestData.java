package TietoRus.Entity.BookingChargeLinesTests;

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
        String[] keys = new String[22];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = String.valueOf(999001); //BOOK_NR smallint
        keys[3] = String.valueOf(98); //FRG_BT_NR smallint
        keys[4] = String.valueOf(97); //LINIE_NR bigint
        keys[5] = String.valueOf(1); //SrcSystemId
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = String.valueOf(0);// statusSat
        keys[10] = String.valueOf(0);// statusLnk
        keys[11] = null;// cdcOperation
        keys[12] = String.valueOf(10.99); //ANTAL decimal(11,3)
        keys[13] = "Q"; //BET_KODE nvarchar(1 CHAR)
        keys[14] = "CONT_NR"; //CONT_NR nvarchar(15 CHAR)
        keys[15] = "ENHE"; //ENHED nvarchar(4 CHAR)
        keys[16] = "GRUPPE"; //GRUPPE nvarchar(6 CHAR)
        keys[17] = String.valueOf(11.99); //OMR_KURS decimal(18,6)
        keys[18] = "VALUTA"; //OMR_VALUTA nvarchar(6 CHAR)
        keys[19] = "TEKST"; //TEKST nvarchar(100 CHAR)
        keys[20] = String.valueOf(12.99); //TOTAL_BEL decimal(12,2)
        keys[21] = String.valueOf(13);//YDE_NR smallint
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("bookingChargeLines.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("bookingChargeLines.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("bookingChargeLines.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("bookingChargeLines.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("bookingChargeLines.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("bookingChargeLines.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, BOOK_NR, FRG_BT_NR, LINIE_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                "ANTAL, BET_KODE, CONT_NR, ENHED, GRUPPE, OMR_KURS, OMR_VALUTA, TEKST, TOTAL_BEL, YDE_NR) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", " + keys[12] + ", '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "', " + keys[17]
                + ", '" + keys[18] + "', '" + keys[19] + "', " + keys[20] + ", " + keys[21] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, bookingNumber, positionNr, lineNr, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5]+ ", " + keys[7] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and BOOK_NR = " + keys[2]
                + " and FRG_BT_NR = '" + keys[3]  + " and LINIE_NR = '" + keys[4]  + "' and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and positionNr = " + keys[3]  + " and lineNr = " + keys[3]  + " and SrcSystemId = " + keys[4];
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
                + " and FRG_BT_NR = '" + keys[3]  + " and LINIE_NR = '" + keys[4]  + "' and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and positionNr = " + keys[3]  + " and lineNr = " + keys[3]  + " and SrcSystemId = " + keys[4];
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
