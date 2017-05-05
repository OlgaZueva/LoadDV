package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ShipItConstantsHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ShipItConstatsHubObjects {
    private DBHelper db = new DBHelper();

    public ShipItConstantsHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ShipItConstantsHub shipItConstantsHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String cName = rsFromSA.getString("C_NAME");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                shipItConstantsHub = new ShipItConstantsHub(cName, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ShipItConstantsHub from SA: " + shipItConstantsHub);
        return shipItConstantsHub;
    }

    public ShipItConstantsHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        ShipItConstantsHub shipItConstantsHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String cName = rsFromDWH.getString("cName");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                shipItConstantsHub = new ShipItConstantsHub(cName, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ShipItConstantsHub from DWH: " + shipItConstantsHub);
        return shipItConstantsHub;
    }


}

