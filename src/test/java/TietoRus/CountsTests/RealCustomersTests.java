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
        String sql = (properties.getProperty("realCustomer.exportBooking.exceptionalCustomers.select"));
        String truncate = (properties.getProperty("realCustomer.truncate"));
        executeInDWH(truncate);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String qwe = (properties.getProperty("realCustomer.insert") + (mapForSource.get("dwhIdHubBooking")) + "," + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + mapForSource.get("customerName") + "', 'Y')");
            System.out.println(qwe);
            executeInDWH(qwe);
            //assertRowCount(countRowInSA, countRowInDWH);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);


       /*
        int partyIdStatCustomer = getPartyId(properties.getProperty("statCustomer.customerPartyID"));
        int partyIdBookingParty = getPartyId(properties.getProperty("bookingParty.customerPartyID"));
        int partyIdContractHolder = getPartyId(properties.getProperty("contractHolder.customerPartyID"));
        int partyIdForwarder = getPartyId(properties.getProperty("forwarder.customerPartyID"));
        int partyIdShipper = getPartyId(properties.getProperty("shipper.customerPartyID"));
        int partyIdNotify = getPartyId(properties.getProperty("notify.customerPartyID"));
        int partyIdConsignee = getPartyId(properties.getProperty("consignee.customerPartyID"));
        int partyIdFreightPayer = getPartyId(properties.getProperty("freightPayer.customerPartyID"));

        System.out.println(partyIdStatCustomer + " " + partyIdBookingParty + " " + partyIdContractHolder + " " + partyIdForwarder
                + " " + partyIdShipper + " " + partyIdNotify + " " + partyIdConsignee + " " + partyIdFreightPayer);
*/
        int month = 3;
        String monthString;
        switch (month) {
            case 1:  monthString = "Январь";
                break;
            case 2:  monthString = "Февраль";
                break;
            case 3:  monthString = "Март";
                break;
            case 4:  monthString = "Апрель";
                break;
            case 5:  monthString = "Май";
                break;
            case 6:  monthString = "Июнь";
                break;
            case 7:  monthString = "Июль";
                break;
            case 8:  monthString = "Август";
                break;
            case 9:  monthString = "Сентябрь";
                break;
            case 10: monthString = "Октябрь";
                break;
            case 11: monthString = "Ноябрь";
                break;
            case 12: monthString = "Декабрь";
                break;
            default: monthString = "Не знаем такого";
                break;
        }
        System.out.println(monthString);
        /*
        int countRowInSA = getPartyId(properties.getProperty("loopSearch.union.CountRows"));
        int countRowInHub = getPartyId(properties.getProperty("loopSearch.hub.CountRows"));
        assertRowCount(countRowInSA, countRowInHub);
        */
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

