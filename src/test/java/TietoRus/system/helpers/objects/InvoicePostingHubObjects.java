package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.InvoicePostingHub;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;


public class InvoicePostingHubObjects {
    private DBHelper db = new DBHelper();

    public InvoicePostingHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        InvoicePostingHub invoicePostingHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String invoiceType = rsFromSA.getString("F_TYPE");
                String invoiceNr = rsFromSA.getString("FAKTURANR");
                String debitCredit = rsFromSA.getString("K_TYPE");
                Double customerCode = rsFromSA.getDouble("KUNDE");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                invoicePostingHub = new InvoicePostingHub(invoiceType, invoiceNr, debitCredit, customerCode, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("InvoicePostingHub from SA: " + invoicePostingHub);
        return invoicePostingHub;
    }

    public InvoicePostingHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        InvoicePostingHub invoicePostingHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String invoiceType = rsFromDWH.getString("invoiceType");
                String invoiceNr = rsFromDWH.getString("invoiceNr");
                String debitCredit = rsFromDWH.getString("debitCredit");
                Double customerCode = rsFromDWH.getDouble("customerCode");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                invoicePostingHub = new InvoicePostingHub(invoiceType, invoiceNr, debitCredit, customerCode, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("InvoicePostingHub from DWH: " + invoicePostingHub);
        return invoicePostingHub;
    }


}
