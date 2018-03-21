package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Класс, содержащий тесты для проверки механизма "Обработка записей не попадающих под условия отбора".
Каждый тест "считает" кол-во записей, которые должны быть удалены согласно условиям и сравнивает это кол-во с фактическим.

Описание функциональности: В SA должны быть логически удалены записи, которые не соотвествуют условиям. У каждой таблицы, к которой применимо, свои условия отбора.
Все условия касаются либо одного параметра, либо двух, есть исключения.
Значения параметров, учавствующих в условиях, по которым записи должны быть найдены и логически удалены:
SET_OF_SELSKAB: select SELSKAB from EDI_KONV_ETL_V where AGENT = 'MSC' and FELT = 'ID' and FRA = 'DWH_LOAD_SELSKAB' and TIL = 'Y'
DWH_START_DATE: SELECT to_date(c_val, 'dd.mm.yyyy') FROM uts_constants_etl_V WHERE c_name = 'DWH_START_DATE'
Выбраны они должны быть пакетом в источниках (не в SA).
 */

public class DiscardAgencyCountsTests {



    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void AbPost() throws SQLException, IOException, ParseException {
        getPropertiesFile();
        String queryMSCRUS = properties.getProperty("common.part1") + " " + properties.getProperty("abPost.MSCRUS.tableName") + " " +
                properties.getProperty("common.part2") + getSelscabFromRTest(properties.getProperty("SET_OF_SELSKAB.sql")) +") or dato < (" +
                properties.getProperty("common.part3") +          getDWH_START_DATE_RTEST(properties.getProperty("DWH_START_DATE.sql")) +
                properties.getProperty("common.part4") + ")q";
        String queryUNITY = properties.getProperty("common.part1") + " " + properties.getProperty("abPost.UNITY.tableName") + " " +
                properties.getProperty("common.part2") + getSelscabFromRTest(properties.getProperty("SET_OF_SELSKAB.sql")) +") or dato < (" +
                properties.getProperty("common.part3") +          getDWH_START_DATE_RTEST(properties.getProperty("DWH_START_DATE.sql")) +
                properties.getProperty("common.part4") + ")q";
        int countRowInMSCRUS = getCountRowInSA(queryMSCRUS);
        int countRowInUNITY = getCountRowInSA(queryUNITY);
        int countRowByCondition = countRowInMSCRUS + countRowInUNITY;
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse() throws SQLException, IOException {
        getPropertiesFile();
        String queryMSCRUS = properties.getProperty("common.part1") + " " + properties.getProperty("adresse.MSCRUS.tableName") + " " +
                properties.getProperty("common.part2") + getSelscabFromRTest(properties.getProperty("SET_OF_SELSKAB.sql")) +"))q";
        String queryUNITY = properties.getProperty("common.part1") + " " + properties.getProperty("adresse.UNITY.tableName") + " " +
                properties.getProperty("common.part2") + getSelscabFromRTest(properties.getProperty("SET_OF_SELSKAB.sql")) +"))q";
        System.out.println(queryMSCRUS);
        int countRowInMSCRUS = getCountRowInSA(queryMSCRUS);
        int countRowInUNITY = getCountRowInSA(queryUNITY);
        int countRowByCondition = countRowInMSCRUS + countRowInUNITY;
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);

    }

