package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.DailyROESat;

import java.sql.*;


public class DailyROESatObjects {
    private DBHelper db = new DBHelper();

    public DailyROESat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        DailyROESat dailyROESat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double rate = rsFromSA.getDouble("KURS");
                String currencyCode = rsFromSA.getString("VALUTA");
                dailyROESat = new DailyROESat(rate, currencyCode);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("DailyROESat from SA: " + dailyROESat);
        return dailyROESat;
    }

    public DailyROESat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        DailyROESat dailyROESat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double rate = rsFromDWH.getDouble("rate");
                String currencyCode = rsFromDWH.getString("currencyCode");
                dailyROESat = new DailyROESat(rate, currencyCode);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("DailyROESat from DWH: " + dailyROESat);
        return dailyROESat;
    }


}
