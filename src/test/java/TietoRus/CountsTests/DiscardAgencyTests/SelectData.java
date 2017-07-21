package TietoRus.CountsTests.DiscardAgencyTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SelectData {

    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();
/*
Функциональность: Обработка записей не попадающих под условия отбора.
Описание: В SA должны быть логически удалены записи, которые не соотвествуют условиям. У каждой таблицы, к которой применимо свои условия отбора.
Все условия касаются либо одного параметра, либо двух, есть исключения.
Значения параметров, учавствующих в условиях, по которым записи должны быть найдены и логически удалены:
SET_OF_SELSKAB: select SELSKAB from EDI_KONV where AGENT = 'MSC' and FELT = 'ID' and FRA = 'DWH_LOAD_SELSKAB' and TIL = 'Y'
DWH_START_DATE: SELECT to_date(c_val, 'dd.mm.yyyy') FROM uts_constants WHERE c_name = 'DWH_START_DATE'
Выбраны они должны быть в источниках (не в SA).
Все тестовые данные построены таким образом, что даты действия, по которым отбираются записи лежат за границами обычного значения параметра  DWH_START_DATE = 01.12.2013,
а значение SELSKAB'а для каждой записи где нужно подобрано из реальных данных либо заведомо несуществующих и с большой долей вероятности не засуществующих значений
в реальной БД Заказчика
 */

    @Test(enabled = true)
    /*
    Тест проверяет записи в SA-таблицах.
     */
    public void SelectFromSAtablesTest() throws SQLException, IOException {
        String[] select = new String[60];
        select[0] = "abPost.UNITY.select";
        select[1] = "abPost.MSCRUS.select";
        select[2] = "adresse.UNITY.select";
        select[3] = "adresse.MSCRUS.select";
        select[4] = "bogfTrans.UNITY.select";
        select[5] = "bogfTrans.MSCRUS.select";
        select[6] = "book.UNITY.select";
        select[7] = "book.MSCRUS.select";
        select[8] = "bookDetails.UNITY.select";
        select[9] = "bookDetails.MSCRUS.select";
        select[10] = "bookDetailsMof.UNITY.select";
        select[11] = "bookDetailsMof.MSCRUS.select";
        select[12] = "bookDryPort.UNITY.select";
        select[13] = "bookDryPort.MSCRUS.select";
        select[14] = "bookEvent.UNITY.select";
        select[15] = "bookEvent.MSCRUS.select";
        select[16] = "bookFak.UNITY.select";
        select[17] = "bookFak.MSCRUS.select";
        select[18] = "bookGods.UNITY.select";
        select[19] = "bookGods.MSCRUS.select";
        select[20] = "bookKor.UNITY.select";
        select[21] = "bookKor.MSCRUS.select";
        select[22] = "bookLin.UNITY.select";
        select[23] = "bookLin.MSCRUS.select";
        select[24] = "bookManifests.UNITY.select";
        select[25] = "bookManifests.MSCRUS.select";
        select[26] = "bookMftFile.UNITY.select";
        select[27] = "bookMftFile.MSCRUS.select";
        select[28] = "bookMftRemarks.UNITY.select";
        select[29] = "bookMftRemarks.MSCRUS.select";
        select[30] = "bookVessel.UNITY.select";
        select[31] = "bookVessel.MSCRUS.select";
        select[32] = "contHolliday.UNITY.select";
        select[33] = "contHolliday.MSCRUS.select";
        select[34] = "contType.UNITY.select";
        select[35] = "contType.MSCRUS.select";
        select[36] = "ediKonv.UNITY.select";
        select[37] = "ediKonv.MSCRUS.select";
        select[38] = "expVessels.UNITY.select";
        select[39] = "expVessels.MSCRUS.select";
        select[40] = "faktPost.UNITY.select";
        select[41] = "faktPost.MSCRUS.select";
        select[42] = "henvis.UNITY.select";
        select[43] = "henvis.MSCRUS.select";
        select[44] = "kunde.UNITY.select";
        select[45] = "kunde.MSCRUS.select";
        select[46] = "ordre.UNITY.select";
        select[47] = "ordre.MSCRUS.select";
        select[48] = "ordreLin.UNITY.select";
        select[49] = "ordreLin.MSCRUS.select";
        select[50] = "sag.UNITY.select";
        select[51] = "sag.MSCRUS.select";
        select[52] = "sagKurs.UNITY.select";
        select[53] = "sagKurs.MSCRUS.select";
        select[54] = "shipKurs.UNITY.select";
        select[55] = "shipKurs.MSCRUS.select";
        select[56] = "selskab.UNITY.select";
        select[57] = "selskab.MSCRUS.select";
        select[58] = "getCharges.UNITY.select";
        select[59] = "getCharges.MSCRUS.select";

        System.err.println("Ожидаемые значения во всех SA-таблицах: StatusHub = 1, StatusSat=1, StatusLnk =1, PartitionId =1, IsDeleted =1, ReasonDeleted = 1");
        System.err.println("Для таблицы ediKonv запись с SELSKAB = 20 должна остаться не помеченной на удаление");
        getPropertiesFile();
        for (int i = 0; i < select.length; i++) {
            String query = properties.getProperty(select[i]);
            System.out.println("-------");
            selectTestRowFromSA(query, select[i]);
            mapForSource.clear();
        }
    }


    @Test(enabled = true)
    /*
    Тест проверяет записи в DEL-таблицах.
     */
    public void SelectFromDelTablesTest() throws SQLException, IOException {
        String[] select = new String[2];
        select[0] = "sag_del.MSCRUS.select";
        select[1] = "sag_del.UNITY.select";
        //select[2] = "sag.MSCRUS.select";
        //select[3] = "sag.UNITY.select";


        getPropertiesFile();
        for (int i = 0; i < select.length; i++) {
            String query = properties.getProperty(select[i]);
            System.out.println("-------");
            selectTestRowFromDelTables(query, select[i]);
            mapForSource.clear();
        }
    }



    public Map<String, Object> selectTestRowFromDelTables(String query, String s) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, query);
        if (rsFromSA.next()) {
            do {
                for (int k = 1; k <= rsFromSA.getMetaData().getColumnCount(); k++) {
                    mapForSource.put(rsFromSA.getMetaData().getColumnName(k), rsFromSA.getObject(k));
                    if (rsFromSA.wasNull()) {
                        System.err.println("In [" + s + "] column " + rsFromSA.getMetaData().getColumnName(k) + " is null!");
                    }
                }
                System.out.println(s + ": " + mapForSource);
            }
            while (rsFromSA.next());
        } else {
            System.err.println("No data found for " + s + "!");
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return mapForSource;

    }

    public Map<String, Object> selectTestRowFromSA(String query, String s) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, query);
        if (rsFromSA.next()) {
            do {
                for (int k = 1; k <= rsFromSA.getMetaData().getColumnCount(); k++) {
                    mapForSource.put(rsFromSA.getMetaData().getColumnName(k), rsFromSA.getObject(k));
                    if (rsFromSA.getObject(k).equals("1")) {
                        System.err.println("In [" + s + "] column " + rsFromSA.getMetaData().getColumnName(k) + " is Value is not 1!");
                    }
                }
                System.out.println(s + ": " + mapForSource);
            }
            while (rsFromSA.next());
        } else {
            System.err.println("No data found for " + s + "!");
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return mapForSource;

    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DiscardAgency.properties"))));
    }

    public void insertTestRowInSA(String query, String tableName) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(query);
        System.out.println("Insert test row in " + tableName + " complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

    public void deleteTestRowFromSA(String delete, String tableName) throws SQLException {
        System.out.println("SQL for Delete from SA: " + delete);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(delete);
        System.out.println("Delete test row from " + tableName + " complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

}
