package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ControllingOfficeHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ControllingOfficeHubObjects {
    private DBHelper db = new DBHelper();

    public ControllingOfficeHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ControllingOfficeHub controllingOfficeHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int officeId = rsFromSA.getInt("OFFICE_ID");
                int companyId = rsFromSA.getInt("COMPANY_ID");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                controllingOfficeHub = new ControllingOfficeHub(officeId, companyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ControllingOfficeHub from SA: " + controllingOfficeHub);
        return controllingOfficeHub;
    }

    public ControllingOfficeHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        ControllingOfficeHub controllingOfficeHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int officeId = rsFromDWH.getInt("officeId");
                int companyId = rsFromDWH.getInt("companyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                controllingOfficeHub = new ControllingOfficeHub(officeId, companyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ControllingOfficeHub from DWH: " + controllingOfficeHub);
        return controllingOfficeHub;
    }


}

