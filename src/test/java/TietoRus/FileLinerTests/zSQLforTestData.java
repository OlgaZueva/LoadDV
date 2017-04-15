package TietoRus.FileLinerTests;

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
        String[] keys = new String[20];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB
        keys[2] = String.valueOf(999001); //SAGSNR
        keys[3] = String.valueOf(98); //AFDELING
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = "D ";// cdcOperation
        keys[9] = String.valueOf(1); //CALL_ID decimal(1,0)
        keys[10] = "01-10-1999"; //EXP_AFG_DATO datetime
        keys[11] = "02-10-1999"; //IMP_ANK_DATO datetime
        keys[12] = "03-10-1999"; //PROFORMA_DATE datetime
        keys[13] = "TEST rej"; //REJSE_NR nvarchar(10 CHAR)
        keys[14] = String.valueOf(9977); //S_WEEK decimal(4,0)
        keys[15] = "TEst SKIBS_NAVN"; //SKIBS_NAVN nvarchar(35 CHAR)
        keys[16] = "04-10-1999"; //SLUT_DATO datetime
        keys[17] = "05-10-1999"; //START_DATO datetime
        keys[18] = "R"; //STAT nvarchar(1 CHAR);
        keys[19] = "Y"; //TEMPORARY_FLAG nvarchar(1 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("fileLiner.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("fileLiner.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("fileLiner.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("fileLiner.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("fileLiner.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("fileLiner.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, SAGSNR, AFDELING, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation, CALL_ID, EXP_AFG_DATO, " +
                "IMP_ANK_DATO, PROFORMA_DATE, REJSE_NR, S_WEEK, SKIBS_NAVN, SLUT_DATO, START_DATO, STAT, TEMPORARY_FLAG) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", '" + keys[10] + "', '" + keys[11] + "', '" + keys[12] + "', '" + keys[13] + "', " + keys[14] + ", '" + keys[15] + "', '" + keys[16] + "', '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, fileLinerNr, serviceCode, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[6] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and SAGSNR = " + keys[2]
                + " and AFDELING = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and fileLinerNr = " + keys[2]
                + " and serviceCode = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }

    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and SAGSNR = " + keys[2]
                + " and AFDELING = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and fileLinerNr = " + keys[2]
                + " and serviceCode = '" + keys[3] + "' " + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
