
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.LocationsPortsOverviewHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LocationsPortsOverviewHubObjects {
    private DBHelper db = new DBHelper();

    public LocationsPortsOverviewHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        LocationsPortsOverviewHub locationsPortsOverviewHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String locationCode = rsFromSA.getString("locationCode");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                locationsPortsOverviewHub = new LocationsPortsOverviewHub(locationCode, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("LocationsPortsOverviewHub from SA: " + locationsPortsOverviewHub);
        return locationsPortsOverviewHub;
    }

    public LocationsPortsOverviewHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        LocationsPortsOverviewHub locationsPortsOverviewHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String locationCode = rsFromDWH.getString("locationCode");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                locationsPortsOverviewHub = new LocationsPortsOverviewHub(locationCode,  srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("LocationsPortsOverviewHub from DWH: " + locationsPortsOverviewHub);
        return locationsPortsOverviewHub;
    }


}
