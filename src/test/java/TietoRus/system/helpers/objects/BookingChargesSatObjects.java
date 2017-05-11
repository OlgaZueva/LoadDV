
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingChargesSat;

import java.sql.*;


public class BookingChargesSatObjects {
    private DBHelper db = new DBHelper();

    public BookingChargesSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingChargesSat bookingChargesSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String invoiceType = rsFromSA.getString("DK_KODE");
                Double customerCode = rsFromSA.getDouble("KUNDE");
                String invoiceRelationType = rsFromSA.getString("REL_TYPE");
                String invoiceStatus = rsFromSA.getString("STAT");
                bookingChargesSat = new BookingChargesSat(invoiceType, customerCode, invoiceRelationType, invoiceStatus);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingChargesSat from SA: " + bookingChargesSat);
        return bookingChargesSat;
    }

    public BookingChargesSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingChargesSat bookingChargesSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String invoiceType = rsFromDWH.getString("invoiceType");
                Double customerCode = rsFromDWH.getDouble("customerCode");
                String invoiceRelationType = rsFromDWH.getString("invoiceRelationType");
                String invoiceStatus = rsFromDWH.getString("invoiceStatus");
                bookingChargesSat = new BookingChargesSat(invoiceType, customerCode, invoiceRelationType, invoiceStatus);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingChargesSat from DWH: " + bookingChargesSat);
        return bookingChargesSat;
    }


}
