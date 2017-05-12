package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingDTXFileHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingDTXFileHubObjects {
    private DBHelper db = new DBHelper();

    public BookingDTXFileHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingDTXFileHub bookingDTXFileHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int manifestFileId = rsFromSA.getInt("MFT_FILE_ID");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingDTXFileHub = new BookingDTXFileHub(manifestFileId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingDTXFileHub from SA: " + bookingDTXFileHub);
        return bookingDTXFileHub;
    }

    public BookingDTXFileHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingDTXFileHub bookingDTXFileHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int manifestFileId = rsFromDWH.getInt("manifestFileId");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingDTXFileHub = new BookingDTXFileHub(manifestFileId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingDTXFileHub from DWH: " + bookingDTXFileHub);
        return bookingDTXFileHub;
    }


}

