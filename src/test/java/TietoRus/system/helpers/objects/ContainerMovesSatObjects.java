package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.ContainerMovesSat;

import java.sql.*;


public class ContainerMovesSatObjects {
    private DBHelper db = new DBHelper();

    public ContainerMovesSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        ContainerMovesSat containerMovesSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String remarks = rsFromSA.getString("BEMARK");
                int bookingNr = rsFromSA.getInt("BOOK_NR");
                String containerNr = rsFromSA.getString("CONT_NR");
                String containerSize = rsFromSA.getString("CONT_SIZE");
                String containerType = rsFromSA.getString("CONT_TYPE");
                String createdBy = rsFromSA.getString("CREATED_BY");
                Date creationDate = rsFromSA.getDate("CREATION_DATE");
                String damaged = rsFromSA.getString("DAMAGED");
                Date moveDate = rsFromSA.getDate("DATO");
                String destination = rsFromSA.getString("DEST");
                String moveCode = rsFromSA.getString("KODE");
                String depotLocation = rsFromSA.getString("LOC");
                String extendedLocation = rsFromSA.getString("LOC2");
                Date reportedToGvaDate = rsFromSA.getDate("RAP_DATO");
                int reportToGvaNr = rsFromSA.getInt("RAP_NR");
                String reportStatus = rsFromSA.getString("RAP_STATUS");
                String reportToGvaModule = rsFromSA.getString("REP_MODULE");
                String status = rsFromSA.getString("STAT");
                String moveTime = rsFromSA.getString("TID");
                containerMovesSat = new ContainerMovesSat(remarks, bookingNr, containerNr, containerSize, containerType, createdBy, creationDate, damaged, moveDate,
                        destination, moveCode, depotLocation, extendedLocation, reportedToGvaDate, reportToGvaNr, reportStatus, reportToGvaModule, status, moveTime );
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("ContainerMovesSat from SA: " + containerMovesSat);
        return containerMovesSat;
    }

    public ContainerMovesSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        ContainerMovesSat containerMovesSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String remarks = rsFromDWH.getString("remarks");
                int bookingNr = rsFromDWH.getInt("bookingNr");
                String containerNr = rsFromDWH.getString("containerNr");
                String containerSize = rsFromDWH.getString("containerSize");
                String containerType = rsFromDWH.getString("containerType");
                String createdBy = rsFromDWH.getString("createdBy");
                Date creationDate = rsFromDWH.getDate("creationDate");
                String damaged = rsFromDWH.getString("damaged");
                Date moveDate = rsFromDWH.getDate("moveDate");
                String destination = rsFromDWH.getString("destination");
                String moveCode = rsFromDWH.getString("moveCode");
                String depotLocation = rsFromDWH.getString("depotLocation");
                String extendedLocation = rsFromDWH.getString("extendedLocation");
                Date reportedToGvaDate = rsFromDWH.getDate("reportedToGvaDate");
                int reportToGvaNr = rsFromDWH.getInt("reportToGvaNr");
                String reportStatus = rsFromDWH.getString("reportStatus");
                String reportToGvaModule = rsFromDWH.getString("reportToGvaModule");
                String status = rsFromDWH.getString("status");
                String moveTime = rsFromDWH.getString("moveTime");
                containerMovesSat = new ContainerMovesSat(remarks, bookingNr, containerNr, containerSize, containerType, createdBy, creationDate, damaged, moveDate,
                        destination, moveCode, depotLocation, extendedLocation, reportedToGvaDate, reportToGvaNr, reportStatus, reportToGvaModule, status, moveTime );
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("ContainerMovesSat from DWH: " + containerMovesSat);
        return containerMovesSat;
    }


}
