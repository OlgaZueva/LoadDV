package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingManifestAdditionalsHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingManifestAdditionalsHubObjects {
    private DBHelper db = new DBHelper();

    public BookingManifestAdditionalsHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingManifestAdditionalsHub bookingManifestAdditionalsHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNumber = rsFromSA.getInt("BOOK_NR");;
                String segmentCode = rsFromSA.getString("SEGMENT_CODE");;
                int accessCompanyId = rsFromSA.getInt("SELSKAB");;
                int sequenceNr = rsFromSA.getInt("SEQ_NR");;
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingManifestAdditionalsHub = new BookingManifestAdditionalsHub(bookingNumber, segmentCode, accessCompanyId, sequenceNr, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingManifestAdditionalsHub from SA: " + bookingManifestAdditionalsHub);
        return bookingManifestAdditionalsHub;
    }

    public BookingManifestAdditionalsHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingManifestAdditionalsHub bookingManifestAdditionalsHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNumber = rsFromDWH.getInt("bookingNumber");
                String segmentCode = rsFromDWH.getString("segmentCode");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int sequenceNr = rsFromDWH.getInt("sequenceNr");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingManifestAdditionalsHub = new BookingManifestAdditionalsHub(bookingNumber, segmentCode, accessCompanyId, sequenceNr, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingManifestAdditionalsHub from DWH: " + bookingManifestAdditionalsHub);
        return bookingManifestAdditionalsHub;
    }


}
