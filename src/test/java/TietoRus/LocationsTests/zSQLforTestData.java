package TietoRus.LocationsTests;

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
        String[] keys = new String[17];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = "LO"; //ART nvarchar(2 CHAR)
        // ВНИМАНИЕ! значение ART должно быть  = LO - это условие выбора записей для Locations
        keys[3] = "LNK_KodE"; //KODE nvarchar(16 CHAR)
        keys[4] = String.valueOf(1); //LOBE_NR int
        // ВНИМАНИЕ! значение LOBE_NR должно быть  = 1 - это условие выбора записей для Locations
        keys[5] = String.valueOf(1); //SrcSystemId
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = String.valueOf(0);// statusSat
        keys[10] = String.valueOf(0);// statusLnk
        keys[11] = null;// cdcOperation
        keys[12] = "32Characteeeeeer"; //CHAR_ATRIBUTE1 nvarchar(32 CHAR)
        keys[13] = "64Characteeeeeer"; //CHAR_ATRIBUTE2 nvarchar(64 CHAR)
        keys[14] = "64Characteeeeeer"; //CHAR_ATRIBUTE9 nvarchar(100 CHAR)
        keys[15] = "16Characteeeeeer"; //MONT_ENHED nvarchar(6 CHAR)
        keys[16] = "50CharacteeeeeerLooooooooooooooooooooooooooooooong"; //TEKST_1 nvarchar(50 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("locations.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("locations.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("locations.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("locations.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("locations.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("locations.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, ART, KODE, LOBE_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation, " +
                "CHAR_ATRIBUTE1, CHAR_ATRIBUTE2, CHAR_ATRIBUTE9, MONT_ENHED, TEKST_1) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", '" + keys[12] + "', '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, dictionaryType, locationCode, sequenceNumber, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[7] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and ART = '" + keys[2]
                + "' and KODE = '" + keys[3] + "' and LOBE_NR = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and dictionaryType = '" + keys[2]
                + "' and locationCode = '" + keys[3] + "' and sequenceNumber = " + keys[4] + " and SrcSystemId = " + keys[5];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and ART = '" + keys[2]
                + "' and KODE = '" + keys[3] + "' and LOBE_NR = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and dictionaryType = '" + keys[2]
                + "' and locationCode = '" + keys[3] + "' and sequenceNumber = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }

    public String getSelectSat(String tableName, String fieldNameForHubId, int dwhHubId) {
        String select = "SELECT * from " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
