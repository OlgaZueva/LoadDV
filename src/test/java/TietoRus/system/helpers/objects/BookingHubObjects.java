package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingHubObjects {
    private DBHelper db = new DBHelper();

    public BookingHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingHub booking = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNumber = rsFromSA.getInt("BOOK_NR");;
                int accessCompanyId = rsFromSA.getInt("SELSKAB");;
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                booking = new BookingHub(bookingNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingHub from SA: " + booking);
        return booking;
    }

    public BookingHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingHub booking = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNumber = rsFromDWH.getInt("bookingNumber");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");;
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                booking = new BookingHub(bookingNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingHub from DWH: " + booking);
        return booking;
    }


}
