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


public class PartitionTests {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void AbPost() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("abPost.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInSA(properties.getProperty("adresse.union.counts"));
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.destination.counts"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/Partition.properties"))));
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
}
