package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingEMCRemarksSat;

import java.sql.*;


public class BookingEMCRemarksSatObjects {
    private DBHelper db = new DBHelper();

    public BookingEMCRemarksSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingEMCRemarksSat bookingEMCRemarksSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String remarkCode = rsFromSA.getString("REMARK_CODE");
                String remarkText = rsFromSA.getString("REMARK_TEXT");
                bookingEMCRemarksSat = new BookingEMCRemarksSat(remarkCode, remarkText);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingEMCRemarksSat from SA: " + bookingEMCRemarksSat);
        return bookingEMCRemarksSat;
    }

    public BookingEMCRemarksSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingEMCRemarksSat bookingEMCRemarksSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String remarkCode = rsFromDWH.getString("remarkCode");
                String remarkText = rsFromDWH.getString("remarkText");
                bookingEMCRemarksSat = new BookingEMCRemarksSat(remarkCode, remarkText);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingEMCRemarksSat from DWH: " + bookingEMCRemarksSat);
        return bookingEMCRemarksSat;
    }


}
