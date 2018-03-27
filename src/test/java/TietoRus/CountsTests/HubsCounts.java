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

    /*
    Каждый тест содержит контрольный запрос (выводящий число подходящих записей) на SA-таблицу и запрос кол-ва записей в hub_таблице и сравнивает их.
    Пракчтически все хабы в ключе содержат SELSKAB, который может быть удалени при какой-либо из загрузок изменений (до удаления записи с ним могли быть справедливо загружены).
    Контрольный запрос учитывает такие записи.
    Кроме того, при первоначальной загрузке в таблицы BOGF_TRANS, SAG, BOOK, ORDRE и CONT_RULES попадают записи, подходяшие под условия удаления механизмомо DisсardAgency.
    Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,
    а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо.
    С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW
    Это какие то старые буки и записи с "кривыми"датами, которые нужны, мы их загружаем и контрольные запросы на хабы, создающиеся на этих таблицах это учитывают.
   */


    /*-------------------------------------------------------------
Блок для таблиц, которые перезагружаются полностью, нет изменений из CDC, поэтому тут действует правило: сколько хабов - столько сатов и сат статусов.
При загрузке сатов существующая запись в рамках ключа хаба удаляется и вставляется новая.
Т.к. хабы строятся на перезагружаемых полностью таблицфх, то могут быть нюансы: если хаб был создан, потом запись физически удалили из источника, то
хабов будет больше, чем расчетное число, потому как контрольный запрос идет на SA-таблицы.
*///-----------------------------------------------------------

    @Test(enabled = true)
    public void hubControllingOffice() throws SQLException, IOException {
        System.out.println("В случае паденя теста см комментарий к блоку тестов");
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("controllingOffice.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOffice.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerType() throws SQLException, IOException {
        System.out.println("В случае паденя теста см комментарий к блоку тестов");
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("containerType.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerType.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCompany() throws SQLException, IOException {
        /*
        В ключе есть SELSKAB, который может быть удалени при какой-либо из загрузок изменений (до удаления записи с ним могля быть справедливо загружены).
        Контрольный запрос учитывает такие ситуации
         */
        System.out.println("В случае паденя теста см комментарий к блоку тестов");
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("company.union.CountRows")) + 1);//fake row
        int countRowInHub = getCountRowOfHub(properties.getProperty("company.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubShipItConstants() throws SQLException, IOException {
        System.out.println("В случае паденя теста см комментарий к блоку тестов");
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("shipItConstants.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("shipItConstants.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    /*-------------------------------------------------------------
    Конец блока для таблиц, которые перезагружаются полностью.
*///-----------------------------------------------------------


    @Test(enabled = true)
    public void hubWeekendsHolidays() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("weekendsHolidays.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("weekendsHolidays.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
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
        CleaningCustomersNames CreatePrecondition = new CleaningCustomersNames();
        CreatePrecondition.FillingDictEmptyCustomer();
        getPropertiesFile();
        /*
        int countRowinEmptyCustomertable_Adresse  = getCountRowOfHub(properties.getProperty("customersAdresse.emptyCustomers.CountRows"));
        System.out.println("countRowinEmptyCustomertable_Adresse: " + countRowinEmptyCustomertable_Adresse);
        int countRowinEmptyCustomertable_Kunde  = getCountRowOfHub(properties.getProperty("customersKunde.emptyCustomers.CountRows"));
        System.out.println("countRowinEmptyCustomertable_Kunde: " + countRowinEmptyCustomertable_Kunde);
        */
        int countRowInKundeInSA = getCountRowInSA(properties.getProperty("customersKunde.union.CountRows"));
        System.out.println("countRowInKundeInSA: " + countRowInKundeInSA);
        int countRowInAdresseInSA = getCountRowInSA(properties.getProperty("customersAdresse.union.CountRows"));
        System.out.println("countRowInAdresseInSA:" + countRowInAdresseInSA);
        int countRowInIboxInSA = getCountRowInSA(properties.getProperty("customersIbox.union.CountRows"));
        System.out.println("countRowInIboxInSA:" + countRowInIboxInSA);
        int countRowInHubFromIbox = getCountRowOfHub(properties.getProperty("customersIbox.hub.CountRows"));
        System.out.println("countRowInHubFromIbox:" + countRowInHubFromIbox);
        int countRowInSA = ((countRowInKundeInSA + countRowInAdresseInSA + countRowInIboxInSA) + 1);  //One fake records
        //- (countRowinEmptyCustomertable_Adresse + countRowinEmptyCustomertable_Kunde)+ 1); //One fake records
        int countRowInHub = getCountRowOfHub(properties.getProperty("customers.hub.CountRows"));
        assertRowCount(countRowInIboxInSA, countRowInHubFromIbox);
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void masterCustomers() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Тест проверяет правильность загрузки таблицы mdm.masterCustomers");
        System.out.println("Таблица mdm.masterCustomers загружается отдельным пакетом");
        int countRowInMDS_AllCondition = getCountRowInMDS(properties.getProperty("masterCustomers.MDS.CountRows"));
        int countRowInMDS_TMSNumberNull = getCountRowInMDS(properties.getProperty("masterCustomers.MDS_WithTMSNumberIsnull.CountRows"));
        int countRowInMDS = countRowInMDS_AllCondition - countRowInMDS_TMSNumberNull;
        int countRowInDWH = getCountRowOfHub(properties.getProperty("masterCustomers.DWH.CountRows"));
        assertRowCount(countRowInMDS, countRowInDWH);
    }

    @Test(enabled = true)
    public void hubAccountingTransaction() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу BogfTrans при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSA = getCountRowInSA(properties.getProperty("accountingTransaction.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("accountingTransaction.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBooking() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSA = getCountRowInSA(properties.getProperty("booking.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("booking.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestAdditionals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingManifestAdditionals.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEvents() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingEvents.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEvents.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingCharges.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCharges.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingCargo.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargo.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingHaulageDetails() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("bookingHaulageDetails.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingChargeLines.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingChargeLines.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingManifest.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifest.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingDTXFile() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingDTXFile.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingDTXFile.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingEMCRemarks() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingEMCRemarks.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingOceanVessel() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingOceanVessel.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingOceanVessel.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerMoves() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerMoves.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoves.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubExportVessels() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("exportVessels.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("exportVessels.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoicePosting() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("invoicePosting.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoicePosting.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

     @Test(enabled = true)
    public void hubInvoice() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу Ordre при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSA = getCountRowInSA(properties.getProperty("invoice.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoice.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubInvoiceLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("invoiceLines.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoiceLines.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileLiner() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу Sag при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSA = getCountRowInSA(properties.getProperty("fileLiner.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileLiner.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFileROE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("fileROE.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileROE.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubDailyRoe() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("dailyRoe.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("dailyRoe.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubOceanVesselService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("oceanVesselService.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselService.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("oceanVesselStatus.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselStatus.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubImsChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("imsChargeLines.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("imsChargeLines.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSpecialContractTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("specialContractTypes.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("specialContractTypes.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCorrectorRemarkTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("correctorRemarkTypes.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSublocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("sublocation.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("sublocation.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubTransportMode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("transportMode.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("transportMode.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerLocation() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("containerLocation.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerLocation.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubVesselRegistry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("vesselRegistry.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("vesselRegistry.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCountry() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("country.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("country.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubLocations() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("locations.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("locations.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCurrency() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("currency.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("currency.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingManifestedHaulage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingManifestedHaulage.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubIsoCodeData() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA_isoCode4 = getCountRowInSA(properties.getProperty("isoCode4.union.CountRows"));
        int countRowInSA_isoCode6 = getCountRowInSA(properties.getProperty("isoCode6.union.CountRows"));
        int countRowInSA_isoCode6_2 = getCountRowInSA(properties.getProperty("isoCode6_2.union.CountRows"));
        int countRowInSA_isoCode6_3 = getCountRowInSA(properties.getProperty("isoCode6_3.union.CountRows"));
        int countRowInSA_isoCode6_4 = getCountRowInSA(properties.getProperty("isoCode6_4.union.CountRows"));
        int countRowInSA_isoCode6_5 = getCountRowInSA(properties.getProperty("isoCode6_5.union.CountRows"));
        int countRowInSA = countRowInSA_isoCode4 + countRowInSA_isoCode6 + countRowInSA_isoCode6_2 + countRowInSA_isoCode6_3 +
                countRowInSA_isoCode6_4 + countRowInSA_isoCode6_5;
        int countRowInHub_isoCode4 = getCountRowOfHub(properties.getProperty("isoCode4.hub.CountRows"));
        int countRowInHub_isoCode6 = getCountRowOfHub(properties.getProperty("isoCode6.hub.CountRows"));
        int countRowInHub_isoCode6_2 = getCountRowOfHub(properties.getProperty("isoCode6_2.hub.CountRows"));
        int countRowInHub_isoCode6_3 = getCountRowOfHub(properties.getProperty("isoCode6_3.hub.CountRows"));
        int countRowInHub_isoCode6_4 = getCountRowOfHub(properties.getProperty("isoCode6_4.hub.CountRows"));
        int countRowInHub_isoCode6_5 = getCountRowOfHub(properties.getProperty("isoCode6_5.hub.CountRows"));
        System.out.println("isoCode4 count rows in SA [" + countRowInSA_isoCode4 + "] in DWH [" + countRowInHub_isoCode4 + "]");
        System.out.println("isoCode6 count rows in SA [" + countRowInSA_isoCode6 + "] in DWH [" + countRowInHub_isoCode6 + "]");
        System.out.println("isoCode6_2 count rows in SA [" + countRowInSA_isoCode6_2 + "] in DWH [" + countRowInHub_isoCode6_2 + "]");
        System.out.println("isoCode6_3 count rows in SA [" + countRowInSA_isoCode6_3 + "] in DWH [" + countRowInHub_isoCode6_3 + "]");
        System.out.println("isoCode6_4 count rows in SA [" + countRowInSA_isoCode6_4 + "] in DWH [" + countRowInHub_isoCode6_4 + "]");
        System.out.println("isoCode6_5 count rows in SA [" + countRowInSA_isoCode6_5 + "] in DWH [" + countRowInHub_isoCode6_5 + "]");
        assertRowCount(countRowInSA_isoCode4, countRowInHub_isoCode4);
        assertRowCount(countRowInSA_isoCode6, countRowInHub_isoCode6);
        assertRowCount(countRowInSA_isoCode6_2, countRowInHub_isoCode6_2);
        assertRowCount(countRowInSA_isoCode6_3, countRowInHub_isoCode6_3);
        assertRowCount(countRowInSA_isoCode6_4, countRowInHub_isoCode6_4);
        assertRowCount(countRowInSA_isoCode6_5, countRowInHub_isoCode6_5);
        int countAllRowInHub = getCountRowOfHub(properties.getProperty("isoCodeAll.hub.CountRows"));
        assertRowCount(countRowInSA, countAllRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerTypeSpecEquip() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("containerTypeSpecEquip.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }


    @Test(enabled = true)
    public void hubLocationsPortsOverview() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("locationsPortsOverview.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("locationsPortsOverview.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingReportingCustomer() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingReportingCustomer.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubWorkingTime() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("workingTime.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("workingTime.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBookingInternalRemarks() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("bookingInternalRemarks.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingInternalRemarks.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubCommodityHsCodes() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("для записей в SA с AFDELING is null в хаб записывается знаеяние = -1 (hubCommodityHsCodes.serviceCode = -1)");
        int countRowInSA = (getCountRowInSA(properties.getProperty("commodityHsCodes.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("commodityHsCodes.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerMoveTypes() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerMoveTypes.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoveTypes.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContMaster() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("contMaster.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("contMaster.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }


    @Test(enabled = true)
    public void hubContainerDemurrageRules() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerDemurrageRules.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerDemurrageRules.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerStatusEventsGVA() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerStatusEventsGVA.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerStatusEventsGVA.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubMarketShareTier4() throws SQLException, IOException {
        getPropertiesFile();
        int countRowfromSAExcel = getCountRowInSA(properties.getProperty("marketShareTier4.EXCELData.CountRows"));
        int countRowInHubFromEXCEL = getCountRowOfHub(properties.getProperty("marketShareTier4.hubFromEXCEL.CountRows"));
        assertRowCount(countRowfromSAExcel, countRowInHubFromEXCEL);
    }

    @Test(enabled = true)
    public void hubMarketShareTier5() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("marketShareTier5.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("marketShareTier5.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = false)//Изменена загрузка Demurrage. Отдельный алгоритм. Тест неактуален.
    public void hubDemurrageStorage() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("demurrageStorage.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("demurrageStorage.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubTerminals() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("terminals.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("terminals.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFemeFeederCosts() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("femeFeederCosts.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("femeFeederCosts.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubExpectedImportGVA() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("expectedImportGVA.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("expectedImportGVA.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSourceSystemUsers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = (getCountRowInSA(properties.getProperty("sourceSystemUsers.union.CountRows")) + 1);
        int countRowInHub = getCountRowOfHub(properties.getProperty("sourceSystemUsers.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubIboxData() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("iboxData.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("iboxData.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubBranch() throws SQLException, IOException {
        getPropertiesFile();
        /*
        В ключе есть SELSKAB, который может быть удалени при какой-либо из загрузок изменений (до удаления записи с ним могля быть справедливо загружены).
        Контрольный запрос учитывает такие ситуации
         */
        int countRowInSA = (getCountRowInSA(properties.getProperty("branch.union.CountRows")) + 1);//fake row
        int countRowInHub = getCountRowOfHub(properties.getProperty("branch.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    // Специальная обработка. Создается факт на основе данных EXCEL-файла по отдельному алгоритму.
    public void fctLoopSearch() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("loopSearch.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("loopSearch.hub.CountRows"));
        System.out.println(countRowInHub);
        assertRowCount(countRowInSA, countRowInHub);
    }


    @Test(enabled = true)
    // Специальная обработка. Создается факт на основе данных MSCRUS_DEMURRAGE- таблиц по отдельному алгоритму.
    public void fctDemurrage_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("demurrage.mscrus.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("demurrage.mscrus.hub.CountRows"));
        System.out.println(countRowInHub);
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    // Специальная обработка. Создается факт на основе данных UNITY_DEMURRAGE по отдельному алгоритму.
    public void fctDemurrage_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("demurrage.unity.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("demurrage.unity.hub.CountRows"));
        System.out.println(countRowInHub);
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubSeagoData() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("seagoData.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("seagoData.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubService() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("service.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("service.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubServiceNames() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("serviceNames.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("serviceNames.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubFeederCosts() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("feederCosts.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("feederCosts.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubContainerVgm() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("containerVgm.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerVgm.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    public void hubVgmCode() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("vgmCode.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("vgmCode.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
    }

    @Test(enabled = true)
    // Специальная обработка. Создается факт на основе данных saValidAgency по отдельному алгоритму.
    public void fctValidCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("validCompany.union.CountRows"));
        int countRowInHub = getCountRowOfHub(properties.getProperty("validCompany.fct.CountRows"));
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


    public int getCountRowInMDS(String saSQL) throws SQLException {
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, saSQL);
        int countRowMDS = 0;
        while (rsFromMDS.next()) {
            countRowMDS = Integer.parseInt(rsFromMDS.getString("c"));
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
        return countRowMDS;
    }

}
