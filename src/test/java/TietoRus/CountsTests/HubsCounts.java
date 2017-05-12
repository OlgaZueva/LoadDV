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

    @Test(enabled = true)
    public void hubPaymentsDataFromSAToView() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInMSCRUS = dh.getCountRowInSA(properties.getProperty("abpost.MSCRUS.CountRows"));
        int countRowInUNITY = dh.getCountRowInSA(properties.getProperty("abpost.UNITY.CountRows"));
        int countRowInView = dh.getCountRowInSA(properties.getProperty("abpost.View.CountRows"));
        assertRowCount((countRowInMSCRUS + countRowInUNITY), countRowInView);
    }

    @Test(enabled = true)
    public void hubPaymentsDataFromSAtoDWH() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInViewDistinct = dh.getCountRowInSA(properties.getProperty("abpost.ViewDistinct.CountRows"));
        int countRowInHub = dh.getCountRowOfHub(properties.getProperty("abpost.HUB.Payments.CountRows"));
        assertRowCount(countRowInViewDistinct, countRowInHub);
    }





    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/hubsCount.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }
}
