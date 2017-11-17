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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Тест для проверки механизма загрузки данных из DWH в MDS
 */

public class DWHtoMDS {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private GetDataHelper getDataHelper =  new GetDataHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    @Test(enabled = true)
    public void LocationsTest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDWH = getCountRowFromDWH(properties.getProperty("locations.DWH.count"));
        int countRowInMDS = getDataFromMDS(properties.getProperty("locations.MDS.count"));
        assertRowCount(countRowInDWH, countRowInMDS);
    }


    @Test(enabled = true)
    public void MarketShareTest() throws SQLException, IOException {
        //CleaningCustomersNames CreatePrecondition = new CleaningCustomersNames();
        //CreatePrecondition.FillingDictLocations();
        getPropertiesFile();
        String create = (properties.getProperty("mapMarketShareTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("mapMarketShareTable.truncate"));
        getDataHelper.executeInDWH(truncate);

        mapForSource = getKeys(properties.getProperty("mapMarketShareTable.sql.selectKeys"));

        System.out.println(mapForSource);

        int countRowInDWH = getCountRowFromDWH(properties.getProperty("marketShare.DWH.count"));
        int countRowInMDS = getDataFromMDS(properties.getProperty("marketShare.MDS.count"));
        assertRowCount(countRowInDWH, countRowInMDS);
    }




    public Map getKeys (String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return mapForSource;
    }


    public Map<String, Object> getMapFromSource(ResultSet rsFromSource) throws SQLException {
        for (int k = 1; k <= rsFromSource.getMetaData().getColumnCount(); k++) {
            mapForSource.put(rsFromSource.getMetaData().getColumnName(k), rsFromSource.getObject(k));
        }
        return mapForSource;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DWHtoMDS.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowFromDWH(String hubSQL) throws SQLException {
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


    public int getDataFromMDS(String sql) throws SQLException {
        Connection connectionToMDS = db.connToMDS();
        Statement stForMDS = db.stFromConnection(connectionToMDS);
        ResultSet rsFromMDS = db.rsFromDB(stForMDS, sql);
        int countRowInMDS = 0;
        while (rsFromMDS.next()) {
            countRowInMDS = Integer.parseInt(rsFromMDS.getString("c"));
        }
        db.closeConnecions(rsFromMDS, stForMDS, connectionToMDS);
        return countRowInMDS;
    }


}

