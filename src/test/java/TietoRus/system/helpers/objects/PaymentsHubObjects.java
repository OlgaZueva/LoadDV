package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.PaymentsHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PaymentsHubObjects {
    private DBHelper db = new DBHelper();

    public PaymentsHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        PaymentsHub payments = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String invoiceType = rsFromSA.getString("F_TYPE");
                String invoiceNr = rsFromSA.getString("FAKTURANR");
                String debitCredit = rsFromSA.getString("K_TYPE");
                Double customerCode = rsFromSA.getDouble("KUNDE");
                int sequenceNr = rsFromSA.getInt("LOBE_NR");
                int  accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                payments = new PaymentsHub(invoiceType, invoiceNr, debitCredit, customerCode,  sequenceNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("PaymentsHub from SA: " + payments);
        return payments;
    }

    public PaymentsHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        PaymentsHub payments = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String invoiceType = rsFromDWH.getString("invoiceType");
                String invoiceNr = rsFromDWH.getString("invoiceNr");
                String debitCredit = rsFromDWH.getString("debitCredit");
                Double customerCode = rsFromDWH.getDouble("customerCode");
                int sequenceNr = rsFromDWH.getInt("sequenceNr");
                int  accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                payments = new PaymentsHub(invoiceType, invoiceNr, debitCredit, customerCode,  sequenceNr, sequenceNr, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("PaymentsHub from DWH: " + payments);
        return payments;
    }


}
