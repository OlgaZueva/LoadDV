package TietoRus.Entity.InvoiceLinesTests;

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
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = String.valueOf(999001); //LINIE_NR bigint
        keys[3] = String.valueOf(98); //ORDRE_NR nvarchar(3 CHAR)
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = String.valueOf(0);// statusSat
        keys[9] = String.valueOf(0);// statusLnk
        keys[10] = null;// cdcOperation
        keys[11] = String.valueOf(99.99); //A_PRIS decimal(12,2)
        keys[12] = "QWE"; //AKTIVITET nvarchar(3 CHAR)
        keys[13] = String.valueOf(98.999); //ANTAL decimal(11,3)
        keys[14] = "15Charrrrrrrrrrr"; //CONT_NR nvarchar(15 CHAR)
        keys[15] = "6Charr"; //CONT_TYPE nvarchar(6 CHAR)
        keys[16] = "4Cha"; //ENHED nvarchar(4 CHAR)
        keys[17] = String.valueOf(987.999999); //OMR_KURS decimal(18,6)
        keys[18] = "6Chaar"; //OMR_VALUTA nvarchar(6 CHAR)
        keys[19] = "TeXT"; //TEKST nvarchar(4000 CHAR
        keys[20] = String.valueOf(96.99); //TOTAL_BEL decimal(16,2);
        keys[21] = String.valueOf(95); //YDE_NR smallint
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("invoiceLines.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("invoiceLines.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("invoiceLines.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("invoiceLines.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("invoiceLines.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("invoiceLines.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, LINIE_NR, ORDRE_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation, " +
                "A_PRIS, AKTIVITET, ANTAL, CONT_NR, CONT_TYPE, ENHED, OMR_KURS, OMR_VALUTA, TEKST, TOTAL_BEL, YDE_NR) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", '" + keys[12] + "', " + keys[13] + ", '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "', " + keys[17]
                + ", '" + keys[18] + "', '" + keys[19] + "', " + keys[20] + ", " + keys[21] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, invoiceLineNr, orderNr, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[6] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and LINIE_NR = " + keys[2]
                + " and ORDRE_NR = " + keys[3]  + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and invoiceLineNr = " + keys[2]
                + " and orderNr = " + keys[3]  + " and SrcSystemId = " + keys[4];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and LINIE_NR = " + keys[2]
                + " and ORDRE_NR = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and invoiceLineNr = " + keys[2]
                + " and orderNr = " + keys[3] + " and SrcSystemId = " + keys[4];
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
