package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ContainerTypeSpecEquipSat;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class ContainerTypeSpecEquipSatObjects {
    private DBHelper db = new DBHelper();

    public ContainerTypeSpecEquipSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ContainerTypeSpecEquipSat containerTypeSpecEquipSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String masterContType = rsFromSA.getString("MasterContType");
                String isTank = rsFromSA.getString("TANK");
                String isSpecialWithRef = rsFromSA.getString("SpecialEqpInclREF");
                String isSpecialWoRef = rsFromSA.getString("SpecialEqpWoREF");
                String isFlatrack = rsFromSA.getString("FLATRACK");
                String isPlatform = rsFromSA.getString("PLATFORM");
                String isOpentop = rsFromSA.getString("OPENTOP");
                Date fromDate = rsFromSA.getDate("VALID_FROM");
                containerTypeSpecEquipSat = new ContainerTypeSpecEquipSat(masterContType, isTank, isSpecialWithRef, isSpecialWoRef, isFlatrack, isPlatform, isOpentop, fromDate);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ContainerTypeSpecEquipSat from SA: " + containerTypeSpecEquipSat);
        return containerTypeSpecEquipSat;
    }

    public ContainerTypeSpecEquipSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ContainerTypeSpecEquipSat containerTypeSpecEquipSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String masterContType = rsFromDWH.getString("masterContType");
                String isTank = rsFromDWH.getString("isTank");
                String isSpecialWithRef = rsFromDWH.getString("isSpecialWithRef");
                String isSpecialWoRef = rsFromDWH.getString("isSpecialWoRef");
                String isFlatrack = rsFromDWH.getString("isFlatrack");
                String isPlatform = rsFromDWH.getString("isPlatform");
                String isOpentop = rsFromDWH.getString("isOpentop");
                Date fromDate = rsFromDWH.getDate("fromDate");
                containerTypeSpecEquipSat = new ContainerTypeSpecEquipSat(masterContType, isTank, isSpecialWithRef, isSpecialWoRef, isFlatrack, isPlatform, isOpentop, fromDate);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ContainerTypeSpecEquipSat from DWH: " + containerTypeSpecEquipSat);
        return containerTypeSpecEquipSat;
    }


}
