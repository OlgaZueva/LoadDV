package TietoRus.ContainerTypeSpecEquipTests;

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
        keys[1] = String.valueOf(99); //CONT_FEET tinyint
        keys[2] = "SIZE 99"; //CONT_SIZE nvarchar(6 CHAR)
        keys[3] = "CONT_TYPE value"; //CONT_TYPE nvarchar(6 CHAR)
        keys[4] = "40 HIGH CUBE REEFER CONTAINER S.T.C.:"; //TEKST nvarchar(42 CHAR)
        keys[5] = String.valueOf(4); //SrcSystemId
        // ВНИМАНИЕ! значение SrcSystemId должно быть  = 4 - это соотвествует файлу CNTR_TYPE_SPEC_EQUIP.xls из котрого грузятся в SA данные
        keys[6] = String.valueOf(0); //TryCnt
        keys[7] = String.valueOf(0); //PartitionId
        keys[8] = String.valueOf(0);// statusHub
        keys[9] = String.valueOf(0);// statusSat
        keys[10] = String.valueOf(0);// statusLnk
        keys[11] = null;// cdcOperation
        keys[12] = "HR"; //MasterContType nvarchar(6 CHAR)
        keys[13] = "Q"; //TANK nvarchar(1 CHAR)
        keys[14] = "W"; //SpecialEqpInclREF nvarchar(1 CHAR)
        keys[15] = "E"; //SpecialEqpWoREF nvarchar(1 CHAR)
        keys[16] = "R"; //FLATRACK nvarchar(1 CHAR)
        keys[17] = "T"; //PLATFORM nvarchar(1 CHAR)
        keys[18] = "Y"; //OPENTOP nvarchar(1 CHAR)
        keys[19] = "04-10-2015"; //VALID_FROM datetime
        return keys;
    }

    @Test
    public void getSaSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("Staging Area");
        System.out.println("---------------------------");
        System.out.println(getInsertIntoSA(properties.getProperty("containerTypeSpecEquip.EXCEL.table")));
        System.out.println("------");
        System.out.println(getDeleteFromSA(properties.getProperty("containerTypeSpecEquip.EXCEL.table")));
        System.out.println("------");
        System.out.println(getSelectFromSA(properties.getProperty("containerTypeSpecEquip.EXCEL.table")));
        System.out.println("---------------------------");

    }

    @Test
    public void getHubSQLs() throws IOException {
        getPropertiesFile();
        System.err.println("DataVault");
        System.out.println("---------------------------");
        System.out.println(getInsertHub(properties.getProperty("containerTypeSpecEquip.hub.table")));
        System.out.println("------");
        System.out.println(getDeleteHub(properties.getProperty("containerTypeSpecEquip.hub.table")));
        System.out.println("------");
        System.out.println(getSelectHub(properties.getProperty("containerTypeSpecEquip.hub.table")));
        System.out.println("---------------------------");
    }

    public String getInsertIntoSA(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (CONT_FEET, CONT_SIZE, CONT_TYPE, TEKST, SrcSystemId, TryCnt,  PartitionId, statusHub, statusSat, statusLnk, cdcOperation," +
                " MasterContType, TANK, SpecialEqpInclREF, SpecialEqpWoREF, FLATRACK, PLATFORM, OPENTOP, VALID_FROM) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ", " + keys[9]
                + ", " + keys[10] + ", " + keys[11] + ", '" + keys[12] + "', '" + keys[13] + "', '" + keys[14] + "', '" + keys[15] + "', '" + keys[16] + "', '" + keys[17]
                + "', '" + keys[18] + "', '" + keys[19]  + "')";
        //System.out.println(insert);
        return insert;
    }



    public String getInsertHub(String tableName) {
        String[] keys = getValues(tableName);
        String insert = "Insert into " + keys[0] + " (contFeet, contSize, contType, contTypeName, SrcSystemId, PartitionId) Values ("
                + keys[1] + ", '" + keys[2] + "', '" + keys[3] + "', '" + keys[4] + "', " + keys[5] + ", " + keys[7] + ")";
        //System.out.println(insert);
        return insert;
    }


    public String getDeleteFromSA(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE CONT_FEET = " + keys[1] + " and CONT_SIZE = '" + keys[2]
                + "' and CONT_TYPE = '" + keys[3]  + "' and TEKST = '" + keys[4] + "' and SrcSystemId = " + keys[5];
        //System.out.println(delete);
        return delete;
    }

    public String getDeleteHub(String tableName) {
        String[] keys = getValues(tableName);
        String delete = "DELETE FROM " + keys[0] + " WHERE contFeet = " + keys[1] + " and contSize = '" + keys[2]
                + "' and contType = '" + keys[3] + "' and contTypeName = '" + keys[4] + "' and SrcSystemId = " + keys[5];
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
        String select = "SELECT * from " + keys[0] + " WHERE CONT_FEET = " + keys[1] + " and CONT_SIZE = '" + keys[2]
                + "' and CONT_TYPE = '" + keys[3]  + "' and TEKST = '" + keys[4] + "' and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }


    public String getSelectHub(String tableName) {
        String[] keys = getValues(tableName);
        String select = "SELECT * from " + keys[0] + " WHERE contFeet = " + keys[1] + " and contSize = " + keys[2]
                + "' and contType = '" + keys[3] + "' and contTypeName = '" + keys[4] + "' and SrcSystemId = " + keys[5];
        //System.out.println(select);
        return select;
    }

    public String getSelectSat(String tableName, String fieldNameForHubId, int dwhHubId) {
        String select = "SELECT * from " + tableName + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println(select);
        return select;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/system.properties"))));
    }
}
