package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.InvoiceSat;

import java.sql.*;


public class InvoiceSatObjects {
    private DBHelper db = new DBHelper();

    public InvoiceSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        InvoiceSat invoiceSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String paymentTerms = rsFromSA.getString("BETAL_BET");
                String invoiceType = rsFromSA.getString("DK_KODE");
                String invoiceNr = rsFromSA.getString("FAK_NR");
                Date invoiceDate = rsFromSA.getDate("FAKT_DATO");
                Date dueDate = rsFromSA.getDate("FORF_DATO");
                String importExportCode = rsFromSA.getString("IMP_EXP");
                Double invoiceRoe = rsFromSA.getDouble("KURS");
                Date printDate = rsFromSA.getDate("PRINT_DATE");
                String isRelatedType = rsFromSA.getString("REF_TYPE");
                Date registrationDate = rsFromSA.getDate("REG_DATO");
                int bookingNr = rsFromSA.getInt("REG_NR");
                Date serviceDate = rsFromSA.getDate("SERVICE_DATE");
                String status = rsFromSA.getString("STAT");
                String responsibleUser = rsFromSA.getString("VOR_REF");
                invoiceSat = new InvoiceSat(paymentTerms, invoiceType, invoiceNr, invoiceDate, dueDate, importExportCode, invoiceRoe, printDate, isRelatedType, registrationDate,
                        bookingNr, serviceDate, status,responsibleUser);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("InvoiceSat from SA: " + invoiceSat);
        return invoiceSat;
    }

    public InvoiceSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        InvoiceSat invoiceSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String paymentTerms = rsFromDWH.getString("paymentTerms");
                String invoiceType = rsFromDWH.getString("invoiceType");
                String invoiceNr = rsFromDWH.getString("invoiceNr");
                Date invoiceDate = rsFromDWH.getDate("invoiceDate");
                Date dueDate = rsFromDWH.getDate("dueDate");
                String importExportCode = rsFromDWH.getString("importExportCode");
                Double invoiceRoe = rsFromDWH.getDouble("invoiceRoe");
                Date printDate = rsFromDWH.getDate("printDate");
                String isRelatedType = rsFromDWH.getString("isRelatedType");
                Date registrationDate = rsFromDWH.getDate("registrationDate");
                int bookingNr = rsFromDWH.getInt("bookingNr");
                Date serviceDate = rsFromDWH.getDate("serviceDate");
                String status = rsFromDWH.getString("status");
                String responsibleUser = rsFromDWH.getString("responsibleUser");
                invoiceSat = new InvoiceSat(paymentTerms, invoiceType, invoiceNr, invoiceDate, dueDate, importExportCode, invoiceRoe, printDate, isRelatedType, registrationDate,
                        bookingNr, serviceDate, status,responsibleUser);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("InvoiceSat from DWH: " + invoiceSat);
        return invoiceSat;
    }


}

