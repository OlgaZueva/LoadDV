
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

public class DictCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void dictFullEmpty() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("fullEmpty.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("fullEmpty.dict.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dictCrossBookingType() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("crossBookingType.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("crossBookingType.dict.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dictImportExport() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("importExport.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("importExport.dict.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dictPPCCEE() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("PPCCEE.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("PPCCEE.dict.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    @Test(enabled = true)
    public void dictRegion() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("region.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("region.dict.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }


    @Test(enabled = true)
    public void dictDestinationRegion() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("destinationRegion.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("destinationRegion.dict.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/dictsCountsSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowInDM(String saSQL) throws SQLException {
        Connection connectionToDM = db.connToDM();
        Statement stForDM = db.stFromConnection(connectionToDM);
        ResultSet rsFromDM = db.rsFromDB(stForDM, saSQL);
        int countRowSA = 0;
        while (rsFromDM.next()) {
            countRowSA = Integer.parseInt(rsFromDM.getString("c"));
        }
        db.closeConnecions(rsFromDM, stForDM, connectionToDM);
        return countRowSA;
    }

    public int getCountRowInDV(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        int countRowHub = 0;
        while (rsFromDWH.next()) {
            countRowHub = Integer.parseInt(rsFromDWH.getString("c"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return countRowHub;
    }


}



