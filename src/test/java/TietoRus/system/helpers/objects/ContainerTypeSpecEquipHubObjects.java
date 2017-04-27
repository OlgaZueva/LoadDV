package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ContainerTypeSpecEquipHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ContainerTypeSpecEquipHubObjects {
    private DBHelper db = new DBHelper();

    public ContainerTypeSpecEquipHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ContainerTypeSpecEquipHub containerTypeSpecEquipHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int contFeet = rsFromSA.getInt("CONT_FEET");
                String contSize = rsFromSA.getString("CONT_SIZE");
                String contType = rsFromSA.getString("CONT_TYPE");
                String contTypeName = rsFromSA.getString("TEKST");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                containerTypeSpecEquipHub = new ContainerTypeSpecEquipHub(contFeet, contSize, contType, contTypeName, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ContainerTypeSpecEquipHub from SA: " + containerTypeSpecEquipHub);
        return containerTypeSpecEquipHub;
    }

    public ContainerTypeSpecEquipHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        ContainerTypeSpecEquipHub containerTypeSpecEquipHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int contFeet = rsFromDWH.getInt("contFeet");
                String contSize = rsFromDWH.getString("contSize");
                String contType = rsFromDWH.getString("contType");
                String contTypeName = rsFromDWH.getString("contTypeName");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                containerTypeSpecEquipHub = new ContainerTypeSpecEquipHub(contFeet, contSize, contType, contTypeName, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ContainerTypeSpecEquipHub from DWH: " + containerTypeSpecEquipHub);
        return containerTypeSpecEquipHub;
    }


}
