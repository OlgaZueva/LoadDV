package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.AccountingTransactionSat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccountingTransactionSatObjects {
    private DBHelper db = new DBHelper();

    public AccountingTransactionSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        AccountingTransactionSat accountingTransactionSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String serviceCode = rsFromSA.getString("AFDELING");
                String activity = rsFromSA.getString("AKTIVITET");
                Double amountHomeCurr = rsFromSA.getDouble("BELOBDKK");
                Double amountOrigCurr = rsFromSA.getDouble("BELOBVAL");
                int diaryNum = rsFromSA.getInt("DAGBOG");
                int uniqueIdentValue = rsFromSA.getInt("FP_ID");
                String group = rsFromSA.getString("GRUPPE");
                String createdBy = rsFromSA.getString("INIT");
                Double nominalNr = rsFromSA.getDouble("KONTONR");
                int branch = rsFromSA.getInt("KONTOR");
                int period = rsFromSA.getInt("PERIODE");
                int bookingNr = rsFromSA.getInt("REF_NR");
                int fileLinerNr = rsFromSA.getInt("SAGSNR");
                String text = rsFromSA.getString("TEKST");
                String transactionType = rsFromSA.getString("TRANSTYPE");
                accountingTransactionSat = new AccountingTransactionSat(serviceCode, activity, amountHomeCurr, amountOrigCurr, diaryNum, uniqueIdentValue,
                        group, createdBy, nominalNr, branch, period, bookingNr, fileLinerNr, text, transactionType);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("AccountingTransactionSat from SA: " + accountingTransactionSat);
        return accountingTransactionSat;
    }

    public AccountingTransactionSat getSatFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        AccountingTransactionSat accountingTransactionSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String serviceCode = rsFromDWH.getString("serviceCode");
                String activity = rsFromDWH.getString("activity");
                Double amountHomeCurr = rsFromDWH.getDouble("amountHomeCurr");
                Double amountOrigCurr = rsFromDWH.getDouble("amountOrigCurr");
                int diaryNum = rsFromDWH.getInt("diaryNum");
                int uniqueIdentValue = rsFromDWH.getInt("uniqueIdentValue");
                String group = rsFromDWH.getString("group");
                String createdBy = rsFromDWH.getString("createdBy");
                Double nominalNr = rsFromDWH.getDouble("nominalNr");
                int branch = rsFromDWH.getInt("branch");
                int period = rsFromDWH.getInt("period");
                int bookingNr = rsFromDWH.getInt("bookingNr");
                int fileLinerNr = rsFromDWH.getInt("fileLinerNr");
                String text = rsFromDWH.getString("text");
                String transactionType = rsFromDWH.getString("transactionType");
                accountingTransactionSat = new AccountingTransactionSat(serviceCode, activity, amountHomeCurr, amountOrigCurr, diaryNum, uniqueIdentValue,
                        group, createdBy, nominalNr, branch, period, bookingNr, fileLinerNr, text, transactionType);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("AccountingTransactionSat from DWH: " + accountingTransactionSat);
        return accountingTransactionSat;
    }


}
