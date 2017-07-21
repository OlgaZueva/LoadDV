package TietoRus.CountsTests.DiscardAgencyTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CountsInSAtests {

    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    @Test
    public void AbPostMSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInLink = getCountRowInSA(properties.getProperty("lnkFileROECompany.lnk.CountRows"));
        int countRowInSatLink = getCountRowInSA(properties.getProperty("lnkFileROECompany.satLnk.CountRows"));
        assertRowCount(countRowInLink, countRowInSatLink);

    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DiscardAgency.properties"))));
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

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in HubTable1 [" + countInSource + "], in LinkTable [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }
}
