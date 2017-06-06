
package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.DailyROEHub;

        import java.sql.*;


public class DailyROEHubObjects {
    private DBHelper db = new DBHelper();

    public DailyROEHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        DailyROEHub dailyROEHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Date roeDate = rsFromSA.getDate("DATO");
                int sequenceNr = rsFromSA.getInt("LOBE_NR");
                int accessCompanyId = rsFromSA.getInt("accessCompanyId");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                dailyROEHub = new DailyROEHub(roeDate, sequenceNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("DailyROEHub from SA: " + dailyROEHub);
        return dailyROEHub;
    }

    public DailyROEHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        DailyROEHub dailyROEHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Date roeDate = rsFromDWH.getDate("roeDate");
                int sequenceNr = rsFromDWH.getInt("sequenceNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                dailyROEHub = new DailyROEHub(roeDate, sequenceNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("DailyROEHub from DWH: " + dailyROEHub);
        return dailyROEHub;
    }


}
