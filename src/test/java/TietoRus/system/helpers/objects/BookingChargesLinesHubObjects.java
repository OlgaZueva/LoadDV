package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingChargesLinesHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingChargesLinesHubObjects {
    private DBHelper db = new DBHelper();

    public BookingChargesLinesHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingChargesLinesHub bookingChargesLinesHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNumber = rsFromSA.getInt("BOOK_NR");
                int positionNr = rsFromSA.getInt("FRG_BT_NR");
                int lineNr = rsFromSA.getInt("LINIE_NR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingChargesLinesHub = new BookingChargesLinesHub(bookingNumber, positionNr, lineNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingChargesLinesHub from SA: " + bookingChargesLinesHub);
        return bookingChargesLinesHub;
    }

    public BookingChargesLinesHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingChargesLinesHub bookingChargesLinesHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNumber = rsFromDWH.getInt("bookingNumber");
                int positionNr = rsFromDWH.getInt("positionNr");
                int lineNr = rsFromDWH.getInt("lineNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingChargesLinesHub = new BookingChargesLinesHub(bookingNumber, positionNr, lineNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingChargesLinesHub from DWH: " + bookingChargesLinesHub);
        return bookingChargesLinesHub;
    }


}
