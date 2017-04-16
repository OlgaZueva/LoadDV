package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.FileLinerSat;

import java.sql.*;


public class FileLinerSatObjects {
    private DBHelper db = new DBHelper();

    public FileLinerSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        FileLinerSat fileLinerSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int callId = rsFromSA.getInt("CALL_ID");
                Date expPolDate = rsFromSA.getDate("EXP_AFG_DATO");
                Date impPodDate = rsFromSA.getDate("IMP_ANK_DATO");
                Date proformaDate = rsFromSA.getDate("PROFORMA_DATE");
                String voyageNr = rsFromSA.getString("REJSE_NR");
                int sailingWeek = rsFromSA.getInt("S_WEEK");
                String vesselName = rsFromSA.getString("SKIBS_NAVN");
                Date closeDate = rsFromSA.getDate("SLUT_DATO");
                Date openDate = rsFromSA.getDate("START_DATO");
                String status = rsFromSA.getString("STAT");
                String temporaryFlag = rsFromSA.getString("TEMPORARY_FLAG");
                fileLinerSat = new FileLinerSat(callId, expPolDate, impPodDate, proformaDate, voyageNr, sailingWeek, vesselName, closeDate, openDate,status, temporaryFlag);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("FileLinerHub from SA: " + fileLinerSat);
        return fileLinerSat;
    }

    public FileLinerSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        FileLinerSat fileLinerSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int callId = rsFromDWH.getInt("callId");
                Date expPolDate = rsFromDWH.getDate("expPolDate");
                Date impPodDate = rsFromDWH.getDate("impPodDate");
                Date proformaDate = rsFromDWH.getDate("proformaDate");
                String voyageNr = rsFromDWH.getString("voyageNr");
                int sailingWeek = rsFromDWH.getInt("sailingWeek");
                String vesselName = rsFromDWH.getString("vesselName");
                Date closeDate = rsFromDWH.getDate("closeDate");
                Date openDate = rsFromDWH.getDate("openDate");
                String status = rsFromDWH.getString("status");
                String temporaryFlag = rsFromDWH.getString("temporaryFlag");
                fileLinerSat = new FileLinerSat(callId, expPolDate, impPodDate, proformaDate, voyageNr, sailingWeek, vesselName, closeDate, openDate,status, temporaryFlag);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("FileLinerHub from DWH: " + fileLinerSat);
        return fileLinerSat;
    }


}
