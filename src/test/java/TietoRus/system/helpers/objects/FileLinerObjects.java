package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.FileLiner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FileLinerObjects {
    private DBHelper db = new DBHelper();

    public FileLiner getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        FileLiner fileLiner = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String serviceCode = rsFromSA.getString("AFDELING");
                int fileLinerNr = rsFromSA.getInt("SAGSNR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                fileLiner = new FileLiner(serviceCode, fileLinerNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("FileLiner from SA: " + fileLiner);
        return fileLiner;
    }

    public FileLiner getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        FileLiner fileLiner = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String serviceCode = rsFromDWH.getString("serviceCode");
                int fileLinerNr = rsFromDWH.getInt("fileLinerNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                fileLiner = new FileLiner(serviceCode, fileLinerNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("FileLiner from DWH: " + fileLiner);
        return fileLiner;
    }


}
