package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingManifestSat;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class BookingManifestSatObjects {
    private DBHelper db = new DBHelper();

    public BookingManifestSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingManifestSat bookingManifestSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String correctorReason = rsFromSA.getString("CORR_REASON");
                String correctorType = rsFromSA.getString("CORR_TYPE");
                String createdBy = rsFromSA.getString("CREATED_BY");
                Date creationDate = rsFromSA.getDate("CREATION_DATE");
                String manifestType = rsFromSA.getString("MANIFEST_TYPE");
                int manifestFileId = rsFromSA.getInt("MFT_FILE_ID");
                String rolloverServiceCode = rsFromSA.getString("ROLL_AFDELING");
                int rolloverFileLiner = rsFromSA.getInt("ROLL_SAGSNR");
                String isSplit = rsFromSA.getString("SPLIT_BL_FLAG");
                String status = rsFromSA.getString("STATUS");
                bookingManifestSat = new BookingManifestSat(correctorReason, correctorType, createdBy, creationDate, manifestType, manifestFileId,
                        rolloverServiceCode, rolloverFileLiner, isSplit, status);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingManifestSat from SA: " + bookingManifestSat);
        return bookingManifestSat;
    }

    public BookingManifestSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingManifestSat bookingManifestSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String correctorReason = rsFromDWH.getString("voyageNr");
                String correctorType = rsFromDWH.getString("voyageNr");
                String createdBy = rsFromDWH.getString("voyageNr");
                Date creationDate = rsFromDWH.getDate("expPolDate");
                String manifestType = rsFromDWH.getString("voyageNr");
                int manifestFileId = rsFromDWH.getInt("sailingWeek");
                String rolloverServiceCode = rsFromDWH.getString("voyageNr");
                int rolloverFileLiner = rsFromDWH.getInt("sailingWeek");
                String isSplit = rsFromDWH.getString("voyageNr");
                String status = rsFromDWH.getString("voyageNr");
                bookingManifestSat = new BookingManifestSat(correctorReason, correctorType, createdBy, creationDate, manifestType, manifestFileId,
                        rolloverServiceCode, rolloverFileLiner, isSplit, status);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingManifestSat from DWH: " + bookingManifestSat);
        return bookingManifestSat;
    }


}
