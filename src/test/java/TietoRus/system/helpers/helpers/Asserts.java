package TietoRus.system.helpers.helpers;

import TietoRus.system.helpers.models.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Asserts {

    public void assertAccountingTransactionHub(AccountingTransactionHub hubfromSA, AccountingTransactionHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertAccountingTransactionSat(AccountingTransactionSat satfromSA, AccountingTransactionSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertBookingCargoChargesSat(BookingCargoChargesSat satfromSA, BookingCargoChargesSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertBookingCargoHub(BookingCargoHub hubfromSA, BookingCargoHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertBookingCargoSat(BookingCargoSat satfromSA, BookingCargoSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertBookingManifestAdditionalsHub(BookingManifestAdditionalsHub hubfromSA, BookingManifestAdditionalsHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertBookingManifestAdditionalsSat(BookingManifestAdditionalsSat satfromSA, BookingManifestAdditionalsSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertCompanyHub(CompanyHub hubfromSA, CompanyHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertCompanySat(CompanySat satfromSA, CompanySat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertContainerTypeSpecEquipHub(ContainerTypeSpecEquipHub hubfromSA, ContainerTypeSpecEquipHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertContainerTypeSpecEquipSat(ContainerTypeSpecEquipSat satfromSA, ContainerTypeSpecEquipSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertContainerTypeHub(ContainerTypeHub hubfromSA, ContainerTypeHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertContainerTypeSat(ContainerTypeSat satfromSA, ContainerTypeSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertCurrencyHub(CurrencyHub hubfromSA, CurrencyHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertCurrencySat(CurrencySat satfromSA, CurrencySat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertFileLinerHub(FileLinerHub hubfromSA, FileLinerHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertFileLinerSat(FileLinerSat satfromSA, FileLinerSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertOceanVesselStatusHub(OceanVesselStatusHub hubfromSA, OceanVesselStatusHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertOceanVesselStatusSat(OceanVesselStatusSat satfromSA, OceanVesselStatusSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }

    public void assertVesselRegistryHub(VesselRegistryHub hubfromSA, VesselRegistryHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertVesselRegistrySat(VesselRegistrySat satfromSA, VesselRegistrySat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }
}
