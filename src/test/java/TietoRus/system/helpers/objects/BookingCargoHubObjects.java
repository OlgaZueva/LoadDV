package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingCargoHub;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingCargoHubObjects {
    private DBHelper db = new DBHelper();

    public BookingCargoHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingCargoHub bookingCargoHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNumber = rsFromSA.getInt("bookingNumber");
                int accessCompanyId = rsFromSA.getInt("accessCompanyId");
                int cargoLineNr = rsFromSA.getInt("cargoLineNr");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingCargoHub = new BookingCargoHub(bookingNumber, accessCompanyId, cargoLineNr, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingCargoHub from SA: " + bookingCargoHub);
        return bookingCargoHub;
    }

    public BookingCargoHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingCargoHub bookingCargoHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNumber = rsFromDWH.getInt("BOOK_NR");
                int accessCompanyId = rsFromDWH.getInt("SELSKAB");
                int cargoLineNr = rsFromDWH.getInt("VAREPOST_NR");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingCargoHub = new BookingCargoHub(bookingNumber, accessCompanyId, cargoLineNr, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingCargoHub from DWH: " + bookingCargoHub);
        return bookingCargoHub;
    }


}

