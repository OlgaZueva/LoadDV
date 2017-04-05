package TietoRus.helpers;

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
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Integer tryCtn = null;
        if (rsFromSA.getRow() < 1) {
            System.err.println("Record in SA not found!");
        } else {
            if (rsFromSA.getRow() > 1) {
                System.err.println("More then one record in SA. Check query!");
            } else {
                while (rsFromSA.next()) {
                    tryCtn = Integer.valueOf(rsFromSA.getString("tryCtn"));
                }
            }
        }
        closeConnecions(rsFromSA, stForSA, connectionToSA);
        return tryCtn;
    }

    public Integer getHubStatusFromSA(String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Integer hubStatus = null;
        if (rsFromSA.getRow() < 1) {
            System.err.println("Record in SA not found!");
        } else {
            if (rsFromSA.getRow() > 1) {
                System.err.println("More then one record in SA. Check query!");
            } else {
                while (rsFromSA.next()) {
                    hubStatus = Integer.valueOf(rsFromSA.getString("statusHub"));
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
        int countRowHub = rsFromDWH.getRow();
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return countRowHub;
    }

    public Integer getdwhIdHub(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        Integer dwhIdHubFileLiner = null;
        if (rsFromDWH.getRow() == 1) {
            dwhIdHubFileLiner = rsFromDWH.getInt("dwhIdHubFileLiner");
        } else {
            System.err.println("Record nor found or more one!");
            return dwhIdHubFileLiner;
        }
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return dwhIdHubFileLiner;
    }

    public FileLiner getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        FileLiner fileLiner;
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
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return fileLiner;
    }

    public FileLiner getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        FileLiner fileLiner;
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
        closeConnecions(rsFromSA, stForSA, connectionToSA);
        return fileLiner;
    }

    public Integer getSatHubStatus(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        Integer status = null;
        if (rsFromDWH.getRow() == 1) {
            System.out.println("Запрашиваем status запросом: " + sql);
            status = rsFromDWH.getInt("status");
        } else {
            System.err.println("Record nor found or more one!");
            return status;
        }
        closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return status;
    }

    private void closeConnecions(ResultSet rs, Statement st, Connection connection) throws SQLException {
        rs.close();
        st.close();
        connection.close();
    }
}
