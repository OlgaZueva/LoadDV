package TietoRus.Entity.CustomersTests;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение готовых запросов для вставки, редактирования и удаления тестовых записей в БД
 */
public class zSQLforTestDataFromKunde {
    private Properties properties = new Properties();

    private String[] getValues(String tableName) {
        String[] keys = new String[23];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = String.valueOf(999001); //KUNDENR decimal(10,0)
        keys[3] = String.valueOf(1); //SrcSystemId
        keys[4] = String.valueOf(0); //TryCnt
        keys[5] = String.valueOf(0); //PartitionId
        keys[6] = String.valueOf(0);// statusHub
        keys[7] = String.valueOf(0);// statusSat
        keys[8] = String.valueOf(0);// statusLnk
        keys[9] = null;// cdcOperation
        keys[10] = "ADRESSE1"; //ADRESSE1 nvarchar(60 char)
        keys[11] = "ADRESSE2"; //ADRESSE2 nvarchar(60 char)
        keys[12] = "ADRESSE3"; //ADRESSE3 nvarchar(100 char)
        keys[13] = "BYNAVN"; //BYNAVN nvarchar(60 char)
        keys[14] = "TEST1"; //CITY_CODE nvarchar(5 char)
        keys[15] = "65464"; //CUSTOMER_NUMBER nvarchar(8 char)
        keys[16] = "DEB"; //DEB_LAND nvarchar(6 char)
        keys[17] = "DERES_REF"; //DERES_REF nvarchar(20 char)
        keys[18] = "EMAILADR"; //EMAILADR nvarchar(100 char)
        keys[19] = "FORKORT"; //FORKORT nvarchar(10 char)
        keys[20] = "NAVN"; //NAVN nvarchar(10 char)
        keys[21] = "TELEFAX"; //TELEFAX nvarchar(20 char)
        keys[22] = "TELEFON"; //TELEFON nvarchar(30 char)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("kunde.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("kunde.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("kunde.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("customers.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("customers.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("customers.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, KUNDENR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation, " +
                "ADRESSE1, ADRESSE2, ADRESSE3, BYNAVN, CITY_CODE, CUSTOMER_NUMBER, DEB_LAND, DERES_REF, EMAILADR, FORKORT, NAVN, TELEFAX,TELEFON ) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", '" + keys[10] + "', '" + keys[11] + "', '" + keys[12] + "', '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "', '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19] + "', '" + keys[20] + "', '" + keys[21] + "', '" + keys[22]  + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, customerNr, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[5] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and KUNDENR = " + keys[2]
                 + " and SrcSystemId = " + keys[3];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and customerNr = " + keys[2]
                  + " and SrcSystemId = " + keys[3];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and KUNDENR = " + keys[2]
                + " and SrcSystemId = " + keys[3];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and customerNr = " + keys[2]
                + " and SrcSystemId = " + keys[3];
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
