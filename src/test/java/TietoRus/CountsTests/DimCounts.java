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

public class DimCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();



    @Test(enabled = true)
    public void dimCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("customers.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("customers.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimFileLiner() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("fileLiner.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("fileLiner.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimControllingOffice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("controllingOffice.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("controllingOffice.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("vesselRegistry.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("vesselRegistry.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("bookingManifest.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingManifest.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("country.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("country.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimGvaTrade() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("gvaTrade.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("gvaTrade.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimOvTradeName() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("ovTradeName.union.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("ovTradeName.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }



    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/dimsCountsSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowInDM(String saSQL) throws SQLException {
        Connection connectionToDM = db.connToDM();
        Statement stForDM = db.stFromConnection(connectionToDM);
        ResultSet rsFromDM = db.rsFromDB(stForDM, saSQL);
        int countRowSA = 0;
        while (rsFromDM.next()) {
            countRowSA = Integer.parseInt(rsFromDM.getString("c"));
        }
        db.closeConnecions(rsFromDM, stForDM, connectionToDM);
        return countRowSA;
    }

    public int getCountRowInDV(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        int countRowHub = 0;
        while (rsFromDWH.next()) {
            countRowHub = Integer.parseInt(rsFromDWH.getString("c"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return countRowHub;
    }


}



