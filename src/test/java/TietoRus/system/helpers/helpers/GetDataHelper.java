package TietoRus.system.helpers.helpers;

import TietoRus.FileLinerTests.zSQLforTestData;

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
                    System.err.println("TryCtn. More then one record in SA. Check query if it's not expected!");
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
                    System.err.println("HubStatus. More then one record in SA. Check query if it's not expected!");
                } else {
                    hubStatus = Integer.valueOf(rsFromSA.getString("statusHub"));
                    //System.out.println("statusHub in SA: " + hubStatus);
                }
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return hubStatus;
    }

    public Integer getSatStatusFromSA(String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Integer satStatus = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() < 1) {
                System.err.println("SatStatus. Record in SA not found!");
            } else {
                if (rsFromSA.getRow() > 1) {
                    System.err.println("SatStatus. More then one record in SA. Check query!");
                } else {
                    satStatus = Integer.valueOf(rsFromSA.getString("statusSat"));
                    //System.out.println("statusSat in SA: " + satStatus);
                }
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return satStatus;
    }

    public Integer getLnkStatusFromSA(String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Integer lnkStatus = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() < 1) {
                System.err.println("LnkStatus. Record in SA not found!");
            } else {
                if (rsFromSA.getRow() > 1) {
                    System.err.println("LnkStatus. More then one record in SA. Check query if it's not expected!");
                } else {
                    lnkStatus = Integer.valueOf(rsFromSA.getString("statusLnk"));
                    //System.out.println("statusLnk in SA: " + lnkStatus);
                }
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return lnkStatus;
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
        //System.out.println("countRowHub in DWH: " + countRowHub);
        return countRowHub;
    }

    public Integer getDWHHubId(String hubSQL, String fieldNameForHubId) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        Integer dwhHubId = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                dwhHubId = rsFromDWH.getInt(fieldNameForHubId);
            } else {
                System.err.println("dwhHubId. Record not found or more one!");
                return dwhHubId;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("dwhHubId in DWH: " + dwhHubId);
        return dwhHubId;
    }

    public Integer getSatHubStatus(String table, String fieldNameForHubId, Integer dwhHubId) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        String select = "SELECT * from " + table + " WHERE " + fieldNameForHubId + " = " + dwhHubId;
        //System.out.println("SQL for Status из DWH: " + select);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, select);
        Integer status = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                status = rsFromDWH.getInt("status");
            } else {
                System.err.println("status. Record not found or more one!");
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
        zSQLforTestData SQL = new zSQLforTestData();
        String insert = SQL.getInsertIntoSA(tableName);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(insert);
        // System.out.println("SQL for Insert in SA: " + insert);
        System.out.println("Insert test row complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

    public void insertTestRowInDWH(String tableName) throws SQLException {
        zSQLforTestData SQL = new zSQLforTestData();
        String insert = SQL.getInsertHub(tableName);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(insert);
        //System.out.println("SQL for Insert in DWH: " + insert);
        System.out.println("Insert test row complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }

    public void deleteTestRowFromSA(String delete) throws SQLException {
        System.out.println("SQL for Delete from SA: " + delete);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(delete);
        System.out.println("Delete test row in SA complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

    public void deleteHub(String delete) throws SQLException {
        System.out.println("SQL for Delete from DWH: " + delete);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(delete);
        System.out.println("Delete Hub in DWH complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }

    public void deleteSat(String tableName, String fieldNameForHubId, Integer dwhHubId) throws SQLException {
        zSQLforTestData SQL = new zSQLforTestData();
        String delete = SQL.getDeleteSat(tableName, fieldNameForHubId, dwhHubId);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(delete);
        //System.out.println("SQL for Delete from DWH: " + delete);
        System.out.println("Delete Sat in DWH complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }

    public void deleteSatHubStatus(String tableName, String fieldNameForHubId, Integer dwhHubId) throws SQLException {
        zSQLforTestData SQL = new zSQLforTestData();
        String delete = SQL.getDeleteSat(tableName, fieldNameForHubId, dwhHubId);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(delete);
        //System.out.println("SQL for Delete from DWH: " + delete);
        System.out.println("Delete HubSatStatus in DWH complete!");
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
