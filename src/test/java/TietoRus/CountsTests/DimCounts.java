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
        int countRowInDV = getCountRowInDV(properties.getProperty("ovTradeName.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("ovTradeName.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimOvTradeNumber() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("ovTradeNumber.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("ovTradeNumber.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("bookingCargo.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingCargo.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }


    @Test(enabled = true)
    /*
    В запросе (в процедуре) для определения tradeNameGvaForEmedStat (см спецификацию (DBMappinngDWHDataMart-v1.4P)) убрано условие: where tradeNameGvaForEmedStat is not null
    Это порождает 200 "лишних" записи в dimLocations, решили, что сбору отчета это не помешает и ко-лво незначительно - оставляем как есть.
            */
    public void dimLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("locations.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("locations.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("booking.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("booking.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimBookingOceanVessel() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("bookingOceanVessel.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingOceanVessel.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("oceanVesselService.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("oceanVesselService.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimContainerType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("containerType.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("containerType.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("company.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("company.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimTransshipmentPorts() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("transshipmentPorts.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("transshipmentPorts.dim.CountRows"));
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



