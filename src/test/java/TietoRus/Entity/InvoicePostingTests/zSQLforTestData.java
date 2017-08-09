package TietoRus.Entity.InvoicePostingTests;

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
        String[] keys = new String[33];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(99); //SELSKAB smallint
        keys[2] = "Q"; //F_TYPE nvarchar(1 CHAR)
        keys[3] = "20FacturaNr"; //FAKTURANR nvarchar(20 CHAR)
        keys[4] = "W"; //K_TYPE nvarchar(1 CHAR)
        keys[5] = String.valueOf(101); //KUNDE decimal(10,0)
        keys[6] = String.valueOf(1); //SrcSystemId
        keys[7] = String.valueOf(0); //TryCnt
        keys[8] = String.valueOf(0); //PartitionId
        keys[9] = String.valueOf(0);// statusHub
        keys[10] = String.valueOf(0);// statusSat
        keys[11] = String.valueOf(0);// statusLnk
        keys[12] = null;// cdcOperation
        keys[13] = String.valueOf(12); //BELOBDKK decimal(18,2)
        keys[14] = String.valueOf(13); //BELOBVAL decimal(18,2)
        keys[15] = "02-10-1999"; //BET_DATO datetime
        keys[16] = "BET_MD"; //BET_MD nvarchar(6 CHAR)
        keys[17] = "B_BET"; //BETAL_BET nvarchar(6 CHAR)
        keys[18] = "BETAL_REF value"; //BETAL_REF nvarchar(30 CHAR)
        keys[19] = String.valueOf(9977); //BILAGSNR bigint
        keys[20] = String.valueOf(9978); //DAGBOG int
        keys[21] = "05-10-1999"; //DATO datetime
        keys[22] = "06-10-1999"; //FORF_DATO datetime
        keys[23] = String.valueOf(9979); //FP_ID int
        keys[24] = "INIT_1"; //INIT nvarchar(6 CHAR)
        keys[25] = String.valueOf(14); //KURS decimal(18,6)
        keys[26] = "MONT_1"; //MONT_ENHED nvarchar(6 CHAR)
        keys[27] = String.valueOf(9979); //REL_FAK_NR int
        keys[28] = String.valueOf(15); //RESTDKK decimal(18,2)
        keys[29] = String.valueOf(16); //RESTVAL decimal(18,2)
        keys[30] = "SAP_test"; //SAP_DOCUMENT_NBR nvarchar(20 CHAR)
        keys[31] = "SOURCE__test"; //SOURCE_MODULE nvarchar(20 CHAR)
        keys[32] = "TEKST_test"; //TEKST nvarchar(30 CHAR)
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("invoicePosting.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("invoicePosting.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("invoicePosting.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("invoicePosting.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("invoicePosting.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("invoicePosting.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, F_TYPE, FAKTURANR, K_TYPE, KUNDE, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                "BELOBDKK, BELOBVAL, BET_DATO, BET_MD, BETAL_BET, BETAL_REF, BILAGSNR, DAGBOG, DATO, FORF_DATO, FP_ID, INIT, KURS, MONT_ENHED, REL_FAK_NR, RESTDKK," +
                " RESTVAL, SAP_DOCUMENT_NBR, SOURCE_MODULE, TEKST) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", " + keys[12] + ", " + keys[13] + ", " + keys[14] + ", '" + keys[15] + "', '" + keys[16] + "', '" + keys[17]
                + "', '" + keys[18] + "', " + keys[19] + ", " + keys[20] + ", '" + keys[21] +  "', '" + keys[22] + "', " + keys[23] + ", '" + keys[24] + "', "
                + keys[25] + ", '" + keys[26] + "', " + keys[27] + ", " + keys[28] + ", " + keys[29] + ", '" + keys[30] + "', '" + keys[31]+ "', '" + keys[32]+ "')";
        //System.out.println(insert);
        return insert;
    }


    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (accessCompanyId, invoiceType, invoiceNr, debitCredit, customerCode, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', "  + keys[5] + ", " + keys[6] + ", "+ keys[8] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and F_TYPE = " + keys[2]
                + " and FAKTURANR = '" + keys[3]  + " and K_TYPE = '" + keys[4] + " and KUNDE = '" + keys[5]  + "' and SrcSystemId = " + keys[6];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and invoiceType = " + keys[2]
                + " and invoiceNr = '" + keys[3] + " and debitCredit = '" + keys[4] + " and customerCode = '" + keys[5] + "' and SrcSystemId = " + keys[6];
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
        String select = "SELECT * from " + keys[0] + " WHERE SELSKAB = " + keys[1] + " and F_TYPE = " + keys[2]
                + " and FAKTURANR = '" + keys[3]  + " and K_TYPE = '" + keys[4] + " and KUNDE = '" + keys[5]  + "' and SrcSystemId = " + keys[6];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE accessCompanyId = " + keys[1] + " and invoiceType = " + keys[2]
                + " and invoiceNr = '" + keys[3] + " and debitCredit = '" + keys[4] + " and customerCode = '" + keys[5] + "' and SrcSystemId = " + keys[6];
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
