
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.VesselRegistrySat;

import java.sql.*;


public class VesselRegistrySatObjects {
    private DBHelper db = new DBHelper();

    public VesselRegistrySat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        VesselRegistrySat vesselRegistrySat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double lloydsNr = rsFromSA.getDouble("KONTONR");
                String callSign = rsFromSA.getString("MANIFEST");
                String vesselName = rsFromSA.getString("TEKST_1");
                vesselRegistrySat = new VesselRegistrySat(lloydsNr, callSign, vesselName);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("VesselRegistrySat from SA: " + vesselRegistrySat);
        return vesselRegistrySat;
    }

    public VesselRegistrySat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        VesselRegistrySat vesselRegistrySat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double lloydsNr = rsFromDWH.getDouble("lloydsNr");
                String callSign = rsFromDWH.getString("callSign");
                String vesselName = rsFromDWH.getString("vesselName");
                vesselRegistrySat = new VesselRegistrySat(lloydsNr, callSign, vesselName);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("VesselRegistrySat from DWH: " + vesselRegistrySat);
        return vesselRegistrySat;
    }


}
