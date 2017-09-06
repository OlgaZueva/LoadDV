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
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Класс, содержащий тесты для проверки механизма "Секционирование".
Каждый тест "считает" кол-во записей, которые должны быть перемещены в партицию =1 (все, у которых StatusHub=1 and StatusSat =1 and StatusLnk =1,
исключение  -  таблицы saGetCharges -  там должны быть перемещены в партицию =1  запииь, у которых только StatusHub=1)
и сравнивает это кол-во с фактическим.
*/
public class PartitionTests {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void AbPost() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("AbPost");
        int countRowByCondition = getCountRowInSA(properties.getProperty("abPost.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adgang() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Adgang");
        int countRowByCondition = getCountRowInSA(properties.getProperty("adgang.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("adgang.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void AdgangLin() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("AdgangLin");
        int countRowByCondition = getCountRowInSA(properties.getProperty("adgangLin.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("adgangLin.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Adresse");
        int countRowByCondition = getCountRowInSA(properties.getProperty("adresse.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BogfTrans() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BogfTrans");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bogfTrans.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bogfTrans.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("AbPost");
        int countRowByCondition = getCountRowInSA(properties.getProperty("book.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("book.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookDetails");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDetails.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetailsMof() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookDetailsMof");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDetailsMof.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetailsMof.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookDryPort");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDryPort.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookEvent() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookEvent");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookEvent.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookFak");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookFak.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookGods() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookGods");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookGods.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookGods.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookKor() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookKor");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookKor.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookKor.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookLin");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookLin.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifests() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookManifests");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookManifests.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifests.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookMftFile");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftFile.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftRemarks() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookMftRemarks");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftRemarks.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftRemarks.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("BookVessel");
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookVessel.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContHolliday() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("ContHolliday");
        int countRowByCondition = getCountRowInSA(properties.getProperty("contHolliday.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contHolliday.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRep() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("ContRep");
        int countRowByCondition = getCountRowInSA(properties.getProperty("contRep.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ControlOffice() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("ControlOffice");
        int countRowByCondition = getCountRowInSA(properties.getProperty("controlOffice.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("controlOffice.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContType() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("ContType");
        int countRowByCondition = getCountRowInSA(properties.getProperty("contType.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contType.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void EdiKonv() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("EdiKonv");
        int countRowByConditionCommon = getCountRowInSA(properties.getProperty("ediKonv0.union.counts"));
        int countRowByCondition1 = getCountRowInSA(properties.getProperty("ediKonv1.union.counts"));
        int countRowByCondition2 = getCountRowInSA(properties.getProperty("ediKonv2.union.counts"));
        int countRowByCondition3 = getCountRowInSA(properties.getProperty("ediKonv3.union.counts"));
        int countRowByCondition4 = getCountRowInSA(properties.getProperty("ediKonv4.union.counts"));
        int countRowByCondition5 = getCountRowInSA(properties.getProperty("ediKonv5.union.counts"));
        int countRowByCondition6 = getCountRowInSA(properties.getProperty("ediKonv6.union.counts"));
        int countRowByCondition7 = getCountRowInSA(properties.getProperty("ediKonv7.union.counts"));
        int countRowByCondition = countRowByConditionCommon + countRowByCondition1
                + countRowByCondition2 + countRowByCondition3 + countRowByCondition4 + countRowByCondition5 + countRowByCondition6 + countRowByCondition7;
        int countRowInSA = getCountRowInSA(properties.getProperty("ediKonv.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ExpVessels() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("ExpVessels");
        int countRowByCondition = getCountRowInSA(properties.getProperty("expVessels.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("expVessels.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void FaktPost() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("FaktPost");
        int countRowByCondition = getCountRowInSA(properties.getProperty("faktPost.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("faktPost.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void GetCharges() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("GetCharges");
        int countRowByCondition = getCountRowInSA(properties.getProperty("getCharges.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("getCharges.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Henvis() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Henvis");
        int countRowByConditionCommon = getCountRowInSA(properties.getProperty("henvis0.union.counts"));
        int countRowByCondition1 = getCountRowInSA(properties.getProperty("henvis1.union.counts"));
        int countRowByCondition = countRowByConditionCommon + countRowByCondition1;
        int countRowInSA = getCountRowInSA(properties.getProperty("henvis.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Kunde() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Kunde");
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
        System.out.println("OrdreLin");
        int countRowByCondition = getCountRowInSA(properties.getProperty("ordreLin.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("ordreLin.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Sag");
        int countRowByCondition = getCountRowInSA(properties.getProperty("sag.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SagKurs() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("SagKurs");
        int countRowByCondition = getCountRowInSA(properties.getProperty("sagKurs.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("sagKurs.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Selskab() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Selskab");
        int countRowByCondition = getCountRowInSA(properties.getProperty("selskab.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("selskab.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ShipKurs() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("ShipKurs");
        int countRowByCondition = getCountRowInSA(properties.getProperty("shipKurs.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("shipKurs.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void UtsConstants() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("UtsConstants");
        int countRowByCondition = getCountRowInSA(properties.getProperty("utsConstants.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("utsConstants.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void EXCEL_CleanUp() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("EXCEL_CleanUp");
        int countRowByCondition = getCountRowInSA(properties.getProperty("EXCEL_CleanUp.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("EXCEL_CleanUp.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void EXCEL_CntrTypeSpecEquip() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("EXCEL_CntrTypeSpecEquip");
        int countRowByCondition = getCountRowInSA(properties.getProperty("EXCEL_CntrTypeSpecEquip.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("EXCEL_CntrTypeSpecEquip.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void EXCEL_ControllingOfficeLocationCode() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("EXCEL_ControllingOfficeLocationCode");
        int countRowByCondition = getCountRowInSA(properties.getProperty("EXCEL_ControllingOfficeLocationCode.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("EXCEL_ControllingOfficeLocationCode.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void EXCEL_PortsOverview() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("EXCEL_PortsOverview");
        int countRowByCondition = getCountRowInSA(properties.getProperty("EXCEL_PortsOverview.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("EXCEL_PortsOverview.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/Partition.properties"))));
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
}
