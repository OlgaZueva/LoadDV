package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.GetDataHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HubsCounts {
    private Properties properties = new Properties();
    private GetDataHelper dh = new GetDataHelper();

    @Test(enabled = false)
    public void hubPaymentsDataFromSource() throws SQLException, IOException {
        getPropertiesFile();
        String sqlMSCRUS = properties.getProperty("abpost.MSCRUS.CountRow");
        String sqlUNITY = properties.getProperty("abpost.UNITY.CountRow");
        String sqlInView = properties.getProperty("abpost.View.CountRow");
        int countRowInMSCRUS = dh.getCountRowInSA(sqlMSCRUS);
        int countRowInUNITY = dh.getCountRowInSA(sqlUNITY);
        int countRowInView = dh.getCountRowInSA(sqlInView);
        System.out.println("countRowInMSCRUS: " + countRowInMSCRUS);
        System.out.println("countRowInUNITY: " + countRowInUNITY);
        System.out.println("countRowInView: " + countRowInView);
        System.out.println("SUM: " + (countRowInMSCRUS + countRowInUNITY));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = false)
    public void hubPaymentsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        String sqlSAViewDistinct = properties.getProperty("abpost.ViewDistinct.CountRow");
        String sqlHub = properties.getProperty("abpost.HUB.Payments.CountRow");
        int countRowInViewDistinct = dh.getCountRowInSA(sqlSAViewDistinct);
        int countRowInHub = dh.getCountRowOfHub(sqlHub);
        System.out.println("countRowInViewDistinct: " + countRowInViewDistinct);
        System.out.println("sqlHub: " + sqlHub);
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }





    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/hubsCount.properties"))));
    }

    public void assertRowCount(int countInSource, int countInSA) {
        System.out.println("Count rows in Source [" + countInSource + "], in SA [" + countInSA + "]");
        assertThat(countInSA, equalTo(countInSource));
    }
}
