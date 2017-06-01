
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.InvoiceLinesSat;

import java.sql.*;


public class InvoiceLinesSatObjects {
    private DBHelper db = new DBHelper();

    public InvoiceLinesSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        InvoiceLinesSat invoiceLinesSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double price = rsFromSA.getDouble("A_PRIS");
                String activity = rsFromSA.getString("AKTIVITET");
                Double unitCount = rsFromSA.getDouble("ANTAL");
                String containerNr = rsFromSA.getString("CONT_NR");
                String containerType = rsFromSA.getString("CONT_TYPE");
                String units = rsFromSA.getString("ENHED");
                Double roe = rsFromSA.getDouble("OMR_KURS");
                String invoiceCurrency = rsFromSA.getString("OMR_VALUTA");
                String chargeName = rsFromSA.getString("TEKST");
                Double invoicedAmount = rsFromSA.getDouble("TOTAL_BEL");
                int chargeNr = rsFromSA.getInt("YDE_NR");
                invoiceLinesSat = new InvoiceLinesSat(price, activity, unitCount, containerNr, containerType, units, roe, invoiceCurrency, chargeName, invoicedAmount, chargeNr);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("InvoiceLinesSat from SA: " + invoiceLinesSat);
        return invoiceLinesSat;
    }

    public InvoiceLinesSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        InvoiceLinesSat invoiceLinesSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double price = rsFromDWH.getDouble("price");
                String activity = rsFromDWH.getString("activity");
                Double unitCount = rsFromDWH.getDouble("unitCount");
                String containerNr = rsFromDWH.getString("containerNr");
                String containerType = rsFromDWH.getString("containerType");
                String units = rsFromDWH.getString("units");
                Double roe = rsFromDWH.getDouble("roe");
                String invoiceCurrency = rsFromDWH.getString("invoiceCurrency");
                String chargeName = rsFromDWH.getString("chargeName");
                Double invoicedAmount = rsFromDWH.getDouble("invoicedAmount");
                int chargeNr = rsFromDWH.getInt("chargeNr");
                invoiceLinesSat = new InvoiceLinesSat(price, activity, unitCount, containerNr, containerType, units, roe, invoiceCurrency, chargeName, invoicedAmount, chargeNr);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("InvoiceLinesSat from DWH: " + invoiceLinesSat);
        return invoiceLinesSat;
    }


}
