package TietoRus.Entity.InvoiceTests;

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
        String[] keys = new String[24];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = String.valueOf(999001); //ORDRE_NR bigint
        keys[3] = String.valueOf(1); //SrcSystemId
        keys[4] = String.valueOf(0); //TryCnt
        keys[5] = String.valueOf(0); //PartitionId
        keys[6] = String.valueOf(0);// statusHub
        keys[7] = String.valueOf(0);// statusSat
        keys[8] = String.valueOf(0);// statusLnk
        keys[9] = null;// cdcOperation
        keys[10] = "BETAL"; //BETAL_BET nvarchar(6 CHAR)
        keys[11] = "Q"; //DK_KODE nvarchar(1 CHAR)
        keys[12] = String.valueOf(17); //FAK_NR int
        keys[13] = "03-10-1999"; //FAKT_DATO datetime
        keys[14] = "TEST rej"; //FORF_DATO datetime
        keys[15] = "E"; //IMP_EXP nvarchar(1 CHAR)
        keys[16] = String.valueOf(18.99); //KURS decimal(18,6)
        keys[17] = "04-10-1999"; //PRINT_DATE datetime
        keys[18] = "REF_T"; //REF_TYPE nvarchar(6 CHAR)
        keys[19] = "05-10-1999"; //REG_DATO datetime
        keys[20] = String.valueOf(19); //REG_NR bigint
        keys[21] = "06-10-1999"; //SERVICE_DATE datetime
        keys[22] = "R"; //STAT nvarchar(1 CHAR)
        keys[23] = "VOR_REF"; //VOR_REF nvarchar(20 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("invoice.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("invoice.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("invoice.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("invoice.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("invoice.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("invoice.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, ORDRE_NR, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                "BETAL_BET, DK_KODE, FAK_NR, FAKT_DATO, FORF_DATO, IMP_EXP, KURS, PRINT_DATE, REF_TYPE, REG_DATO, REG_NR, SERVICE_DATE, STAT, VOR_REF) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", '" + keys[10] + "', '" + keys[11] + "', " + keys[12] + ", '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', " + keys[16] + ", '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19] + "', " + keys[20] + ", '" + keys[21] + "', '" + keys[22] + "', '" + keys[23] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, orderNr,  SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[5] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and ORDRE_NR = " + keys[2]
                 + " and SrcSystemId = " + keys[3];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and orderNr = " + keys[2]
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and ORDRE_NR = " + keys[2]
                + " and SrcSystemId = " + keys[3];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and orderNr = " + keys[2]
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
