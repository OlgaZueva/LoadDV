package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.GetDataHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HubsCounts {
    private Properties properties = new Properties();
    private GetDataHelper dh = new GetDataHelper();

    @Test(enabled = true)
    public void hubPaymentsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("payments.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("payments.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("payments.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubPaymentsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("payments.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("payments.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCustomersDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("customers.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("customers.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("customers.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCustomersDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("customers.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubAccountingTransactionDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("accountingTransaction.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("accountingTransaction.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("accountingTransaction.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubAccountingTransactionDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("accountingTransaction.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("accountingTransaction.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("booking.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("booking.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("booking.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("booking.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestAdditionalsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingManifestAdditionals.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingManifestAdditionals.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingManifestAdditionals.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingManifestAdditionalsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingManifestAdditionals.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEventsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingEvents.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingEvents.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingEvents.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingEventsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingEvents.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingEvents.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingCharges.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingCharges.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingCharges.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingChargesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingCharges.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingCharges.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingCargoDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingCargo.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingCargo.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingCargo.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingCargoDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingCargo.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingCargo.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubBookingHaulageDetailsoDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingHaulageDetails.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingHaulageDetails.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingHaulageDetails.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingHaulageDetailsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingHaulageDetails.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingHaulageDetails.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargeLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingChargeLines.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingChargeLines.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingChargeLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingChargeLinesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingChargeLines.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    @Test(enabled = true)
    public void hubBookingManifestDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingManifest.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingManifest.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingManifest.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingManifestDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingManifest.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

     @Test(enabled = true)
    public void hubBookingDTXFileDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingDTXFile.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingDTXFile.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingDTXFile.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingDTXFileDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingDTXFile.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingDTXFile.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarksDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingEMCRemarks.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingEMCRemarks.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingEMCRemarks.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarksDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingEMCRemarks.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingEMCRemarks.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingOceanVesselDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("bookingOceanVessel.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("bookingOceanVessel.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingOceanVessel.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingOceanVesselDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingOceanVessel.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubWeekendsHolidaysDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("weekendsHolidays.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("weekendsHolidays.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("weekendsHolidays.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubWeekendsHolidaysDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("weekendsHolidays.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("weekendsHolidays.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("controllingOffice.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("controllingOffice.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("controllingOffice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("controllingOffice.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("controllingOffice.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("controllingOffice.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("controllingOffice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("controllingOffice.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerTypeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("containerType.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("containerType.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("containerType.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerTypeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("containerType.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerMovesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("containerMoves.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("containerMoves.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("containerMoves.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerMovesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("containerMoves.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubExportVesselsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("exportVessels.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("exportVessels.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("exportVessels.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubExportVesselsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("exportVessels.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoicePostingDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("invoicePosting.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("invoicePosting.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("invoicePosting.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoicePostingDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("invoicePosting.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCustomersDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("customers.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("customers.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("customers.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCustomersDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("customers.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("invoice.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("invoice.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("invoice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoiceDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("invoice.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("invoice.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("invoiceLines.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("invoiceLines.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("invoiceLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoiceLinesDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("invoiceLines.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("invoiceLines.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileLinerDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("fileLiner.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("fileLiner.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("fileLiner.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubFileLinerDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("fileLiner.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("fileLiner.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileROEDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("fileROE.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("fileROE.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("fileROE.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubFileROEDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("fileROE.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("fileROE.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubDailyRoeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("dailyRoe.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("dailyRoe.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("dailyRoe.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubDailyRoeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("dailyRoe.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("dailyRoe.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCompanyDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("company.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("company.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("company.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCompanyDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("company.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("company.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerTypeSpecEquipDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = dh.getCountRowInSA(properties.getProperty("containerTypeSpecEquip.EXCEL.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("containerTypeSpecEquip.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerTypeSpecEquipDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("containerTypeSpecEquip.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubLocationsPortsOverviewDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = dh.getCountRowInSA(properties.getProperty("locationsPortsOverview.EXCEL.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("locationsPortsOverview.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubLocationsPortsOverviewEquipDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("locationsPortsOverview.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("locationsPortsOverview.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomerDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = dh.getCountRowInSA(properties.getProperty("bookingReportingCustomer.EXCEL.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("bookingReportingCustomer.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomerDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("bookingReportingCustomer.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("bookingReportingCustomer.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = dh.getCountRowInSA(properties.getProperty("controllingOffice.EXCEL.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("controllingOffice.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubControllingOfficeDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("controllingOffice.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/hubsCount.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }
}
