package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.WeekendsHolidaysHub;

import java.sql.*;


public class WeekendsHolidaysHubObjects {
    private DBHelper db = new DBHelper();

    public WeekendsHolidaysHub getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        WeekendsHolidaysHub weekendsHolidaysHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String serviceCode = rsFromSA.getString("AFDELING");
                Date date = rsFromSA.getDate("DATO");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                weekendsHolidaysHub = new WeekendsHolidaysHub(serviceCode, date, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("WeekendsHolidaysHub from SA: " + weekendsHolidaysHub);
        return weekendsHolidaysHub;
    }

    public WeekendsHolidaysHub getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        WeekendsHolidaysHub weekendsHolidaysHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String serviceCode = rsFromDWH.getString("serviceCode");
                Date date = rsFromDWH.getDate("date");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                weekendsHolidaysHub = new WeekendsHolidaysHub(serviceCode, date, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("WeekendsHolidaysHub from DWH: " + weekendsHolidaysHub);
        return weekendsHolidaysHub;
    }


}
