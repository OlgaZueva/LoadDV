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
        int countRowInDV = getCountRowInDV(properties.getProperty("fctBookingCargo.dwh.CountRows"));
        int countRowInFct = getCountRowInDM(properties.getProperty("fctBookingCargo.fct.CountRows"));
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



