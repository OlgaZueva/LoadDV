package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.helpers.GetDataHelper;
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

public class HubsCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

    @Test(enabled = true)
    public void hubPaymentsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("payments.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("payments.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("payments.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubPaymentsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("payments.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("payments.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCustomersAdresseDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("customersAdresse.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("customersAdresse.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("customersAdresse.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCustomersAdresseDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("customersAdresse.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("customersAdresse.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubAccountingTransactionDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("accountingTransaction.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("accountingTransaction.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("accountingTransaction.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubAccountingTransactionDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("accountingTransaction.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("accountingTransaction.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("booking.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("booking.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("booking.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("booking.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestAdditionalsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingManifestAdditionalsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEventsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingEvents.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingEvents.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingEvents.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingEventsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingEvents.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEvents.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingCharges.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingCharges.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingCharges.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingChargesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingCharges.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCharges.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingCargoDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingCargo.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingCargo.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingCargo.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingCargoDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingCargo.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargo.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubBookingHaulageDetailsoDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingHaulageDetails.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingHaulageDetails.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingHaulageDetails.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingHaulageDetailsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingHaulageDetails.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargeLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingChargeLines.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingChargeLines.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingChargeLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingChargeLinesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingChargeLines.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubBookingManifestDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingManifest.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingManifest.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingManifest.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingManifestDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingManifest.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingDTXFileDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingDTXFile.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingDTXFile.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingDTXFile.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingDTXFileDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingDTXFile.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingDTXFile.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarksDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingEMCRemarks.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingEMCRemarks.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingEMCRemarks.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarksDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingEMCRemarks.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingOceanVesselDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingOceanVessel.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingOceanVessel.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingOceanVessel.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingOceanVesselDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingOceanVessel.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubWeekendsHolidaysDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("weekendsHolidays.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("weekendsHolidays.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("weekendsHolidays.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubWeekendsHolidaysDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("weekendsHolidays.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("weekendsHolidays.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("controllingOffice.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("controllingOffice.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("controllingOffice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("controllingOffice.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerTypeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("containerType.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("containerType.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerType.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerTypeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerType.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerMovesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("containerMoves.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("containerMoves.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerMoves.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerMovesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerMoves.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubExportVesselsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("exportVessels.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("exportVessels.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("exportVessels.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubExportVesselsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("exportVessels.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoicePostingDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("invoicePosting.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("invoicePosting.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("invoicePosting.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoicePostingDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("invoicePosting.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCustomersKundeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("customersKunde.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("customersKunde.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("customersKunde.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCustomersKundeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("customersKunde.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("customersKunde.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("invoice.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("invoice.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("invoice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoiceDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("invoice.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoice.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("invoiceLines.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("invoiceLines.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("invoiceLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoiceLinesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("invoiceLines.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoiceLines.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileLinerDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("fileLiner.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("fileLiner.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("fileLiner.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubFileLinerDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("fileLiner.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileLiner.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileROEDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("fileROE.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("fileROE.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("fileROE.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubFileROEDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("fileROE.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileROE.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubDailyRoeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("dailyRoe.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("dailyRoe.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("dailyRoe.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubDailyRoeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("dailyRoe.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("dailyRoe.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCompanyDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("company.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("company.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("company.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCompanyDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("company.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("company.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubOceanVesselServiceDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("oceanVesselService.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("oceanVesselService.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("oceanVesselService.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubOceanVesselServiceDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("oceanVesselService.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselService.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubOceanVesselStatusDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("oceanVesselStatus.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("oceanVesselStatus.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("oceanVesselStatus.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubOceanVesselStatusDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("oceanVesselStatus.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselStatus.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubImsChargeLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("imsChargeLines.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("imsChargeLines.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("imsChargeLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubImsChargeLinesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("imsChargeLines.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("imsChargeLines.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSpecialContractTypesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("specialContractTypes.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("specialContractTypes.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("specialContractTypes.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubSpecialContractTypesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("specialContractTypes.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("specialContractTypes.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCorrectorRemarkTypesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("correctorRemarkTypes.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("correctorRemarkTypes.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("correctorRemarkTypes.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCorrectorRemarkTypesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("correctorRemarkTypes.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSublocationDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("sublocation.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("sublocation.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("sublocation.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubSublocationDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("sublocation.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("sublocation.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubTransportModeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("transportMode.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("transportMode.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("transportMode.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubTransportModeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("transportMode.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("transportMode.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerLocationDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("containerLocation.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("containerLocation.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerLocation.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerLocationDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerLocation.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerLocation.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubVesselRegistryDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("vesselRegistry.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("vesselRegistry.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("vesselRegistry.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubVesselRegistryDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("vesselRegistry.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("vesselRegistry.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCountryDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("country.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("country.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("country.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCountryDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("country.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("country.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubLocationsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("locations.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("locations.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("locations.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubLocationsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("locations.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("locations.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCurrencyDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("currency.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("currency.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("currency.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCurrencyDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("currency.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("currency.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubShipItConstantsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("shipItConstants.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("shipItConstants.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("shipItConstants.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubShipItConstantsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("shipItConstants.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("shipItConstants.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestedHaulageFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingManifestedHaulageDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubIsoCodeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countIsoCode4RowInMSCRUS = getCountRowInSA(properties.getProperty("isoCode4.MSCRUS.CountRows"));
        int countIsoCode6RowInMSCRUS = getCountRowInSA(properties.getProperty("isoCode6.MSCRUS.CountRows"));
        int countIsoCode6_2RowInMSCRUS = getCountRowInSA(properties.getProperty("isoCode6_2.MSCRUS.CountRows"));
        int countIsoCode6_3RowInMSCRUS = getCountRowInSA(properties.getProperty("isoCode6_3.MSCRUS.CountRows"));
        int countIsoCode6_4RowInMSCRUS = getCountRowInSA(properties.getProperty("isoCode6_4.MSCRUS.CountRows"));
        int countIsoCode6_5RowInMSCRUS = getCountRowInSA(properties.getProperty("isoCode6_5.MSCRUS.CountRows"));
        int countRowInMSCRUS = (countIsoCode4RowInMSCRUS + countIsoCode6RowInMSCRUS + countIsoCode6_2RowInMSCRUS +
                countIsoCode6_3RowInMSCRUS + countIsoCode6_4RowInMSCRUS + countIsoCode6_5RowInMSCRUS);

        int countIsoCode4RowInUNITY = getCountRowInSA(properties.getProperty("isoCode4.UNITY.CountRows"));
        int countIsoCode6RowInUNITY = getCountRowInSA(properties.getProperty("isoCode6.UNITY.CountRows"));
        int countIsoCode6_2RowInUNITY = getCountRowInSA(properties.getProperty("isoCode6_2.UNITY.CountRows"));
        int countIsoCode6_3RowInUNITY = getCountRowInSA(properties.getProperty("isoCode6_3.UNITY.CountRows"));
        int countIsoCode6_4RowInUNITY = getCountRowInSA(properties.getProperty("isoCode6_4.UNITY.CountRows"));
        int countIsoCode6_5RowInUNITY = getCountRowInSA(properties.getProperty("isoCode6_5.UNITY.CountRows"));
        int countRowInUNITY = (countIsoCode4RowInUNITY + countIsoCode6RowInUNITY + countIsoCode6_2RowInUNITY +
                countIsoCode6_3RowInUNITY + countIsoCode6_4RowInUNITY + countIsoCode6_5RowInUNITY);

        int countIsoCode4RowInView = getCountRowInSA(properties.getProperty("isoCode4.View.CountRows"));
        int countIsoCode6RowInView = getCountRowInSA(properties.getProperty("isoCode6.View.CountRows"));
        int countIsoCode6_2RowInView = getCountRowInSA(properties.getProperty("isoCode6_2.View.CountRows"));
        int countIsoCode6_3RowInView = getCountRowInSA(properties.getProperty("isoCode6_3.View.CountRows"));
        int countIsoCode6_4RowInView = getCountRowInSA(properties.getProperty("isoCode6_4.View.CountRows"));
        int countIsoCode6_5RowInView = getCountRowInSA(properties.getProperty("isoCode6_5.View.CountRows"));
        int countRowInView = (countIsoCode4RowInView + countIsoCode6RowInView + countIsoCode6_2RowInView +
                countIsoCode6_3RowInView + countIsoCode6_4RowInView + countIsoCode6_5RowInView);
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubIsoCodeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct =  getCountRowInSA(properties.getProperty("isoCode.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("isoCode.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubContainerTypeSpecEquipDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.EXCEL.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerTypeSpecEquipDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubLocationsPortsOverviewDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = getCountRowInSA(properties.getProperty("locationsPortsOverview.EXCEL.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("locationsPortsOverview.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubLocationsPortsOverviewEquipDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("locationsPortsOverview.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("locationsPortsOverview.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomerDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = getCountRowInSA(properties.getProperty("bookingReportingCustomer.EXCEL.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingReportingCustomer.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomerDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = getCountRowInSA(properties.getProperty("bookingReportingCustomer.ViewDistinct.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }



    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/hubsCountsSQL.properties"))));
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
