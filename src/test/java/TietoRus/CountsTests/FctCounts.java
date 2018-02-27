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

public class FctCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void fctBookingCargo() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("fctBookingCargo.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("fctBookingCargo.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void factBookingChargeLines() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factBookingChargeLines.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("factBookingChargeLines.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void factBookingCharges() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factBookingCharges.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("factBookingCharges.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void factBookingEvents() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factBookingEvents.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("factBookingEvents.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void factContainerMoves() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factContainerMoves.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("factContainerMoves.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void fctDemurrageStorageMscRus() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("fctDemurrageStorageMscRus.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("fctDemurrageStorageMscRus.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void fctDemurrageStorageUnity() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("fctDemurrageStorageUnity.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("fctDemurrageStorageUnity.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void factFileRoe() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factFileRoe.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("factFileRoe.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    @Test(enabled = true)
    public void factInvoiceLines() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factInvoiceLines.dataInDV.commonPart");
        System.out.println(query);
        int countRowInDV = getCountRowInDV(query);
        int countRowInFct = getCountRowInDM(properties.getProperty("factInvoiceLines.fct.CountRows"));
        assertRowCount(countRowInDV, countRowInFct);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/fctCountsSQL.properties"))));
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



