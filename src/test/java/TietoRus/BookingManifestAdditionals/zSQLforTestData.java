package TietoRus.BookingManifestAdditionals;

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
        String[] keys = new String[11];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //BOOK_NR
        keys[2] = String.valueOf(999001); //SEGMENT_CODE
        keys[3] = String.valueOf(9); //SELSKAB
        keys[4] = String.valueOf(98); //SEQ_NR
        keys[5] = String.valueOf(1); //SrcSystemId
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = null;// cdcOperation
        return keys;
    }


    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("bookingManifestAdditionals.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("bookingManifestAdditionals.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("bookingManifestAdditionals.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getDWHSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoDWH(properties.getProperty("bookingManifestAdditionals.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteFromDWH(properties.getProperty("bookingManifestAdditionals.hub.table")));
        System.out.println("------");
        System.out.println(getSelectFromDWH(properties.getProperty("bookingManifestAdditionals.hub.table")));
        System.out.println("---------------------------");
    }



    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (BOOK_NR, SEGMENT_CODE, SELSKAB, SEQ_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertIntoDWH(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (bookingNumber, segmentCode, accessCompanyId, sequenceNr, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[2] + "', " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[7] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE BOOK_NR = " + keys[1] + " and SEGMENT_CODE = '" + keys[2]
                + "' and SELSKAB = " + keys[3] + " and SEQ_NR = " + keys[4] +  " and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE bookingNumber = " + keys[1] + " and segmentCode = '" + keys[2]
                + "' and accessCompanyId = " + keys[3] + " " + " and sequenceNr = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE BOOK_NR = " + keys[1] + " and SEGMENT_CODE = '" + keys[2]
                + "' and SELSKAB = " + keys[3] + " and SEQ_NR = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }


    public String getSelectFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE bookingNumber = " + keys[1] + " and segmentCode = '" + keys[2]
                + "' and accessCompanyId = " + keys[3] + " " + " and sequenceNr = " + keys[4] + " and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
