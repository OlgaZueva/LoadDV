package TietoRus.system.helpers.helpers;

import TietoRus.system.helpers.models.AccountingTransactionHub;
import TietoRus.system.helpers.models.FileLinerHub;
import TietoRus.system.helpers.models.FileLinerSat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Asserts {

    public void assertAccountingTransactionHubs(AccountingTransactionHub hubfromSA, AccountingTransactionHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertFileLinerHubs(FileLinerHub hubfromSA, FileLinerHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertFileLinerSat(FileLinerSat satfromSA, FileLinerSat satFromDWH) {
        assertThat(satFromDWH, equalTo(satfromSA));
    }
}
