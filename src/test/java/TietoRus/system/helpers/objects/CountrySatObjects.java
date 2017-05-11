package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.CountrySat;

import java.sql.*;


public class CountrySatObjects {
    private DBHelper db = new DBHelper();

    public CountrySat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        CountrySat countrySat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String countryName = rsFromSA.getString("TEKST_1");
                countrySat = new CountrySat(countryName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("CountrySat from SA: " + countrySat);
        return countrySat;
    }

    public CountrySat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        CountrySat countrySat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String countryName = rsFromDWH.getString("countryName");                ;
                countrySat = new CountrySat(countryName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("CountrySat from DWH: " + countrySat);
        return countrySat;
    }


}
