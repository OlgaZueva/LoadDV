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
        closeConnecions(rsFromSA, stForSA, connectionToSA);
        return mapAllFromSA;
    }

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
        closeConnecions(rsFromSA, stForSA, connectionToSA);
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
        closeConnecions(rsFromSA, stForSA, connectionToSA);
        return hubStatus;
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
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return mapHubFromDWH;
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
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
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
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("dwhIdHubFileLiner in DWH: " + dwhIdHubFileLiner);
        return dwhIdHubFileLiner;
    }

    public FileLiner getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        FileLiner fileLiner = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String serviceCode = rsFromDWH.getString("serviceCode");
                int fileLinerNr = rsFromDWH.getInt("fileLinerNr");
                int accessCompanyId = rsFromDWH.getInt("accessCompanyId");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                fileLiner = new FileLiner(serviceCode, fileLinerNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("FileLiner from DWH: " + fileLiner);
        return fileLiner;
    }

    public FileLiner getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        FileLiner fileLiner = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String serviceCode = rsFromSA.getString("AFDELING");
                int fileLinerNr = rsFromSA.getInt("SAGSNR");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                fileLiner = new FileLiner(serviceCode, fileLinerNr, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("FileLiner from SA: " + fileLiner);
        return fileLiner;
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
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("status in DWH: " + status);
        return status;
    }

    private void closeConnecions(ResultSet rs, Statement st, Connection connection) throws SQLException {
        if (rs==null){
            st.close();
            connection.close();
        } else {
            rs.close();
            st.close();
            connection.close();
        }
    }

    public void insertTestRowInSA(String tableName) throws SQLException {
        String insert = SQL.getInsertIntoSA(tableName);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(insert);
        System.out.println("SQL for Insert in SA: " + insert);
        System.out.println("Insert test row complete!");
        closeConnecions(null, stForSA, connectionToSA);
    }

    public int getCountRowInSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        int countRowSA = 0;
        while (rsFromSA.next()) {
            countRowSA = rsFromSA.getRow();
        }
        closeConnecions(rsFromSA, stForSA, connectionToSA);
        return countRowSA;
    }

    public void deleteTestRowFromSA(String tableName) throws SQLException {
        String delete = SQL.getDeleteFromSA(tableName);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(delete);
        System.out.println("SQL for Delete from SA: " + delete);
        System.out.println("Delete test row in SA complete!");
        closeConnecions(null, stForSA, connectionToSA);
    }

    public void deleteTestRowFromDWH(String tableName) throws SQLException {
        String delete = SQL.getDeleteFromDWH(tableName);
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(delete);
        System.out.println("SQL for Delete from DWH: " + delete);
        System.out.println("Delete test row in DWH complete!");
        closeConnecions(null, stForDWH, connectionToDWH);
    }
}
