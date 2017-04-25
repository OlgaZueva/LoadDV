package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingCargoChargesSat;

import java.sql.*;

// Для BookingCargoCharges hub'ом является BookingCargoHub, а источником данных таблица saGetCharges
public class BookingCargoChargesSatObjects {
    private DBHelper db = new DBHelper();

    public BookingCargoChargesSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingCargoChargesSat bookingCargoChargesSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double price = rsFromSA.getDouble("A_PRIS");
                Double unitCount = rsFromSA.getDouble("ANTAL");
                String allPaymentTerms = rsFromSA.getString("DISTINCT_BET_KODE");
                String units = rsFromSA.getString("ENHED");
                int positionNr = rsFromSA.getInt("FRG_BT_NR");
                String chargeGroup = rsFromSA.getString("GRUPPE");
                Double roe = rsFromSA.getDouble("OMR_KURS");
                Double amount = rsFromSA.getDouble("TOTAL_BEL");
                bookingCargoChargesSat = new BookingCargoChargesSat(price, unitCount, allPaymentTerms, units, positionNr, chargeGroup, roe, amount);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingCargoChargesSat from SA: " + bookingCargoChargesSat);
        return bookingCargoChargesSat;
    }

    public BookingCargoChargesSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingCargoChargesSat bookingCargoChargesSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double price = rsFromDWH.getDouble("price");
                Double unitCount = rsFromDWH.getDouble("unitCount");
                String allPaymentTerms = rsFromDWH.getString("allPaymentTerms");
                String units = rsFromDWH.getString("units");
                int positionNr = rsFromDWH.getInt("positionNr");
                String chargeGroup = rsFromDWH.getString("chargeGroup");
                Double roe = rsFromDWH.getDouble("roe");
                Double amount = rsFromDWH.getDouble("amount");
                bookingCargoChargesSat = new BookingCargoChargesSat(price, unitCount, allPaymentTerms, units, positionNr, chargeGroup, roe, amount);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingCargoChargesSat from DWH: " + bookingCargoChargesSat);
        return bookingCargoChargesSat;
    }


}
