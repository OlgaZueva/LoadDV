
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.OceanVesselStatusHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OceanVesselServiceHubObjects {
    private DBHelper db = new DBHelper();

    public OceanVesselStatusHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        OceanVesselStatusHub oceanVesselStatusHubHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String carrierCode = rsFromSA.getString("AGENT");
                String serviceCode = rsFromSA.getString("FELT");
                String segmentCode = rsFromSA.getString("FRA");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                String statusCode = rsFromSA.getString("TIL");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                oceanVesselStatusHubHub = new OceanVesselStatusHub(carrierCode, serviceCode, segmentCode, accessCompanyId, statusCode, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("OceanVesselStatusHub from SA: " + oceanVesselStatusHubHub);
        return oceanVesselStatusHubHub;
    }

    public OceanVesselStatusHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        OceanVesselStatusHub oceanVesselStatusHubHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String carrierCode = rsFromDWH.getString("carrierCode");
                String serviceCode = rsFromDWH.getString("serviceCode");
                String segmentCode = rsFromDWH.getString("segmentCode");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                String statusCode = rsFromDWH.getString("statusCode");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                oceanVesselStatusHubHub = new OceanVesselStatusHub(carrierCode, serviceCode, segmentCode, accessCompanyId, statusCode, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("OceanVesselStatusHub from DWH: " + oceanVesselStatusHubHub);
        return oceanVesselStatusHubHub;
    }


}
