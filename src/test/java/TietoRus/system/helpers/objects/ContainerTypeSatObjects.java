
package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ContainerTypeSat;

import java.sql.*;


public class ContainerTypeSatObjects {
    private DBHelper db = new DBHelper();

    public ContainerTypeSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ContainerTypeSat containerTypeSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                int feet = rsFromSA.getInt("CONT_FEET");
                String size = rsFromSA.getString("CONT_SIZE");
                String typeCode = rsFromSA.getString("CONT_TYPE");
                String shiperOwned = rsFromSA.getString("SHIP_OWN");
                String typeName = rsFromSA.getString("TEKST");
                String isReefer = rsFromSA.getString("TEMPERATUR");
                containerTypeSat = new ContainerTypeSat(feet, size, typeCode, shiperOwned, typeName, isReefer);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ContainerTypeSat from SA: " + containerTypeSat);
        return containerTypeSat;
    }

    public ContainerTypeSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ContainerTypeSat containerTypeSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                int feet = rsFromDWH.getInt("feet");
                String size = rsFromDWH.getString("size");
                String typeCode = rsFromDWH.getString("typeCode");
                String shiperOwned = rsFromDWH.getString("shiperOwned");
                String typeName = rsFromDWH.getString("typeName");
                String isReefer = rsFromDWH.getString("isReefer");
                containerTypeSat = new ContainerTypeSat(feet, size, typeCode, shiperOwned, typeName, isReefer);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ContainerTypeSat from DWH: " + containerTypeSat);
        return containerTypeSat;
    }


}
