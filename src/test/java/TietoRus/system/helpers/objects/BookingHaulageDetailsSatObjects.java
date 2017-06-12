package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingHaulageDetailsSat;

import java.sql.*;


public class BookingHaulageDetailsSatObjects {
    private DBHelper db = new DBHelper();

    public BookingHaulageDetailsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingHaulageDetailsSat bookingHaulageDetailsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String city = rsFromSA.getString("BYNAVN");
                Date haulageDate = rsFromSA.getDate("DATO");
                String haulageType = rsFromSA.getString("HAULAGE");
                bookingHaulageDetailsSat = new BookingHaulageDetailsSat(city, haulageDate, haulageType);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingHaulageDetailsSat from SA: " + bookingHaulageDetailsSat);
        return bookingHaulageDetailsSat;
    }

    public BookingHaulageDetailsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingHaulageDetailsSat bookingHaulageDetailsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String city = rsFromDWH.getString("city");
                Date haulageDate = rsFromDWH.getDate("haulageDate");
                String haulageType = rsFromDWH.getString("haulageType");
                bookingHaulageDetailsSat = new BookingHaulageDetailsSat(city, haulageDate, haulageType);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingHaulageDetailsSat from DWH: " + bookingHaulageDetailsSat);
        return bookingHaulageDetailsSat;
    }


}
