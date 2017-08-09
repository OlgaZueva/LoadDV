package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SomeFieldsInSATests {
    private DBHelper db = new DBHelper();

/*
Класс содержит разные вспогательные тесты,  как правило используемы один-два раза
 */

    @Test(enabled = true)
    public void StatusInSATablesTest() throws SQLException, IOException {
        /*
Тест для генерации запросов во все таблицы SA
 */
        String[] keys = getTableNames();
        for (int i = 0; i < keys.length; i++) {
            String sql = "select count (*) c from " + keys[i] + "where StatusLnk != 1 and isDeleted = 0 ";
            System.out.println(sql);

        }
    }

    @Test(enabled = true)
    public void PartitionIdInSATablesTest() throws SQLException, IOException {
        /*
Тест для проверки секционирования.
В главном пакете загрузки из SA в DWH (т.н."мейне мейнов") после запуска всех пакетов вызывает код, который для всех записей в SA-таблицах,
у которых StatusHub =1, StatusSat =1 и  StatusLnk = 1 проставляет значение PartitionId=1, т.е. меняет секцию.
Исключение - таблица getCharges- там партиция меняется у записей, у которых statusHub=1 (таблица особенная, никакие другие статусы не проставляются в ней)
Процесс описан в EA в Loading Staging Area - DWH -> main package load
 */
        String[] keys = getTableNames();
        for (int i = 0; i < keys.length; i++) {
            String sql = "select distinct PartitionId c from " + keys[i] + "where StatusHub =1 and StatusSat=1 and StatusLnk = 1";
            String partitionId = getDataFromSA(sql);
            if (partitionId.equals("No data found")) {
                System.out.println("In " + keys[i] + " No data matched query.");
            } else if (partitionId.equals("1")) {
                System.out.println(keys[i] + " [Succeeded]");
            } else
                System.err.println("In " + keys[i] + " Not valid partitionId: [" + partitionId + "]");
        }
    }

    @Test(enabled = true)
    public void DataLoadIdInSATablesTest() throws SQLException, IOException {
        /*
Тест для проверки заполнения поля DataLoadId  в SA-таблицах.
 */
        String[] keys = getTableNames();
        for (int i = 0; i < keys.length; i++) {
            String sql = "select DataLoadId c from " + keys[i] + "where DataLoadId is null";
            String partitionId = getDataFromSA(sql);
            if (partitionId.equals("No data found")) {
                System.out.println(keys[i] + " [Succeeded]");
            } else
                System.err.println("In " + keys[i] + " DataLoadId is null. It is error!");
        }
    }

    @Test(enabled = true)
    public void CdcRsidInSATablesTest() throws SQLException, IOException {
        /*
Тест для проверки заполнения поля cdcRsid  в SA-таблицах.
Поле заполняется только при загрузке изменений.
тест не применим для данные первоначальной загрузки.
Тест не применим для полностью прегружаемых таблиц (этого поля там просто нет)
 */
        String[] keys = getTableNames();
        for (int i = 0; i < keys.length; i++) {
            String sql = "select cdcRsid c from " + keys[i] + " where cdcTimestamp is not null and cdcRsid is null";
            String partitionId = getDataFromSA(sql);
            if (partitionId.equals("No data found")) {
                System.out.println(keys[i] + " [Succeeded]");
            } else
                System.err.println("In " + keys[i] + " cdcRsid is null. It is error!");
        }
    }

    @Test(enabled = true)
    public void ReasonDeletedInSATablesTest() throws SQLException, IOException {
        /*
Тест для проверки заполнения поля reasonDeleted  в SA-таблицах.
Поле заполняется пакетами, логически удаляющими записи (DiscardAgency.dtsx , DelSelskab.dtsx).
При isDeleted=1 поле reasonDeleted НЕ должно содержать значение = 0.
 */
        String[] keys = getTableNames();
        for (int i = 0; i < keys.length; i++) {
            String sql = "select reasonDeleted c from " + keys[i] + " where isDeleted = 1 and reasonDeleted = 0";
            String partitionId = getDataFromSA(sql);
            if (partitionId.equals("No data found")) {
                System.out.println(keys[i] + " [Succeeded]");
            } else
                System.err.println("In " + keys[i] + " reasonDeleted is null. It is error!");
        }
    }

    private String[] getTableNames() {
        String[] keys = new String[71];
        keys[0] = "stg.MSCRUS_AbPost ";
        keys[1] = "stg.MSCRUS_Adgang ";
        keys[2] = "stg.MSCRUS_AdgangLin ";
        keys[3] = "stg.MSCRUS_Adresse ";
        keys[4] = "stg.MSCRUS_BogfTrans ";
        keys[5] = "stg.MSCRUS_Book ";
        keys[6] = "stg.MSCRUS_BookDetails ";
        keys[7] = "stg.MSCRUS_BookDetailsMof ";
        keys[8] = "stg.MSCRUS_BookDryPort ";
        keys[9] = "stg.MSCRUS_BookEvent ";
        keys[10] = "stg.MSCRUS_BookFak ";
        keys[11] = "stg.MSCRUS_BookGods ";
        keys[12] = "stg.MSCRUS_BookKor ";
        keys[13] = "stg.MSCRUS_BookLin ";
        keys[14] = "stg.MSCRUS_BookManifests ";
        keys[15] = "stg.MSCRUS_BookMftFile ";
        keys[16] = "stg.MSCRUS_BookMftRemarks ";
        keys[17] = "stg.MSCRUS_BookVessel ";
        keys[18] = "stg.MSCRUS_ContHolliday ";
        keys[19] = "stg.MSCRUS_ContRep ";
        keys[20] = "stg.MSCRUS_ControlOffice ";
        keys[21] = "stg.MSCRUS_ContType ";
        keys[22] = "stg.MSCRUS_CustomerGuide ";
        keys[23] = "stg.MSCRUS_EdiKonv ";
        keys[24] = "stg.MSCRUS_ExpVessels ";
        keys[25] = "stg.MSCRUS_FaktPost ";
        keys[26] = "stg.MSCRUS_GetCharges ";
        keys[27] = "stg.MSCRUS_Henvis ";
        keys[28] = "stg.MSCRUS_Kunde ";
        keys[29] = "stg.MSCRUS_Ordre ";
        keys[30] = "stg.MSCRUS_OrdreLin ";
        keys[31] = "stg.MSCRUS_Sag ";
        keys[32] = "stg.MSCRUS_SagKurs ";
        keys[33] = "stg.MSCRUS_SelsKab ";
        keys[34] = "stg.MSCRUS_ShipKurs ";
        keys[35] = "stg.MSCRUS_UtsConstants ";
        keys[36] = "stg.UNITY_AbPost ";
        keys[37] = "stg.UNITY_Adgang ";
        keys[38] = "stg.UNITY_AdgangLin ";
        keys[39] = "stg.UNITY_Adresse ";
        keys[40] = "stg.UNITY_BogfTrans ";
        keys[41] = "stg.UNITY_Book ";
        keys[42] = "stg.UNITY_BookDetails ";
        keys[43] = "stg.UNITY_BookDetailsMof ";
        keys[44] = "stg.UNITY_BookDryPort ";
        keys[45] = "stg.UNITY_BookEvent ";
        keys[46] = "stg.UNITY_BookFak ";
        keys[47] = "stg.UNITY_BookGods ";
        keys[48] = "stg.UNITY_BookKor ";
        keys[49] = "stg.UNITY_BookLin ";
        keys[50] = "stg.UNITY_BookManifests ";
        keys[51] = "stg.UNITY_BookMftFile ";
        keys[52] = "stg.UNITY_BookMftRemarks ";
        keys[53] = "stg.UNITY_BookVessel ";
        keys[54] = "stg.UNITY_ContHolliday ";
        keys[55] = "stg.UNITY_ContRep ";
        keys[56] = "stg.UNITY_ControlOffice ";
        keys[57] = "stg.UNITY_ContType ";
        keys[58] = "stg.UNITY_CustomerGuide ";
        keys[59] = "stg.UNITY_EdiKonv ";
        keys[60] = "stg.UNITY_ExpVessels ";
        keys[61] = "stg.UNITY_FaktPost ";
        keys[62] = "stg.UNITY_GetCharges ";
        keys[63] = "stg.UNITY_Henvis ";
        keys[64] = "stg.UNITY_Kunde ";
        keys[65] = "stg.UNITY_Ordre ";
        keys[66] = "stg.UNITY_OrdreLin ";
        keys[67] = "stg.UNITY_Sag ";
        keys[68] = "stg.UNITY_SagKurs ";
        keys[68] = "stg.UNITY_SelsKab ";
        keys[69] = "stg.UNITY_ShipKurs ";
        keys[70] = "stg.UNITY_UtsConstants ";
        return keys;
    }


    public String getDataFromSA(String hubSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, hubSQL);
        String partitionId = "No data found";
        while (rsFromSA.next()) {
            partitionId = rsFromSA.getString("c");
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return partitionId;
    }


}
