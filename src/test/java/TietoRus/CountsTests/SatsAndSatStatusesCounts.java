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

public class SatsAndSatStatusesCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

    @Test(enabled = true)
    public void PaymentsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("payments.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("payments.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void PaymentsSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("payments.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("payments.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CustomersSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("customers.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void CustomersSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("customers.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("customers.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void AccountingTransactionSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("accountingTransaction.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("accountingTransaction.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void AccountingTransactionSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("accountingTransaction.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("accountingTransaction.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("booking.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("booking.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("booking.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingManifestAdditionalsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingManifestAdditionalsSatStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingManifestAdditionals.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingManifestedHaulageSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingManifestedHaulageStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingManifestedHaulage.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    //Для  satBookingNonManifestedHaulage используется hubBooking, своего hab'а нет. HubStatus тоже нет
    @Test(enabled = true)
    public void BookingNonManifestedHaulageSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowsInSATable = getCountRowInSA(properties.getProperty("bookingNonManifestedHaulage.SAViewDistinct.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingNonManifestedHaulage.sat.CountRows"));
        assertRowCount(countRowsInSATable, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingEventsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEvents.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingEvents.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingEventsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEvents.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingEvents.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingChargesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCharges.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCharges.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingChargesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCharges.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingCharges.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingCargoSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargo.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingCargo.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingCargoStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingCargo.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingCargo.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingHaulageDetailsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingHaulageDetailsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingHaulageDetails.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingChargeLinesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingChargeLines.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingChargeLinesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingChargeLines.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingChargeLines.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingManifestSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingManifest.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingManifestStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingManifest.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingManifest.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingDTXFileSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingDTXFile.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingDTXFile.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingDTXFileStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingDTXFile.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingDTXFile.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingEMCRemarksSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingEMCRemarksStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingEMCRemarks.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void BookingOceanVesselSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingOceanVessel.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void BookingOceanVesselStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingOceanVessel.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("bookingOceanVessel.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    // SAT для WeekendsHolidays не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void WeekendsHolidaysStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("weekendsHolidays.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("weekendsHolidays.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ControllingOfficeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("controllingOffice.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ControllingOfficeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOffice.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("controllingOffice.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerTypeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerType.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerTypeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerType.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerType.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerMovesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerMoves.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerMovesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerMoves.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerMoves.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }


    // SAT для OceanVesselService не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void OceanVesselServiceStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselService.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("oceanVesselService.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    // SAT для OceanVesselStatus не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void OceanVesselStatusStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("oceanVesselStatus.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("oceanVesselStatus.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    // SAT для ImsChargeLines не создается. Только SatStatus проверяем.
    @Test(enabled = true)
    public void ImsChargeLinesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("imsChargeLines.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("imsChargeLines.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void SpecialContractTypesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("specialContractTypes.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("specialContractTypes.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void SpecialContractTypesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("specialContractTypes.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("specialContractTypes.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CorrectorRemarkTypesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void CorrectorRemarkTypesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("correctorRemarkTypes.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void SublocationSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("sublocation.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("sublocation.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void SublocationStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("sublocation.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("sublocation.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void TransportModeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("transportMode.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("transportMode.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void TransportModeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("transportMode.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("transportMode.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ExportVesselsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("exportVessels.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ExportVesselsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("exportVessels.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("exportVessels.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void InvoicePostingSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("invoicePosting.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void InvoicePostingStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoicePosting.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("invoicePosting.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ContainerLocationSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerLocation.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerLocation.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ContainerLocationStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerLocation.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("containerLocation.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void VesselRegistrySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("vesselRegistry.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("vesselRegistry.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void VesselRegistryStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("vesselRegistry.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("vesselRegistry.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CountrySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("country.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("country.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void CountryStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("country.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("country.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void LocationsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("locations.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("locations.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void LocationsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("locations.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("locations.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CurrencySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("currency.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("currency.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void CurrencyStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("currency.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("currency.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }


    @Test(enabled = true)
    public void InvoiceSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoice.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("invoice.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void InvoiceStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoice.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("invoice.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void InvoiceLinesSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoiceLines.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("invoiceLines.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void InvoiceLinesStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("invoiceLines.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("invoiceLines.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void FileLinerSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileLiner.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("fileLiner.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void FileLinerStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileLiner.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("fileLiner.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void FileROESat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileROE.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("fileROE.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void FileROEStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("fileROE.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("fileROE.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void DailyRoeSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("dailyRoe.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("dailyRoe.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void DailyRoeStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("dailyRoe.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("dailyRoe.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void CompanySat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("company.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("company.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void CompanyStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("company.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("company.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    @Test(enabled = true)
    public void ShipItConstantsSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("shipItConstants.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("shipItConstants.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    @Test(enabled = true)
    public void ShipItConstantsStatus() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("shipItConstants.HUB.CountRows"));
        int countRowInSatHubStatus = getCountRowOfHub(properties.getProperty("shipItConstants.satStatus.CountRows"));
        assertRowCount(countRowInHub, countRowInSatHubStatus);
    }

    //У ContainerTypeSpecEquip SatStatus'а нет. HUB грузится из EXCEL'я
    @Test(enabled = true)
    public void ContainerTypeSpecEquipSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("containerTypeSpecEquip.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }


    //У ControllingOfficeAuxLocation нет SatStatus'а. HUB'ом является hubControllingOffice, satStatus которому проставляется satControllingOfficeStatus
    @Test(enabled = true)
    public void ControllingOfficeAuxLocationSat() throws SQLException, IOException {
        getPropertiesFile();
        //int countRowInHub = getCountRowOfHub(properties.getProperty("controllingOfficeAuxLocation.HUB.CountRows"));
        int countRowsInSA = getCountRowInSA(properties.getProperty("controllingOfficeAuxLocation.SAViewDistinct.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("controllingOfficeAuxLocation.sat.CountRows"));
        assertRowCount(countRowsInSA, countRowInSat);
    }

    // у LocationsPortsOverviewSat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void LocationsPortsOverviewSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("locationsPortsOverview.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("locationsPortsOverview.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }

    // у BookingReportingCustomerSat SatStatus'а нет. Hub грузится из EXCEL'я
    @Test(enabled = true)
    public void BookingReportingCustomerSat() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInHub = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.HUB.CountRows"));
        int countRowInSat = getCountRowOfHub(properties.getProperty("bookingReportingCustomer.sat.CountRows"));
        assertRowCount(countRowInHub, countRowInSat);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/satsAndSatStatusesCountsSQL.properties"))));
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

