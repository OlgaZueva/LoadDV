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

public class HubsCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

        /*-------------------------------------------------------------
   Блок для таблиц, которые перезагружаются полностью, нет изменений из CDC, поэтому тут действует правило: сколько хабов - столько сатов и сат статусов.
   При загрузке сатов существующая запись в рамках ключа хаба удаляется и вставляется новая.
    *///-----------------------------------------------------------
    @Test(enabled = false)
    public void hubWeekendsHolidaysDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("weekendsHolidays.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("weekendsHolidays.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("weekendsHolidays.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubWeekendsHolidays() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Могут быть нюансы если хаб был создан, потом запись физически удалили из источника и загрузили изменения");
        System.out.println("В данном случае перегрузили всю таблицу");
        System.out.println("В этом случае хабо будет больше, чем расчетное число, потому как контрольный запрос идет на SA-таблицы");
        int countRowInSA = getCountRowInSA(properties.getProperty("weekendsHolidays.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("weekendsHolidays.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubControllingOfficeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("controllingOffice.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("controllingOffice.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("controllingOffice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubControllingOffice() throws SQLException, IOException {
        System.out.println("Могут быть нюансы если хаб был создан, потом запись физически удалили из источника и загрузили изменения");
        System.out.println("В данном случае перегрузили всю таблицу");
        System.out.println("В этом случае хабо будет больше, чем расчетное число, потому как контрольный запрос идет на SA-таблицы");
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("controllingOffice.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOffice.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubContainerTypeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("containerType.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("containerType.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerType.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerType() throws SQLException, IOException {
        System.out.println("Могут быть нюансы если хаб был создан, потом запись физически удалили из источника и загрузили изменения");
        System.out.println("В данном случае перегрузили всю таблицу");
        System.out.println("В этом случае хабо будет больше, чем расчетное число, потому как контрольный запрос идет на SA-таблицы");
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerType.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerType.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }


    @Test(enabled = false)
    public void hubCompanyDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("company.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("company.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("company.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCompany() throws SQLException, IOException {
        System.out.println("Могут быть нюансы если хаб был создан, потом запись физически удалили из источника и загрузили изменения");
        System.out.println("В данном случае перегрузили всю таблицу");
        System.out.println("В этом случае хабо будет больше, чем расчетное число, потому как контрольный запрос идет на SA-таблицы");
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("company.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("company.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubShipItConstantsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("shipItConstants.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("shipItConstants.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("shipItConstants.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubShipItConstants() throws SQLException, IOException {
        System.out.println("Могут быть нюансы если хаб был создан, потом запись физически удалили из источника и загрузили изменения");
        System.out.println("В данном случае перегрузили всю таблицу");
        System.out.println("В этом случае хабо будет больше, чем расчетное число, потому как контрольный запрос идет на SA-таблицы");
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("shipItConstants.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("shipItConstants.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    /*-------------------------------------------------------------
    Конец блока для таблиц, которые перезагружаются полностью.
*///-----------------------------------------------------------
    @Test(enabled = false)
    public void hubPaymentsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("payments.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("payments.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("payments.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubPayments() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("payments.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("payments.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInKundeInSA = getCountRowInSA(properties.getProperty("customersKunde.union.CountRows"));
        System.out.println("countRowInKundeInSA: " + countRowInKundeInSA);
        int countRowInAdresseInSA =  getCountRowInSA(properties.getProperty("customersAdresse.union.CountRows"));
        System.out.println("countRowInAdresseInSA:" + countRowInAdresseInSA);
        int countRowInSA = countRowInKundeInSA + countRowInAdresseInSA;
        int countRowInHub = getCountRowOfHub(properties.getProperty("customers.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubAccountingTransactionDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("accountingTransaction.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("accountingTransaction.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("accountingTransaction.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubAccountingTransaction() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("accountingTransaction.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("accountingTransaction.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("booking.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("booking.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("booking.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("booking.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("booking.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingManifestAdditionalsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

   @Test(enabled = true)
    public void hubBookingManifestAdditionals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingEventsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingEvents.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingEvents.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingEvents.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

   @Test(enabled = true)
    public void hubBookingEvents() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingEvents.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEvents.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingChargesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingCharges.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingCharges.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingCharges.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingCharges.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCharges.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingCargoDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingCargo.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingCargo.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingCargo.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingCargo.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargo.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingHaulageDetailsoDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingHaulageDetails.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingHaulageDetails.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingHaulageDetails.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingHaulageDetails.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingChargeLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingChargeLines.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingChargeLines.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingChargeLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingChargeLines.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingChargeLines.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingManifestDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingManifest.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingManifest.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingManifest.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingManifest.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifest.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingDTXFileDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingDTXFile.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingDTXFile.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingDTXFile.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingDTXFile() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingDTXFile.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingDTXFile.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingEMCRemarksDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingEMCRemarks.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingEMCRemarks.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingEMCRemarks.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarks() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingEMCRemarks.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingOceanVesselDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingOceanVessel.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingOceanVessel.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingOceanVessel.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingOceanVessel() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingOceanVessel.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingOceanVessel.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

     @Test(enabled = false)
    public void hubContainerMovesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("containerMoves.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("containerMoves.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerMoves.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerMoves() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerMoves.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoves.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubExportVesselsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("exportVessels.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("exportVessels.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("exportVessels.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubExportVessels() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("exportVessels.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("exportVessels.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubInvoicePostingDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("invoicePosting.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("invoicePosting.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("invoicePosting.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("invoicePosting.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoicePosting.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubInvoiceDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("invoice.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("invoice.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("invoice.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("invoice.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoice.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubInvoiceLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("invoiceLines.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("invoiceLines.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("invoiceLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubInvoiceLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("invoiceLines.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoiceLines.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubFileLinerDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("fileLiner.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("fileLiner.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("fileLiner.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubFileLiner() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("fileLiner.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileLiner.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubFileROEDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("fileROE.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("fileROE.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("fileROE.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubFileROE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("fileROE.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileROE.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubDailyRoeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("dailyRoe.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("dailyRoe.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("dailyRoe.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubDailyRoe() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("dailyRoe.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("dailyRoe.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubOceanVesselServiceDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("oceanVesselService.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("oceanVesselService.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("oceanVesselService.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("oceanVesselService.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselService.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubOceanVesselStatusDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("oceanVesselStatus.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("oceanVesselStatus.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("oceanVesselStatus.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("oceanVesselStatus.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselStatus.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubImsChargeLinesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("imsChargeLines.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("imsChargeLines.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("imsChargeLines.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubImsChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("imsChargeLines.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("imsChargeLines.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubSpecialContractTypesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("specialContractTypes.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("specialContractTypes.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("specialContractTypes.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubSpecialContractTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("specialContractTypes.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("specialContractTypes.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubCorrectorRemarkTypesDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("correctorRemarkTypes.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("correctorRemarkTypes.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("correctorRemarkTypes.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCorrectorRemarkTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("correctorRemarkTypes.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubSublocationDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("sublocation.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("sublocation.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("sublocation.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubSublocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("sublocation.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("sublocation.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubTransportModeDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("transportMode.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("transportMode.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("transportMode.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("transportMode.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("transportMode.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubContainerLocationDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("containerLocation.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("containerLocation.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerLocation.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerLocation.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerLocation.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubVesselRegistryDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("vesselRegistry.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("vesselRegistry.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("vesselRegistry.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("vesselRegistry.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("vesselRegistry.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubCountryDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("country.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("country.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("country.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("country.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("country.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubLocationsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("locations.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("locations.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("locations.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("locations.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("locations.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubCurrencyDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("currency.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("currency.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("currency.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("currency.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("currency.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

     @Test(enabled = false)
    public void hubBookingManifestedHaulageFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.MSCRUS.CountRows"));
        int countRowInUNITY = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.UNITY.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

     @Test(enabled = true)
    public void hubBookingManifestedHaulage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
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
    public void hubIsoCodeData() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("isoCode.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("isoCode.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubContainerTypeSpecEquipDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.EXCEL.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubContainerTypeSpecEquip() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerTypeSpecEquip.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubLocationsPortsOverviewDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = getCountRowInSA(properties.getProperty("locationsPortsOverview.EXCEL.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("locationsPortsOverview.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubLocationsPortsOverview() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("locationsPortsOverview.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("locationsPortsOverview.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)
    public void hubBookingReportingCustomerDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInExcel = getCountRowInSA(properties.getProperty("bookingReportingCustomer.EXCEL.CountRows"));
        int countRowInView = getCountRowInSA(properties.getProperty("bookingReportingCustomer.View.CountRows"));
        assertRowCount(countRowInExcel, countRowInView);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingReportingCustomer.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
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
