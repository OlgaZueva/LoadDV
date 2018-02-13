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
        // тест пригоден к использованию в текущем варианте (версия 1)
        int countRowInDV = getCountRowInDV(properties.getProperty("customers.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("customers.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimFileLiner() throws SQLException, IOException {
        // тест пригоден к использованию в текущем варианте (версия 1)
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("fileLiner.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("fileLiner.dim.CountRows"));
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
    public void dimBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("bookingCargo.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingCargo.dim.CountRows"));
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

    @Test(enabled = true)
    public void dimCompanyLocation() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("companyLocation.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("companyLocation.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("bookingManifest.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingManifest.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimContainerType() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("containerType.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("containerType.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("bookingHaulageDetails.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingHaulageDetails.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimCompanyRegion() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("companyRegion.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("companyRegion.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimControllingOffice() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("controllingOffice.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("controllingOffice.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimLocationDestinationRegion() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("locationDestinationRegion.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("locationDestinationRegion.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimLocationRegion() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("locationRegion.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("locationRegion.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }


    @Test(enabled = true)
    public void dimOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("oceanVesselService.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("oceanVesselService.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("oceanVesselStatus.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("oceanVesselStatus.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimTradeForEmedStat() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("tradeForEmedStat.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("tradeForEmedStat.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimOvTradeName() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("ovTradeName.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("ovTradeName.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimOvTradeNumber() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("ovTradeNumber.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("ovTradeNumber.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = false)
    public void dimLocations() throws SQLException, IOException {
        getPropertiesFile();
        /*
        Контрольный запрос учитывает validFrom и выбирает саты и статусы соотвественно. Плюс, процедура getDimLocations вне зависимости от результата
трех внешних соединений для  dwhIdHubLocations берет validFrom из satLocationsPortsOverview. Что порождает лишние записи Это решили оставить - не мешает.
Контрольный запрос это учитывает (последний UNION в контрольном запросе). Но записи из satLocationsPortsOverview множатся из-за привязки к satLocations
(т.е. если в satLocations более одной записи, различающиеся одним из загружаемых в DM полей, то завпрос записей в satLocationsPortsOverview вернет столько же.
Это не победила (О.Зуева) , надо смотреть галазами что не так при падении теста. )
         */
        System.out.println("dimLocations_matchData. В случае падения теста см комментарий к нему");
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("locations.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("locations.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimGvaTrade() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("gvaTrade.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("gvaTrade.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("transportMode.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("transportMode.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }


    @Test(enabled = true)
    public void dimVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("vesselRegistry.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("vesselRegistry.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = false)
    public void dimBooking() throws SQLException, IOException {
        /*
        02.2018. Контрольный запрос этого теста переработан- count считается по всем нужным таблицам без "лишних"  полей
        И в тесте нужно использовать именно booking.dwh.CountRows -запрос
         */
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("booking.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("booking.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
       }

    @Test(enabled = true)
    public void dimBookingOceanVessel() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("bookingOceanVessel.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("bookingOceanVessel.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dimExportVessels() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("exportVessels.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInDim = getCountRowInDM(properties.getProperty("exportVessels.dim.CountRows"));
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



