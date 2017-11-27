package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.helpers.GetDataHelper;
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

/*
Тест для проверки механизма MDS_Customers и заполнение спрввочников (во вспомогательные таблицы DWH из MDS).
Перед его запуском необходимо запустить Filling*-тесты (для заполнения всех справчников)
 */

public class CleaningCustomersNames {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private GetDataHelper getDataHelper = new GetDataHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();
    ArrayList finalSQL = new ArrayList();

    @Test(enabled = true)
    public void CleaningCustomersNames() throws SQLException, IOException {
        getPropertiesFile();
        FillingDictExcludedSymbols();
        String create = (properties.getProperty("cleanedCustomersNamesTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("cleanedCustomersNamesTable.truncate"));
        getDataHelper.executeInDWH(truncate);
        ArrayList excludedSymbols = getDataFromDict(properties.getProperty("dictExcludedSymbols.DWH.select"));
        String sql = (properties.getProperty("satCustomers.names.select"));
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String originalCustomerName = String.valueOf(mapForSource.get("customerName"));
            for (int i = 0; i < excludedSymbols.size(); i++) {
                originalCustomerName = trim(originalCustomerName.replaceAll("(^|\\s)(" + Pattern.quote(String.valueOf(excludedSymbols.get(i))) + ")(\\s|$)", "$1$3"));
                originalCustomerName =  originalCustomerName.replace("  ", " ");
            }
            mapForSource.put("customerName", originalCustomerName);
            String qwe = (properties.getProperty("cleanedCustomersNamesTable.insert") + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + originalCustomerName.replace("'", "''") + "','" + (mapForSource.get("TMScustomerNr")) + "')");
            finalSQL.add(qwe);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

        String str = String.join(" ", finalSQL);
        getDataHelper.executeInDWH(str);
        String updateTnsNumber = (properties.getProperty("cleanedCustomersNamesTable.tnsNumber.update"));
        getDataHelper.executeInDWH(updateTnsNumber);
        String updateCustomerName = (properties.getProperty("cleanedCustomersNamesTable.customerName.update"));
        getDataHelper.executeInDWH(updateCustomerName);
        String updateCustomerNameSpace = (properties.getProperty("cleanedCustomersNamesTable.customerNameSpace.update"));
        getDataHelper.executeInDWH(updateCustomerNameSpace);
    }


    @Test(enabled = true)
    public void FillingDictExcludedSymbols() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("dictExcludedSymbolsTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("dictExcludedSymbols.truncate"));
        getDataHelper.executeInDWH(truncate);
        ArrayList dictEmptyCustomer = getSQLForExcludedSymbolsFromMDS(properties.getProperty("dictExcludedSymbols.MDS.select"));
        String qwe = String.join(" ", dictEmptyCustomer);
        getDataHelper.executeInDWH(qwe);
     }


    @Test(enabled = true)
    public void FillingDictEmptyCustomer() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("dictEmptyCustomerTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("dictEmptyCustomerTable.truncate"));
        getDataHelper.executeInDWH(truncate);
        ArrayList dictEmptyCustomer = getDataFromMDS(properties.getProperty("dictEmptyCustomer.select"));
        for (int i = 0; i < dictEmptyCustomer.size(); i++) {
            String qwe = (properties.getProperty("dictEmptyCustomerTable.insert") + "'" + String.valueOf(dictEmptyCustomer.get(i)).replace("'", "''") + "')");
            getDataHelper.executeInDWH(qwe);
        }

    }

    @Test(enabled = true)
    public void FillingDictExceptionalCustomer() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("dictExceptionalCustomerTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("dictExceptionalCustomer.truncate"));
        getDataHelper.executeInDWH(truncate);
        ArrayList dictEmptyCustomer = getDataFromMDS(properties.getProperty("dictExceptionalCustomer.select"));
        for (int i = 0; i < dictEmptyCustomer.size(); i++) {

            String qwe = (properties.getProperty("dictExceptionalCustomer.insert") + "'" + String.valueOf( dictEmptyCustomer.get(i)).replace("'", "''") + "')");
            getDataHelper.executeInDWH(qwe);
        }
    }

    @Test(enabled = true)
    public void FillingDictMasterCustomerName() throws SQLException, IOException {
        getPropertiesFile();
        String truncate = (properties.getProperty("dictMasterCustomerName.truncate"));
        getDataHelper.executeInDWH(truncate);
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, properties.getProperty("dictMasterCustomerName.select"));
        while (rsFromMDS.next()) {
            mapForSource = getMapFromSource(rsFromMDS);
            String qwe = (properties.getProperty("dictMasterCustomerName.insert") + "'" + String.valueOf(mapForSource.get("name")).replace("'", "''") + "'," + mapForSource.get("code") + ")");

            getDataHelper.executeInDWH(qwe);
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
    }

    @Test(enabled = true)
    public void FillingDictLocations() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("dictLocations.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("dictLocations.truncate"));
        getDataHelper.executeInDWH(truncate);
        ArrayList dictLocations = getSQLForLocationsFromMDS(properties.getProperty("dictLocations.MDS.select"));
        String qwe = String.join(" ", dictLocations);
        getDataHelper.executeInDWH(qwe);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DWHtoMDS.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
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


    public ArrayList getSQLForExcludedSymbolsFromMDS(String select) throws SQLException {
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, select);
        ArrayList excludedSymbols = new ArrayList();
        String template = null;
        while (rsFromMDS.next()) {
            template = (properties.getProperty("dictExcludedSymbols.insert") + "'" + rsFromMDS.getString("name").replace("'", "''") + "')");
            ;
            excludedSymbols.add(template);
            //System.out.println("Template [" + template + "]");
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);

        return excludedSymbols;
    }

    public ArrayList getSQLForLocationsFromMDS(String select) throws SQLException {
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, select);
        ArrayList array = new ArrayList();
        String template = null;
        while (rsFromMDS.next()) {
            template = (properties.getProperty("dictLocations.insert") + "'" + rsFromMDS.getString("name").replace("'", "''")
                    + "', '" + rsFromMDS.getString("code").replace("'", "''")
                    + "')");
            ;
            array.add(template);
            //System.out.println("Template [" + template + "]");
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);

        return array;
    }

    public ArrayList getDataFromMDS(String select) throws SQLException {
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, select);
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



}
