package TietoRus.Entity.BookingTests;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение готовых запросов для вставки, редактирования и удаления тестовых записей в БД
 */
public class zSQLforTestDataBookDryPort {
    private Properties properties = new Properties();

    private String[] getValues(String tableName) {
        String[] keys = new String[14];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(999001); //BOOK_NR
        keys[2] = String.valueOf(99); //SELSKAB
        keys[3] = String.valueOf(1); //SrcSystemId
        keys[4] = String.valueOf(0); //TryCnt
        keys[5] = String.valueOf(0); //PartitionId
        keys[6] = String.valueOf(0);// statusHub
        keys[7] = null;// cdcOperation
        keys[8] = "CARRIAGE"; //CARRIAGE_MODE nvarchar(10 CHAR)
        keys[9] = "DRY_PORT_NAME";//DRY_PORT_NAME	nvarchar(100 CHAR)
        keys[10] = "PL_O_DELIVER";// PL_O_DELIVER	nvarchar(100 CHAR)
        keys[11] = "PL_O_RECEIPT"; //PL_O_RECEIPT	nvarchar(100 CHAR)
        keys[12] = "PLD_ZIPCODE";//PLD_ZIPCODE	nvarchar(10 CHAR)
        keys[13] = "PLR_ZIPCODE";//PLR_ZIPCODE	nvarchar(10 CHAR)
        return keys;
    }


    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("bookDryPort.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("bookDryPort.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("bookDryPort.UNITY.table")));
        System.out.println("---------------------------");

    }

    /*
    хаб BookDryPort не создается- используется запись из hubBooking
     */
     @Test
    public void getDWHSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println("------");
        System.out.println(getSelectFromDWH(properties.getProperty("booking.hub.table")));
        System.out.println("---------------------------");
    }


    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (BOOK_NR, SELSKAB, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation, " +
                "CARRIAGE_MODE, DRY_PORT_NAME, PL_O_DELIVER, PL_O_RECEIPT, PLD_ZIPCODE, PLR_ZIPCODE) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", '" +
                keys[8] + "', '" + keys[9] + "', '" + keys[10] + "', '" + keys[11] + "', '" + keys[12] + "', '" + keys[13]  + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE BOOK_NR = " + keys[1]
                + " and SELSKAB = " + keys[2] + " and SrcSystemId = " + keys[3];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE BOOK_NR = " + keys[1]
                + " and SELSKAB = " + keys[2] + " and SrcSystemId = " + keys[3];
        //System.out.println(select);
        return select;
    }


    public String getSelectFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE bookingNumber = " + keys[1]
                + " and accessCompanyId = " + keys[2] + " and SrcSystemId = " + keys[3];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
