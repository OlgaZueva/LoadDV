

package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingManifestHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingManifestHubObjects {
    private DBHelper db = new DBHelper();

    public BookingManifestHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingManifestHub bookingManifestHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookMftId = rsFromSA.getInt("BOOK_MFT_ID");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingManifestHub = new BookingManifestHub(bookMftId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingManifestHub from SA: " + bookingManifestHub);
        return bookingManifestHub;
    }

    public BookingManifestHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingManifestHub bookingManifestHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookMftId = rsFromDWH.getInt("bookMftId");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingManifestHub = new BookingManifestHub(bookMftId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingManifestHub from DWH: " + bookingManifestHub);
        return bookingManifestHub;
    }


}
