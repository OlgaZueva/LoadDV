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

public class LinksCounts {

    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

    @Test(enabled = true)
    public void BogfTrans_lnkAccountingTransactionBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionBooking.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionBooking.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BogfTrans_satLnkAccountingTransactionBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionBooking.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionBooking.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BogfTrans_lnkAccountingTransactionCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCurrency.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCurrency.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BogfTrans_satLnkAccountingTransactionCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCurrency.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCurrency.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BogfTrans_lnkAccountingTransactionCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BogfTrans_satLnkAccountingTransactionCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void AbPost_lnkPaymentsInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkPaymentsInvoicePosting.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkPaymentsInvoicePosting.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void AbPost_satLnkPaymentsInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkPaymentsInvoicePosting.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkPaymentsInvoicePosting.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void AbPost_lnkPaymentsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkPaymentsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkPaymentsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void AbPost_satLnkPaymentsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkPaymentsCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkPaymentsCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Adresse_lnkCustomersCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkCustomersCountry.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCountry.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Adresse_satLnkCustomersCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCountry.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCustomersCountry.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Adresse_lnkCustomersLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByConditionAdresse = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.conditionAdresse.CountRows"));
        System.out.println( "countRowByConditionAdresse: " + countRowByConditionAdresse);
        int countRowByConditionKunde = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.conditionKunde.CountRows"));
        System.out.println("countRowByConditionKunde: " + countRowByConditionKunde);
        int countRowByCondition = countRowByConditionAdresse + countRowByConditionKunde;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Adresse_satLnkCustomersLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Adresse_lnkCustomersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByConditionAdresse = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.conditionAdresse.CountRows"));
        System.out.println( "countRowByConditionAdresse: " + countRowByConditionAdresse);
        int countRowByConditionKunde = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.conditionKunde.CountRows"));
        System.out.println("countRowByConditionKunde: " + countRowByConditionKunde);
        int countRowByCondition = countRowByConditionAdresse +countRowByConditionKunde;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Adresse_satLnkCustomersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingLnkBookingBooking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void Book_lnkBookingBookingReportingCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingLnkBookingContainerLocation.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingContainerLocation.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingControllingOffice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingLnkBookingControllingOffice.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingControllingOffice.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCountry.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCrossBookingType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingFileLiner() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingFileLiner.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingImportExport() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingImportExport.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingLocations.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingPPCCEE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingPPCCEE.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetails_lnkBookingBookingManifestAdditionals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetails_satLnkBookingBookingManifestAdditionals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetails_lnkBookingManifestAdditionalsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetails_satLnkBookingManifestAdditionalsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_lnkBookingBookingManifestedHaulage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_satLnkBookingBookingManifestedHaulage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_lnkBookingManifestedHaulageTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_satLnkBookingManifestedHaulageTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_lnkBookingManifestedHaulageCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_satLnkBookingManifestedHaulageCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageCompany.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void lnkBookingLocations() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Внимание! Линк один на две таблицы (BookDryPort и Book). Тут надо руками проверить условия");
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingLocations.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookEvent_lnkBookingBookingEvents() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingEvents.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingEvents.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookEvent_satLnkBookingBookingEvents() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingEvents.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingEvents.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookEvent_lnkBookingEventsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingEventsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEventsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookEvent_satLnkBookingEventsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingEventsCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEventsCompany.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookFak_lnkBookingBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingCharges.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingCharges.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookFak_satLnkBookingBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingCharges.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingCharges.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookFak_lnkBookingChargesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingChargesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookFak_satLnkBookingChargesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingChargesCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargesCompany.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingCargo.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingCargo.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingBookingCargo.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingCargo.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoBookingHaulageDetails.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoBookingHaulageDetails.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoBookingHaulageDetails.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoBookingHaulageDetails.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoContainerType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerType.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerType.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoContainerType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerType.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerType.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoFullEmpty() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA1 = getCountRowInSA(properties.getProperty("lnkBookingCargoFullEmptyE.condition.CountRows"));
        System.out.println("lnkBookingCargoFullEmptyE: " + countRowInSA1);
        int countRowInSA2 = getCountRowInSA(properties.getProperty("lnkBookingCargoFullEmptyF.condition.CountRows"));
        System.out.println("lnkBookingCargoFullEmptyF: " + countRowInSA2);
        int couRowInSAbyCondition = (countRowInSA1 + countRowInSA2);
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoFullEmpty.lnk.CountRows"));
        assertRowCount(couRowInSAbyCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satlnkBookingCargoFullEmpty() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoFullEmpty.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoFullEmpty.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoLocations.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoLocations.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoLocations.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoLocations.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoCompany.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookKor_lnkBookingHaulageDetailsCustomers() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В документе в кач-ве hub1 указано hubCustomers, в реализации hubBookingHaulageDetails. Разобраться как правильно");
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCustomers.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookKor_lnkBookingHaulageDetailsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookLin_lnkBookingChargeLinesCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCurrency.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookLin_lnkBookingChargesBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargesBookingChargeLines.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookLin_lnkBookingChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifest.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingBookingManifestShadowCopy() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingManifestLnkBookingBookingManifestShadowCopy.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestShadowCopy.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingManifestBookingDTXFile() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestBookingDTXFile.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingManifestCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookMftFile_lnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingDTXFile.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void BookMftRemarks_lnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileBookingEMCRemarks.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_lnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingOceanVesselExportVessels() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselExportVessels.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingOceanVesselOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselOceanVesselStatus.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingOceanVesselCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ContHolliday_lnkWeekendsHolidaysCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("weekendsHolidays.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ContRep_lnkContainerMovesBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesBooking.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ContRep_lnkContainerMovesContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerLocation.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void ContRep_lnkContainerMovesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void ControllingOfficeLocationCode_lnkControllingOfficeLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkControllingOfficeLocations.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void ContType_lnkControllingOfficeLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeContainerTypeSpecEquip.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ContType_lnkContainerTypeIsoCode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ContType_lnkIsoCodeCompany() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("линк специфичен, проверить руками");
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("isoCode.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ContType_lnkContainerTypeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkOceanVesselServiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("oceanVesselService.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkImsChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("imsChargeLines.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkSpecialContractTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("specialContractTypes.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkSublocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("sublocation.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkTransportModeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("transportMode.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkCorrectorRemarkTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsLocations.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsOceanVesselService.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Нетипичные условия для выборки. Проверить руками");
        int couRowInSAbyCondition = getCountRowInSA(properties.getProperty("exportVessels.view.ConditionCounts"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.lnk.CountRows"));
        assertRowCount(couRowInSAbyCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoicePostingAccountingTransaction() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoicePostingCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCurrency.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoicePostingCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkLocationsCountry() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Могут быть варианты с null'ом в полях условий. Рроверить руками дополнительно");
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("locations.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCountry.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkLocationsLocationsPortsOverview() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Могут быть варианты с null'ом в полях условий. Рроверить руками дополнительно");
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("locations.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsLocationsPortsOverview.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkCountryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("country.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCountryCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkLocationsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("locations.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkContainerLocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("containerLocation.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkVesselRegistryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("vesselRegistry.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    @Test(enabled = true)
    public void Kunde_lnkCustomersLocations_Kunde() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations_Kunde.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void Kunde_lnkCustomersCompany_Kunde() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany_Kunde.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/linksCountsSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in HubTable1 [" + countInSource + "], in LinkTable [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
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
