package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.GetChargesHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GetChargesHubObjects {
    private DBHelper db = new DBHelper();

    public GetChargesHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        GetChargesHub getChargesHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int SELSKAB = rsFromSA.getInt("SELSKAB");
                int BOOK_NR = rsFromSA.getInt("BOOK_NR");
                int VAREPOST_NR = rsFromSA.getInt("VAREPOST_NR");
                String OMR_VALUTA = rsFromSA.getString("OMR_VALUTA");
                getChargesHub = new GetChargesHub(SELSKAB, BOOK_NR, VAREPOST_NR, OMR_VALUTA);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("GetChargesHub from SA: " + getChargesHub);
        return getChargesHub;
    }

    public GetChargesHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        GetChargesHub getChargesHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int SELSKAB = rsFromDWH.getInt("SELSKAB");
                int BOOK_NR = rsFromDWH.getInt("BOOK_NR");
                int VAREPOST_NR = rsFromDWH.getInt("VAREPOST_NR");
                String OMR_VALUTA = rsFromDWH.getString("OMR_VALUTA");
                getChargesHub = new GetChargesHub(SELSKAB, BOOK_NR, VAREPOST_NR, OMR_VALUTA);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("GetChargesHub from DWH: " + getChargesHub);
        return getChargesHub;
    }


}


