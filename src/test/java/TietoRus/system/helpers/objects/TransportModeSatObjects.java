package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.TrasportModeSat;

import java.sql.*;


public class TransportModeSatObjects {
    private DBHelper db = new DBHelper();

    public TrasportModeSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        TrasportModeSat transportModeSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String transportModeName = rsFromSA.getString("TEKST");
                transportModeSat = new TrasportModeSat(transportModeName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("TrasportModeSat from SA: " + transportModeSat);
        return transportModeSat;
    }

    public TrasportModeSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        TrasportModeSat transportModeSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String transportModeName = rsFromDWH.getString("transportModeName");
                transportModeSat = new TrasportModeSat(transportModeName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("TrasportModeSat from DWH: " + transportModeSat);
        return transportModeSat;
    }


}

