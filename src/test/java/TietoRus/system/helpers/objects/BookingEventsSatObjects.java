
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingEventsSat;

import java.sql.*;


public class BookingEventsSatObjects {
    private DBHelper db = new DBHelper();

    public BookingEventsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingEventsSat bookingEventsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String serviceCode = rsFromSA.getString("AFDELING");
                int bookingNr = rsFromSA.getInt("BOOK_NR");
                int eventCode = rsFromSA.getInt("EVENT_ID");
                String eventStatus = rsFromSA.getString("EVENT_STAT");
                Date eventTime = rsFromSA.getDate("EVENT_TIME");
                String eventUser = rsFromSA.getString("EVENT_USER");
                String remark = rsFromSA.getString("REMARK");
                String remark2 = rsFromSA.getString("REMARK2");
                String remark3 = rsFromSA.getString("REMARK3");
                int fileLinerNr = rsFromSA.getInt("SAGSNR");
                bookingEventsSat = new BookingEventsSat(serviceCode, bookingNr, eventCode, eventStatus, eventTime, eventUser, remark, remark2, remark3, fileLinerNr);

            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingEventsSat from SA: " + bookingEventsSat);
        return bookingEventsSat;
    }

    public BookingEventsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingEventsSat bookingEventsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String serviceCode = rsFromDWH.getString("serviceCode");
                int bookingNr = rsFromDWH.getInt("bookingNr");
                int eventCode = rsFromDWH.getInt("eventCode");
                String eventStatus = rsFromDWH.getString("eventStatus");
                Date eventTime = rsFromDWH.getDate("eventTime");
                String eventUser = rsFromDWH.getString("eventUser");
                String remark = rsFromDWH.getString("remark");
                String remark2 = rsFromDWH.getString("remark2");
                String remark3 = rsFromDWH.getString("remark3");
                int fileLinerNr = rsFromDWH.getInt("fileLinerNr");
                bookingEventsSat = new BookingEventsSat(serviceCode, bookingNr, eventCode, eventStatus, eventTime, eventUser, remark, remark2, remark3, fileLinerNr);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingEventsSat from DWH: " + bookingEventsSat);
        return bookingEventsSat;
    }


}
