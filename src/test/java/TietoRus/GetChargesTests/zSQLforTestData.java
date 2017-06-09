package TietoRus.GetChargesTests;

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
        keys[2] = String.valueOf(999001); //BOOK_NR bigint
        keys[3] = String.valueOf(98); //VAREPOST_NR int
        keys[4] = "VAL"; //OMR_VALUTA nvarchar(6 CHAR)
        keys[5] = String.valueOf(1); //SrcSystemId
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = String.valueOf(0);// statusSat
        keys[10] = String.valueOf(0);// statusLnk
        keys[11] = null;// cdcOperation
        keys[12] = String.valueOf(10.2); //A_PRIS decimal(12,2)
        keys[13] = String.valueOf(11.2); //ANTAL decimal(11,3)
        keys[14] = "DISTINCT_BET_KODE"; //DISTINCT_BET_KODE nvarchar(2000 CHAR)
        keys[15] = "ENHE"; //ENHED nvarchar(4 CHAR)
        keys[16] = String.valueOf(12); //FRG_BT_NR smallint
        keys[17] = "GRUPPE"; //GRUPPE nvarchar(6 CHAR)
        keys[18] = String.valueOf(13.2); //OMR_KURS decimal(18,6)
        keys[19] = String.valueOf(14.2); //TOTAL_BEL decimal(12,2)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("getCharges.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("getCharges.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("getCharges.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("getCharges.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("getCharges.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("getCharges.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, BOOK_NR, VAREPOST_NR, OMR_VALUTA, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation, CALL_ID, EXP_AFG_DATO, " +
                "A_PRIS, ANTAL, DISTINCT_BET_KODE, ENHED, FRG_BT_NR, GRUPPE, OMR_KURS, TOTAL_BEL) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", '" + keys[4] + "', " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", " + keys[12] + ", " + keys[13] + ", '" + keys[14] + "', '" + keys[15] + "', " + keys[16] + ", '" + keys[17]
                + "', " + keys[18] + ", " + keys[19]  + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, bookingNumber, cargoLineNr , SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", '" + keys[5] + "', " + keys[7] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and BOOK_NR = " + keys[2]
                + " and VAREPOST_NR = " + keys[3] + " and OMR_VALUTA = '" + keys[4]  + "' and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and cargoLineNr = " + keys[3]  + " and SrcSystemId = " + keys[4];
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
                + " and VAREPOST_NR = " + keys[3] + " and OMR_VALUTA = '" + keys[4]  + "' and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and cargoLineNr = '" + keys[3]  + "' and SrcSystemId = " + keys[4];
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
