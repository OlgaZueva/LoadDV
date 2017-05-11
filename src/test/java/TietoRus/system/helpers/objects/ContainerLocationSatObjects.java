package TietoRus.system.helpers.objects;

        import TietoRus.system.helpers.helpers.DBHelper;
        import TietoRus.system.helpers.models.ContainerLocationSat;

        import java.sql.*;


public class ContainerLocationSatObjects {
    private DBHelper db = new DBHelper();

    public ContainerLocationSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ContainerLocationSat containerLocationSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String contLocationName = rsFromSA.getString("TEKST_1");
                containerLocationSat = new ContainerLocationSat(contLocationName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ContainerLocationSat from SA: " + containerLocationSat);
        return containerLocationSat;
    }

    public ContainerLocationSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ContainerLocationSat containerLocationSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String contLocationName = rsFromDWH.getString("contLocationName");                ;
                containerLocationSat = new ContainerLocationSat(contLocationName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ContainerLocationSat from DWH: " + containerLocationSat);
        return containerLocationSat;
    }


}
