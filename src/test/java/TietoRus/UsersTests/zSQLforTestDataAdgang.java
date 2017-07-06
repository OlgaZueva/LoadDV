package TietoRus.UsersTests;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение готовых запросов для вставки, редактирования и удаления тестовых записей в БД
 */
public class zSQLforTestDataAdgang {
    private Properties properties = new Properties();

    private String[] getValues(String tableName) {
        String[] keys = new String[22];
        keys[0] = tableName; //table name
        keys[1] = "VORREF"; //VOR_REF nvarchar(6 CHAR)
        keys[2] = "TEST_LOGIN"; //AD_LOGIN nvarchar(100 CHAR)
        keys[3] = String.valueOf(99); //START_SEL smallint
        keys[4] = String.valueOf(1); //SrcSystemId
        keys[5] = String.valueOf(0); //TryCnt
        keys[6] = String.valueOf(0); //PartitionId
        keys[7] = String.valueOf(0);// statusHub
        keys[8] = String.valueOf(0);// statusSat
        keys[9] = String.valueOf(0);// statusLnk
        keys[10] = null;// cdcOperation
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("usersAdgang.UNITY.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("usersAdgang.UNITY.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("usersAdgang.UNITY.table")));
        System.out.println("---------------------------");

    }


    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (VOR_REF, AD_LOGIN, START_SEL, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation) Values ('"
                + keys[1] + "', '" + keys[2] + "', " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10]  + ")";
        //System.out.println(insert);
        return insert;
    }



    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE VOR_REF = '" + keys[1] + "' and AD_LOGIN = '" + keys[2]
                + "' and START_SEL = " + keys[3]  + " and SrcSystemId = " + keys[4];
        //System.out.println(delete);
        return delete;
    }



    public String getSelectFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE VOR_REF = '" + keys[1] + "' and AD_LOGIN = '" + keys[2]
                + "' and START_SEL = " + keys[3] + " and SrcSystemId = " + keys[4];
        //System.out.println(select);
        return select;
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/systemSQL.properties"))));
    }
}
