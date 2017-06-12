package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingChargesLinesSat;

import java.sql.*;


public class BookingChargesLinesSatObjects {
    private DBHelper db = new DBHelper();

    public BookingChargesLinesSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingChargesLinesSat bookingChargesLinesSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double count = rsFromSA.getDouble("ANTAL");
                String paymentTermCode = rsFromSA.getString("BET_KODE");
                String contNr = rsFromSA.getString("CONT_NR");
                String units = rsFromSA.getString("ENHED");
                String chargeGroup = rsFromSA.getString("GRUPPE");
                Double currencyRoe = rsFromSA.getDouble("OMR_KURS");
                String currency = rsFromSA.getString("OMR_VALUTA");
                String chargeName = rsFromSA.getString("TEKST");
                Double amount = rsFromSA.getDouble("TOTAL_BEL");
                int chargeNr = rsFromSA.getInt("YDE_NR");
                bookingChargesLinesSat = new BookingChargesLinesSat(count, paymentTermCode, contNr, units, chargeGroup, currencyRoe, currency, chargeName, amount, chargeNr);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingChargesLinesSat from SA: " + bookingChargesLinesSat);
        return bookingChargesLinesSat;
    }

    public BookingChargesLinesSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingChargesLinesSat bookingChargesLinesSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double count = rsFromDWH.getDouble("count");
                String paymentTermCode = rsFromDWH.getString("paymentTermCode");
                String contNr = rsFromDWH.getString("contNr");
                String units = rsFromDWH.getString("units");
                String chargeGroup = rsFromDWH.getString("chargeGroup");
                Double currencyRoe = rsFromDWH.getDouble("currencyRoe");
                String currency = rsFromDWH.getString("currency");
                String chargeName = rsFromDWH.getString("chargeName");
                Double amount = rsFromDWH.getDouble("amount");
                int chargeNr = rsFromDWH.getInt("chargeNr");
               bookingChargesLinesSat = new BookingChargesLinesSat(count, paymentTermCode, contNr, units, chargeGroup, currencyRoe, currency, chargeName, amount, chargeNr);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingChargesLinesSat from DWH: " + bookingChargesLinesSat);
        return bookingChargesLinesSat;
    }


}
