
package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.LocationsSat;

        import java.sql.*;


public class LocationsSatObjects {
    private DBHelper db = new DBHelper();

    public LocationsSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        LocationsSat locationsSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String ovTradeNumberExp = rsFromSA.getString("CHAR_ATRIBUTE1");
                String ovTradeNumberImp = rsFromSA.getString("CHAR_ATRIBUTE4");
                String ovTradeName = rsFromSA.getString("CHAR_ATRIBUTE2");
                String gvaTrade = rsFromSA.getString("CHAR_ATRIBUTE9");
                String countryCode = rsFromSA.getString("MONT_ENHED");
                String locationName = rsFromSA.getString("TEKST_1");
                locationsSat = new LocationsSat(ovTradeNumberExp, ovTradeNumberImp, ovTradeName, gvaTrade,  countryCode, locationName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("LocationsSat from SA: " + locationsSat);
        return locationsSat;
    }

    public LocationsSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        LocationsSat locationsSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String ovTradeNumberExp = rsFromDWH.getString("ovTradeNumberExp");
                String ovTradeNumberImp = rsFromDWH.getString("ovTradeNumberImp");
                String ovTradeName = rsFromDWH.getString("ovTradeName");
                String gvaTrade = rsFromDWH.getString("gvaTrade");
                String countryCode = rsFromDWH.getString("countryCode");
                String locationName = rsFromDWH.getString("locationName");
                locationsSat = new LocationsSat(ovTradeNumberExp, ovTradeNumberImp, ovTradeName, gvaTrade, countryCode, locationName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("LocationsSat from DWH: " + locationsSat);
        return locationsSat;
    }


}
