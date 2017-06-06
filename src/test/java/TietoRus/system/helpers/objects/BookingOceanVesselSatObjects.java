
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingOceanVesselSat;

import java.sql.*;


public class BookingOceanVesselSatObjects {
    private DBHelper db = new DBHelper();

    public BookingOceanVesselSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingOceanVesselSat bookingOceanVesselSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String blockStowCode = rsFromSA.getString("BLOCK_CODE");
                String createdBy = rsFromSA.getString("CREATED_BY");
                Date creationDate = rsFromSA.getDate("CREATION_DATE");
                bookingOceanVesselSat = new BookingOceanVesselSat(blockStowCode, createdBy, creationDate);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingOceanVesselSat from SA: " + bookingOceanVesselSat);
        return bookingOceanVesselSat;
    }

    public BookingOceanVesselSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingOceanVesselSat bookingOceanVesselSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String blockStowCode = rsFromDWH.getString("blockStowCode");
                String createdBy = rsFromDWH.getString("createdBy");
                Date creationDate = rsFromDWH.getDate("creationDate");
                bookingOceanVesselSat = new BookingOceanVesselSat(blockStowCode, createdBy, creationDate);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingOceanVesselSat from DWH: " + bookingOceanVesselSat);
        return bookingOceanVesselSat;
    }


}
