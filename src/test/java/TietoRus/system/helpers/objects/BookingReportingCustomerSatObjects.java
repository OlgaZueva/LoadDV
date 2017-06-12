
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingReportingCustomerSat;

import java.sql.*;


public class BookingReportingCustomerSatObjects {
    private DBHelper db = new DBHelper();

    public BookingReportingCustomerSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingReportingCustomerSat bookingReportingCustomerSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String reportingCustomer = rsFromSA.getString("Customer");
                String customerType = rsFromSA.getString("NvoBcoFrw");
                Date validFrom = rsFromSA.getDate("VALID_FROM");
                bookingReportingCustomerSat = new BookingReportingCustomerSat(reportingCustomer, customerType, validFrom);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingReportingCustomerSat from SA: " + bookingReportingCustomerSat);
        return bookingReportingCustomerSat;
    }

    public BookingReportingCustomerSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingReportingCustomerSat bookingReportingCustomerSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String reportingCustomer = rsFromDWH.getString("reportingCustomer");
                String customerType = rsFromDWH.getString("customerType");
                Date validFrom = rsFromDWH.getDate("validFrom");
                bookingReportingCustomerSat = new BookingReportingCustomerSat(reportingCustomer, customerType, validFrom);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingReportingCustomerSat from DWH: " + bookingReportingCustomerSat);
        return bookingReportingCustomerSat;
    }


}
