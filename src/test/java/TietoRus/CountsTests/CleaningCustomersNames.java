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

/*
Тест для проверки механизма MDS_Customers и заполнение спрввочников (во вспомогательные таблицы DWH из MDS).
Перед его запуском необходимо запустить Filling*-тесты (для заполнения всех справчников)
 */

public class CleaningCustomersNames {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();
    ArrayList finalSQL = new ArrayList();

    @Test(enabled = true)
    public void CleaningCustomersNames() throws SQLException, IOException {
        getPropertiesFile();
        FillingDictExcludedSymbols();
        String create = (properties.getProperty("cleanedCustomersNamesTable.create"));
        executeInDWH(create);
        String truncate = (properties.getProperty("cleanedCustomersNamesTable.truncate"));
        executeInDWH(truncate);
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
            }
            mapForSource.put("customerName", originalCustomerName);
            String qwe = (properties.getProperty("cleanedCustomersNamesTable.insert") + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + originalCustomerName.replace("'", "''") + "','" + (mapForSource.get("TMScustomerNr")) + "')");
            finalSQL.add(qwe);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

        String str = String.join(" ", finalSQL);
        executeInDWH(str);
        String update = (properties.getProperty("cleanedCustomersNamesTable.update"));
        executeInDWH(update);
    }


    @Test(enabled = true)
    public void FillingDictExcludedSymbols() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("dictExcludedSymbolsTable.create"));
        executeInDWH(create);
        String truncate = (properties.getProperty("dictExcludedSymbols.truncate"));
        executeInDWH(truncate);
        ArrayList dictEmptyCustomer = getDataFromMDS(properties.getProperty("dictExcludedSymbols.MDS.select"));
        String qwe = String.join(" ", dictEmptyCustomer);
        executeInDWH(qwe);
        //}
    }


    @Test(enabled = true)
    public void FillingDictEmptyCustomer() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("dictEmptyCustomerTable.create"));
        executeInDWH(create);
        String truncate = (properties.getProperty("dictEmptyCustomerTable.truncate"));
        executeInDWH(truncate);
        ArrayList dictEmptyCustomer = getDataFromMDS(properties.getProperty("dictEmptyCustomer.select"));


        for (int i = 0; i < dictEmptyCustomer.size(); i++) {
            String qwe = (properties.getProperty("dictEmptyCustomerTable.insert") + "'" + String.valueOf(dictEmptyCustomer.get(i)).replace("'", "''") + "')");
            executeInDWH(qwe);
        }

    }

    @Test(enabled = true)
    public void FillingDictExceptionalCustomer() throws SQLException, IOException {
        getPropertiesFile();
        String truncate = (properties.getProperty("dictExceptionalCustomer.truncate"));
        executeInDWH(truncate);
        ArrayList dictEmptyCustomer = getDataFromMDS(properties.getProperty("dictExceptionalCustomer.select"));
        for (int i = 0; i < dictEmptyCustomer.size(); i++) {

            String qwe = (properties.getProperty("dictExceptionalCustomer.insert") + "'" + dictEmptyCustomer.get(i) + "')");
            executeInDWH(qwe);
        }
    }

    @Test(enabled = true)
    public void FillingDictMasterCustomerName() throws SQLException, IOException {
        getPropertiesFile();
        String truncate = (properties.getProperty("dictMasterCustomerName.truncate"));
        executeInDWH(truncate);
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, properties.getProperty("dictMasterCustomerName.select"));
        while (rsFromMDS.next()) {
            mapForSource = getMapFromSource(rsFromMDS);
            String qwe = (properties.getProperty("dictMasterCustomerName.insert") + "'" + String.valueOf(mapForSource.get("name")).replace("'", "''") + "'," + mapForSource.get("code") + ")");

            executeInDWH(qwe);
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
    }

    @Test(enabled = true)
    public void FillingDictLocations() throws SQLException, IOException {
        getPropertiesFile();
        String truncate = (properties.getProperty("dictLocations.truncate"));
        executeInDWH(truncate);
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, properties.getProperty("dictLocations.select"));
        while (rsFromMDS.next()) {
            mapForSource = getMapFromSource(rsFromMDS);
            String qwe = (properties.getProperty("dictLocations.insert") + "'"
                    + String.valueOf(mapForSource.get("name")).replace("'", "''") + "','" + mapForSource.get("code") + "')");
            System.out.println(qwe);
            executeInDWH(qwe);
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
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


    public ArrayList getDataFromMDS(String select) throws SQLException {
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
