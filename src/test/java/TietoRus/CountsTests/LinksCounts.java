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

    @Test(enabled = false)
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

    @Test(enabled = false)
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
/*
    @Test(enabled = true)
    public void Adresse_lnkCustomersLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByConditionAdresse = getDataFromDWH(properties.getProperty("lnkCustomersLocations.conditionAdresse.CountRows"));
        System.out.println("countRowByConditionAdresse: " + countRowByConditionAdresse);
        int countRowByConditionKunde = getDataFromDWH(properties.getProperty("lnkCustomersLocations.conditionKunde.CountRows"));
        System.out.println("countRowByConditionKunde: " + countRowByConditionKunde);
        int countRowByCondition = countRowByConditionAdresse + countRowByConditionKunde;
        int countRowInLink = getDataFromDWH(properties.getProperty("lnkCustomersLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Adresse_satLnkCustomersLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getDataFromDWH(properties.getProperty("lnkCustomersLocations.lnk.CountRows"));
        int countRowInSatLink = getDataFromDWH(properties.getProperty("lnkCustomersLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

*/
    @Test(enabled = true)
    public void Adresse_lnkCustomersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByConditionAdresse = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.conditionAdresse.CountRows"));
        System.out.println("countRowByConditionAdresse: " + countRowByConditionAdresse);
        int countRowByConditionKunde = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.conditionKunde.CountRows"));
        System.out.println("countRowByConditionKunde: " + countRowByConditionKunde);
        //int countRowByCondition = countRowByConditionAdresse + countRowByConditionKunde;
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.condition.CountRows"));;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Adresse_satLnkCustomersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBooking.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBookingReportingCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBookingReportingCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_E = getCountRowOfHub(properties.getProperty("lnkBookingContainerLocation_E.condition.CountRows"));
        int countRowByCondition_I = getCountRowOfHub(properties.getProperty("lnkBookingContainerLocation_I.condition.CountRows"));
        int countRowByCondition = countRowByCondition_E + countRowByCondition_I;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingContainerLocation.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingContainerLocation.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingContainerLocation.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingControllingOffice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingControllingOffice.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingControllingOffice.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingControllingOffice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingControllingOffice.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingControllingOffice.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = false)
    public void Book_lnkBookingCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCountry.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCountry.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Book_satLnkBookingCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCountry.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCountry.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Book_lnkBookingCrossBookingType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingCrossBookingType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCustomers() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Отдельно где то описано надо условия посомтреть");
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingFileLiner() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingFileLiner.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingFileLiner.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingFileLiner() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingFileLiner.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingFileLiner.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingImportExport() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingImportExport.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingImportExport.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = true)
    public void Book_satLnkBookingImportExport() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingImportExport.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingImportExport.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingPPCCEE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingPPCCEE.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingPPCCEE.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingPPCCEE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingPPCCEE.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingPPCCEE.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Book_satLnkBookingCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookDetails_lnkBookingBookingManifestAdditionals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetails_satLnkBookingBookingManifestAdditionals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestAdditionals.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookDetails_lnkBookingManifestAdditionalsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookDetails_satLnkBookingManifestAdditionalsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestAdditionalsCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_lnkBookingBookingManifestedHaulage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_satLnkBookingBookingManifestedHaulage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestedHaulage.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_lnkBookingManifestedHaulageTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_satLnkBookingManifestedHaulageTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageTransportMode.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookDetailsMof_lnkBookingManifestedHaulageCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestedHaulageCompany.lnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }


    /*
int countRowByCondition = getDataFromDWH(properties.getProperty("lnkPaymentsCompany.condition.CountRows"));
int countRowInLink = getDataFromDWH(properties.getProperty("lnkPaymentsCompany.lnk.CountRows"));
int countRowInLink = getDataFromDWH(properties.getProperty("lnkPaymentsCompany.lnk.CountRows"));
int countRowInSatLink = getDataFromDWH(properties.getProperty("lnkPaymentsCompany.satLnk.CountRows"));
*/
    @Test(enabled = false)
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
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void satLnkBookingLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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
        int countRowByCondition_E = getCountRowOfHub(properties.getProperty("lnkBookingCargoFullEmptyE.condition.CountRows"));
        System.out.println("lnkBookingCargoFullEmptyE: " + countRowByCondition_E);
        int countRowByCondition_F = getCountRowOfHub(properties.getProperty("lnkBookingCargoFullEmptyF.condition.CountRows"));
        System.out.println("lnkBookingCargoFullEmptyF: " + countRowByCondition_F);
        int countRowByCondition = (countRowByCondition_E + countRowByCondition_F);
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoFullEmpty.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
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

    @Test(enabled = false)
    public void BookGods_satLnkBookingCargoCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub1 = getCountRowOfHub(properties.getProperty("lnkBookingCargoCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoCompany.satLnk.CountRows"));
        assertRowCount(countRowInHub1, countRowInLink);
    }

    @Test(enabled = true)
    public void BookKor_lnkBookingHaulageDetailsCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCustomers.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCustomers.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookKor_satLnkBookingHaulageDetailsCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCustomers.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCustomers.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookKor_lnkBookingHaulageDetailsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookKor_satLnkBookingHaulageDetailsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingHaulageDetailsCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookLin_lnkBookingChargeLinesCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCurrency.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCurrency.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookLin_satLnkBookingChargeLinesCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCurrency.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCurrency.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookLin_lnkBookingChargesBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingChargesBookingChargeLines.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargesBookingChargeLines.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookLin_satLnkBookingChargesBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargesBookingChargeLines.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingChargesBookingChargeLines.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookLin_lnkBookingChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookLin_satLnkBookingChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingChargeLinesCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifest.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifest.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_satLnkBookingBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifest.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifest.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingBookingManifestShadowCopy() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestShadowCopy.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestShadowCopy.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_satLnkBookingBookingManifestShadowCopy() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestShadowCopy.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingManifestShadowCopy.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingManifestBookingDTXFile() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingManifestBookingDTXFile.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestBookingDTXFile.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookManifests_satLnkBookingManifestBookingDTXFile() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestBookingDTXFile.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestBookingDTXFile.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookManifests_lnkBookingManifestCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingManifestCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookManifests_satLnkBookingManifestCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingManifestCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookMftFile_lnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookMftFile_satLnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_lnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileBookingEMCRemarks.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileBookingEMCRemarks.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_satLnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileBookingEMCRemarks.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileBookingEMCRemarks.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_lnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookMftRemarks_satLnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_satLnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingOceanVesselExportVessels() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselExportVessels.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselExportVessels.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_satLnkBookingOceanVesselExportVessels() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselExportVessels.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselExportVessels.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingOceanVesselOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselOceanVesselStatus.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselOceanVesselStatus.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_satLnkBookingOceanVesselOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselOceanVesselStatus.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselOceanVesselStatus.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingOceanVesselCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void BookVessel_satLnkBookingOceanVesselCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingOceanVesselCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContHolliday_lnkWeekendsHolidaysCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void ContHolliday_satLnkWeekendsHolidaysCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContRep_lnkContainerMovesBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerMovesBooking.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesBooking.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ContRep_satLnkContainerMovesBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesBooking.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesBooking.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContRep_lnkContainerMovesContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerLocation.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerLocation.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ContRep_satLnkContainerMovesContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerLocation.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerLocation.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void ContRep_lnkContainerMovesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerMovesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void ContRep_satLnkContainerMovesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ControllingOfficeLocationCode_lnkControllingOfficeLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkControllingOfficeLocations.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkControllingOfficeLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ControllingOfficeLocationCode_satLnkControllingOfficeLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkControllingOfficeLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkControllingOfficeLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContType_lnkContainerTypeContainerTypeSpecEquip() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerTypeContainerTypeSpecEquip.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeContainerTypeSpecEquip.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ContType_satLnkContainerTypeContainerTypeSpecEquip() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeContainerTypeSpecEquip.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeContainerTypeSpecEquip.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContType_lnkContainerTypeIsoCode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ContType_satLnkContainerTypeIsoCode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContType_lnkIsoCodeCompany() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("линк специфичен, проверить руками");
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void ContType_satLnkIsoCodeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.lnk.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.satLnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ContType_lnkContainerTypeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void ContType_satLnkContainerTypeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkOceanVesselServiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void EdiKonv_satLnkOceanVesselServiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkImsChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void EdiKonv_satLnkImsChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkSpecialContractTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void EdiKonv_satLnkSpecialContractTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkSublocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void EdiKonv_satLnkSublocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkTransportModeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void EdiKonv_satLnkTransportModeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkCorrectorRemarkTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = false)
    public void EdiKonv_satLnkCorrectorRemarkTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkExportVesselsLocations.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_satLnkExportVesselsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkExportVesselsOceanVesselService.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsOceanVesselService.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_satLnkExportVesselsOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsOceanVesselService.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsOceanVesselService.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_Null = getCountRowInSA(properties.getProperty("lnkExportVesselsVesselRegistry_Null.condition.CountRows"));
        int countRowByCondition_NotNull = getCountRowInSA(properties.getProperty("lnkExportVesselsVesselRegistry_NotNull.condition.CountRows"));
        int countRowByCondition = countRowByCondition_Null + countRowByCondition_NotNull;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_satLnkExportVesselsVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowInSA(properties.getProperty("lnkExportVesselsVesselRegistry.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ExpVessels_lnkExportVesselsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void ExpVessels_satLnkExportVesselsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoicePostingAccountingTransaction() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_satLnkInvoicePostingAccountingTransaction() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoicePostingCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCurrency.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCurrency.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_satLnkInvoicePostingCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCurrency.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCurrency.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoicePostingCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = false)
    public void FaktPost_satLnkInvoicePostingCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkLocationsCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkLocationsCountry.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCountry.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_satLnkLocationsCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCountry.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkLocationsCountry.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkLocationsLocationsPortsOverview() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkLocationsLocationsPortsOverview.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsLocationsPortsOverview.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_satLnkLocationsLocationsPortsOverview() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsLocationsPortsOverview.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkLocationsLocationsPortsOverview.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkCountryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkCountryCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCountryCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Henvis_satLnkCountryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCountryCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCountryCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkLocationsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Henvis_satLnkLocationsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkContainerLocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Henvis_satLnkContainerLocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.satLnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkVesselRegistryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Henvis_satLnkVesselRegistryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void lnkCustomersLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByConditionFromKunde = getCountRowOfHub(properties.getProperty("lnkCustomersLocations_Kunde.condition.CountRows"));
        int countRowByConditionFromAdresse = getCountRowOfHub(properties.getProperty("lnkCustomersLocations_Adresse.condition.CountRows"));
        int countRowByCondition = countRowByConditionFromKunde + countRowByConditionFromAdresse;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void satLnkCustomersLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCustomersLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceBooking.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBooking.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBooking.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBooking.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceCurrency.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCurrency.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCurrency.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCurrency.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceCustomers.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCustomers.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCustomers.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCustomers.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceInvoice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoice.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoice.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceInvoice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoice.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoice.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceInvoiceLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true )
    public void Ordre_satLnkInvoiceInvoiceLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Ordre_lnkInvoiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Ordre_satLnkInvoiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void OrdreLin_lnkInvoiceLinesCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCurrency.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCurrency.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void OrdreLin_satLnkInvoiceLinesCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCurrency.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCurrency.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void OrdreLin_lnkInvoiceLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void OrdreLin_satLnkInvoiceLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceLinesCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Sag_lnkFileLinerFileROE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkFileLinerFileROE.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerFileROE.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Sag_satLnkFileLinerFileROE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerFileROE.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFileLinerFileROE.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Sag_lnkFileLinerLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_EXP = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations_EXP.condition.CountRows"));
        System.out.println("countRowByCondition_EXP: " + countRowByCondition_EXP);
        int countRowByCondition_IMP = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations_IMP.condition.CountRows"));
        System.out.println("lnkFileLinerLocations_IMP: " + countRowByCondition_IMP);
        int countRowByCondition = countRowByCondition_EXP +countRowByCondition_IMP;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Sag_satLnkFileLinerLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Sag_lnkFileLinerCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkFileLinerCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void Sag_satLnkFileLinerCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFileLinerCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void SagKurs_lnkFileROECompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkFileROECompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void SagKurs_satLnkFileROECompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileROECompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFileROECompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ShipKurs_lnkDailyRoeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkDailyRoeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDailyRoeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    public void ShipKurs_satLnkDailyRoeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDailyRoeCompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkDailyRoeCompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookingCargoChargesLnk() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargoCharges.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCargoCharges.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }
    //Sat создается пакетом GET_CHARGES - загрузка чарджерс из SA в DWH.
    @Test(enabled = true)
    public void BookingCargoChargesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargoCharges.lnk.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCargoCharges.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingCargoChargesSatLnk() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargoCharges.lnk.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCargoCharges.satLnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
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