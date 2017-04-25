package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.CurrencySat;

import java.sql.*;


public class CurrencySatObjects {
    private DBHelper db = new DBHelper();

    public CurrencySat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        CurrencySat currencySat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String currencyName = rsFromSA.getString("TEKST_1");
                currencySat = new CurrencySat(currencyName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("CurrencySat from SA: " + currencySat);
        return currencySat;
    }

    public CurrencySat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        CurrencySat currencySat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String currencyName = rsFromDWH.getString("TEKST_1");
                currencySat = new CurrencySat(currencyName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("CurrencySat from DWH: " + currencySat);
        return currencySat;
    }


}
