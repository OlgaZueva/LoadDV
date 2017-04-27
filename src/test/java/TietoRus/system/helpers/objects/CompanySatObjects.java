package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.CompanySat;

import java.sql.*;


public class CompanySatObjects {
    private DBHelper db = new DBHelper();

    public CompanySat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        CompanySat companySat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String address1 = rsFromSA.getString("ADRESSE1");
                String address2 = rsFromSA.getString("ADRESSE2");
                String address3 = rsFromSA.getString("ADRESSE3");
                String companyName = rsFromSA.getString("NAVN");
                String zipCode = rsFromSA.getString("POSTNR");
                companySat = new CompanySat(address1, address2, address3, companyName, zipCode);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("CompanySat from SA: " + companySat);
        return companySat;
    }

    public CompanySat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        CompanySat companySat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String address1 = rsFromDWH.getString("address1");
                String address2 = rsFromDWH.getString("address2");
                String address3 = rsFromDWH.getString("address3");
                String companyName = rsFromDWH.getString("companyName");
                String zipCode = rsFromDWH.getString("zipCode");
                companySat = new CompanySat(address1, address2, address3, companyName, zipCode);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("CompanySat from DWH: " + companySat);
        return companySat;
    }


}
