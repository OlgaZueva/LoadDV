package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.CompanyHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CompanyHubObjects {
    private DBHelper db = new DBHelper();

    public CompanyHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        CompanyHub companyHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int companyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                companyHub = new CompanyHub(companyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("CompanyHub from SA: " + companyHub);
        return companyHub;
    }

    public CompanyHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        CompanyHub companyHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int companyId = rsFromDWH.getInt("companyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                companyHub = new CompanyHub(companyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("CompanyHub from DWH: " + companyHub);
        return companyHub;
    }


}
