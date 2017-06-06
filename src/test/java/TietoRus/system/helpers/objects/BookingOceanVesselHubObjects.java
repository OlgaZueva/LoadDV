package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingOceanVesselHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingOceanVesselHubObjects {
    private DBHelper db = new DBHelper();

    public BookingOceanVesselHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingOceanVesselHub bookingOceanVesselHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNumber = rsFromSA.getInt("BOOK_NR");
                int bookVesselId = rsFromSA.getInt("BOOK_VESSEL_ID");
                int accessCompanyId = rsFromSA.getInt("accessCompanyId");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingOceanVesselHub = new BookingOceanVesselHub(bookingNumber, bookVesselId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingOceanVesselHub from SA: " + bookingOceanVesselHub);
        return bookingOceanVesselHub;
    }

    public BookingOceanVesselHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingOceanVesselHub bookingOceanVesselHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNumber = rsFromDWH.getInt("bookingNumber");
                int bookVesselId = rsFromDWH.getInt("bookVesselId");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingOceanVesselHub = new BookingOceanVesselHub(bookingNumber, bookVesselId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingOceanVesselHub from DWH: " + bookingOceanVesselHub);
        return bookingOceanVesselHub;
    }


}
