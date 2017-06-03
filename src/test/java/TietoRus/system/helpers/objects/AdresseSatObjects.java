package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.AdresseSat;

import java.sql.*;


public class AdresseSatObjects {
    private DBHelper db = new DBHelper();

    public AdresseSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        AdresseSat adresseSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String address1 = rsFromSA.getString("ADRESSE1");
                String address2 = rsFromSA.getString("ADRESSE2");
                String address3 = rsFromSA.getString("ADRESSE3");
                String cityName = rsFromSA.getString("BYNAVN");
                String cityCode = rsFromSA.getString("CITY_CODE");
                String TMScustomerNr = rsFromSA.getString("CUSTOMER_NUMBER");
                String countryCode = rsFromSA.getString("DEB_LAND");
                String email = rsFromSA.getString("EMAILADR");
                String customerName = rsFromSA.getString("NAVN");
                String fax = rsFromSA.getString("TELEFAX");
                String phone = rsFromSA.getString("TELEFON");
                adresseSat = new AdresseSat(address1, address2, address3, cityName, cityCode, TMScustomerNr, countryCode, email,  customerName, fax, phone);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("AdresseSat from SA: " + adresseSat);
        return adresseSat;
    }

    public AdresseSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        AdresseSat adresseSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String address1 = rsFromDWH.getString("address1");
                String address2 = rsFromDWH.getString("address2");
                String address3 = rsFromDWH.getString("address3");
                String cityName = rsFromDWH.getString("cityName");
                String cityCode = rsFromDWH.getString("cityCode");
                String TMScustomerNr = rsFromDWH.getString("TMScustomerNr");
                String countryCode = rsFromDWH.getString("countryCode");
                String email = rsFromDWH.getString("email");
                String customerName = rsFromDWH.getString("customerName");
                String fax = rsFromDWH.getString("fax");
                String phone = rsFromDWH.getString("phone");
                adresseSat = new AdresseSat(address1, address2, address3, cityName, cityCode, TMScustomerNr, countryCode, email,  customerName, fax, phone);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("AdresseSat from DWH: " + adresseSat);
        return adresseSat;
    }


}

