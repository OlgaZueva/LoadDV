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
}
