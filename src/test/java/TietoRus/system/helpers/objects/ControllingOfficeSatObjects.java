package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ControllingOfficeSat;

import java.sql.*;


public class ControllingOfficeSatObjects {
    private DBHelper db = new DBHelper();

    public ControllingOfficeSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ControllingOfficeSat controllingOfficeSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String companyName = rsFromSA.getString("COMPANY_NAME");
                String controllingOfficeFullName = rsFromSA.getString("CONTROLING_OFFICE");
                String officeName = rsFromSA.getString("OFFICE_NAME");
                controllingOfficeSat = new ControllingOfficeSat(companyName, controllingOfficeFullName, officeName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ControllingOfficeSat from SA: " + controllingOfficeSat);
        return controllingOfficeSat;
    }

    public ControllingOfficeSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ControllingOfficeSat controllingOfficeSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String companyName = rsFromDWH.getString("companyName");
                String controllingOfficeFullName = rsFromDWH.getString("controllingOfficeFullName");
                String officeName = rsFromDWH.getString("officeName");
                controllingOfficeSat = new ControllingOfficeSat(companyName, controllingOfficeFullName, officeName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ControllingOfficeSat from DWH: " + controllingOfficeSat);
        return controllingOfficeSat;
    }


}
