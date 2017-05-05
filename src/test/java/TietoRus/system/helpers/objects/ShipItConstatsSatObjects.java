package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ShipItConstantsSat;

import java.sql.*;


public class ShipItConstatsSatObjects {
    private DBHelper db = new DBHelper();

    public ShipItConstantsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ShipItConstantsSat shipItConstantsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String cVal = rsFromSA.getString("C_VAL");
                shipItConstantsSat = new ShipItConstantsSat(cVal);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ShipItConstantsSat from SA: " + shipItConstantsSat);
        return shipItConstantsSat;
    }

    public ShipItConstantsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ShipItConstantsSat shipItConstantsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String cVal = rsFromDWH.getString("cVal");
                shipItConstantsSat = new ShipItConstantsSat(cVal);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ShipItConstantsSat from DWH: " + shipItConstantsSat);
        return shipItConstantsSat;
    }


}
