package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ExportVesselsHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ExportVesselsHubObjects {
    private DBHelper db = new DBHelper();

    public ExportVesselsHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ExportVesselsHub exportVesselsHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int expVesselId = rsFromSA.getInt("EXP_VESSEL_ID");
                int accessCompanyId = rsFromSA.getInt("accessCompanyId");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                exportVesselsHub = new ExportVesselsHub(expVesselId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ExportVesselsHub from SA: " + exportVesselsHub);
        return exportVesselsHub;
    }

    public ExportVesselsHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        ExportVesselsHub exportVesselsHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int expVesselId = rsFromDWH.getInt("expVesselId");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                exportVesselsHub = new ExportVesselsHub(expVesselId, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ExportVesselsHub from DWH: " + exportVesselsHub);
        return exportVesselsHub;
    }


}


