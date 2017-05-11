package TietoRus.LocationsPortsOverviewTests;

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
        keys[1] = "LNK_KodE"; //locationCode nvarchar(16)
        keys[2] = String.valueOf(4); //SrcSystemId
        // ВНИМАНИЕ! значение SrcSystemId должно быть  = 4 - это соотвествует файлу PORTS_overview.xls из котрого грузятся в SA данные
        keys[3] = String.valueOf(0); //TryCnt
        keys[4] = String.valueOf(0); //PartitionId
        keys[5] = String.valueOf(0);// statusHub
        keys[6] = String.valueOf(0);// statusSat
        keys[7] = String.valueOf(0);// statusLnk
        keys[8] = null;// cdcOperation
        keys[9] = "50CharacterLooooooooooooooooooooooooooooooooooong1"; //liner nvarchar(50)
        keys[10] = "50CharacterLooooooooooooooooooooooooooooooooooong2"; //Liner_Assistant nvarchar(50)
        keys[11] = "20CharacterLooooong1"; //Destination_Region nvarchar(20)
        keys[12] = "20CharacterLooooong2"; //Trade_Name_GVA_style_For_EMED_statistic nvarchar(20)
        keys[13] = "R"; //LAND_LOCKED_country nvarchar(1 CHAR)
        keys[14] = "16CharacterLong1"; //Agency_Region nvarchar(16 CHAR)
        keys[15] = "QWE"; //Agency_ID nvarchar(3 CHAR)
        keys[16] = "20CharacterLooooong3"; //CTSTier5 nvarchar(20 CHAR)
        keys[17] = "20CharacterLooooong4"; //CTSTier4_Location nvarchar(20 CHAR)
        keys[18] = "20CharacterLooooong1"; //CTSTier4_Country nvarchar(20 CHAR)
        keys[19] = "16CharacterLong2"; //dkEXPteam nvarchar(16 CHAR)
        keys[20] = "T"; //DeepseaFlag nvarchar(1 CHAR)
        keys[21] = "04-10-2015"; //VALID_FROM datetime
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("locationsPortsOverview.EXCEL.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("locationsPortsOverview.EXCEL.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("locationsPortsOverview.EXCEL.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("locationsPortsOverview.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("locationsPortsOverview.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("locationsPortsOverview.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (location_Code, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                " liner, Liner_Assistant, Destination_Region, Trade_Name_GVA_style_For_EMED_statistic, LAND_LOCKED_country, Agency_Region, Agency_ID, CTSTier5," +
                "CTSTier4_Location, CTSTier4_Country, dkEXPteam, DeepseaFlag, VALID_FROM) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", '" + keys[9]
                + "', '" + keys[10] + "', '" + keys[11] + "', '" + keys[12] + "', '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "', '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19] + "', '" + keys[20] + "', '" + keys[21] + "')";
        //System.out.println(insert);
        return insert;
    }



    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (locationCode, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[4] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE location_Code = " + keys[1]  + " and SrcSystemId = " + keys[2];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE locationCode = " + keys[1] + " and SrcSystemId = " + keys[2];
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
        String select = "SELECT * from " + keys[0] + " WHERE location_Code = " + keys[1] + " and SrcSystemId = " + keys[2];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE locationCode = " + keys[1] +  " and SrcSystemId = " + keys[2];
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
