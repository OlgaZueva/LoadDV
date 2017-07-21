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


public class DelSelskabCountsTests {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void Sag_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("sag.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("sag.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag_Del_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("sag.UNITY.counts"));
        int countRowInDelTable = getCountRowInSA(properties.getProperty("sag.UNITY.delTable.count"));
        assertRowCount(countRowByCondition, countRowInDelTable);
    }

    @Test(enabled = true)
    public void Sag_Del_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("sag.MSCRUS.counts"));
        int countRowInDelTable = getCountRowInSA(properties.getProperty("sag.MSCRUS.delTable.count"));
        assertRowCount(countRowByCondition, countRowInDelTable);
    }

    @Test(enabled = true)
    public void ContRep_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("contRep.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRep_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("contRep.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_book1Step = getCountRowInSA(properties.getProperty("book1Step.UNITY.counts"));
        int countRowByCondition_book2Step = getCountRowInSA(properties.getProperty("book2Step.UNITY.counts"));
        int countRowByCondition =  countRowByCondition_book1Step + countRowByCondition_book2Step;
        int countRowInSA = getCountRowInSA(properties.getProperty("book.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_book1Step = getCountRowInSA(properties.getProperty("book1Step.MSCRUS.counts"));
        int countRowByCondition_book2Step = getCountRowInSA(properties.getProperty("book2Step.MSCRUS.counts"));
        int countRowByCondition =  countRowByCondition_book1Step + countRowByCondition_book2Step;
        int countRowInSA = getCountRowInSA(properties.getProperty("book.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_Del_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_book1Step = getCountRowInSA(properties.getProperty("book1Step.UNITY.counts"));
        int countRowByCondition_book2Step = getCountRowInSA(properties.getProperty("book2Step.UNITY.counts"));
        int countRowByCondition =  countRowByCondition_book1Step + countRowByCondition_book2Step;
        int countRowInDelTable = getCountRowInSA(properties.getProperty("book.UNITY.delTable.count"));
        assertRowCount(countRowByCondition, countRowInDelTable);
    }

    @Test(enabled = true)
    public void Book_Del_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_book1Step = getCountRowInSA(properties.getProperty("book1Step.MSCRUS.counts"));
        int countRowByCondition_book2Step = getCountRowInSA(properties.getProperty("book2Step.MSCRUS.counts"));
        int countRowByCondition =  countRowByCondition_book1Step + countRowByCondition_book2Step;
        int countRowInDelTable = getCountRowInSA(properties.getProperty("book.MSCRUS.delTable.count"));
        assertRowCount(countRowByCondition, countRowInDelTable);
    }

    @Test(enabled = true)
    public void BookManifests_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookManifests.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifests.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifests_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookManifests.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifests.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifests_Del_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookManifests.UNITY.counts"));
        int countRowInDelTable = getCountRowInSA(properties.getProperty("bookManifests.UNITY.delTable.count"));
        assertRowCount(countRowByCondition, countRowInDelTable);
    }

    @Test(enabled = true)
    public void BookManifests_Del_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookManifests.MSCRUS.counts"));
        int countRowInDelTable = getCountRowInSA(properties.getProperty("bookManifests.MSCRUS.delTable.count"));
        assertRowCount(countRowByCondition, countRowInDelTable);
    }

    @Test(enabled = true)
    public void BookGods_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookGods.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookGods.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookGods_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookGods.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookGods.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookVessel.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookLin.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDetails.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftRemarks_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftRemarks.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftRemarks.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftRemarks_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftRemarks.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftRemarks.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("adresse.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("adresse.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookFak.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_Events_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_BOOK_NRisNotNull = getCountRowInSA(properties.getProperty("bookEvent.UNITY_BOOK_NRisNotNull.counts"));
        int countRowByCondition_BOOK_NRisNull = getCountRowInSA(properties.getProperty("bookEvent.UNITY_BOOK_NRisNull.counts"));
        int countRowByCondition = countRowByCondition_BOOK_NRisNotNull + countRowByCondition_BOOK_NRisNull;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_Events_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_BOOK_NRisNotNull = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS_BOOK_NRisNotNull.counts"));
        int countRowByCondition_BOOK_NRisNull = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS_BOOK_NRisNull.counts"));
        int countRowByCondition = countRowByCondition_BOOK_NRisNotNull + countRowByCondition_BOOK_NRisNull;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookKor_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookKor.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookKor.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookKor_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookKor.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookKor.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void BookMftFile_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DelSelskab_validAgency.properties"))));
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
