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

    @Test(enabled = true)
    public void hubBookingEventsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingEvents.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEvents.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargesCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingCharges.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCharges.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingCargoCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingCargo.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargo.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingHaulageDetailsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingHaulageDetails.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargeLinesCDC () throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingChargeLines.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingChargeLines.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingManifest.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifest.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingDTXFileCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingDTXFile.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingDTXFile.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarksCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingEMCRemarks.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingOceanVesselCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingOceanVessel.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingOceanVessel.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubWeekendsHolidaysCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("weekendsHolidays.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("weekendsHolidays.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubControllingOfficeCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("controllingOffice.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOffice.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerTypeCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerType.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerType.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerMovesCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerMoves.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoves.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubExportVesselsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("exportVessels.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("exportVessels.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoicePostingCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("invoicePosting.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoicePosting.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("invoice.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoice.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceLinesCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("invoiceLines.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoiceLines.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileLinerCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("fileLiner.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileLiner.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileROECDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("fileROE.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileROE.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubDailyRoeCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("dailyRoe.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("dailyRoe.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCompanyCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("company.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("company.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubOceanVesselServiceCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("oceanVesselService.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselService.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubOceanVesselStatusCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("oceanVesselStatus.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselStatus.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubImsChargeLinesCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("imsChargeLines.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("imsChargeLines.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSpecialContractTypesCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("specialContractTypes.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("specialContractTypes.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCorrectorRemarkTypesCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("correctorRemarkTypes.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSublocationCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("sublocation.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("sublocation.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubTransportModeCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("transportMode.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("transportMode.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerLocationCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerLocation.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerLocation.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubVesselRegistryCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("vesselRegistry.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("vesselRegistry.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCountryCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("country.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("country.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubLocationsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("locations.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("locations.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCurrencyCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("currency.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("currency.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubShipItConstantsCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("shipItConstants.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("shipItConstants.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestedHaulageCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubIsoCodeCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct =  getCountRowInSA(properties.getProperty("isoCode.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("isoCode.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerTypeSpecEquipCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubLocationsPortsOverviewCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("locationsPortsOverview.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("locationsPortsOverview.CDCHUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomerCDC() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingReportingCustomer.CDCViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.CDCHUB.CountRows"));
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
