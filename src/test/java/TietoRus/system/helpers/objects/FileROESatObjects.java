package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.FileROESat;

import java.sql.*;


public class FileROESatObjects {
    private DBHelper db = new DBHelper();

    public FileROESat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        FileROESat fileROESat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                Double rate = rsFromSA.getDouble("KURS");
                fileROESat = new FileROESat(rate);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("FileROESat from SA: " + fileROESat);
        return fileROESat;
    }

    public FileROESat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        FileROESat fileROESat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                Double rate = rsFromDWH.getDouble("KURS");
                fileROESat = new FileROESat(rate);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("FileROESat from DWH: " + fileROESat);
        return fileROESat;
    }


}
