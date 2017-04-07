package TietoRus.helpers;

import TietoRus.FileLinerTests.zSQLforTestData;
import TietoRus.models.FileLiner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GetDataHelper {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private zSQLforTestData SQL = new zSQLforTestData();

    private Map<String, Object> mapAllFromSA = new HashMap<String, Object>();
    private Map<String, Object> mapHubFromDWH = new HashMap<String, Object>();

    public Integer getTryCtnFromSA(String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Integer tryCnt = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() < 1) {
                System.err.println("TryCtn. Record in SA not found!");
            } else {
                if (rsFromSA.getRow() > 1) {
                    System.err.println("TryCtn. More then one record in SA. Check query!");
                } else {
                    tryCnt = Integer.valueOf(rsFromSA.getString("tryCnt"));
                    //System.out.println("tryCnt in SA: " + tryCnt);
                }
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return tryCnt;
    }

    public Integer getHubStatusFromSA(String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Integer hubStatus = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() < 1) {
                System.err.println("HubStatus. Record in SA not found!");
            } else {
                if (rsFromSA.getRow() > 1) {
                    System.err.println("HubStatus. More then one record in SA. Check query!");
                } else {
                    hubStatus = Integer.valueOf(rsFromSA.getString("statusHub"));
                    //System.out.println("statusHub in SA: " + hubStatus);
                }
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return hubStatus;
    }



    public int getCountRowOfHub(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        int countRowHub = 0;
        while (rsFromDWH.next()) {
            countRowHub = rsFromDWH.getRow();
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("countRowHub in DWH: " + countRowHub);
        return countRowHub;
    }

    public Integer getDWHidHub(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        Integer dwhIdHubFileLiner = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                dwhIdHubFileLiner = rsFromDWH.getInt("dwhIdHubFileLiner");
            } else {
                System.err.println("dwhIdHubFileLiner. Record nor found or more one!");
                return dwhIdHubFileLiner;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("dwhIdHubFileLiner in DWH: " + dwhIdHubFileLiner);
        return dwhIdHubFileLiner;
    }

    public Integer getSatHubStatus(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        System.out.println("Status из DWH: " + sql);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        Integer status = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                status = rsFromDWH.getInt("status");
            } else {
                System.err.println("status. Record nor found or more one!");
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("status in DWH: " + status);
        return status;
    }



    public int getCountRowInSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        int countRowSA = 0;
        while (rsFromSA.next()) {
            countRowSA = rsFromSA.getRow();
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return countRowSA;
    }

    public void insertTestRowInSA(String tableName) throws SQLException {
        String insert = SQL.getInsertIntoSA(tableName);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(insert);
        System.out.println("SQL for Insert in SA: " + insert);
        System.out.println("Insert test row complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

    public void insertTestRowInDWH(String tableName) throws SQLException {
        String insert = SQL.getInsertIntoDWH(tableName);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(insert);
        System.out.println("SQL for Insert in DWH: " + insert);
        System.out.println("Insert test row complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }

    public void deleteTestRowFromSA(String tableName) throws SQLException {
        String delete = SQL.getDeleteFromSA(tableName);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(delete);
        System.out.println("SQL for Delete from SA: " + delete);
        System.out.println("Delete test row in SA complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

    public void deleteTestRowFromDWH(String tableName) throws SQLException {
        String delete = SQL.getDeleteFromDWH(tableName);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(delete);
        System.out.println("SQL for Delete from DWH: " + delete);
        System.out.println("Delete test row in DWH complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }

    public Map<String, Object> getMapFromSA(String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);

        while (rsFromSA.next()) {
            if (rsFromSA.getRow() > 1) {
                System.err.println("Count rows got from SA: " + rsFromSA.getRow()
                        + ". If its > 1 check values in keys in sql query to SA! SQL: " + sql);
            } else {
                for (int k = 1; k <= rsFromSA.getMetaData().getColumnCount(); k++) {
                    mapAllFromSA.put(rsFromSA.getMetaData().getColumnName(k), rsFromSA.getObject(k));
                    System.out.println("SA MAP: " + mapAllFromSA);
                }
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return mapAllFromSA;
    }

    public Map<String, Object> getHubMap(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() < 1) {
                System.out.println("Record in SA not found!");
            } else {
                for (int k = 1; k <= rsFromDWH.getMetaData().getColumnCount(); k++) {
                    mapHubFromDWH.put(rsFromDWH.getMetaData().getColumnName(k), rsFromDWH.getObject(k));
                    System.out.println("Hub Map: " + mapHubFromDWH);
                }
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return mapHubFromDWH;
    }
}
