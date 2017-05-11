package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ContainerLocationHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ContainerLocationHubObjects {
    private DBHelper db = new DBHelper();

    public ContainerLocationHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ContainerLocationHub containerLocationHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String dictionaryType = rsFromSA.getString("ART");
                String containerLocationCode = rsFromSA.getString("KODE");
                int sequenceNumber = rsFromSA.getInt("LOBE_NR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                containerLocationHub = new ContainerLocationHub(dictionaryType, containerLocationCode, sequenceNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ContainerLocationHub from SA: " + containerLocationHub);
        return containerLocationHub;
    }

    public ContainerLocationHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        ContainerLocationHub containerLocationHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String dictionaryType = rsFromDWH.getString("dictionaryType");
                String containerLocationCode = rsFromDWH.getString("containerLocationCode");
                int sequenceNumber = rsFromDWH.getInt("sequenceNumber");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                containerLocationHub = new ContainerLocationHub(dictionaryType, containerLocationCode, sequenceNumber, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ContainerLocationHub from DWH: " + containerLocationHub);
        return containerLocationHub;
    }


}
