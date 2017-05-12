
package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.BookingDTXFileSat;

        import java.sql.*;


public class BookingDTXFileSatObjects {
    private DBHelper db = new DBHelper();

    public BookingDTXFileSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingDTXFileSat bookingDTXFileSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Date fileDate = rsFromSA.getDate("FILE_DATE");
                bookingDTXFileSat = new BookingDTXFileSat(fileDate);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingDTXFileSat from SA: " + bookingDTXFileSat);
        return bookingDTXFileSat;
    }

    public BookingDTXFileSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingDTXFileSat bookingDTXFileSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Date fileDate = rsFromDWH.getDate("fileDate");
                bookingDTXFileSat = new BookingDTXFileSat(fileDate);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingDTXFileSat from DWH: " + bookingDTXFileSat);
        return bookingDTXFileSat;
    }


}
