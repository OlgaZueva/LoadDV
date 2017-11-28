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
import java.util.*;

public class DimsDataMatching {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapFromDV = new HashMap<String, Object>();
    private Map<String, Object> mapFromDM = new HashMap<String, Object>();


    @Test(enabled = true)
    public void dimCustomers_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("customers.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("customers.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("customers.dataInDM.RowByKeys") + " where dwhIdHubCustomers = " +
                        rsFromDWH.getInt("dwhIdHubCustomers") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimFileLiner_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("fileLiner.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("fileLiner.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("fileLiner.dataInDM.RowByKeys") + " where dwhIdHubFileLiner = " +
                        rsFromDWH.getInt("dwhIdHubFileLiner") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimControllingOffice_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("controllingOffice.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("controllingOffice.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("controllingOffice.datainDM.RowByKeys") + " where dwhIdHubControllingOffice = " +
                        rsFromDWH.getInt("dwhIdHubControllingOffice") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimVesselRegistry_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("vesselRegistry.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("vesselRegistry.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("vesselRegistry.datainDM.RowByKeys") + " where dwhIdHubVesselRegistry = " +
                        rsFromDWH.getInt("dwhIdHubVesselRegistry") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimCountry_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("country.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("country.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("country.dataInDM.RowByKeys") + " where dwhIdHubCountry = " +
                        rsFromDWH.getInt("dwhIdHubCountry") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimBookingManifest_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("bookingManifest.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("bookingManifest.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingManifest.dataInDM.RowByKeys") + " where dwhIdHubBookingManifest = " +
                        rsFromDWH.getInt("dwhIdHubBookingManifest") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimOvTradeName_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("ovTradeName.union.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("ovTradeName.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                mapFromDV.put("dmStatus", 1);
                mapFromDV.put("srcSystemId", 0);
                mapFromDV.put("validFrom", "2000-01-01");
                mapFromDV.put("validTo", "2100-01-01");

                String sqlForDM = (properties.getProperty("ovTradeName.dataInDM.RowByKeys") + " where ovTradeName = '" +
                        rsFromDWH.getString("ovTradeName") + "' and accessCompanyId = '" + rsFromDWH.getInt("accessCompanyId") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimGvaTrade_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("gvaTrade.union.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("gvaTrade.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                mapFromDV.put("dmStatus", 1);
                mapFromDV.put("srcSystemId", 0);
                mapFromDV.put("validFrom", "2000-01-01");
                mapFromDV.put("validTo", "2100-01-01");

                String sqlForDM = (properties.getProperty("gvaTrade.dataInDM.RowByKeys") + " where gvaTrade = '" +
                        rsFromDWH.getString("gvaTrade") + "' and accessCompanyId = '" + rsFromDWH.getInt("accessCompanyId") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimBookingCargo_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("bookingCargo.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);
/*
        System.out.println("Before Sorting:");
        for(Object counter: arrayRows){
            System.out.println(counter);
        }

        Collections.sort(arrayRows);

	   /* ArrayList after sorting
        System.out.println("After Sorting:");
        for(Object counter: arrayRows){
            System.out.println(counter);
        }
        */

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("bookingCargo.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingCargo.dataInDM.RowByKeys") + " where dwhIdHubBookingCargo = " +
                        rsFromDWH.getInt("dwhIdHubBookingCargo") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimBooking_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("booking.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("booking.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("booking.dataInDM.RowByKeys") + " where dwhIdHubBooking = " +
                        rsFromDWH.getInt("dwhIdHubBooking") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimBookingOceanVessel_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("bookingOceanVessel.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("bookingOceanVessel.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingOceanVessel.dataInDM.RowByKeys") + " where dwhIdHubBookingOceanVessel = " +
                        rsFromDWH.getInt("dwhIdHubBookingOceanVessel") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimOceanVesselService_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("oceanVesselService.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("oceanVesselService.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("oceanVesselService.dataInDM.RowByKeys") + " where dwhIdHubOceanVesselService = " +
                        rsFromDWH.getInt("dwhIdHubOceanVesselService") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimContainerType_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("containerType.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("containerType.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("containerType.dataInDM.RowByKeys") + " where dwhIdHubContainerType = " +
                        rsFromDWH.getInt("dwhIdHubContainerType") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/dimsCountsSQL.properties"))));
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

    public ArrayList getArray(int countRowsInTable) throws IOException {
        getPropertiesFile();
        ArrayList arrayRows = new ArrayList();
        int defaultRowsForMatch = Integer.parseInt(properties.getProperty("system.default.RowsForMatch"));
        Double percent = Double.valueOf((properties.getProperty("system.PercentOfRows")));
        int countRowsForMatch = (int) Math.round(((countRowsInTable * percent) / 100));
        if (countRowsForMatch != 0) {
            int increment = Math.round(countRowsInTable / countRowsForMatch);
            for (int i = 0; i < (countRowsInTable - increment); i = i + increment) {
                arrayRows.add(i);
            }
            System.out.println("Кол-во записей пула, которые будут сравниваться: " + arrayRows.size());
            return arrayRows;
        } else
            for (int i = 0; i < (defaultRowsForMatch); i++) {
                arrayRows.add(i);
            }

        System.out.println("В DV мало записей, будет сравниваться " + arrayRows.size());
        return arrayRows;
    }


    public Map<String, Object> getMapFromDV(ResultSet rsFromSource) throws SQLException {
        for (int k = 1; k <= rsFromSource.getMetaData().getColumnCount(); k++) {
            mapFromDV.put(rsFromSource.getMetaData().getColumnName(k), rsFromSource.getObject(k));
            mapFromDV.remove("RowNumber");
        }
        return mapFromDV;
    }

    public Map<String, Object> getMapFromDM(int mapForRTestSize, String sql) throws SQLException {
        Connection connectionToDM = db.connToDM();
        Statement stForDM = db.stFromConnection(connectionToDM);
        ResultSet rsFromDM = db.rsFromDB(stForDM, sql);
        Map<String, Object> mapForDM = new HashMap<String, Object>();

        while (rsFromDM.next()) {
            if (rsFromDM.getRow() > 1) {
                System.err.println("Count rows got from DM: " + rsFromDM.getRow()
                        + ". If its > 1 check the unique key in sql query to DM! SQL: " + sql);
            } else {
                for (int l = 1; l <= mapForRTestSize; l++) {
                    mapForDM.put(rsFromDM.getMetaData().getColumnName(l), rsFromDM.getObject(l));
                }
            }
        }
        db.closeConnecions(rsFromDM, stForDM, connectionToDM);
        return mapForDM;
    }


    public void matchMaps(Map<String, Object> mapDV, Map<String, Object> mapDM) {
        // System.out.println("Map from DV = " + mapDV);
        // System.out.println("Map from DM = " + mapDM);

        if (mapDM.size() == 0) {
            System.err.println("Record in DataMart not found!");
        } else {
            for (Map.Entry entry : mapDV.entrySet()) {
                Object q1 = entry.getKey();
                Object q2 = entry.getValue();
                if (q2 == null) {
                    if (mapDM.get(q1) != null) {
                        System.err.println("Column [" + q1 + "] expected null but was [" + mapDM.get(q1).toString() + "]");
                    } else if (!mapDM.keySet().contains(q1)) {
                        System.err.println("Column [" + q1 + "] not exist");
                    }
                } else {
                    if (!q2.equals(mapDM.get(q1))) {
                        Object secondValue = mapDM.get(q1);
                        if (!q2.toString().equals(secondValue != null ? secondValue.toString() : null)) {
                            System.err.println("Column [" + q1.toString() + "] does not match. Expected [" + q2 + "], actual - [" + mapDM.get(q1) + "]");
                        }
                    }
                }
            }
        }
    }
}



