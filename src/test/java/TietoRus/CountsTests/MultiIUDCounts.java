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


public class MultiIUDCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void AbPost_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("abPost.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("abPost.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("abPost.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void AbPost_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("abPost.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("abPost.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("abPost.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

     @Test(enabled = true)
    public void Book_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("book.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("book.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("book.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("book.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("book.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("book.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("book.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("book.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/MultiIUD.properties"))));
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
