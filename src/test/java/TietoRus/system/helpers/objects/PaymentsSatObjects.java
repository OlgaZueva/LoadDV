package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.PaymentsSat;

import java.sql.*;


public class PaymentsSatObjects {
    private DBHelper db = new DBHelper();

    public PaymentsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        PaymentsSat paymentsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double invoiceAmountHomeCurr = rsFromSA.getDouble("BELOBDKK");
                Double invoiceAmountOrigCurr = rsFromSA.getDouble("BELOBVAL");
                int itemNr = rsFromSA.getInt("BILAGSNR");
                Date paymentDate = rsFromSA.getDate("DATO");
                String text = rsFromSA.getString("TEKST");
                String invoiceOrigCurr = rsFromSA.getString("VALUTA");
                paymentsSat = new PaymentsSat(invoiceAmountHomeCurr, invoiceAmountOrigCurr, itemNr, paymentDate, text, invoiceOrigCurr);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("PaymentsSat from SA: " + paymentsSat);
        return paymentsSat;
    }

    public PaymentsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        PaymentsSat paymentsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double invoiceAmountHomeCurr = rsFromDWH.getDouble("invoiceAmountHomeCurr");
                Double invoiceAmountOrigCurr = rsFromDWH.getDouble("invoiceAmountOrigCurr");
                int itemNr = rsFromDWH.getInt("itemNr");
                Date paymentDate = rsFromDWH.getDate("paymentDate");
                String text = rsFromDWH.getString("text");
                String invoiceOrigCurr = rsFromDWH.getString("invoiceOrigCurr");
                paymentsSat = new PaymentsSat(invoiceAmountHomeCurr, invoiceAmountOrigCurr, itemNr, paymentDate, text, invoiceOrigCurr);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("PaymentsSat from DWH: " + paymentsSat);
        return paymentsSat;
    }


}

