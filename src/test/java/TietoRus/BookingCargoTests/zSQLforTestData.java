package TietoRus.BookingCargoTests;

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
        String[] keys = new String[42];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = String.valueOf(999001); //BOOK_NR bigint
        keys[3] = String.valueOf(98); //VAREPOST_NR int
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = String.valueOf(0);// statusSat
        keys[9] = String.valueOf(0);// statusLnk
        keys[10] = null;// cdcOperation
        keys[11] = "AgencyCC"; //AGENCY_CCC nvarchar(8 CHAR)
        keys[12] = "Y"; //ATMOSPHERE_CONTROL nvarchar(1 CHAR)
        keys[13] = "N"; //AUTOMATIC_TEMP_FLAG nvarchar(1 CHAR)
        keys[14] = String.valueOf(1.007); //AUTOMATIC_TEMP_VALUE decimal(3,1)
        keys[15] = String.valueOf(2.0); //BRUTTO decimal(10,3)
        keys[16] = "Q"; //BULB_MODE nvarchar(1 CHAR)
        keys[17] = "W"; //COLD_TREATMENT nvarchar(1 CHAR)
        keys[18] = String.valueOf(32767); //CONT_ANT smallint
        keys[19] = "15CharactersCnt"; //CONT_NR nvarchar(15 CHAR)
        keys[20] = "42CharactersCountLooooooooooooooooooooong1"; //CONT_TEKST nvarchar(42 CHAR)
        keys[21] = "06Char"; //CONT_TYPE nvarchar(6 CHAR)
        keys[22] = "R"; //DEHUMIDIFICATION_FLAG nvarchar(1 CHAR)
        keys[23] = String.valueOf(99); //DEHUMIDIFICATION_VALUE decimal(2,0)
        keys[24] = "06Cha1"; //IMCO_KL nvarchar(6 CHAR)
        keys[25] = "05Cha"; //IMS_TRANSPORT_CODE nvarchar(5 CHAR)
        keys[26] = "17CharactersCount"; //KOLLI_ENHED nvarchar(17 CHAR)
        keys[27] = "T"; //MANUAL_TEMP_FLAG nvarchar(1 CHAR)
        keys[28] = String.valueOf(9.0); //MANUAL_TEMP_VALUE decimal(3,1)
        keys[29] = "18CharactersCoount"; //MRK_NR nvarchar(18 CHAR)
        keys[30] = "U"; //OPERATING_REEFER_INDICATOR nvarchar(1 CHAR)
        keys[31] = "I"; //PART_LOAD_FLAG nvarchar(1 CHAR)
        keys[32] = "30CharactersCountLoooooooooong"; //PRECARRIAGE nvarchar(30 CHAR)
        keys[33] = "20CharactersCountLng"; //SEAL_NO nvarchar(20 CHAR)
        keys[34] = String.valueOf(2147483647); //TARA int
        keys[35] = "O"; //TEMP_ENH nvarchar(1 CHAR)
        keys[36] = String.valueOf(998); //TEMP1 decimal(3,1)
        keys[37] = String.valueOf(997); //TEMP2 decimal(3,1)
        keys[38] = "06Cha2"; //UN_NR nvarchar(6 CHAR)
        keys[39] = "42CharactersCountLooooooooooooooooooooong2"; //VAREART nvarchar(42 CHAR)
        keys[40] = String.valueOf(Double.valueOf(123)); //VOL decimal(11,3)
        keys[41] = "04Ch"; //VOL_ENHED nvarchar(4 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("bookingCargo.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("bookingCargo.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("bookingCargo.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("bookingCargo.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("bookingCargo.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("bookingCargo.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, BOOK_NR, VAREPOST_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                " AGENCY_CCC, ATMOSPHERE_CONTROL, AUTOMATIC_TEMP_FLAG, AUTOMATIC_TEMP_VALUE, BRUTTO, BULB_MODE, COLD_TREATMENT, CONT_ANT, CONT_NR, CONT_TEKST," +
                " CONT_TYPE, DEHUMIDIFICATION_FLAG, DEHUMIDIFICATION_VALUE, IMCO_KL, IMS_TRANSPORT_CODE, KOLLI_ENHED, MANUAL_TEMP_FLAG, MANUAL_TEMP_VALUE, MRK_NR," +
                " OPERATING_REEFER_INDICATOR, PART_LOAD_FLAG, PRECARRIAGE, SEAL_NO, TARA, TEMP_ENH, TEMP1,TEMP2, UN_NR, VAREART, VOL, VOL_ENHED) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", '" + keys[11] + "', '" + keys[12] + "', '" + keys[13] + "', " + keys[14] + ", " + keys[15] + ", '" + keys[16] + "', '" + keys[17]
                + "', " + keys[18] + ", '" + keys[19] + "', '" + keys[20] + "', '" + keys[21] + "', '" + keys[22] + "', " + keys[23] + ", '" + keys[24] + "', '" + keys[25]
                + "', '" + keys[26] + "', '" + keys[27] + "', " + keys[28] + ", '" + keys[29] + "', '" + keys[30] + "', '" + keys[31] + "', '" + keys[32] + "', '" + keys[33]
                + "', " + keys[34] + ", '" + keys[35] + "', " + keys[36] + ", " + keys[37] + ", '" + keys[38] + "', '" + keys[39] + "', " + keys[40] + ", '" + keys[41] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, bookingNumber, cargoLineNr , SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[6] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and BOOK_NR = " + keys[2]
                + " and VAREPOST_NR = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and cargoLineNr = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
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
                + " and VAREPOST_NR = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and cargoLineNr = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
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
