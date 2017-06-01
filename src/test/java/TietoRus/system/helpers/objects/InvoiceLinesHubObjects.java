package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.InvoiceLinesHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InvoiceLinesHubObjects {
    private DBHelper db = new DBHelper();

    public InvoiceLinesHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        InvoiceLinesHub invoiceLinesHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int invoiceLineNr = rsFromSA.getInt("LINIE_NR");
                int orderNr = rsFromSA.getInt("ORDRE_NR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                invoiceLinesHub = new InvoiceLinesHub(invoiceLineNr, orderNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("InvoiceLinesHub from SA: " + invoiceLinesHub);
        return invoiceLinesHub;
    }

    public InvoiceLinesHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        InvoiceLinesHub invoiceLinesHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int invoiceLineNr = rsFromDWH.getInt("invoiceLineNr");
                int orderNr = rsFromDWH.getInt("orderNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                invoiceLinesHub = new InvoiceLinesHub(invoiceLineNr, orderNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("InvoiceLinesHub from DWH: " + invoiceLinesHub);
        return invoiceLinesHub;
    }


}
