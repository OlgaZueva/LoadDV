package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.KundeSat;

import java.sql.*;


public class KundeSatObjects {
    private DBHelper db = new DBHelper();

    public KundeSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        KundeSat kundeSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String address1 = rsFromSA.getString("ADRESSE1");
                String address2 = rsFromSA.getString("ADRESSE2");
                String address3 = rsFromSA.getString("ADRESSE3");
                String cityName = rsFromSA.getString("BYNAVN");
                String cityCode = rsFromSA.getString("CITY_CODE");
                String TMScustomerNr = rsFromSA.getString("CUSTOMER_NUMBER");
                String countryCode = rsFromSA.getString("DEB_LAND");
                String clientReference = rsFromSA.getString("DERES_REF");
                String email = rsFromSA.getString("EMAILADR");
                String shortName = rsFromSA.getString("FORKORT");
                String customerName = rsFromSA.getString("NAVN");
                String fax = rsFromSA.getString("TELEFAX");
                String phone = rsFromSA.getString("TELEFON");
                kundeSat = new KundeSat(address1, address2, address3, cityName, cityCode, TMScustomerNr, countryCode, clientReference, email, shortName, customerName, fax, phone);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("KundeSat from SA: " + kundeSat);
        return kundeSat;
    }

    public KundeSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        KundeSat kundeSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String address1 = rsFromDWH.getString("address1");
                String address2 = rsFromDWH.getString("address2");
                String address3 = rsFromDWH.getString("address3");
                String cityName = rsFromDWH.getString("cityName");
                String cityCode = rsFromDWH.getString("cityCode");
                String TMScustomerNr = rsFromDWH.getString("TMScustomerNr");
                String countryCode = rsFromDWH.getString("countryCode");
                String clientReference = rsFromDWH.getString("clientReference");
                String email = rsFromDWH.getString("email");
                String shortName = rsFromDWH.getString("shortName");
                String customerName = rsFromDWH.getString("customerName");
                String fax = rsFromDWH.getString("fax");
                String phone = rsFromDWH.getString("phone");
                kundeSat = new KundeSat(address1, address2, address3, cityName, cityCode, TMScustomerNr, countryCode, clientReference, email, shortName, customerName, fax, phone);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("FileLinerSat from DWH: " + kundeSat);
        return kundeSat;
    }


}
