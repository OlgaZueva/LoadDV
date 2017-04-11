package TietoRus.system.helpers.helpers;

import TietoRus.system.helpers.models.AccountingTransaction;
import TietoRus.system.helpers.models.FileLiner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Asserts {

    public void assertAccountingTransactionHubs(AccountingTransaction hubfromSA, AccountingTransaction hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }

    public void assertFileLinerHubs(FileLiner hubfromSA, FileLiner hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }
}
