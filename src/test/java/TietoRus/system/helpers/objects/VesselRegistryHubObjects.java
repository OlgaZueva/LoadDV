package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.VesselRegistryHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class VesselRegistryHubObjects {
    private DBHelper db = new DBHelper();

    public VesselRegistryHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        VesselRegistryHub vesselRegistryHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String dictionaryType = rsFromSA.getString("ART");
                String feederCode = rsFromSA.getString("KODE");
                int sequenctNumber = rsFromSA.getInt("LOBE_NR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                vesselRegistryHub = new VesselRegistryHub(dictionaryType, feederCode, sequenctNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("VesselRegistryHub from SA: " + vesselRegistryHub);
        return vesselRegistryHub;
    }

    public VesselRegistryHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        VesselRegistryHub vesselRegistryHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String dictionaryType = rsFromDWH.getString("dictionaryType");
                String feederCode = rsFromDWH.getString("feederCode");
                int sequenctNumber = rsFromDWH.getInt("sequenctNumber");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                vesselRegistryHub = new VesselRegistryHub(dictionaryType, feederCode, sequenctNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("VesselRegistryHub from DWH: " + vesselRegistryHub);
        return vesselRegistryHub;
    }


}
