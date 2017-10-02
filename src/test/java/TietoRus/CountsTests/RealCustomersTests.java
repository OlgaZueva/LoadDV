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

public class RealCustomersTests {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    @Test(enabled = true)
    public void RealCustomersCounts() throws SQLException, IOException {
        getPropertiesFile();
        String sqlForExceptionalCustomers = (properties.getProperty("realCustomer.exportBooking.exceptionalCustomers.select"));
        String truncate = (properties.getProperty("realCustomer.truncate"));
        executeInDWH(truncate);
        insertExceptionalCustToTestTable(sqlForExceptionalCustomers, "Y", null);

        ArrayList excludedSymbols = getDataFromDict(properties.getProperty("dictExcludedSymbols.DWH.select"));

        String sqlForStatCustomer_E = (properties.getProperty("realCustomer.exportBooking.StatCustomer.select"));
        insertToTestTable(sqlForStatCustomer_E, "N", getPartyId(properties.getProperty("statCustomer.customerPartyID")), excludedSymbols);

        String sqlForBookingParty_E = (properties.getProperty("realCustomer.exportBooking.BookingParty.select"));
        insertToTestTable(sqlForBookingParty_E, "N", getPartyId(properties.getProperty("bookingParty.customerPartyID")), excludedSymbols);

        String sqlForContractHolder_E = (properties.getProperty("realCustomer.exportBooking.ContractHolder.select"));
        insertToTestTable(sqlForContractHolder_E, "N", getPartyId(properties.getProperty("contractHolder.customerPartyID")), excludedSymbols);

        String sqlForForwarder_E = (properties.getProperty("realCustomer.exportBooking.Forwarder.select"));
        insertToTestTable(sqlForForwarder_E, "N", getPartyId(properties.getProperty("forwarder.customerPartyID")), excludedSymbols);

        String sqlForShipper_E = (properties.getProperty("realCustomer.exportBooking.Shipper.select"));
        insertToTestTable(sqlForShipper_E, "N", getPartyId(properties.getProperty("shipper.customerPartyID")), excludedSymbols);

        String sqlForNotify_E = (properties.getProperty("realCustomer.exportBooking.Notify.select"));
        insertToTestTable(sqlForNotify_E, "N", getPartyId(properties.getProperty("notify.customerPartyID")), excludedSymbols);

        String sqlForConsignee_E = (properties.getProperty("realCustomer.exportBooking.Consignee.select"));
        insertToTestTable(sqlForConsignee_E, "N", getPartyId(properties.getProperty("consignee.customerPartyID")), excludedSymbols);

        String sqlForFreightPayer_E = (properties.getProperty("realCustomer.exportBooking.FreightPayer.select"));
        insertToTestTable(sqlForFreightPayer_E, "N", getPartyId(properties.getProperty("freightPayer.customerPartyID")), excludedSymbols);

    }

    private void insertExceptionalCustToTestTable(String sqlForStatCustomer_E, String isExceptional, Integer role) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlForStatCustomer_E);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String qwe = (properties.getProperty("realCustomer.insert") + (mapForSource.get("dwhIdHubBooking")) + "," + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + mapForSource.get("customerName") + "', '" + isExceptional + "'," + role + ")");
            executeInDWH(qwe);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
    }

    private void insertToTestTable(String sqlForStatCustomer_E, String isExceptional, int role, ArrayList excludedSymbols) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlForStatCustomer_E);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String originalCustomerName = String.valueOf(mapForSource.get("customerName"));
            for (int i = 0; i < excludedSymbols.size(); i++) {
                originalCustomerName = trim(originalCustomerName.replaceAll("(^|\\s)(" + Pattern.quote(String.valueOf(excludedSymbols.get(i))) + ")(\\s|$)", "$1$3"));
            }
            mapForSource.put("customerName", originalCustomerName);
            String qwe = (properties.getProperty("realCustomer.insert") + (mapForSource.get("dwhIdHubBooking")) + "," + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + originalCustomerName.replace("'", "''") + "', '" + isExceptional + "'," + role + ")");
            executeInDWH(qwe);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
    }


    public ArrayList getDataFromDict(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        ArrayList excludedSymbols = new ArrayList();
        String template = null;
        while (rsFromDWH.next()) {
            template = rsFromDWH.getString("name");
            excludedSymbols.add(template);
            //System.out.println("Template [" + template + "]");
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return excludedSymbols;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DWHtoMDS.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getPartyId(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        int partyId = 0;
        while (rsFromDWH.next()) {
            partyId = Integer.parseInt(rsFromDWH.getString("dwhIdHubCustomerParty"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return partyId;
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