    @Test(enabled = true)
    public void BogfTrans() throws SQLException, IOException, ParseException {
        getPropertiesFile();
        String queryMSCRUS = properties.getProperty("common.part1") + " " + properties.getProperty("bogfTrans.MSCRUS.tableName") + " " +
                properties.getProperty("common.part2") + getSelscabFromRTest(properties.getProperty("SET_OF_SELSKAB.sql")) +") or dato < (" +
                properties.getProperty("common.part3") +          getDWH_START_DATE_RTEST(properties.getProperty("DWH_START_DATE.sql")) +
                properties.getProperty("common.part4") + ")q";
        String queryUNITY = properties.getProperty("common.part1") + " " + properties.getProperty("bogfTrans.UNITY.tableName") + " " +
                properties.getProperty("common.part2") + getSelscabFromRTest(properties.getProperty("SET_OF_SELSKAB.sql")) +") or dato < (" +
                properties.getProperty("common.part3") +          getDWH_START_DATE_RTEST(properties.getProperty("DWH_START_DATE.sql")) +
                properties.getProperty("common.part4") + ")q";
        int countRowInMSCRUS = getCountRowInSA(queryMSCRUS);
        int countRowInUNITY = getCountRowInSA(queryUNITY);
        int countRowByCondition = countRowInMSCRUS + countRowInUNITY;
        int countRowInSA = getCountRowInSA(properties.getProperty("bogfTrans.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("book.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("book.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDetails.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDryPort.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookEvent() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookEvent.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookFak.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookGods() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookGods.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookGods.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookKor() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookKor.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookKor.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookLin.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifests() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookManifests.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifests.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftFile.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftRemarks() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftRemarks.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftRemarks.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookVessel.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContHolliday() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("contHolliday.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contHolliday.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("contType.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contType.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void EdiKonv() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCommonCondition = getCountRowInSA(properties.getProperty("ediKonv.union.counts"));
        int countRowBySelskab20Condition = getCountRowInSA(properties.getProperty("ediKonv.selskab20.counts"));
        int countRowByCondition = countRowByCommonCondition - countRowBySelskab20Condition;
        int countRowInSA = getCountRowInSA(properties.getProperty("ediKonv.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ExpVessels() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("expVessels.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("expVessels.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void FaktPost() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("faktPost.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("faktPost.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Henvis() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCommonCondition = getCountRowInSA(properties.getProperty("henvis.union.counts"));
        int countRowBySelskab20Condition = getCountRowInSA(properties.getProperty("henvis.selskab20.counts"));
        int countRowByCondition = countRowByCommonCondition - countRowBySelskab20Condition;
        int countRowInSA = getCountRowInSA(properties.getProperty("henvis.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Kunde() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("kunde.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("kunde.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Ordre() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("ordre.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("ordre.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void OrdreLin() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("ordreLin.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("ordreLin.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("sag.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SagKurs() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("sagKurs.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("sagKurs.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ShipKurs() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("shipKurs.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("shipKurs.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Selskab() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("selskab.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("selskab.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetailsMof() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDetailsMof.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetailsMof.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void GetCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("getCharges.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("getCharges.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Service() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("service.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("service.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Demurrage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("demurrage.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("demurrage.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContBev() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("contBev.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contBev.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRules() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("contRules.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contRules.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void CtsContEvent() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("ctsContEvent.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("ctsContEvent.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookBemInternal() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookBemInternal.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookBemInternal.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Kontor() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("kontor.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("kontor.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Commodity() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("commodity.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("commodity.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Vgm() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("vgm.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("vgm.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DiscardAgency.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowInSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        int countRowSA = 0;
        while (rsFromSA.next()) {
            countRowSA = Integer.parseInt(rsFromSA.getString("c"));
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return countRowSA;
    }

    private String getDWH_START_DATE_RTEST(String sql) throws SQLException, ParseException {
        Connection connectionToRTest = db.connToRTest();
        Statement statmentForRTest = db.stFromConnection(connectionToRTest);
        ResultSet rsFromRTest = db.rsFromDB(statmentForRTest, sql);
        String c_val = null;
        while (rsFromRTest.next()) {
            c_val = rsFromRTest.getString("c_val");
        }
        db.closeConnecions(rsFromRTest, statmentForRTest, connectionToRTest);
        return c_val;
    }

    private String getDWH_START_DATE_ITEST(String sql) throws SQLException, ParseException {
        Connection connectionToITest = db.connToITest();
        Statement statmentForITest = db.stFromConnection(connectionToITest);
        ResultSet rsFromITest = db.rsFromDB(statmentForITest, sql);
        String c_val = null;
        while (rsFromITest.next()) {
            c_val = rsFromITest.getString("c_val");
        }
        db.closeConnecions(rsFromITest, statmentForITest, connectionToITest);
        return c_val;
    }

    public String getSelscabFromRTest(String sql) throws SQLException {
        List<String> MSCRUSselskabPool = new ArrayList<String>();
        Connection connectionToRTest = db.connToRTest();
        Statement statmentForRTest = db.stFromConnection(connectionToRTest);
        ResultSet rsCountRowFromRTest = db.rsFromDB(statmentForRTest, sql);
        while (rsCountRowFromRTest.next()) {
            MSCRUSselskabPool.add(String.valueOf((rsCountRowFromRTest.getInt("SELSKAB"))));
        }
        rsCountRowFromRTest.close();
        statmentForRTest.close();
        connectionToRTest.close();
        String selscab = String.join(", ", MSCRUSselskabPool);
        return selscab;
    }

    public String getSelscabFromITest(String sql) throws SQLException {
        List<String> UNITYselskabPool = new ArrayList<String>();
        Connection connectionToITest = db.connToITest();
        Statement statmentForITest = db.stFromConnection(connectionToITest);
        ResultSet rsCountRowFromITest = db.rsFromDB(statmentForITest, sql);
        while (rsCountRowFromITest.next()) {
            UNITYselskabPool.add(String.valueOf((rsCountRowFromITest.getInt("SELSKAB"))));
        }
        rsCountRowFromITest.close();
        statmentForITest.close();
        connectionToITest.close();
        String selscab = String.join(", ", UNITYselskabPool);
        return selscab;
    }
}

