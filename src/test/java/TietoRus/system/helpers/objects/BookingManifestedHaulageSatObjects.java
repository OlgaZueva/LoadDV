package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingManifestedHaulageSat;

import java.sql.*;


public class BookingManifestedHaulageSatObjects {
    private DBHelper db = new DBHelper();

    public BookingManifestedHaulageSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingManifestedHaulageSat bookingManifestedHaulageSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String precarriageMode = rsFromSA.getString("PRE_CARRIAGE_MODE");
                bookingManifestedHaulageSat = new BookingManifestedHaulageSat(precarriageMode);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingManifestedHaulageSat from SA: " + bookingManifestedHaulageSat);
        return bookingManifestedHaulageSat;
    }

    public BookingManifestedHaulageSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingManifestedHaulageSat bookingManifestedHaulageSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String precarriageMode = rsFromDWH.getString("precarriageMode");
                bookingManifestedHaulageSat = new BookingManifestedHaulageSat(precarriageMode);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingManifestedHaulageSat from DWH: " + bookingManifestedHaulageSat);
        return bookingManifestedHaulageSat;
    }


}
