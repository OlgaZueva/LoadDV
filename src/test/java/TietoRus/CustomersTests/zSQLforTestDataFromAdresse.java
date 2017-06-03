package TietoRus.CustomersTests;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение готовых запросов для вставки, редактирования и удаления тестовых записей в БД
 */
public class zSQLforTestDataFromAdresse {
    private Properties properties = new Properties();

    private String[] getValues(String tableName) {
        String[] keys = new String[22];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //NR
        keys[2] = String.valueOf(999001); //REF_NR
        keys[3] = String.valueOf(9); //REF_TYPE
        keys[4] = String.valueOf(98); //SELSKAB
        keys[5] = String.valueOf(1); //SrcSystemId
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = null;// cdcOperation
        keys[10] = "ADRESSE1"; //ADRESSE1 nvarchar(60 char)
        keys[11] = "ADRESSE2"; //ADRESSE2 nvarchar(60 char)
        keys[12] = "ADRESSE3"; //ADRESSE3 nvarchar(100 char)
        keys[13] = "BYNAVN"; //BYNAVN nvarchar(60 char)
        keys[14] = "TEST1"; //CITY_CODE nvarchar(5 char)
        keys[15] = "65464"; //CUSTOMER_NUMBER nvarchar(8 char)
        keys[16] = "DEB"; //DEB_LAND nvarchar(6 char)
        keys[17] = "EMAILADR"; //EMAILADR nvarchar(100 char)
        keys[18] = "NAVN"; //NAVN nvarchar(10 char)
        keys[19] = "TELEFAX"; //TELEFAX nvarchar(20 char)
        keys[20] = "TELEFON"; //TELEFON nvarchar(30 char)
        keys[21] = "A";//customerType (not null in DB, because it's here)
        return keys;
    }


    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("adresse.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("adresse.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("adresse.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getDWHSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoDWH(properties.getProperty("customers.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteFromDWH(properties.getProperty("customers.hub.table")));
        System.out.println("------");
        System.out.println(getSelectFromDWH(properties.getProperty("customers.hub.table")));
        System.out.println("---------------------------");
    }



    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (NR, REF_NR, REF_TYPE, SELSKAB, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation, " +
        "ADRESSE1, ADRESSE2, ADRESSE3, BYNAVN, CITY_CODE, CUSTOMER_NUMBER, DEB_LAND, EMAILADR,  NAVN, TELEFAX,TELEFON ) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", '" + keys[10] + "', '" + keys[11] + "', '" + keys[12] + "', '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "', '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19] + "', '" + keys[20]  + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertIntoDWH(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (number, bookingNumber, refType, accessCompanyId, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[7] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE NR = " + keys[1] + " and REF_NR = " + keys[2]
                + " and REF_TYPE = '" + keys[3] + "' and SELSKAB = " + keys[4] +  " and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE number = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and refType = '" + keys[3] + "' " + " and accessCompanyId = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE NR = " + keys[1] + " and REF_NR = " + keys[2]
                + " and REF_TYPE = '" + keys[3] + "' " + " and SELSKAB = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }


    public String getSelectFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE number = " + keys[1] + " and bookingNumber = " + keys[2]
                + " and refType = '" + keys[3] + "' " + " and accessCompanyId = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
