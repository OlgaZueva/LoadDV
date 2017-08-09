package TietoRus.Entity.BookingTests;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение готовых запросов для вставки, редактирования и удаления тестовых записей в БД
 */
public class zSQLforTestDataBook {
    private Properties properties = new Properties();

    private String[] getValues(String tableName) {
        String[] keys = new String[54];
        keys[0] = tableName; //table name
        keys[1] = String.valueOf(999001); //BOOK_NR
        keys[2] = String.valueOf(99); //SELSKAB
        keys[3] = String.valueOf(1); //SrcSystemId
        keys[4] = String.valueOf(0); //TryCnt
        keys[5] = String.valueOf(0); //PartitionId
        keys[6] = String.valueOf(0);// statusHub
        keys[7] = null;// cdcOperation
        keys[8] = "AFD"; //AFDELING	nvarchar(3 CHAR)
        keys[9] = "AFG_BY";//AFG_BY	nvarchar(50 CHAR)
        keys[10] = "01-01-2017";// AFG_DATO	datetime
        keys[11] = "AFGZ"; //AFG_ZONE	nvarchar(6 CHAR)
        keys[12] = String.valueOf(70);//AFSENDER	decimal(10,0)
        keys[13] = "AiC";//AIRLINECODE_3	nvarchar(3 CHAR)
        keys[14] = "ANK_BY";//ANK_BY	nvarchar(50 CHAR)
        keys[15] = "02-01-2017";//ANK_DATO	datetime
        keys[16] = "ANKZON";//ANK_ZONE	nvarchar(6 CHAR)
        keys[17] = String.valueOf(71);//CONTROL_KONTOR	smallint
        keys[18] = String.valueOf(72); //CONTROL_SELSKAB	decimal(38,0)
        keys[19] = "CR_BY";//CREATED_BY	nvarchar(6 CHAR)
        keys[20] = "03-01-2017";//CREATION_DATE	datetime
        keys[21] = "CROSS_BOOK";//CROSS_BOOK	nvarchar(20 CHAR)
        keys[22] = "04-01-2017";// DATO	datetime
        keys[23] = "DER_REF";// DER_REF	nvarchar(35 CHAR)
        keys[24] = "Q";// DIRECT_FLAG	nvarchar(1 CHAR)
        keys[25] = "05-01-2017"; //FEEDER_DATO	datetime
        keys[26] = "FR_PAY";// FREIGHT_PAY	nvarchar(6 CHAR)
        keys[27] = "FR_P_I";// FREIGHT_PAY_IMS	nvarchar(6 CHAR)
        keys[28] = "GVA_AFDELING";//GVA_AFDELING	nvarchar(16 CHAR)
        keys[29] = "W";//IMP_EXP	nvarchar(1 CHAR)
        keys[30] = String.valueOf(73); //KONTOR	smallint
        keys[31] = "LOC";//LOC	nvarchar(6 CHAR)
        keys[32] = "LOC_REF";//LOC_REF	nvarchar(20 CHAR)
        keys[33] = "LOC2";//LOC2	nvarchar(6 CHAR)
        keys[34] = "LOC2_REF";//LOC2_REF	nvarchar(20 CHAR)
        keys[35] = "qw1";// MANIFEST_TYPE	nvarchar(3 CHAR)
        keys[36] = "qw2";//OCEAN_AFDELING	nvarchar(3 CHAR)
        keys[37] = "PL_O_DELIVER";//PL_O_DELIVER	nvarchar(50 CHAR)
        keys[38] = "PL_O_RECEIPT"; //PL_O_RECEIPT	nvarchar(50 CHAR)
        keys[39] = "E";//PLD_HAULAGE_AT_DESTINATION	nvarchar(1 CHAR)
        keys[40] = "PSC";//PLD_SUBLOCATION_CODE1	nvarchar(3 CHAR)
        keys[41] = "PLDZIP";//PLD_ZIPCODE	nvarchar(8 CHAR)
        keys[42] = "R";//PLR_HAULAGE_AT_ORIGIN	nvarchar(1 CHAR)
        keys[43] = "qw3";//PLR_SUBLOCATION_CODE1	nvarchar(3 CHAR)
        keys[44] = "PLRZIP";//PLR_ZIPCODE	nvarchar(8 CHAR)
        keys[45] = "05-01-2017";//POD_DATO	datetime
        keys[46] = "06-01-2017";//POR_DATO	datetime
        keys[47] = "QUOTATION_REF_NR";//QUOTATION_REF_NR	nvarchar(25 CHAR)
        keys[48] = "REF_NR";//REF_NR	nvarchar(20 CHAR)
        keys[49] = "SPEC_REF_NUMBER";//SPEC_REF_NUMBER	nvarchar(25 CHAR)
        keys[50] = "T";//SPECIAL_CONTRACT_REF	nvarchar(1 CHAR)
        keys[51] = "Y";//STAT	nvarchar(1 CHAR)
        keys[52] = "TIDL_GODS";//TIDL_GODS	nvarchar(35 CHAR)
        keys[53] = "UDF2";//UDF_ANG2	Vnvarchar(4 CHAR)
        return keys;
    }


    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("booking.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("booking.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("booking.UNITY.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getDWHSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoDWH(properties.getProperty("booking.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteFromDWH(properties.getProperty("booking.hub.table")));
        System.out.println("------");
        System.out.println(getSelectFromDWH(properties.getProperty("booking.hub.table")));
        System.out.println("---------------------------");
    }


    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (BOOK_NR, SELSKAB, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation, " +
                "AFDELING, AFG_BY, AFG_DATO, AFG_ZONE, AFSENDER, AIRLINECODE_3, ANK_BY, ANK_DATO, ANK_ZONE, CONTROL_KONTOR, CONTROL_SELSKAB," +
                " CREATED_BY, CREATION_DATE, CROSS_BOOK, DATO, DER_REF, DIRECT_FLAG, FEEDER_DATO, FREIGHT_PAY, FREIGHT_PAY_IMS, GVA_AFDELING, IMP_EXP," +
                "KONTOR, LOC, LOC_REF, LOC2, LOC2_REF, MANIFEST_TYPE, OCEAN_AFDELING, PL_O_DELIVER, PL_O_RECEIPT, PLD_HAULAGE_AT_DESTINATION," +
                " PLD_SUBLOCATION_CODE1, PLD_ZIPCODE, PLR_HAULAGE_AT_ORIGIN, PLR_SUBLOCATION_CODE1,PLR_ZIPCODE, POD_DATO, POR_DATO, QUOTATION_REF_NR," +
                " REF_NR, SPEC_REF_NUMBER, SPECIAL_CONTRACT_REF, STAT, TIDL_GODS, UDF_ANG2) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", '" +
                keys[8] + "', '" + keys[9] + "', '" + keys[10] + "', '" + keys[11] + "', " + keys[12] + ", '" + keys[13] + "', '" + keys[14] + "', '" +
                keys[15] + "', '" + keys[16] + "', " + keys[17] + ", " + keys[18] + ", '" + keys[19] + "', '" + keys[20] + "', '" + keys[21] + "', '" +
                keys[22] + "', '" + keys[23] + "', '" + keys[24] + "', '" + keys[25] + "', '" + keys[26] + "', '" + keys[27] + "', '" + keys[28] + "', '" +
                keys[29] + "', " + keys[30] + ", '" + keys[31] + "', '" + keys[32] + "', '" + keys[33] + "', '" + keys[34] + "', '" + keys[35] + "', '" +
                keys[36] + "', '" + keys[37] + "', '" + keys[38] + "', '" + keys[39] + "', '" + keys[40] + "', '" + keys[41] + "', '" + keys[42] + "', '" +
                keys[43] + "', '" + keys[44] + "', '" + keys[45] + "', '" + keys[46] + "', '" + keys[47] + "', '" + keys[48] + "', '" + keys[49] + "', '" +
                keys[50] + "', '" + keys[51] + "', '" + keys[52] + "', '" + keys[53] + "')";
        //System.out.println(insert);
        return insert;
    }

    public String getInsertIntoDWH(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (bookingNumber, accessCompanyId, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[5] + ")";
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

    public String getDeleteFromDWH(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE bookingNumber = " + keys[1]
                + " and accessCompanyId = " + keys[2] + " and SrcSystemId = " + keys[3];
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
