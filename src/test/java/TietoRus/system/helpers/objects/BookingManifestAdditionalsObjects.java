package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingManifestAdditionals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingManifestAdditionalsObjects {
    private DBHelper db = new DBHelper();

    public BookingManifestAdditionals getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingManifestAdditionals bookingManifestAdditionals = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNumber = rsFromSA.getInt("BOOK_NR");;
                String segmentCode = rsFromSA.getString("SEGMENT_CODE");;
                int accessCompanyId = rsFromSA.getInt("SELSKAB");;
                int sequenceNr = rsFromSA.getInt("SEQ_NR");;
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingManifestAdditionals = new BookingManifestAdditionals(bookingNumber, segmentCode, accessCompanyId, sequenceNr, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingManifestAdditionals from SA: " + bookingManifestAdditionals);
        return bookingManifestAdditionals;
    }

    public BookingManifestAdditionals getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingManifestAdditionals bookingManifestAdditionals = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNumber = rsFromDWH.getInt("bookingNumber");
                String segmentCode = rsFromDWH.getString("segmentCode");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int sequenceNr = rsFromDWH.getInt("sequenceNr");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingManifestAdditionals = new BookingManifestAdditionals(bookingNumber, segmentCode, accessCompanyId, sequenceNr, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingManifestAdditionals from DWH: " + bookingManifestAdditionals);
        return bookingManifestAdditionals;
    }


}
