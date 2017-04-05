package TietoRus.helpers;

import TietoRus.models.FileLiner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Asserts {
    public void assertHubs(FileLiner hubfromSA, FileLiner hubFromDWH) {
        assertThat(hubFromDWH, equalTo(hubfromSA));
    }
}
