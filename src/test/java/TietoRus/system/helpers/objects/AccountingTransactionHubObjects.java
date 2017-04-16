package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.AccountingTransactionHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccountingTransactionHubObjects {
    private DBHelper db = new DBHelper();

    public AccountingTransactionHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        AccountingTransactionHub accountingTransactionHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int itemNr = rsFromSA.getInt("BILAGSNR");;
                int sequenceNr = rsFromSA.getInt("LOBE_NR");;
                int accessCompanyId = rsFromSA.getInt("SELSKAB");;
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                accountingTransactionHub = new AccountingTransactionHub(itemNr, sequenceNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("AccountingTransactionHub from SA: " + accountingTransactionHub);
        return accountingTransactionHub;
    }

    public AccountingTransactionHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        AccountingTransactionHub accountingTransactionHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int itemNr = rsFromDWH.getInt("itemNr");;
                int sequenceNr = rsFromDWH.getInt("sequenceNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");;
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                accountingTransactionHub = new AccountingTransactionHub(itemNr, sequenceNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("AccountingTransactionHub from DWH: " + accountingTransactionHub);
        return accountingTransactionHub;
    }


}
