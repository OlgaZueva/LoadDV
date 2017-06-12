
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingReportingCustomerHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookingReportingCustomerHubObjects {
    private DBHelper db = new DBHelper();

    public BookingReportingCustomerHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingReportingCustomerHub bookingReportingCustomerHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String blNr = rsFromSA.getString("BL_NUMBER");
                int accessCompanyId = rsFromSA.getInt("accessCompanyId");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingReportingCustomerHub = new BookingReportingCustomerHub(blNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingReportingCustomerHub from SA: " + bookingReportingCustomerHub);
        return bookingReportingCustomerHub;
    }

    public BookingReportingCustomerHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingReportingCustomerHub bookingReportingCustomerHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String blNr = rsFromDWH.getString("blNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingReportingCustomerHub = new BookingReportingCustomerHub(blNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingReportingCustomerHub from DWH: " + bookingReportingCustomerHub);
        return bookingReportingCustomerHub;
    }


}
