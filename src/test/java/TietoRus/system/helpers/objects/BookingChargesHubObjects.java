
package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.BookingChargesHub;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;


public class BookingChargesHubObjects {
    private DBHelper db = new DBHelper();

    public BookingChargesHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingChargesHub bookingChargesHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int bookingNr = rsFromSA.getInt("BOOK_NR");
                int positionNr = rsFromSA.getInt("FRG_BT_NR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                bookingChargesHub = new BookingChargesHub(bookingNr, positionNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingChargesHub from SA: " + bookingChargesHub);
        return bookingChargesHub;
    }

    public BookingChargesHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        BookingChargesHub bookingChargesHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int bookingNr = rsFromDWH.getInt("bookingNr");
                int positionNr = rsFromDWH.getInt("positionNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                bookingChargesHub = new BookingChargesHub(bookingNr, positionNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingChargesHub from DWH: " + bookingChargesHub);
        return bookingChargesHub;
    }


}
