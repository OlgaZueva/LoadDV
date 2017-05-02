
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingEMCRemarksHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingEMCRemarksHubObjects {
    private DBHelper db = new DBHelper();

    public BookingEMCRemarksHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingEMCRemarksHub bookingEMCRemarksHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int manifestFileIdId = rsFromSA.getInt("BOOK_MFT_ID");
                int remarkId = rsFromSA.getInt("SEQ");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingEMCRemarksHub = new BookingEMCRemarksHub(manifestFileIdId, remarkId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingEMCRemarksHub from SA: " + bookingEMCRemarksHub);
        return bookingEMCRemarksHub;
    }

    public BookingEMCRemarksHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingEMCRemarksHub bookingEMCRemarksHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int manifestFileIdId = rsFromDWH.getInt("manifestFileIdId");
                int remarkId = rsFromDWH.getInt("remarkId");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingEMCRemarksHub = new BookingEMCRemarksHub(manifestFileIdId, remarkId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingEMCRemarksHub from DWH: " + bookingEMCRemarksHub);
        return bookingEMCRemarksHub;
    }


}
