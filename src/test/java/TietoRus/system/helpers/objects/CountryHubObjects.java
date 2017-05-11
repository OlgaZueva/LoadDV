package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.CountryHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CountryHubObjects {
    private DBHelper db = new DBHelper();

    public CountryHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        CountryHub countryHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String dictionaryType = rsFromSA.getString("ART");
                String countryCode = rsFromSA.getString("KODE");
                int sequenceNumber = rsFromSA.getInt("LOBE_NR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                countryHub = new CountryHub(dictionaryType, countryCode, sequenceNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("CountryHub from SA: " + countryHub);
        return countryHub;
    }

    public CountryHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        CountryHub countryHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String dictionaryType = rsFromDWH.getString("dictionaryType");
                String countryCode = rsFromDWH.getString("countryCode");
                int sequenceNumber = rsFromDWH.getInt("sequenceNumber");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                countryHub = new CountryHub(dictionaryType, countryCode, sequenceNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("CountryHub from DWH: " + countryHub);
        return countryHub;
    }


}
