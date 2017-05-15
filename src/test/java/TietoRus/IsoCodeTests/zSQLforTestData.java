package TietoRus.IsoCodeTests;

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
        keys[2] = "kode_4"; //ISO_KODE4 nvarchar(6 CHAR)
        keys[3] = "kode_6"; //ISO_KODE6 nvarchar(6 CHAR)
        keys[4] = "kode62"; //ISO_KODE6_2 nvarchar(6 CHAR)
        keys[5] = "kode63"; //ISO_KODE6_3 nvarchar(6 CHAR)
        keys[6] = "kode64"; //ISO_KODE6_4 nvarchar(6 CHAR)
        keys[7] = "kode65"; //ISO_KODE6_5 nvarchar(6 CHAR)
        keys[8] = String.valueOf(1); //SrcSystemId
        keys[9] = String.valueOf(0); //TryCnt
        keys[10] = String.valueOf(0); //PartitionId
        keys[11] = String.valueOf(0);// statusHub
        keys[12] = String.valueOf(0);// statusSat
        keys[13] = String.valueOf(0);// statusLnk
        keys[14] = null;// cdcOperation
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("isoCode.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("isoCode.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("isoCode.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();

        System.err.println("DataVault");
        System.out.println("---------------------------");
        for (int i = 2; i < 8; i++) {
            System.out.println(getInsertHub(properties.getProperty("isoCode.hub.table"), i));
        }
        System.out.println("------");
        for (int i = 2; i < 8; i++) {
            System.out.println(getDeleteHub(properties.getProperty("isoCode.hub.table"), i));
        }
        System.out.println("------");
        for (int i = 2; i < 8; i++) {
            System.out.println(getSelectHub(properties.getProperty("isoCode.hub.table"), i));
        }
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, ISO_KODE4, ISO_KODE6, ISO_KODE6_2, ISO_KODE6_3, ISO_KODE6_4, ISO_KODE6_5," +
                " SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', '" + keys[5] + "', '" + keys[6] + "', " + keys[7] + ", " + keys[8]
                + ", " + keys[9] + ", " + keys[10] + ", " + keys[11] + ", " + keys[12] + ", " + keys[13] + ", " + keys[14] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName, int i) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, isoCode, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[i] + "', " + keys[8] + ", " + keys[10] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and ISO_KODE4 = '" + keys[2]
                + "' and ISO_KODE6 = '" + keys[3] + "' and ISO_KODE6_2 = '" + keys[4] + "' and ISO_KODE6_3 = '" + keys[5]
                + "' and ISO_KODE6_4 = '" + keys[6] + "' and ISO_KODE6_5 = '" + keys[7] + "' and SrcSystemId = " + keys[8];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName, int i) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and isoCode = '" + keys[i]
                 + "' and SrcSystemId = " + keys[8];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and ISO_KODE4 = '" + keys[2]
                + "' and ISO_KODE6 = '" + keys[3] + "' and ISO_KODE6_2 = '" + keys[4] + "' and ISO_KODE6_3 = '" + keys[5]
                + "' and ISO_KODE6_4 = '" + keys[6] + "' and ISO_KODE6_5 = '" + keys[7] + "' and SrcSystemId = " + keys[8];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName, int i) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and isoCode = '" + keys[i]
                 + "' and SrcSystemId = " + keys[8];
        //System.out.println(select);
        return select;
    }

    public String getSelectSat(String tableName, String fieldNameForHubId, int dwhHubId) {
        String select = "SELECT * from " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
