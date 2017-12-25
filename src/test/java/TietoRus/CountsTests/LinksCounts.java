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
        //В таблицу BogfTrans при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
    //В таблицу BogfTrans при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
    // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
    //В таблицу BogfTrans при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
    // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
    public void BogfTrans_lnkAccountingTransactionCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkAccountingTransactionCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void AbPost_lnkPaymentsInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkPaymentsInvoicePosting.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkPaymentsInvoicePosting.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkPaymentsInvoicePosting.error.CountRows"));
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
    public void lnkCustomersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByConditionAdresse = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.conditionAdresse.CountRows"));
        System.out.println("countRowByConditionAdresse: " + countRowByConditionAdresse);
        int countRowByConditionKunde = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.conditionKunde.CountRows"));
        System.out.println("countRowByConditionKunde: " + countRowByConditionKunde);
        int countRowByCondition = countRowByConditionAdresse + countRowByConditionKunde;
        //int countRowByCondition = getPartyId(properties.getProperty("lnkCustomersCompany.condition.CountRows"));
        ;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBooking() throws SQLException, IOException {
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBooking.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingBooking.error.CountRows"));
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBooking() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBooking.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Book_lnkBookingBookingHaulageDetails() throws SQLException, IOException {
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingHaulageDetails.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingBookingHaulageDetails.error.CountRows"));
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingReportingCustomer.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingBookingReportingCustomer.error.CountRows"));
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowFoundedFor_REF_TYPE = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.foundedForREF_TYPE.CountRows"));
        System.out.println("Столько д.б. записей для REF_TYPE is not null и найденых в hubCrossBooking и для REF_TYPE is null,");
        System.out.println("которые связываются с hubCrossBooking.bookTypeCode = 'NA': " + countRowFoundedFor_REF_TYPE);
        System.out.println("Остальные - те, что не найдены в hubCrossBooking по saBook.REF_TYPE");
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingCrossBookingType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCrossBookingType.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingCustomers() throws SQLException, IOException {
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition_S_NAMED_ACCOUNT_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_NAMED_ACCOUNT_NotNull.CountRows"));
        int countRowByCondition_S_NAMED_ACCOUNT_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_NAMED_ACCOUNT_Null.CountRows"));
        int ccountRowByCondition_both_NAMED_ACCOUNT_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_NAMED_ACCOUNT_Null.CountRows"));
        System.out.println("countRowByCondition_S_NAMED_ACCOUNT_NotNull: " + countRowByCondition_S_NAMED_ACCOUNT_NotNull);
        System.out.println("countRowByCondition_S_NAMED_ACCOUNT_Null: " + countRowByCondition_S_NAMED_ACCOUNT_Null);
        System.out.println("ccountRowByCondition_both_NAMED_ACCOUNT_Null " + ccountRowByCondition_both_NAMED_ACCOUNT_Null);
        System.out.println("Summa S_NAMED_ACCOUNT: " + (countRowByCondition_S_NAMED_ACCOUNT_NotNull + countRowByCondition_S_NAMED_ACCOUNT_Null + ccountRowByCondition_both_NAMED_ACCOUNT_Null));
        int countRowByCondition_S_BOOK_PARTY_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_BOOK_PARTY_NotNull.CountRows"));
        int countRowByCondition_S_BOOK_PARTY_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_BOOK_PARTY_Null.CountRows"));
        int countRowByCondition_both_BOOK_PARTY_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_BOOK_PARTY_Null.CountRows"));
        System.out.println("countRowByCondition_S_BOOK_PARTY_NotNull: " + countRowByCondition_S_BOOK_PARTY_NotNull);
        System.out.println("countRowByCondition_S_BOOK_PARTY_Null: " + countRowByCondition_S_BOOK_PARTY_Null);
        System.out.println("countRowByCondition_both_BOOK_PARTY_Null: " + countRowByCondition_both_BOOK_PARTY_Null);
        System.out.println("Summa S_BOOK_PARTY: " + (countRowByCondition_S_BOOK_PARTY_NotNull + countRowByCondition_S_BOOK_PARTY_Null + countRowByCondition_both_BOOK_PARTY_Null));
        int countRowByCondition_S_CARE_OF_FWD_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_CARE_OF_FWD_NotNull.CountRows"));
        int countRowByCondition_S_CARE_OF_FWD_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_CARE_OF_FWD_Null.CountRows"));
        int countRowByCondition_both_CARE_OF_FWD_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.both_CARE_OF_FWD_Null.CountRows"));
        System.out.println("countRowByCondition_S_CARE_OF_FWD_NotNull: " + countRowByCondition_S_CARE_OF_FWD_NotNull);
        System.out.println("countRowByCondition_S_CARE_OF_FWD_Null: " + countRowByCondition_S_CARE_OF_FWD_Null);
        System.out.println("countRowByCondition_both_CARE_OF_FWD_Null: " + countRowByCondition_both_CARE_OF_FWD_Null);
        System.out.println("Summa S_CARE_OF_FWD: " + (countRowByCondition_S_CARE_OF_FWD_NotNull + countRowByCondition_S_CARE_OF_FWD_Null + countRowByCondition_both_CARE_OF_FWD_Null));
        int countRowByCondition_S_AFSENDER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_AFSENDER_NotNull.CountRows"));
        int countRowByCondition_S_AFSENDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_AFSENDER_Null.CountRows"));
        int countRowByCondition_both_AFSENDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_AFSENDER_Null.CountRows"));
        System.out.println("countRowByCondition_S_AFSENDER_NotNull: " + countRowByCondition_S_AFSENDER_NotNull);
        System.out.println("countRowByCondition_S_AFSENDER_Null: " + countRowByCondition_S_AFSENDER_Null);
        System.out.println("countRowByCondition_both_AFSENDER_Null: " + countRowByCondition_both_AFSENDER_Null);
        System.out.println("Summa S_AFSENDER: " + (countRowByCondition_S_AFSENDER_NotNull + countRowByCondition_S_AFSENDER_Null + countRowByCondition_both_AFSENDER_Null));
        int countRowByCondition_S_MODTAGER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_MODTAGER_NotNull.CountRows"));
        int countRowByCondition_S_MODTAGER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_MODTAGER_Null.CountRows"));
        int countRowByCondition_both_MODTAGER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_MODTAGER_NotNull"));
        int countRowByCondition_MODTAGER_unionAllCondition = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_MODTAGER_unionAllCondition.CountRows"));
        System.out.println("countRowByCondition_S_MODTAGER_NotNull: " + countRowByCondition_S_MODTAGER_NotNull);
        System.out.println("countRowByCondition_S_MODTAGER_Null: " + countRowByCondition_S_MODTAGER_Null);
        System.out.println("countRowByCondition_both_MODTAGER_Null: " + countRowByCondition_both_MODTAGER_Null);
        System.out.println("Summa S_MODTAGER: " + (countRowByCondition_S_MODTAGER_NotNull + countRowByCondition_S_MODTAGER_Null + countRowByCondition_both_MODTAGER_Null
                + "(суммировать эти условия не правильно, нужно объеденять запросы)"));
        int countRowByCondition_S_SLUTSPED_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_SLUTSPED_NotNull.CountRows"));
        int countRowByCondition_S_SLUTSPED_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_SLUTSPED_Null.CountRows"));
        int countRowByCondition_both_SLUTSPED_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_SLUTSPED_Null.CountRows"));
        System.out.println("countRowByCondition_S_SLUTSPED_NotNull: " + countRowByCondition_S_SLUTSPED_NotNull);
        System.out.println("countRowByCondition_S_SLUTSPED_Null: " + countRowByCondition_S_SLUTSPED_Null);
        System.out.println("countRowByCondition_both_SLUTSPED_Null: " + countRowByCondition_both_SLUTSPED_Null);
        System.out.println("Summa S_SLUTSPED: " + (countRowByCondition_S_SLUTSPED_NotNull + countRowByCondition_S_SLUTSPED_Null + countRowByCondition_both_SLUTSPED_Null));
        int countRowByCondition_S_NOTIFY2_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_NOTIFY2_NotNull.CountRows"));
        int countRowByCondition_S_NOTIFY2_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_NOTIFY2_Null.CountRows"));
        int countRowByCondition_both_NOTIFY2_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_NOTIFY2_Null.CountRows"));
        System.out.println("countRowByCondition_S_NOTIFY2_NotNull: " + countRowByCondition_S_NOTIFY2_NotNull);
        System.out.println("countRowByCondition_S_NOTIFY2_Null: " + countRowByCondition_S_NOTIFY2_Null);
        System.out.println("countRowByCondition_both_NOTIFY2_Null: " + countRowByCondition_both_NOTIFY2_Null);
        System.out.println("Summa S_NOTIFY2: " + (countRowByCondition_S_NOTIFY2_NotNull + countRowByCondition_S_NOTIFY2_Null + countRowByCondition_both_NOTIFY2_Null));
        int countRowByCondition_S_NOTIFY3_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_NOTIFY3_NotNull.CountRows"));
        int countRowByCondition_S_NOTIFY3_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_NOTIFY3_Null.CountRows"));
        int countRowByCondition_both_NOTIFY3_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_NOTIFY3_Null.CountRows"));
        System.out.println("countRowByCondition_S_NOTIFY3_NotNull: " + countRowByCondition_S_NOTIFY3_NotNull);
        System.out.println("countRowByCondition_S_NOTIFY3_Null: " + countRowByCondition_S_NOTIFY3_Null);
        System.out.println("countRowByCondition_both_NOTIFY3_Null: " + countRowByCondition_both_NOTIFY3_Null);
        System.out.println("Summa S_NOTIFY3: " + (countRowByCondition_S_NOTIFY3_NotNull + countRowByCondition_S_NOTIFY3_Null + countRowByCondition_both_NOTIFY3_Null));
        int countRowByCondition_S_KUNDE_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_KUNDE_NotNull.CountRows"));
        int countRowByCondition_S_KUNDE_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_KUNDE_Null.CountRows"));
        int countRowByCondition_both_KUNDE_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_KUNDE_Null.CountRows"));
        System.out.println("countRowByCondition_S_KUNDE_NotNull: " + countRowByCondition_S_KUNDE_NotNull);
        System.out.println("countRowByCondition_S_KUNDE_Null: " + countRowByCondition_S_KUNDE_Null);
        System.out.println("countRowByCondition_both_KUNDE_Null: " + countRowByCondition_both_KUNDE_Null);
        System.out.println("Summa S_KUNDE: " + (countRowByCondition_S_KUNDE_NotNull + countRowByCondition_S_KUNDE_Null + countRowByCondition_both_KUNDE_Null));
        int countRowByCondition_S_CONTRACT_HOLDER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_CONTRACT_HOLDER_NotNull.CountRows"));
        int countRowByCondition_S_CONTRACT_HOLDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_CONTRACT_HOLDER_Null.CountRows"));
        int countRowByCondition_both_CONTRACT_HOLDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_CONTRACT_HOLDER_Null.CountRows"));
        System.out.println("countRowByCondition_S_CONTRACT_HOLDER_NotNull: " + countRowByCondition_S_CONTRACT_HOLDER_NotNull);
        System.out.println("countRowByCondition_S_CONTRACT_HOLDER_Null: " + countRowByCondition_S_CONTRACT_HOLDER_Null);
        System.out.println("countRowByCondition_both_CONTRACT_HOLDER_Null: " + countRowByCondition_both_CONTRACT_HOLDER_Null);
        System.out.println("Summa S_CONTRACT_HOLDER: " + (countRowByCondition_S_CONTRACT_HOLDER_NotNull + countRowByCondition_S_CONTRACT_HOLDER_Null + countRowByCondition_both_CONTRACT_HOLDER_Null));
        int countRowByCondition_S_IC_ELSEWHERE_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_IC_ELSEWHERE_NotNull.CountRows"));
        int countRowByCondition_S_IC_ELSEWHERE_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_IC_ELSEWHERE_Null.CountRows"));
        int countRowByCondition_both_IC_ELSEWHERE_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_IC_ELSEWHERE_Null.CountRows"));
        System.out.println("countRowByCondition_S_IC_ELSEWHERE_NotNull: " + countRowByCondition_S_IC_ELSEWHERE_NotNull);
        System.out.println("countRowByCondition_S_IC_ELSEWHERE_Null: " + countRowByCondition_S_IC_ELSEWHERE_Null);
        System.out.println("countRowByCondition_both_IC_ELSEWHERE_Null: " + countRowByCondition_both_IC_ELSEWHERE_Null);
        System.out.println("Summa S_IC_ELSEWHERE: " + (countRowByCondition_S_IC_ELSEWHERE_NotNull + countRowByCondition_S_IC_ELSEWHERE_Null + countRowByCondition_both_IC_ELSEWHERE_Null));
        int countRowByCondition_S_FREIGHT_FWD_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_FREIGHT_FWD_NotNull.CountRows"));
        int countRowByCondition_S_FREIGHT_FWD_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_S_FREIGHT_FWD_Null.CountRows"));
        int countRowByCondition_both_FREIGHT_FWD_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_both_FREIGHT_FWD_Null.CountRows"));
        System.out.println("countRowByCondition_S_FREIGHT_FWD_NotNull: " + countRowByCondition_S_FREIGHT_FWD_NotNull);
        System.out.println("countRowByCondition_S_FREIGHT_FWD_Null: " + countRowByCondition_S_FREIGHT_FWD_Null);
        System.out.println("countRowByCondition_both_FREIGHT_FWD_Null: " + countRowByCondition_both_FREIGHT_FWD_Null);
        System.out.println("Summa S_FREIGHT_FWD: " + (countRowByCondition_S_FREIGHT_FWD_NotNull + countRowByCondition_S_FREIGHT_FWD_Null + countRowByCondition_both_FREIGHT_FWD_Null));
//Блок для роли StatCustomer
        int countRowByCondition_StatCustomer_S_KUNDE_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_KUNDE_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_KUNDE_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_KUNDE_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_KUNDE_NotNull: " + countRowByCondition_StatCustomer_S_KUNDE_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_KUNDE_Null: " + countRowByCondition_StatCustomer_S_KUNDE_Null);
        System.out.println("Summa StatCustomer_S_KUNDE: " + (countRowByCondition_StatCustomer_S_KUNDE_NotNull + countRowByCondition_StatCustomer_S_KUNDE_Null));
        int countRowByCondition_StatCustomer_S_AFSENDER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_AFSENDER_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_AFSENDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_AFSENDER_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_AFSENDER_NotNull: " + countRowByCondition_StatCustomer_S_AFSENDER_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_AFSENDER_Null: " + countRowByCondition_StatCustomer_S_AFSENDER_Null);
        System.out.println("Summa StatCustomer_S_AFSENDER: " + (countRowByCondition_StatCustomer_S_AFSENDER_NotNull + countRowByCondition_StatCustomer_S_AFSENDER_Null));
        int countRowByCondition_StatCustomer_S_MODTAGER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_MODTAGER_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_MODTAGER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_MODTAGER_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_MODTAGER_NotNull: " + countRowByCondition_StatCustomer_S_MODTAGER_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_MODTAGER_Null: " + countRowByCondition_StatCustomer_S_MODTAGER_Null);
        System.out.println("Summa StatCustomer_S_MODTAGER: " + (countRowByCondition_StatCustomer_S_MODTAGER_NotNull + countRowByCondition_StatCustomer_S_MODTAGER_Null));
        int countRowByCondition_StatCustomer_S_SLUTSPED_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_SLUTSPED_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_SLUTSPED_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_SLUTSPED_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_SLUTSPED_NotNull: " + countRowByCondition_StatCustomer_S_SLUTSPED_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_SLUTSPED_Null: " + countRowByCondition_StatCustomer_S_SLUTSPED_Null);
        System.out.println("Summa StatCustomer_S_MODTAGER: " + (countRowByCondition_StatCustomer_S_SLUTSPED_NotNull + countRowByCondition_StatCustomer_S_SLUTSPED_Null));
        int countRowByCondition_StatCustomer_S_NOTIFY2_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_NOTIFY2_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_NOTIFY2_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_NOTIFY2_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_NOTIFY2_NotNull: " + countRowByCondition_StatCustomer_S_NOTIFY2_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_NOTIFY2_Null: " + countRowByCondition_StatCustomer_S_NOTIFY2_Null);
        System.out.println("Summa StatCustomer_S_NOTIFY2: " + (countRowByCondition_StatCustomer_S_NOTIFY2_NotNull + countRowByCondition_StatCustomer_S_NOTIFY2_Null));
        int countRowByCondition_StatCustomer_S_NOTIFY3_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_NOTIFY3_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_NOTIFY3_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_NOTIFY3_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_NOTIFY3_NotNull: " + countRowByCondition_StatCustomer_S_NOTIFY3_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_NOTIFY3_Null: " + countRowByCondition_StatCustomer_S_NOTIFY3_Null);
        System.out.println("Summa StatCustomer_S_NOTIFY3: " + (countRowByCondition_StatCustomer_S_NOTIFY3_NotNull + countRowByCondition_StatCustomer_S_NOTIFY3_Null));
        int countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_CONTRACT_HOLDER_NotNull.CountRows"));
        int countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_S_CONTRACT_HOLDER_Null.CountRows"));
        System.out.println("countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_NotNull: " + countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_NotNull);
        System.out.println("countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_Null: " + countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_Null);
        System.out.println("Summa StatCustomer_S_CONTRACT_HOLDER: " + (countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_NotNull + countRowByCondition_StatCustomer_S_CONTRACT_HOLDER_Null));

        //Блок для роли FreightPayer
        int countRowByCondition_FreightPayer_S_KUNDE_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_KUNDE_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_KUNDE_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_KUNDE_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_KUNDE_NotNull: " + countRowByCondition_FreightPayer_S_KUNDE_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_KUNDE_Null: " + countRowByCondition_FreightPayer_S_KUNDE_Null);
        System.out.println("Summa FreightPayer_S_KUNDE: " + (countRowByCondition_FreightPayer_S_KUNDE_NotNull + countRowByCondition_FreightPayer_S_KUNDE_Null));
        int countRowByCondition_FreightPayer_S_AFSENDER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_AFSENDER_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_AFSENDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_AFSENDER_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_AFSENDER_NotNull: " + countRowByCondition_FreightPayer_S_AFSENDER_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_AFSENDER_Null: " + countRowByCondition_FreightPayer_S_AFSENDER_Null);
        System.out.println("Summa FreightPayer_S_AFSENDER: " + (countRowByCondition_FreightPayer_S_AFSENDER_NotNull + countRowByCondition_FreightPayer_S_AFSENDER_Null));
        int countRowByCondition_FreightPayer_S_MODTAGER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_MODTAGER_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_MODTAGER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_MODTAGER_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_MODTAGER_NotNull: " + countRowByCondition_FreightPayer_S_MODTAGER_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_MODTAGER_Null: " + countRowByCondition_FreightPayer_S_MODTAGER_Null);
        System.out.println("Summa FreightPayer_S_MODTAGER: " + (countRowByCondition_FreightPayer_S_MODTAGER_NotNull + countRowByCondition_FreightPayer_S_MODTAGER_Null));
        int countRowByCondition_FreightPayer_S_SLUTSPED_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_SLUTSPED_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_SLUTSPED_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_SLUTSPED_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_SLUTSPED_NotNull: " + countRowByCondition_FreightPayer_S_SLUTSPED_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_SLUTSPED_Null: " + countRowByCondition_FreightPayer_S_SLUTSPED_Null);
        System.out.println("Summa FreightPayer_S_MODTAGER: " + (countRowByCondition_FreightPayer_S_SLUTSPED_NotNull + countRowByCondition_FreightPayer_S_SLUTSPED_Null));
        int countRowByCondition_FreightPayer_S_NOTIFY2_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_NOTIFY2_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_NOTIFY2_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_NOTIFY2_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_NOTIFY2_NotNull: " + countRowByCondition_FreightPayer_S_NOTIFY2_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_NOTIFY2_Null: " + countRowByCondition_FreightPayer_S_NOTIFY2_Null);
        System.out.println("Summa FreightPayer_S_NOTIFY2: " + (countRowByCondition_FreightPayer_S_NOTIFY2_NotNull + countRowByCondition_FreightPayer_S_NOTIFY2_Null));
        int countRowByCondition_FreightPayer_S_NOTIFY3_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_NOTIFY3_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_NOTIFY3_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_NOTIFY3_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_NOTIFY3_NotNull: " + countRowByCondition_FreightPayer_S_NOTIFY3_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_NOTIFY3_Null: " + countRowByCondition_FreightPayer_S_NOTIFY3_Null);
        System.out.println("Summa FreightPayer_S_NOTIFY3: " + (countRowByCondition_FreightPayer_S_NOTIFY3_NotNull + countRowByCondition_FreightPayer_S_NOTIFY3_Null));
        int countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_NotNull = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_CONTRACT_HOLDER_NotNull.CountRows"));
        int countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_Null = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_S_CONTRACT_HOLDER_Null.CountRows"));
        System.out.println("countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_NotNull: " + countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_NotNull);
        System.out.println("countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_Null: " + countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_Null);
        System.out.println("Summa FreightPayer_S_CONTRACT_HOLDER: " + (countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_NotNull + countRowByCondition_FreightPayer_S_CONTRACT_HOLDER_Null));

        int countRowsByUNIONConditionsForStatCustomer = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_StatCustomer_Union.CountRows"));
        System.out.println("Summa All StatCustomer with UNION: " + countRowsByUNIONConditionsForStatCustomer);

        int countRowsByUNIONConditionsForFreightPayer = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.condition_FreightPayer_Union.CountRows"));
        System.out.println("Summa All FreightPayer with UNION: " + countRowsByUNIONConditionsForFreightPayer);

        int countRowsByConditionsAll =
                countRowByCondition_S_NAMED_ACCOUNT_NotNull + countRowByCondition_S_NAMED_ACCOUNT_Null + ccountRowByCondition_both_NAMED_ACCOUNT_Null +
                        countRowByCondition_S_BOOK_PARTY_NotNull + countRowByCondition_S_BOOK_PARTY_Null + countRowByCondition_both_BOOK_PARTY_Null +
                        countRowByCondition_S_CARE_OF_FWD_NotNull + countRowByCondition_S_CARE_OF_FWD_Null + countRowByCondition_both_CARE_OF_FWD_Null +
                        countRowByCondition_S_AFSENDER_NotNull + countRowByCondition_S_AFSENDER_Null + countRowByCondition_both_AFSENDER_Null +
                        countRowByCondition_MODTAGER_unionAllCondition +
                        countRowByCondition_S_SLUTSPED_NotNull + countRowByCondition_S_SLUTSPED_Null + countRowByCondition_both_SLUTSPED_Null +
                        countRowByCondition_S_NOTIFY2_NotNull + countRowByCondition_S_NOTIFY2_Null + countRowByCondition_both_NOTIFY2_Null +
                        countRowByCondition_S_NOTIFY3_NotNull + countRowByCondition_S_NOTIFY3_Null + countRowByCondition_both_NOTIFY3_Null +
                        countRowByCondition_S_KUNDE_NotNull + countRowByCondition_S_KUNDE_Null + countRowByCondition_both_KUNDE_Null +
                        countRowByCondition_S_CONTRACT_HOLDER_NotNull + countRowByCondition_S_CONTRACT_HOLDER_Null + countRowByCondition_both_CONTRACT_HOLDER_Null +
                        countRowByCondition_S_IC_ELSEWHERE_NotNull + countRowByCondition_S_IC_ELSEWHERE_Null + countRowByCondition_both_IC_ELSEWHERE_Null +
                        countRowByCondition_S_FREIGHT_FWD_NotNull + countRowByCondition_S_FREIGHT_FWD_Null + countRowByCondition_both_FREIGHT_FWD_Null +
                        countRowsByUNIONConditionsForStatCustomer + countRowsByUNIONConditionsForFreightPayer;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCustomers.lnk.CountRows"));
        assertRowCount(countRowsByConditionsAll, countRowInLink);
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_lnkBookingBookingInternalRemarks() throws SQLException, IOException {
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingInternalRemarks.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingInternalRemarks.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingBookingInternalRemarks.error.CountRows"));
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBookingInternalRemarks() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingInternalRemarks.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingInternalRemarks.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Book_lnkBookingBranch() throws SQLException, IOException {
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBranch.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBranch.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingBranch() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBranch.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBranch.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Book_lnkBookingServiceNames() throws SQLException, IOException {
        //В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingServiceNames.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingServiceNames.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Book_satLnkBookingServiceNames() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingServiceNames.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingServiceNames.satLnk.CountRows"));
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

    @Test(enabled = true)
    public void lnkBookingLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition_AFG_ZONE = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_AFG_ZONE.CountRows"));
        int countRowByCondition_ANK_ZONE = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_ANK_ZONE.CountRows"));
        //int countRowByCondition_ANK_ZONE_notNull = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_ANK_ZONE_notNull.CountRows"));
        //int countRowByCondition_ANK_ZONE_Null = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_ANK_ZONE_Null.CountRows"));
        //int countRowByCondition_ANK_ZONE_BothNull = (getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_ANK_ZONE_Null_ForBothNull.CountRows")) -
        //      countRowByCondition_ANK_ZONE_Null);
        int countRowByCondition_POD_notNull = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_POD_notNull.CountRows"));
        int countRowByCondition_POD_Null = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_POD_Null.CountRows"));
        int countRowByCondition_POD_BothNull = (getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_POD_BothNull.CountRows")) -
                countRowByCondition_POD_Null);
        int countRowByCondition_POR_notNull = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_POR_notNull.CountRows"));
        int countRowByCondition_POR_Null = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_POR_Null.CountRows"));
        int countRowByCondition_POR_BothNull = (getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_POR_Null_ForBothNull.CountRows")) -
                countRowByCondition_POR_Null);
        int countRowByCondition_PORT_TRANS = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_TRANS.CountRows"));
        int countRowByCondition_PORT_TRANS2 = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_TRANS2.CountRows"));
        int countRowByCondition_PORT_TRANS3 = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_TRANS3.CountRows"));
        int countRowByCondition_PORT_TRANS4 = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_TRANS4.CountRows"));
        int countRowByCondition_PORT_TRANS5 = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_TRANS5.CountRows"));
        int countRowByCondition_FREIGHT_PAY = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_FREIGHT_PAY.CountRows"));
        int countRowByCondition_FREIGHT_PAY_IMS = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_FREIGHT_PAY_IMS.CountRows"));
        int countRowByCondition_DRY_PORT = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_DRY_PORT.CountRows"));
        int countRowByCondition_PORT_SAIL = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_SAIL.CountRows"));
        int countRowByCondition_PORT_FEED = getCountRowOfHub(properties.getProperty("lnkBookingLocations.condition_PORT_FEED.CountRows"));
        System.out.println("countRowByCondition_AFG_ZONE: " + countRowByCondition_AFG_ZONE);
        System.out.println("countRowByCondition_AFG_ZONE: " + countRowByCondition_ANK_ZONE);
        //System.out.println("countRowByCondition_ANK_ZONE_notNull: " + countRowByCondition_ANK_ZONE_notNull);
        //System.out.println("countRowByCondition_ANK_ZONE_Null: " + countRowByCondition_ANK_ZONE_Null);
        //System.out.println("countRowByCondition_ANK_ZONE_BothNull: " + countRowByCondition_ANK_ZONE_BothNull);
        System.out.println("countRowByCondition_POD_notNull: " + countRowByCondition_POD_notNull);
        System.out.println("countRowByCondition_POD_Null: " + countRowByCondition_POD_Null);
        System.out.println("countRowByCondition_POD_BothNull: " + countRowByCondition_POD_BothNull);
        System.out.println("countRowByCondition_POR_notNull: " + countRowByCondition_POR_notNull);
        System.out.println("countRowByCondition_POR_Null: " + countRowByCondition_POR_Null);
        System.out.println("countRowByCondition_POR_BothNull: " + countRowByCondition_POR_BothNull);
        System.out.println("countRowByCondition_PORT_TRANS: " + countRowByCondition_PORT_TRANS);
        System.out.println("countRowByCondition_PORT_TRANS2: " + countRowByCondition_PORT_TRANS2);
        System.out.println("countRowByCondition_PORT_TRANS3: " + countRowByCondition_PORT_TRANS3);
        System.out.println("countRowByCondition_PORT_TRANS4: " + countRowByCondition_PORT_TRANS4);
        System.out.println("countRowByCondition_PORT_TRANS5: " + countRowByCondition_PORT_TRANS5);
        System.out.println("countRowByCondition_FREIGHT_PAY: " + countRowByCondition_FREIGHT_PAY);
        System.out.println("countRowByCondition_FREIGHT_PAY_IMS: " + countRowByCondition_FREIGHT_PAY_IMS);
        System.out.println("countRowByCondition_DRY_PORT: " + countRowByCondition_DRY_PORT);
        System.out.println("countRowByCondition_PORT_SAIL: " + countRowByCondition_PORT_SAIL);
        System.out.println("countRowByCondition_PORT_FEED: " + countRowByCondition_PORT_FEED);
        int countRowByCondition = countRowByCondition_AFG_ZONE + countRowByCondition_ANK_ZONE
                + countRowByCondition_POD_notNull + countRowByCondition_POD_Null + countRowByCondition_POD_BothNull + countRowByCondition_POR_notNull
                + countRowByCondition_POR_Null + countRowByCondition_POR_BothNull +
                countRowByCondition_PORT_TRANS + countRowByCondition_PORT_TRANS2 +
                countRowByCondition_PORT_TRANS3 + countRowByCondition_PORT_TRANS4 + countRowByCondition_PORT_TRANS5 + countRowByCondition_FREIGHT_PAY +
                countRowByCondition_FREIGHT_PAY_IMS + countRowByCondition_DRY_PORT + countRowByCondition_PORT_SAIL + countRowByCondition_PORT_FEED;
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

    @Test(enabled = true)
    public void BookFak_lnkInvoiceBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookFak_satLnkInvoiceBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceBookingCharges.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
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
        checkErrors(properties.getProperty("lnkBookingCargoBookingHaulageDetails.error.CountRows"));
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

    @Test(enabled = true)
    public void BookLin_lnkBookingBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookLin_satLnkBookingBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingChargeLines.satLnk.CountRows"));
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
        checkErrors(properties.getProperty("lnkBookingManifestBookingDTXFile.error.CountRows"));
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

    @Test(enabled = true)
    public void BookMftFile_lnkBookingDTXFileCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingDTXFileCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_lnkBookingEMCRemarksBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksBookingManifest.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksBookingManifest.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_satLnkBookingEMCRemarksBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksBookingManifest.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksBookingManifest.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookMftRemarks_lnkBookingEMCRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingEMCRemarksCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_lnkBookingBookingOceanVessel() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingBookingOceanVessel.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookVessel_satLnkBookingBookingOceanVessel() throws SQLException, IOException {
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


    @Test(enabled = true)
    public void ContHolliday_lnkWeekendsHolidaysCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkWeekendsHolidaysCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
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
        int countRowByCondition_isoCode4 = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.isoCode4_condition.CountRows"));
        int countRowByCondition_isoCode6 = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.isoCode6_condition.CountRows"));
        int countRowByCondition_isoCode6_2 = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.isoCode6_2_condition.CountRows"));
        int countRowByCondition_isoCode6_3 = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.isoCode6_3_condition.CountRows"));
        int countRowByCondition_isoCode6_4 = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.isoCode6_4_condition.CountRows"));
        int countRowByCondition_isoCode6_5 = getCountRowOfHub(properties.getProperty("lnkContainerTypeIsoCode.isoCode6_5_condition.CountRows"));
        System.out.println("countRowByCondition_isoCode4: " + countRowByCondition_isoCode4);
        System.out.println("countRowByCondition_isoCode6: " + countRowByCondition_isoCode6);
        System.out.println("countRowByCondition_isoCode6_2: " + countRowByCondition_isoCode6_2);
        System.out.println("countRowByCondition_isoCode6_3: " + countRowByCondition_isoCode6_3);
        System.out.println("countRowByCondition_isoCode6_4: " + countRowByCondition_isoCode6_4);
        System.out.println("countRowByCondition_isoCode6_5: " + countRowByCondition_isoCode6_5);
        int countRowByCondition = countRowByCondition_isoCode4 + countRowByCondition_isoCode6 + countRowByCondition_isoCode6_2 +
                countRowByCondition_isoCode6_3 + countRowByCondition_isoCode6_4 + countRowByCondition_isoCode6_5;
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
        int countRowByCondition_isoCode4 = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.isoCode4_condition.CountRows"));
        int countRowByCondition_isoCode6 = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.isoCode6_condition.CountRows"));
        int countRowByCondition_isoCode6_2 = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.isoCode6_2_condition.CountRows"));
        int countRowByCondition_isoCode6_3 = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.isoCode6_3_condition.CountRows"));
        int countRowByCondition_isoCode6_4 = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.isoCode6_4_condition.CountRows"));
        int countRowByCondition_isoCode6_5 = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.isoCode6_5_condition.CountRows"));
        System.out.println("countRowByCondition_isoCode4: " + countRowByCondition_isoCode4);
        System.out.println("countRowByCondition_isoCode6: " + countRowByCondition_isoCode6);
        System.out.println("countRowByCondition_isoCode6_2: " + countRowByCondition_isoCode6_2);
        System.out.println("countRowByCondition_isoCode6_3: " + countRowByCondition_isoCode6_3);
        System.out.println("countRowByCondition_isoCode6_4: " + countRowByCondition_isoCode6_4);
        System.out.println("countRowByCondition_isoCode6_5: " + countRowByCondition_isoCode6_5);
        int countRowByCondition = countRowByCondition_isoCode4 + countRowByCondition_isoCode6 + countRowByCondition_isoCode6_2 +
                countRowByCondition_isoCode6_3 + countRowByCondition_isoCode6_4 + countRowByCondition_isoCode6_5;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkIsoCodeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = true)
    public void ContType_lnkContainerTypeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerTypeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = true)
    public void EdiKonv_lnkOceanVesselServiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkOceanVesselServiceCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkImsChargeLinesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkImsChargeLinesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkSpecialContractTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSpecialContractTypesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = true)
    public void EdiKonv_lnkSublocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkSublocationCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkTransportModeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkTransportModeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkCorrectorRemarkTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCorrectorRemarkTypesCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
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
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ExpVessels_satLnkExportVesselsVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsVesselRegistry.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = false)
    //линк удален из системы, см PBI 6016
    public void ExpVessels_lnkExportVesselsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkExportVesselsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_lnkInvoiceInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void FaktPost_satLnkInvoiceInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoicePosting.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = false)
    //линк удален из системы, см PBI 6016
    public void FaktPost_lnkInvoicePostingAccountingTransaction() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoicePostingAccountingTransaction.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = false)
    //линк удален из системы, см PBI 6016
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
        checkErrors(properties.getProperty("lnkLocationsLocationsPortsOverview.error.CountRows"));
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

    @Test(enabled = true)
    public void Henvis_lnkLocationsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLocationsCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkContainerLocationCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerLocationCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkVesselRegistryCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkVesselRegistryCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Henvis_lnkCurrencyCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkCurrencyCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCurrencyCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
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
        //В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
    public void Ordre_lnkInvoiceCurrency() throws SQLException, IOException {
        //В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkInvoiceInvoiceLines.error.CountRows"));
    }

    @Test(enabled = true)
    public void Ordre_satLnkInvoiceInvoiceLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkInvoiceInvoiceLines.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Ordre_lnkInvoiceCompany() throws SQLException, IOException {
        //В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkInvoiceCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkInvoiceCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
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

    @Test(enabled = true)
    public void Sag_lnkFileLinerFileROE() throws SQLException, IOException {
        getPropertiesFile();
        //В таблицу Sag при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
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
        //В таблицу Sag при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition_EXP = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations_EXP.condition.CountRows"));
        System.out.println("countRowByCondition_EXP: " + countRowByCondition_EXP);
        int countRowByCondition_IMP = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations_IMP.condition.CountRows"));
        System.out.println("countRowByCondition_IMP: " + countRowByCondition_IMP);
        int countRowByCondition_bothNull = getCountRowOfHub(properties.getProperty("lnkFileLinerLocations_bothNull.condition.CountRows"));
        System.out.println("countRowByCondition_bothNull: " + countRowByCondition_bothNull);
        int countRowByCondition = countRowByCondition_EXP + countRowByCondition_IMP + countRowByCondition_bothNull;
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
        //В таблицу Sag при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkFileLinerCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void SagKurs_lnkFileROECompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkFileROECompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileROECompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void ShipKurs_lnkDailyRoeCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkDailyRoeCompany.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDailyRoeCompany.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }


    @Test(enabled = true)
    public void BookingCargoChargesFct_InitialLoad() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInConditionForInitialLoad = getCountRowOfHub(properties.getProperty("bookingCargoCharges.fctCondition_InitialLoad.CountRows"));
        int countRowInFct = getCountRowOfHub(properties.getProperty("bookingCargoCharges.fct.CountRows"));
        assertRowCount(countRowInConditionForInitialLoad, countRowInFct);
    }

    @Test(enabled = true)
    public void BookingCargoChargesFct() throws SQLException, IOException {
        // getPropertiesFile();
        //int countRowInCondition_Union = getPartyId(properties.getProperty("bookingCargoCharges.lnkCondition_Union.CountRows"));
        //int countRowInLnkByCondition = countRowInCondition_ValutaIsNull + countRowInCondition_ValutaIsNotNull;
        // int countRowInLnk = getPartyId(properties.getProperty("bookingCargoCharges.lnk.CountRows"));
        //assertRowCount(countRowInCondition_Union, countRowInLnk);
    }

    @Test(enabled = true)
    public void lnkShipItConstantsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkShipItConstantsCompany.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkShipItConstantsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookBemInternal_lnkBookingInternalRemarksCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkBookingInternalRemarksCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkBookingInternalRemarksCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void Kontor_lnkBranchCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkBranchCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkBranchCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void Commodity_lnkCommodityHsCodesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkCommodityHsCodesCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkCommodityHsCodesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ContRules_lnkContainerDemurrageRulesCompany() throws SQLException, IOException {
        //В таблицу  ContRules при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).")
        // мы их прогружаем в DWH, а затем при первом запуске DiscardAgency удаляем. Это нормально. Контрольный запрос составлен с учетом этой особенности;
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkContainerDemurrageRulesCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkContainerDemurrageRulesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ContBev_lnkContainerMoveTypesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkContainerMoveTypesCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkContainerMoveTypesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void CtsContEvent_lnkContainerStatusEventsGVACompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVACompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVACompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }


    @Test(enabled = true)
    public void CtsContEvent_lnkContainerStatusEventsGVAFullEmpty() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVAFullEmpty.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVAFullEmpty.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void CtsContEvent_satLnkContainerStatusEventsGVAFullEmpty() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVAFullEmpty.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVAFullEmpty.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void CtsContEvent_lnkContainerStatusEventsGVALocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_DEST_Condition = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.DEST.condition.CountRows"));
        System.out.println("countRowBy_DEST_Condition: " + countRowBy_DEST_Condition);
        int countRowBy_EVENT_LOC_Condition = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.EVENT_LOC.condition.CountRows"));
        System.out.println("countRowBy_EVENT_LOC_Condition: " + countRowBy_EVENT_LOC_Condition);
        int countRowBy_PORT_DISC_Condition = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.PORT_DISC.condition.CountRows"));
        System.out.println("countRowBy_PORT_DISC_Condition: " + countRowBy_PORT_DISC_Condition);
        int countRowBy_PORT_LOAD_Condition = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.PORT_LOAD.condition.CountRows"));
        System.out.println("countRowBy_PORT_LOAD_Condition: " + countRowBy_PORT_LOAD_Condition);
        int countRowBy_PORT_TRANS_Condition = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.PORT_TRANS.condition.CountRows"));
        System.out.println("countRowBy_PORT_TRANS_Condition: " + countRowBy_PORT_TRANS_Condition);
        int countRowByCondition = countRowBy_DEST_Condition + countRowBy_EVENT_LOC_Condition + countRowBy_PORT_DISC_Condition + countRowBy_PORT_LOAD_Condition
                + countRowBy_PORT_TRANS_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void CtsContEvent_satLnkContainerStatusEventsGVALocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerStatusEventsGVALocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Vgm_lnkContainerVgmCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkContainerVgmCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkContainerVgmCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }


    @Test(enabled = true)
    public void Vgm_lnkContainerVgmVgmCode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_SOURCE_TYPE_Condition = getCountRowOfHub(properties.getProperty("lnkContainerVgmVgmCode.SOURCE_TYPE.condition.CountRows"));
        System.out.println("countRowBy_SOURCE_TYPE_Condition: " + countRowBy_SOURCE_TYPE_Condition);
        int countRowBy_GEN_STATUS_Condition = getCountRowOfHub(properties.getProperty("lnkContainerVgmVgmCode.GEN_STATUS.condition.CountRows"));
        System.out.println("countRowBy_GEN_STATUS_Condition: " + countRowBy_GEN_STATUS_Condition);
        int countRowByCondition = countRowBy_SOURCE_TYPE_Condition + countRowBy_GEN_STATUS_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerVgmVgmCode.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Vgm_satLnkContainerVgmVgmCode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerVgmVgmCode.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerVgmVgmCode.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Demurrage_lnkDemurrageStorageCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void Demurrage_lnkDemurrageStorageContainerDemurrageRules() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_STD_RULE_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerDemurrageRules.STD_RULE.condition.CountRows"));
        System.out.println("countRowBy_STD_RULE_Condition: " + countRowBy_STD_RULE_Condition);
        int countRowBy_FACT_RULE_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerDemurrageRules.FACT_RULE.condition.CountRows"));
        System.out.println("countRowBy_FACT_RULE_Condition: " + countRowBy_FACT_RULE_Condition);
        int countRowByCondition = countRowBy_STD_RULE_Condition + countRowBy_FACT_RULE_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerDemurrageRules.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        //checkErrors(properties.getProperty("lnkDemurrageStorageContainerDemurrageRules.error.CountRows")); // ошибки проверяла- все те, что нужны ()
    }

    @Test(enabled = true)
    public void Demurrage_satLnkDemurrageStorageContainerDemurrageRules() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerDemurrageRules.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerDemurrageRules.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Demurrage_lnkDemurrageStorageContainerMoveTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_START_MOVE_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerMoveTypes.START_MOVE.condition.CountRows"));
        System.out.println("countRowBy_START_MOVE_Condition: " + countRowBy_START_MOVE_Condition);
        int countRowBy_END_MOVE_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerMoveTypes.END_MOVE.condition.CountRows"));
        System.out.println("countRowBy_END_MOVE_Condition: " + countRowBy_END_MOVE_Condition);
        int countRowByCondition = countRowBy_START_MOVE_Condition + countRowBy_END_MOVE_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerMoveTypes.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkDemurrageStorageContainerMoveTypes.error.CountRows"));
    }

    @Test(enabled = true)
    public void Demurrage_satLnkDemurrageStorageContainerMoveTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerMoveTypes.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageContainerMoveTypes.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Demurrage_lnkDemurrageStorageInvoice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageInvoice.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageInvoice.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        //checkErrors(properties.getProperty("lnkDemurrageStorageInvoice.error.CountRows")); // ошибки проверяла- все те, что нужны ()
    }

    @Test(enabled = true)
    public void Demurrage_satLnkDemurrageStorageInvoice() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageInvoice.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageInvoice.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Demurrage_lnkDemurrageStorageCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_CLIENT_CURRENCY_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCurrency.CLIENT_CURRENCY.condition.CountRows"));
        System.out.println("countRowBy_CLIENT_CURRENCY_Condition: " + countRowBy_CLIENT_CURRENCY_Condition);
        int countRowBy_DEMURRAGE_CURRENCY_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCurrency.DEMURRAGE_CURRENCY.condition.CountRows"));
        System.out.println("countRowBy_DEMURRAGE_CURRENCY_Condition: " + countRowBy_DEMURRAGE_CURRENCY_Condition);
        int countRowBy_STD_CURRENCY_Condition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCurrency.STD_CURRENCY.condition.CountRows"));
        System.out.println("countRowBy_STD_CURRENCY_Condition: " + countRowBy_STD_CURRENCY_Condition);
        int countRowByCondition = countRowBy_CLIENT_CURRENCY_Condition + countRowBy_DEMURRAGE_CURRENCY_Condition + countRowBy_STD_CURRENCY_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCurrency.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Demurrage_satLnkDemurrageStorageCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCurrency.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCurrency.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Service_lnkServiceCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkServiceCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkServiceCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }


    @Test(enabled = true)
    public void Henvis_lnkSourceSystemUsersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkSourceSystemUsersCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkSourceSystemUsersCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void EdiKonv_lnkTerminalsCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkTerminalsCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkTerminalsCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void StedBar_lnkServiceNamesCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkServiceNamesCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkServiceNamesCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void Ibox_lnkIboxDataCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("lnkIboxDataCompany.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("lnkIboxDataCompany.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void Ibox_lnkIboxData() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Кол-во записей по условиям, с которым сравнивается кол-во записей в целевой таблице");
        System.out.println("берутся из вспомогательной таблицы, которую заполняет отдельный тест (см классс IboxTest.class).");
        System.out.println("Он выполняется долго, поэтому сюда не включен. Выполнить его отдельно.");
        int countRowInHub = getCountRowOfHub(properties.getProperty("iBoxData.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("iBoxData.lnk.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoContMaster() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCargoContMaster.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContMaster.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingCargoContMaster.error.CountRows"));
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoContMaster() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContMaster.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContMaster.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoContainerMoves() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerMoves.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerMoves.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingCargoContainerMoves.error.CountRows"));
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoContainerMoves() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerMoves.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerMoves.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoContainerStatusEventsGVA() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerStatusEventsGVA.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerStatusEventsGVA.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingCargoContainerStatusEventsGVA.error.CountRows"));
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoContainerStatusEventsGVA() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerStatusEventsGVA.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerStatusEventsGVA.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void BookGods_lnkBookingCargoContainerVgm() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerVgm.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerVgm.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingCargoContainerVgm.error.CountRows"));
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoContainerVgm() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerVgm.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoContainerVgm.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoDemurrageStorage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCargoDemurrageStorage.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoDemurrageStorage.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
        checkErrors(properties.getProperty("lnkBookingCargoDemurrageStorage.error.CountRows"));
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoDemurrageStorage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoDemurrageStorage.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoDemurrageStorage.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void BookGods_lnkBookingCargoCommodityHsCodes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkBookingCargoCommodityHsCodes.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoCommodityHsCodes.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void BookGods_satLnkBookingCargoCommodityHsCodes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoCommodityHsCodes.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkBookingCargoCommodityHsCodes.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void ContRep_lnkContainerMovesContainerMoveTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerMoveTypes.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerMoveTypes.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);

    }

    @Test(enabled = true)
    public void ContRep_satLnkContainerMovesContainerMoveTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerMoveTypes.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkContainerMovesContainerMoveTypes.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Kunde_lnkCustomersSourceSystemUsers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_KAM_Condition = getCountRowOfHub(properties.getProperty("lnkCustomersSourceSystemUsers.KAM.condition.CountRows"));
        System.out.println("countRowBy_KAM_Condition: " + countRowBy_KAM_Condition);
        int countRowBy_KAM2_Condition = getCountRowOfHub(properties.getProperty("lnkCustomersSourceSystemUsers.KAM2.condition.CountRows"));
        System.out.println("countRowBy_KAM2_Condition: " + countRowBy_KAM2_Condition);
        int countRowByCondition = countRowBy_KAM_Condition + countRowBy_KAM2_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersSourceSystemUsers.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Kunde_satLnkCustomersSourceSystemUsers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkCustomersSourceSystemUsers.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkCustomersSourceSystemUsers.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    @Test(enabled = true)
    public void Sag_lnkFileLinerService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkFileLinerService.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerService.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Sag_satLnkFileLinerService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFileLinerService.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFileLinerService.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void TrpCosts_lnkFeederCostsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_POL_Condition = getCountRowOfHub(properties.getProperty("lnkFeederCostsLocations.POL.condition.CountRows"));
        System.out.println("countRowBy_POL_Condition: " + countRowBy_POL_Condition);
        int countRowBy_POD_Condition = getCountRowOfHub(properties.getProperty("lnkFeederCostsLocations.POD.condition.CountRows"));
        System.out.println("countRowBy_POD_Condition: " + countRowBy_POD_Condition);
        int countRowByCondition = countRowBy_POL_Condition + countRowBy_POD_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFeederCostsLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void TrpCosts_satLnkFeederCostsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFeederCostsLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFeederCostsLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void FemeTrpCosts_lnkFemeFeederCostsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowBy_POL_Condition = getCountRowOfHub(properties.getProperty("lnkFemeFeederCostsLocations.POL.condition.CountRows"));
        System.out.println("countRowBy_POL_Condition: " + countRowBy_POL_Condition);
        int countRowBy_POD_Condition = getCountRowOfHub(properties.getProperty("lnkFemeFeederCostsLocations.POD.condition.CountRows"));
        System.out.println("countRowBy_POD_Condition: " + countRowBy_POD_Condition);
        int countRowByCondition = countRowBy_POL_Condition + countRowBy_POD_Condition;
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFemeFeederCostsLocations.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void FemeTrpCosts_satLnkFemeFeederCostsLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkFemeFeederCostsLocations.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkFemeFeederCostsLocations.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void Demurrage_lnkDemurrageStorageCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCustomer.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCustomer.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void Demurrage_satLnkDemurrageStorageCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCustomer.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkDemurrageStorageCustomer.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }

    @Test(enabled = true)
    public void LoopSearch_lnkLoopSearch() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowOfHub(properties.getProperty("lnkLoopSearch.condition.CountRows"));
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLoopSearch.lnk.CountRows"));
        assertRowCount(countRowByCondition, countRowInLink);
    }

    @Test(enabled = true)
    public void LoopSearch_satLnkLoopSearch() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowOfHub(properties.getProperty("lnkLoopSearch.lnk.CountRows"));
        int countRowInSatLink = getCountRowOfHub(properties.getProperty("lnkLoopSearch.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/linksCountsSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows by condition [" + countInSource + "], in LinkTable [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    private void checkErrors(String sql) throws SQLException {
        int countRowInErrorLogTable = getCountRowInSA(sql);
        if (countRowInErrorLogTable == 0) {
            System.out.println("Link is optional. Checking errorTable. No errors in ErrorLogTable. It's expected!");

        } else {
            System.err.println("В ErrorLogTable есть [" + countRowInErrorLogTable +
                    "] записей об ошибках. Проверить их! если линк опциональный - не должно быть записей о ненахождении записей во втором хабе.");
            System.err.println(" Или не должно быть записей о ненахождении хаба для полей - участников условия построение линка = null. См спеку. ");
            assertThat(0, equalTo(1));
        }
    }

    public int getCountRowOfHub(String hubSQL) throws SQLException {
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
