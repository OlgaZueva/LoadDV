package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.BookingManifestAdditionalsSat;

        import java.sql.*;


public class BookingManifestAdditionalsSatObjects {
    private DBHelper db = new DBHelper();

    public BookingManifestAdditionalsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingManifestAdditionalsSat bookingManifestAdditionalsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String valueCode = rsFromSA.getString("VALUE_CODE");
                bookingManifestAdditionalsSat = new BookingManifestAdditionalsSat(valueCode);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingManifestAdditionalsSat from SA: " + bookingManifestAdditionalsSat);
        return bookingManifestAdditionalsSat;
    }

    public BookingManifestAdditionalsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingManifestAdditionalsSat bookingManifestAdditionalsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String valueCode = rsFromDWH.getString("valueCode");
                bookingManifestAdditionalsSat = new BookingManifestAdditionalsSat(valueCode);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingManifestAdditionalsSat from DWH: " + bookingManifestAdditionalsSat);
        return bookingManifestAdditionalsSat;
    }


}

