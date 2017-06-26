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


public class HubsCDCCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

    @Test(enabled = true)
    public void hubPaymentsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("payments.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("payments.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCustomersCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInKundeViewDistinct = getCountRowInSA(properties.getProperty("customersKunde.CDCViewDistinct.CountRows"));
        int countRowInAdresseViewDistinct =  getCountRowInSA(properties.getProperty("customersAdresse.CDCViewDistinct.CountRows"));
        int countRowKundeInHub = getCountRowOfHub(properties.getProperty("customersKunde.CDCHUB.CountRows"));
        int countRowAdresseInHub = getCountRowOfHub(properties.getProperty("customersAdresse.CDCHUB.CountRows"));
        assertRowCount(countRowInKundeViewDistinct, countRowKundeInHub);
        assertRowCount(countRowInAdresseViewDistinct, countRowAdresseInHub);
    }

    @Test(enabled = true)
    public void hubAccountingTransactionCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("accountingTransaction.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("accountingTransaction.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingDataCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("booking.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("booking.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestAdditionalsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

























    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/hubsCDCCountsSQL.properties"))));
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

    public int getCountRowOfHub(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        int countRowHub = 0;
        while (rsFromDWH.next()) {
            countRowHub = Integer.parseInt(rsFromDWH.getString("c"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        //System.out.println("countRowHub in DWH: " + countRowHub);
        return countRowHub;
    }
}
