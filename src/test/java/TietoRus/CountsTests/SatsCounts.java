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

public class SatsCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

    /*-------------------------------------------------------------
   Блок для таблиц, которые перезагружаются полностью, нет изменений из CDC, поэтому тут действует правило: сколько хабов - столько сатов и сат статусов.
   При загрузке сатов существующая запись в рамках ключа хаба удаляется и вставляется новая.
    *///-----------------------------------------------------------

    @Test(enabled = true)
    public void ControllingOfficeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("controllingOffice.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("controllingOffice.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ControllingOfficeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("controllingOffice.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("controllingOffice.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerTypeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerType.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerType.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerTypeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerType.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerType.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CompanySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("company.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("company.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void CompanyStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("company.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("company.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ShipItConstantsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("shipItConstants.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("shipItConstants.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ShipItConstantsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("shipItConstants.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("shipItConstants.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

/*-------------------------------------------------------------
    Конец блока для таблиц, которые перезагружаются полностью.
*///-----------------------------------------------------------


    /*-------------------------------------------------------------
Блок для таблиц, изменения по которым получаем из CDC, но саты каждый раз создаем новые, поэтому тут действует правило: сколько хабов - столько сатов и сат статусов.
Такие таблицы называем "без версионирования".
При загрузке сатов существующая запись в рамках ключа хаба удаляется и вставляется новая.
*///-----------------------------------------------------------


    /*-------------------------------------------------------------
    Конец блока для таблиц без версионирования
*///-----------------------------------------------------------


        /*-------------------------------------------------------------
Блок для таблиц, изменения по которым получаем из CDC и храним историю (версии)
Такие таблицы называем "с версионированием".
Т.о. для одного dwhIdHub может быть несколько записей в sat satStatus'е.
Для записей с cdcOparation = 'D ' sat не создается - только satStatus (у которого status =0 (удален))
*///-----------------------------------------------------------

    @Test(enabled = true)
    public void ExportVesselsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("exportVessels.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("exportVessels.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ExportVesselsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("exportVessels.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("exportVessels.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("booking.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("booking.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("booking.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("booking.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingChargeLinesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingChargeLines.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingChargeLines.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingChargeLinesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingChargeLines.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingChargeLines.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingChargesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingCharges.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCharges.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingChargesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingCharges.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingCharges.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingHaulageDetailsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingHaulageDetailsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingManifestAdditionalsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingManifestAdditionalsSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    //Для  satBookingNonManifestedHaulage используется hubBooking, своего hab'а нет. SatStatus тоже нет
    public void BookingNonManifestedHaulageSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingNonManifestedHaulage.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingNonManifestedHaulage.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingOceanVesselSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingOceanVessel.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingOceanVessel.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingOceanVessel.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingOceanVessel.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void PaymentsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("payments.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("payments.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void PaymentsSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("payments.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("payments.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void WeekendsHolidaysSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("weekendsHolidays.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("weekendsHolidays.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void WeekendsHolidaysStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("weekendsHolidays.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("weekendsHolidays.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    //SatStatus'а нет -  строится на hubContainerLocation + EdiKonv
    public void ContainerLocationClocSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerLocationCloc.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerLocationCloc.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    //SatStatus'а нет -  строится на hubContainerLocation + EdiKonv
    public void ContainerLocationLocSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerLocationLoc.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerLocationLoc.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void CorrectorRemarkTypesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void CorrectorRemarkTypesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void SublocationSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("sublocation.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("sublocation.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void SublocationStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("sublocation.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("sublocation.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void TransportModeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("transportMode.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("transportMode.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void TransportModeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("transportMode.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("transportMode.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerLocationSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerLocation.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerLocation.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerLocationStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerLocation.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerLocation.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void VesselRegistrySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("vesselRegistry.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("vesselRegistry.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void VesselRegistryStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("vesselRegistry.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("vesselRegistry.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CountrySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("country.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("country.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void CountryStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("country.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("country.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void LocationsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("locations.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("locations.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void LocationsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("locations.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("locations.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CurrencySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("currency.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("currency.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void CurrencyStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("currency.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("currency.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void InvoiceLinesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("invoiceLines.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("invoiceLines.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void InvoiceLinesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("invoiceLines.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("invoiceLines.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void FileROESat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("fileROE.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("fileROE.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void FileROEStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("fileROE.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("fileROE.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void DailyRoeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("dailyRoe.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("dailyRoe.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void DailyRoeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("dailyRoe.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("dailyRoe.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void WorkingTimeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("workingTime.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("workingTime.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void WorkingTimeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("workingTime.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("workingTime.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingInternalRemarksSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("bookingInternalRemarks.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingInternalRemarks.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingInternalRemarksStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("bookingInternalRemarks.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingInternalRemarks.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CommodityHsCodesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("commodityHsCodes.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("commodityHsCodes.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void CommodityHsCodesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("commodityHsCodes.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("commodityHsCodes.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void TerminalsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("terminals.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("terminals.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void TerminalsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("terminals.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("terminals.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    //SatStatus'а нет -  строится на hubCompany + EdiKonv
    public void CompanyDemurrageEurDefaultSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("companyDemurrageEurDefault.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("companyDemurrageEurDefault.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }


    @Test(enabled = true)
    public void BranchSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("branch.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("branch.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BranchStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("branch.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("branch.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ServiceNamesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("serviceNames.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("serviceNames.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ServiceNamesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("serviceNames.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("serviceNames.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ServiceSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("service.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("service.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ServiceStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("service.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("service.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerVgmSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerVgm.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerVgm.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerVgmStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerVgm.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerVgm.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void VgmCodeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("vgmCode.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("vgmCode.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void VgmCodeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("vgmCode.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("vgmCode.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }
    /*-------------------------------------------------------------
    Конец блока для таблиц с версионированием
*///-----------------------------------------------------------


    @Test(enabled = true)
    //SatStatus'а нет -  строится на hubCompany + EdiKonv
    public void CompanyAgentCodeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("companyAgentCode.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("companyAgentCode.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }


    @Test(enabled = true)
    public void CustomersSat() throws SQLException, IOException {

        CleaningCustomersNames CreatePrecondition = new CleaningCustomersNames();
        CreatePrecondition.FillingDictEmptyCustomer();
        //CreatePrecondition.FillingDictExcludedSymbols();
        getPropertiesFile();

        //int countRowinEmptyCustomertable_Adresse = getCountRowOfHub(properties.getProperty("customers.emptyCustomersAdresse.CountRows"));
        //int countRowinEmptyCustomertable_Kunde = getCountRowOfHub(properties.getProperty("customers.emptyCustomersKunde.CountRows"));
        int countRowInSAbyCondition_Kunde = getCountRowInSA(properties.getProperty("customers.satConditionKunde.CountRows"));
        int countRowInSAbyCondition_Adresse = getCountRowInSA(properties.getProperty("customers.satConditionAdresse.CountRows"));
        int countRowInSAbyCondition_Ibox = getCountRowOfHub(properties.getProperty("customers.satConditionIbox.CountRows"));
        System.out.println("countRowInSAbyCondition_Kunde: " + countRowInSAbyCondition_Kunde);
        System.out.println("countRowInSAbyCondition_Adresse: " + countRowInSAbyCondition_Adresse);
        System.out.println("countRowInSAbyCondition_Ibox: " + countRowInSAbyCondition_Ibox);
        //System.out.println("countRowinEmptyCustomertable_Adresse: " + countRowinEmptyCustomertable_Adresse);
        //System.out.println("countRowinEmptyCustomertable_Kunde: " + countRowinEmptyCustomertable_Kunde);
        int countRowInSAbyCondition = (countRowInSAbyCondition_Kunde + countRowInSAbyCondition_Adresse + countRowInSAbyCondition_Ibox);
        //- (countRowinEmptyCustomertable_Adresse + countRowinEmptyCustomertable_Kunde);
        int countRowInSat = getCountRowOfHub(properties.getProperty("customers.sat.CountRows"));
        assertRowCount(countRowInSAbyCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void Customers_MasterCustomerNameInSat() throws SQLException, IOException {
        getPropertiesFile();
        System.out.println("Тест проверяет правильность определения masterClient по таблице mdm.masterCustomers");
        System.out.println("Таблица mdm.masterCustomers загружается отдельым пакетом");
        System.out.println("dwhIdMasterCliet в satCustomers проставляется пакетами загрузки Customer'ов");
        /*
        int countRowByTMSnumber_Kunde = getCountRowOfHub(properties.getProperty("masterCustomerName.condition_TMSnumber_TMSnumberNotNull_Kunde.CountRows"));
        int countRowByTMSnumber_Adresse = getCountRowOfHub(properties.getProperty("masterCustomerName.condition_TMSnumber_TMSnumberNotNull_Adresse.CountRows"));
        int countRowByCleanNameTMSNotNull_Kunde = getCountRowOfHub(properties.getProperty("masterCustomerName.condition_CleanName_TMSnumberNotNull_Kunde.CountRows"));
        int countRowByCleanNameTMSNotNull_Adresse = getCountRowOfHub(properties.getProperty("masterCustomerName.condition_CleanName_TMSnumberNotNull_Adresse.CountRows"));
        int countRowByCleanNameTMSNull_Kunde = getCountRowOfHub(properties.getProperty("masterCustomerName.condition_CleanName_TMSnumberNull_Kunde.CountRows"));
        int countRowByCleanNameTMSNull_Adresse = getCountRowOfHub(properties.getProperty("masterCustomerName.condition_CleanName_TMSnumberNull_Adresse.CountRows"));
        int countRowByAllConditions = (countRowByTMSnumber_Kunde + countRowByTMSnumber_Adresse +
               countRowByCleanNameTMSNotNull_Kunde + countRowByCleanNameTMSNotNull_Adresse + countRowByCleanNameTMSNull_Kunde + countRowByCleanNameTMSNull_Adresse);
*/
        int countRowByUnionCondition = getCountRowOfHub(properties.getProperty("masterCustomerName.UnionCondition.CountRows"));
        int countRowInSatWithMasterClientId = getCountRowOfHub(properties.getProperty("masterCustomerName.masterClientId.CountRows"));
        assertRowCount(countRowByUnionCondition, countRowInSatWithMasterClientId);
    }

    @Test(enabled = true)
    public void CustomersSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        //int countRowinEmptyCustomertable_Adresse = getCountRowOfHub(properties.getProperty("customers.emptyCustomersAdresse.CountRows"));
        //int countRowinEmptyCustomertable_Kunde = getCountRowOfHub(properties.getProperty("customers.emptyCustomersKunde.CountRows"));
        int countRowInSAbyCondition_Kunde = getCountRowInSA(properties.getProperty("customers.satStatusConditionKunde.CountRows"));
        int countRowInSAbyCondition_Adresse = getCountRowInSA(properties.getProperty("customers.satStatusConditionAdresse.CountRows"));
        int countRowInSAbyCondition_Ibox = getCountRowOfHub(properties.getProperty("customers.satStatusConditionIbox.CountRows"));
        int countRowInSAbyCondition = (countRowInSAbyCondition_Kunde + countRowInSAbyCondition_Adresse + countRowInSAbyCondition_Ibox);
        // - (countRowinEmptyCustomertable_Adresse + countRowinEmptyCustomertable_Kunde));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("customers.satStatus.CountRows"));
        assertRowCount(countRowInSAbyCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void AccountingTransactionSat() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу BogfTrans при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("accountingTransaction.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("accountingTransaction.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void AccountingTransactionSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("accountingTransaction.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("accountingTransaction.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingManifestedHaulageSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingManifestedHaulageStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingEventsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingEvents.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingEvents.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingEventsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingEvents.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingEvents.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingCargoSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingCargo.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCargo.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingCargoStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingCargo.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingCargo.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingManifestSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingManifest.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingManifest.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingManifestStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingManifest.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingManifest.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingDTXFileSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingDTXFile.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingDTXFile.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingDTXFileStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingDTXFile.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingDTXFile.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingEMCRemarksSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingEMCRemarksStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerMovesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerMoves.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerMoves.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerMovesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerMoves.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerMoves.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    // SAT для OceanVesselService не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void OceanVesselServiceStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("oceanVesselService.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("oceanVesselService.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    // SAT для OceanVesselStatus не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void OceanVesselStatusStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("oceanVesselStatus.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("oceanVesselStatus.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    // SAT для ImsChargeLines не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void ImsChargeLinesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("imsChargeLines.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("imsChargeLines.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void SpecialContractTypesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("specialContractTypes.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("specialContractTypes.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void SpecialContractTypesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("specialContractTypes.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("specialContractTypes.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void InvoicePostingSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("invoicePosting.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("invoicePosting.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void InvoicePostingStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("invoicePosting.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("invoicePosting.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void InvoiceSat() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу Invoice при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("invoice.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("invoice.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void InvoiceStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("invoice.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("invoice.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void FileLinerSat() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу Sag при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("fileLiner.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("fileLiner.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void FileLinerStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("fileLiner.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("fileLiner.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }


    //У ContainerTypeSpecEquip SatStatus'а нет. HUB грузится из EXCEL'я
    @Test(enabled = true)
    public void ContainerTypeSpecEquipSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }


    //У ControllingOfficeAuxLocation нет SatStatus'а. HUB'ом является hubControllingOffice, satStatus которому проставляется пакетом satControllingOfficeStatus
    @Test(enabled = true)
    public void ControllingOfficeAuxLocationSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowsInSA = getCountRowOfHub(properties.getProperty("controllingOfficeAuxLocation.ViewDistinct.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("controllingOfficeAuxLocation.sat.CountRows"));
        assertRowCount(countRowsInSA, countRowInSat);
    }

    // у LocationsPortsOverviewSat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void LocationsPortsOverviewSat() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Для Узбекистана и Казахстана размножили записи в файле - у них теперь один и тот же LOCATION_CODE, но разные AGENCY_ID,");
        System.err.println("поэтому на один хаб для таких будет по два сата сразу, при первоначальной загрузке.");
        System.err.println("С учетом этого надо и искать различия, если число не совпадет. Контрольный запрос поможет");
        int countRowInSat = getCountRowOfHub(properties.getProperty("locationsPortsOverview.sat.CountRows"));
        int countRowSAByConditionForSat = getCountRowInSA(properties.getProperty("locationsPortsOverview.satCondition.CountRows"));
        assertRowCount(countRowSAByConditionForSat, countRowInSat);
    }

    // у BookingReportingCustomerSat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void BookingReportingCustomerSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowInSA(properties.getProperty("bookingReportingCustomer.condition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    //Sat вспомогательный, для того чтобы собрать доп инфу по кастомерам из бука. Формируется по таблице Book. SatStatus' (о создается пакетом satBooking)
    @Test(enabled = true)
    public void BookingCustomersSat() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу Book при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (некие старые буки, которые нужны для новых сущностей).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSAByCondition = getCountRowOfHub(properties.getProperty("booking.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCustomers.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }


    @Test(enabled = true)
    public void ContainerMoveTypesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerMoveTypes.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerMoveTypes.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerMoveTypesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerMoveTypes.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerMoveTypes.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContMasterSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("contMaster.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("contMaster.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContMasterStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("contMaster.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("contMaster.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }


    @Test(enabled = true)
    public void ContainerDemurrageRulesSat() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("В таблицу ContRule при первоначальной загрузке загружаются данные, которые попадают под условия удаления механизмом DisсardAgency (это кривые даты).");
        System.err.println("С заказчиком это обсуждалось - эти записи нужны и должны быть прогружены в DHW");
        System.err.println("Поскольку после первоначальной загрузки мы не запускаем механизм DisсardAgency, то эти записи загрузим в DWH,");
        System.err.println("а далее, после первой загрузки изменений механизм запускаем регулярно и данные т.о. удалим. Это ожидаемо. Контрольный запрос составлен с учетом этой особенности");
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerDemurrageRules.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerDemurrageRules.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerDemurrageRulesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerDemurrageRules.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerDemurrageRules.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerStatusEventsGVASat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerStatusEventsGVA.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerStatusEventsGVA.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerStatusEventsGVAStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("containerStatusEventsGVA.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerStatusEventsGVA.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    // у MarketShareTier4Sat SatStatus'а нет. Hub грузится из EXCEL'я
    public void MarketShareTier4Sat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByConditionFromEXCEL = getCountRowInSA(properties.getProperty("marketShareTier4.EXCELDataCondition.CountRows"));
        int countRowInSatFromEXCEL = getCountRowOfHub(properties.getProperty("marketShareTier4.EXCELData.CountRows"));
        assertRowCount(countRowInSAByConditionFromEXCEL, countRowInSatFromEXCEL);
    }

    @Test(enabled = true)
    // у MarketShareTier5Sat SatStatus'а нет. Hub грузится из EXCEL'я
    public void MarketShareTier5Sat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("marketShareTier5.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("marketShareTier5.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void SourceSystemUsersSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("sourceSystemUsers.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("sourceSystemUsers.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }

    @Test(enabled = true)
    public void SourceSystemUsersStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("sourceSystemUsers.satStatusCondition.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("sourceSystemUsers.satStatus.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSatHubStatus);
    }

    // у ExpectedImportGVASat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void ExpectedImportGVASat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowInSA(properties.getProperty("expectedImportGVA.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("expectedImportGVA.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    // у FeederCostsSat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void FeederCostsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowInSA(properties.getProperty("feederCosts.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("feederCosts.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    // у FemeFeederCostsSat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void FemeFeederCostsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowInSA(properties.getProperty("femeFeederCosts.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("femeFeederCosts.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    // у IboxSat SatStatus'а нет. Hub грузится из EXCEL'я + дополнительные алгоритмы
    public void IboxSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSAByCondition = getCountRowInSA(properties.getProperty("iBox.satCondition.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("iBox.sat.CountRows"));
        assertRowCount(countRowInSAByCondition, countRowInSat);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/satsCountsSQL.properties"))));
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

