package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.Customers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomersObjects {
    private DBHelper db = new DBHelper();

    public Customers getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        Customers customers = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int number = rsFromSA.getInt("NR");;
                int bookingNumber = rsFromSA.getInt("REF_NR");;
                String refType = rsFromSA.getString("REF_TYPE");;
                int accessCompanyId = rsFromSA.getInt("SELSKAB");;
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                customers = new Customers(number, bookingNumber, refType, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("Customers from SA: " + customers);
        return customers;
    }

    public Customers getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        Customers customers = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int number = rsFromDWH.getInt("number");;
                int bookingNumber = rsFromDWH.getInt("bookingNumber");;
                String refType = rsFromDWH.getString("refType");;
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");;
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                customers = new Customers(number, bookingNumber, refType, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("Customers from DWH: " + customers);
        return customers;
    }


}
