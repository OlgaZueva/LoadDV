package TietoRus.system.helpers.helpers;

import TietoRus.system.helpers.models.AccountingTransaction;
import TietoRus.system.helpers.models.FileLinerHub;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Asserts {

    public void assertAccountingTransactionHubs(AccountingTransaction hubfromSA, AccountingTransaction hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertFileLinerHubs(FileLinerHub hubfromSA, FileLinerHub hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }
}
