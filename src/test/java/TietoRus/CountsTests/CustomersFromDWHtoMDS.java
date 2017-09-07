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
import java.util.ArrayList;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomersFromDWHtoMDS {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();

    @Test(enabled = true)
    public void CustomersTest() throws SQLException, IOException {
        getPropertiesFile();
        //String countRowInSA = 
       ArrayList qwe =  getDataFromMDS(properties.getProperty("dictExcludedSymbols.select"));
        System.out.println(qwe.size());

        //assertRowCount(countRowInSA, countRowInDWH);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/customersSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public ArrayList getDataFromMDS(String sql) throws SQLException {
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, sql);
        ArrayList catNames = new ArrayList();

        String template = null;
        while (rsFromMDS.next()) {
            template = rsFromMDS.getString("name");
            catNames.add(template);
            System.out.println("Template [" + template + "]");
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
        return catNames;
    }


}
