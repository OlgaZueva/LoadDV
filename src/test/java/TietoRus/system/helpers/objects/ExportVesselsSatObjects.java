package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ExportVesselsSat;

import java.sql.*;


public class ExportVesselsSatObjects {
    private DBHelper db = new DBHelper();

    public ExportVesselsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ExportVesselsSat exportVesselsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Date actualSailDate =  rsFromSA.getDate("ACTUAL_SAIL_DATE");
                String gvaTrade = rsFromSA.getString("GVA_TRADE");
                String oceanTrade = rsFromSA.getString("OLD_TRADE");
                String vesselCode = rsFromSA.getString("VESSEL_CODE");
                String vesselName = rsFromSA.getString("VESSEL_NAME");
                String voyageNr = rsFromSA.getString("VOY_NR");
                int week = rsFromSA.getInt("WEEK");
                exportVesselsSat = new ExportVesselsSat(actualSailDate, gvaTrade, oceanTrade, vesselCode, vesselName, voyageNr, week);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ExportVesselsSat from SA: " + exportVesselsSat);
        return exportVesselsSat;
    }

    public ExportVesselsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ExportVesselsSat exportVesselsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Date actualSailDate =  rsFromDWH.getDate("actualSailDate");
                String gvaTrade = rsFromDWH.getString("gvaTrade");
                String oceanTrade = rsFromDWH.getString("oceanTrade");
                String vesselCode = rsFromDWH.getString("vesselCode");
                String vesselName = rsFromDWH.getString("vesselName");
                String voyageNr = rsFromDWH.getString("voyageNr");
                int week = rsFromDWH.getInt("sailingWeek");
                exportVesselsSat = new ExportVesselsSat(actualSailDate, gvaTrade, oceanTrade, vesselCode, vesselName, voyageNr, week);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ExportVesselsSat from DWH: " + exportVesselsSat);
        return exportVesselsSat;
    }


}

