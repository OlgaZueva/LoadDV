package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingEvents;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingEventsObjects {
    private DBHelper db = new DBHelper();

    public BookingEvents getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingEvents bookingEvents = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNr = rsFromSA.getInt("BOOK_NR");;
                int accessCompanyId = rsFromSA.getInt("SELSKAB");;
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingEvents = new BookingEvents(bookingNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingEvents from SA: " + bookingEvents);
        return bookingEvents;
    }

    public BookingEvents getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingEvents bookingEvents = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNr = rsFromDWH.getInt("bookingNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");;
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingEvents = new BookingEvents(bookingNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingEvents from DWH: " + bookingEvents);
        return bookingEvents;
    }


}
