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
    private GetDataHelper getDataHelper = new GetDataHelper();
    // private Map<String, Object> mapForSource = new HashMap<String, Object>();

    @Test(enabled = true)
    public void LocationsTest() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDWH = getCountRowFromDWH(properties.getProperty("locations.DWH.count"));
        int countRowInMDS = getDataFromMDS(properties.getProperty("locations.MDS.count"));
        assertRowCount(countRowInDWH, countRowInMDS);
    }

    @Test(enabled = true)
    public void Customers_mapCustomerNameTest() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Тест упадет если в mapCustomerName_v существовали предустановленные записи");
        int countRowInDWH = getCountRowFromDWH(properties.getProperty("customers.DWH.count"));
        int countRowInMDS = getDataFromMDS(properties.getProperty("customers.MDS.count"));
        assertRowCount(countRowInDWH, countRowInMDS);
    }

    @Test(enabled = true)
    public void MarketShareToMDSTest() throws SQLException, IOException {
        CleaningCustomersNames CreatePrecondition = new CleaningCustomersNames();
        CreatePrecondition.FillingDictLocations();
        getPropertiesFile();
        String create = (properties.getProperty("mapMarketShareTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("mapMarketShareTable.truncate"));
        getDataHelper.executeInDWH(truncate);


        String sqlForKeys = (properties.getProperty("mapMarketShare.sqlKeys.select"));
        String sqlAgencyLocations = (properties.getProperty("mapMarketShare.agencylocations.select"));
        String sqlForTeusForKeysByAgencyLocations = (properties.getProperty("mapMarketShare.teusForKeysByAgencyLocations.select"));

        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlForKeys);
        while (rsFromDWH.next()) {
            Map mapKeys = getMapFromSource(rsFromDWH);
            //System.out.println(mapKeys);
            String insertKeys = ((properties.getProperty("mapMarketShare.keys.insert")) + mapKeys.get("year") +
                    ", " + mapKeys.get("month") + ", '" + mapKeys.get("name") + "')");
            getDataHelper.executeInDWH(insertKeys);


            String sqlForTeusForKeysByAgencyLocationsUpdate = sqlForTeusForKeysByAgencyLocations + " year = " + mapKeys.get("year") +
                    " and month = " + mapKeys.get("month") + " and name = '" + mapKeys.get("name") + "'";

            Connection connectionToDWHforTeus = db.connToDWH();
            Statement stForDWHforTeus = db.stFromConnection(connectionToDWHforTeus);
            ResultSet rsFromDWHforTeus = db.rsFromDB(stForDWHforTeus, sqlForTeusForKeysByAgencyLocationsUpdate);
            while (rsFromDWHforTeus.next()) {
                Map mapTeusByAgencyLocations = getMapFromSource(rsFromDWHforTeus);
                //System.out.println(mapTeusByAgencyLocations);
                String teuForDryColumnName = getTeuForDryColumnName(String.valueOf(mapTeusByAgencyLocations.get("agencyCode")), String.valueOf(mapTeusByAgencyLocations.get("agencyRegion")));
                String teuForReeferColumnName = getTeuForReeferColumnName(String.valueOf(mapTeusByAgencyLocations.get("agencyCode")), String.valueOf(mapTeusByAgencyLocations.get("agencyRegion")));
                //System.out.println(teuForDryColumnName);
                //System.out.println(teuForReeferColumnName);

                String sqlForUpdateTeusByKeys = "Update etl.mapMarketShare_OZ set " + teuForDryColumnName + " = " + mapTeusByAgencyLocations.get("teuForDryContainers") +
                        ", " + teuForReeferColumnName + " = " + mapTeusByAgencyLocations.get("teuForReeferContainers") + " where year = " +
                        mapTeusByAgencyLocations.get("year") + " and month = " + mapTeusByAgencyLocations.get("month") + " and name = '" + mapTeusByAgencyLocations.get("name") +
                        "'";
                getDataHelper.executeInDWH(sqlForUpdateTeusByKeys);
            }
            db.closeConnecions(rsFromDWHforTeus, stForDWHforTeus, connectionToDWHforTeus);

        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
    }

    private String getTeuForDryColumnName(String agencyCode, String agencyRegion) {

        String teuForDryColumnName = "Not found";
        if (agencyCode.equals("aar")) {
            teuForDryColumnName = "marketTeusDryAar";

        } else if (agencyCode.equals("bud")) {
            teuForDryColumnName = "marketTeusDryBud";

        } else if (agencyCode.equals("gdy")) {
            teuForDryColumnName = "marketTeusDryGdy";

        } else if (agencyCode.equals("got")) {
            teuForDryColumnName = "marketTeusDryGot";

        } else if (agencyCode.equals("hki")) {
            teuForDryColumnName = "marketTeusDryHki";

        } else if (agencyCode.equals("kop")) {
            teuForDryColumnName = "marketTeusDryKop";

        } else if (agencyCode.equals("msq")) {
            teuForDryColumnName = "marketTeusDryMsq";

        } else if (agencyCode.equals("osl")) {
            teuForDryColumnName = "marketTeusDryOsl";

        } else if (agencyCode.equals("rix")) {
            teuForDryColumnName = "marketTeusDryRix";

        } else if (agencyCode.equals("rjk")) {
            teuForDryColumnName = "marketTeusDryRjk";

        } else if (agencyCode.equals("sjj")) {
            teuForDryColumnName = "marketTeusDrySjj";

        } else if (agencyCode.equals("tal")) {
            teuForDryColumnName = "marketTeusDryTal";

        } else if (agencyCode.equals("vno")) {
            teuForDryColumnName = "marketTeusDryVno";

        } else if (agencyCode.equals("mee") & agencyRegion.equals("Adriatic")) {
            teuForDryColumnName = "marketTeusDryMeeAdriatic";

        } else if (agencyCode.equals("mee") & agencyRegion.equals("ScanBalt")) {
            teuForDryColumnName = "marketTeusDryMeeScanbalt";
        }
        return teuForDryColumnName;
    }

    private String getTeuForReeferColumnName(String agencyCode, String agencyRegion) {

        String teuForReeferColumnName = "Not found";
        if (agencyCode.equals("aar")) {
            teuForReeferColumnName = "marketTeusReeferAar";

        } else if (agencyCode.equals("bud")) {
            teuForReeferColumnName = "marketTeusReeferBud";

        } else if (agencyCode.equals("gdy")) {
            teuForReeferColumnName = "marketTeusReeferGdy";

        } else if (agencyCode.equals("got")) {
            teuForReeferColumnName = "marketTeusReeferGot";

        } else if (agencyCode.equals("hki")) {
            teuForReeferColumnName = "marketTeusReeferHki";

        } else if (agencyCode.equals("kop")) {
            teuForReeferColumnName = "marketTeusReeferKop";

        } else if (agencyCode.equals("msq")) {
            teuForReeferColumnName = "marketTeusReeferMsq";

        } else if (agencyCode.equals("osl")) {
            teuForReeferColumnName = "marketTeusReeferOsl";

        } else if (agencyCode.equals("rix")) {
            teuForReeferColumnName = "marketTeusReeferRix";

        } else if (agencyCode.equals("rjk")) {
            teuForReeferColumnName = "marketTeusReeferRjk";

        } else if (agencyCode.equals("sjj")) {
            teuForReeferColumnName = "marketTeusReeferSjj";

        } else if (agencyCode.equals("tal")) {
            teuForReeferColumnName = "marketTeusReeferTal";

        } else if (agencyCode.equals("vno")) {
            teuForReeferColumnName = "marketTeusReeferVno";

        } else if (agencyCode.equals("mee") & agencyRegion.equals("Adriatic")) {
            teuForReeferColumnName = "marketTeusReeferMeeAdriatic";

        } else if (agencyCode.equals("mee") & agencyRegion.equals("ScanBalt")) {
            teuForReeferColumnName = "marketTeusReeferMeeScanbalt";
        }
        return teuForReeferColumnName;
    }


    public Map<String, Object> getMapFromSource(ResultSet rsFromSource) throws SQLException {
        Map<String, Object> mapForSource = new HashMap<String, Object>();
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

