package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.InvoicePostingSat;

import java.sql.*;


public class InvoicePostingSatObjects {
    private DBHelper db = new DBHelper();

    public InvoicePostingSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        InvoicePostingSat invoicePostingSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double invoiceAmountHomeCurr = rsFromSA.getDouble("BELOBDKK");
                Double invoiceAmountOrigCurr = rsFromSA.getDouble("BELOBVAL");
                Date paymentDate = rsFromSA.getDate("BET_DATO");
                String paymentMethod = rsFromSA.getString("BET_MD");
                String paymentTerms = rsFromSA.getString("BETAL_BET");
                String paymentReference = rsFromSA.getString("BETAL_REF");
                int itemNr = rsFromSA.getInt("BILAGSNR");
                int diaryNum = rsFromSA.getInt("DAGBOG");
                Date postingDate = rsFromSA.getDate("DATO");
                Date dueDate = rsFromSA.getDate("FORF_DATO");
                int uniqueIdentValue = rsFromSA.getInt("FP_ID");
                String createdBy = rsFromSA.getString("INIT");
                Double roe = rsFromSA.getDouble("KURS");
                String invoiceOrigCurr = rsFromSA.getString("MONT_ENHED");
                Double relatedInvoiceNr = rsFromSA.getDouble("REL_FAK_NR");
                Double remainingAmountHomeCurr = rsFromSA.getDouble("RESTDKK");
                String remainingAmountOrigCurr = rsFromSA.getString("RESTVAL");
                String sapDocumentNr = rsFromSA.getString("SAP_DOCUMENT_NBR");
                String sourceModule = rsFromSA.getString("SOURCE_MODULE");
                String text = rsFromSA.getString("TEKST");
                invoicePostingSat = new InvoicePostingSat(invoiceAmountHomeCurr, invoiceAmountOrigCurr, paymentDate, paymentMethod, paymentTerms, paymentReference, itemNr,
                        diaryNum, postingDate, dueDate, uniqueIdentValue, createdBy, roe, invoiceOrigCurr, relatedInvoiceNr, remainingAmountHomeCurr,
                        remainingAmountOrigCurr, sapDocumentNr, sourceModule, text);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("InvoicePostingSat from SA: " + invoicePostingSat);
        return invoicePostingSat;
    }

    public InvoicePostingSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        InvoicePostingSat invoicePostingSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double invoiceAmountHomeCurr = rsFromDWH.getDouble("invoiceAmountHomeCurr");
                Double invoiceAmountOrigCurr = rsFromDWH.getDouble("invoiceAmountOrigCurr");
                Date paymentDate = rsFromDWH.getDate("paymentDate");
                String paymentMethod = rsFromDWH.getString("paymentMethod");
                String paymentTerms = rsFromDWH.getString("paymentTerms");
                String paymentReference = rsFromDWH.getString("paymentReference");
                int itemNr = rsFromDWH.getInt("itemNr");
                int diaryNum = rsFromDWH.getInt("diaryNum");
                Date postingDate = rsFromDWH.getDate("postingDate");
                Date dueDate = rsFromDWH.getDate("dueDate");
                int uniqueIdentValue = rsFromDWH.getInt("uniqueIdentValue");
                String createdBy = rsFromDWH.getString("createdBy");
                Double roe = rsFromDWH.getDouble("roe");
                String invoiceOrigCurr = rsFromDWH.getString("invoiceOrigCurr");
                Double relatedInvoiceNr = rsFromDWH.getDouble("relatedInvoiceNr");
                Double remainingAmountHomeCurr = rsFromDWH.getDouble("remainingAmountHomeCurr");
                String remainingAmountOrigCurr = rsFromDWH.getString("remainingAmountOrigCurr");
                String sapDocumentNr = rsFromDWH.getString("sapDocumentNr");
                String sourceModule = rsFromDWH.getString("sourceModule");
                String text = rsFromDWH.getString("text");
                invoicePostingSat = new InvoicePostingSat(invoiceAmountHomeCurr, invoiceAmountOrigCurr, paymentDate, paymentMethod, paymentTerms, paymentReference, itemNr,
                        diaryNum, postingDate, dueDate, uniqueIdentValue, createdBy, roe, invoiceOrigCurr,  relatedInvoiceNr, remainingAmountHomeCurr,
                        remainingAmountOrigCurr, sapDocumentNr, sourceModule, text);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("InvoicePostingSat from DWH: " + invoicePostingSat);
        return invoicePostingSat;
    }


}
