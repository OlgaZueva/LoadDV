package TietoRus.OceanVesselServiceTests;

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
        String[] keys = new String[13];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = "MSC"; // AGENT nvarchar(4 CHAR)
        // ВНИМАНИЕ! значение AGENT должно быть  = MSC - это условие выбора записей для OceanVesselService
        keys[3] = "SRDR"; //FELT nvarchar(4 CHAR)
        // ВНИМАНИЕ! значение FELT должно быть  = SRDR - это условие выбора записей для OceanVesselService
        keys[4] = "FRA_TEST_VALUE"; //FRA	nvarchar(100 CHAR)
        keys[5] = "TILvalue"; //TIL	nvarchar(100 CHAR)
        keys[6] = String.valueOf(1); //SrcSystemId
        keys[7] = String.valueOf(0); //TryCnt
        keys[8] = String.valueOf(0); //PartitionId
        keys[9] = String.valueOf(0);// statusHub
        keys[10] = String.valueOf(0);// statusSat
        keys[11] = String.valueOf(0);// statusLnk
        keys[12] = null;// cdcOperation
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("oceanVesselService.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("oceanVesselService.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("oceanVesselService.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("oceanVesselService.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("oceanVesselService.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("oceanVesselService.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, AGENT, FELT, FRA, TIL, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', '" + keys[5] + "', " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", " + keys[12]  + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, carrierCode, serviceCode, segmentCode,  statusCode, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', '" + keys[5] + "', " + keys[6]+ ", " + keys[8] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and AGENT = '" + keys[2]
                + "' and FELT = '" + keys[3] + "' and FRA = '" + keys[4] + "' and TIL = '" + keys[5] + "' and SrcSystemId = " + keys[6];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and carrierCode = '" + keys[2]
                + "' and serviceCode = '" + keys[3]  + "' and segmentCode = '" + keys[4] + "' and statusCode = '" + keys[5]  + "' and SrcSystemId = " + keys[6];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and AGENT = '" + keys[2]
                + "' and FELT = '" + keys[3] + "' and FRA = '" + keys[4] + "' and TIL = '" + keys[5] + "' and SrcSystemId = " + keys[6];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and carrierCode = '" + keys[2]
                + "' and serviceCode = '" + keys[3]  + "' and segmentCode = '" + keys[4] + "' and statusCode = '" + keys[5]  + "' and SrcSystemId = " + keys[6];
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
