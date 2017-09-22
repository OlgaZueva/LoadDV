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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.trim;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CleaningCustomersNames {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    @Test(enabled = true)
    public void CustomersTest_TMScustomerNr() throws SQLException, IOException {
        getPropertiesFile();
        //String countRowInSA = 
        ArrayList excludedSymbols = getDataFromMDS(properties.getProperty("dictExcludedSymbols.select"));
        System.out.println(excludedSymbols.size());
        String sql = (properties.getProperty("satCustomers.names.select"));
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String originalCustomerName = String.valueOf(mapForSource.get("customerName"));

            for (int i = 0; i < excludedSymbols.size(); i++) {
                //System.out.println(String.valueOf(excludedSymbols.get(i)));
                originalCustomerName = trim(originalCustomerName.replaceAll("\\b" + Pattern.quote(String.valueOf(excludedSymbols.get(i))) + "\\b", ""));
            }
            mapForSource.put("customerName", originalCustomerName);
            String qwe = (properties.getProperty("insert.testTable") + (mapForSource.get("dwhIdHubCustomers")) + ",'" + originalCustomerName.replace("'", "''") + "')");
            executeInDWH(qwe);
            //executeInDWH(properties.getProperty("truncate.testTable"));
            //assertRowCount(countRowInSA, countRowInDWH);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
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
        ArrayList excludedSymbols = new ArrayList();
        String template = null;
        while (rsFromMDS.next()) {
            template = rsFromMDS.getString("name");
            excludedSymbols.add(template);
            //System.out.println("Template [" + template + "]");
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
        return excludedSymbols;
    }


    public Map<String, Object> getMapFromSource(ResultSet rsFromSource) throws SQLException {
        for (int k = 1; k <= rsFromSource.getMetaData().getColumnCount(); k++) {
            mapForSource.put(rsFromSource.getMetaData().getColumnName(k), rsFromSource.getObject(k));
        }
        return mapForSource;
    }

    public void executeInDWH(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(sql);
        //System.out.println("SQL for Insert in DWH: " + sql);
        //System.out.println("Executing query in DWH complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }


}
