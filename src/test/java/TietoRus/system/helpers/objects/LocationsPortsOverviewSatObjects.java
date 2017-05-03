package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.LocationsPortsOverviewSat;

import java.sql.*;


public class LocationsPortsOverviewSatObjects {
    private DBHelper db = new DBHelper();

    public LocationsPortsOverviewSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        LocationsPortsOverviewSat locationsPortsOverviewSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String liner = rsFromSA.getString("Liner");
                String linerAssistant = rsFromSA.getString("Liner_Assistant");
                String destinationRegion = rsFromSA.getString("Destination_Region");
                String tradeNameGvaForEmedStat = rsFromSA.getString("Trade_Name_GVA_style_For_EMED_statistic");
                String isLandlockedCountry = rsFromSA.getString("LAND_LOCKED_country");
                String agencyRegion = rsFromSA.getString("Agency_Region");
                String agencyCode = rsFromSA.getString("Agency_Code");
                String ctsTier5 = rsFromSA.getString("CTSTier5");
                String ctsTier4Location = rsFromSA.getString("CTSTier4_Location");
                String ctsTier4Country = rsFromSA.getString("CTSTier4_Country");
                String dkExpTeam = rsFromSA.getString("dkEXPteam");
                String isDeepSea = rsFromSA.getString("DeepseaFlag");
                Date fromDate = rsFromSA.getDate("VALID_FROM");
                locationsPortsOverviewSat = new LocationsPortsOverviewSat(liner, linerAssistant, destinationRegion, tradeNameGvaForEmedStat, isLandlockedCountry, agencyRegion, agencyCode,
                        ctsTier5, ctsTier4Location, ctsTier4Country, dkExpTeam, isDeepSea, fromDate);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("LocationsPortsOverviewSat from SA: " + locationsPortsOverviewSat);
        return locationsPortsOverviewSat;
    }

    public LocationsPortsOverviewSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        LocationsPortsOverviewSat locationsPortsOverviewSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String liner = rsFromDWH.getString("liner");
                String linerAssistant = rsFromDWH.getString("linerAssistant");
                String destinationRegion = rsFromDWH.getString("destinationRegion");
                String tradeNameGvaForEmedStat = rsFromDWH.getString("tradeNameGvaForEmedStat");
                String isLandlockedCountry = rsFromDWH.getString("isLandlockedCountry");
                String agencyRegion = rsFromDWH.getString("agencyRegion");
                String agencyCode = rsFromDWH.getString("agencyCode");
                String ctsTier5 = rsFromDWH.getString("ctsTier5");
                String ctsTier4Location = rsFromDWH.getString("ctsTier4Location");
                String ctsTier4Country = rsFromDWH.getString("ctsTier4Country");
                String dkExpTeam = rsFromDWH.getString("dkExpTeam");
                String isDeepSea = rsFromDWH.getString("isDeepSea");
                Date fromDate = rsFromDWH.getDate("fromDate");
                locationsPortsOverviewSat = new LocationsPortsOverviewSat(liner, linerAssistant, destinationRegion, tradeNameGvaForEmedStat, isLandlockedCountry, agencyRegion, agencyCode,
                        ctsTier5, ctsTier4Location, ctsTier4Country, dkExpTeam, isDeepSea, fromDate);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("LocationsPortsOverviewSat from DWH: " + locationsPortsOverviewSat);
        return locationsPortsOverviewSat;
    }


}
