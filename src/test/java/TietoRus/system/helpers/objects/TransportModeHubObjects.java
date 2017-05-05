package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.TransportModeHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TransportModeHubObjects {
    private DBHelper db = new DBHelper();

    public TransportModeHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        TransportModeHub transportModeHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String carrierCode = rsFromSA.getString("AGENT");
                String dtxCode = rsFromSA.getString("FELT");
                String segmentCode = rsFromSA.getString("FRA");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                String transportModeCode = rsFromSA.getString("TIL");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                transportModeHub = new TransportModeHub(carrierCode, dtxCode, segmentCode, accessCompanyId, transportModeCode, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("TransportModeHub from SA: " + transportModeHub);
        return transportModeHub;
    }

    public TransportModeHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        TransportModeHub transportModeHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String carrierCode = rsFromDWH.getString("carrierCode");
                String dtxCode = rsFromDWH.getString("serviceCode");
                String segmentCode = rsFromDWH.getString("segmentCode");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                String transportModeCode = rsFromDWH.getString("statusCode");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                transportModeHub = new TransportModeHub(carrierCode, dtxCode, segmentCode, accessCompanyId, transportModeCode, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("TransportModeHub from DWH: " + transportModeHub);
        return transportModeHub;
    }


}
