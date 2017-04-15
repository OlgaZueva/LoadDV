package TietoRus.FileLinerTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Mapping {
    private DBHelper db = new DBHelper();

    @Test
    public void MapsForMatch (){

    }

    public Map<String, Object> getMapFromSA(int mapForRTestSize, String sql) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, sql);
        Map<String, Object> mapForSA = new HashMap<String, Object>();

        while (rsFromSA.next()) {
            if (rsFromSA.getRow() > 1) {
                System.err.println("Count rows got from SA: " + rsFromSA.getRow()
                        + ". If its > 1 check the unique key in sql query to SA! SQL: " + sql);
            } else {
                for (int l = 1; l <= mapForRTestSize; l++) {
                    mapForSA.put(rsFromSA.getMetaData().getColumnName(l), rsFromSA.getObject(l));
                }
            }
        }
        rsFromSA.close();
        stForSA.close();
        connectionToSA.close();
        return mapForSA;
    }





    public String getSQLFromSA(String tableName) {
        String[] keys = getStageFields(tableName);
        String insert = "Select " + keys[1] + ", " + keys[2] + ", " + keys[3] + ", " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ")";
        //System.out.println(insert);
        return insert;
    }

    public String getSQLToSat(String tableName) {
        String[] keys = getStageFields(tableName);
        String insert = "Insert into " + keys[0] + " (SELSKAB, SAGSNR, AFDELING, SrcSystemId, TryCnt,  PartitionId, statusHub, cdcOperation) Values ("
                + keys[1] + ", " + keys[2] + ", '" + keys[3] + "', " + keys[4] + ", " + keys[5] + ", " + keys[6] + ", " + keys[7] + ", " + keys[8] + ")";
        //System.out.println(insert);
        return insert;
    }

    private String[] getSatFields(String tableName) {
        String[] keys = new String[12];
        keys[0] = tableName; //table name
        keys[1] = "callId";
        keys[2] = "expPolDate";
        keys[3] = "impPodDate";
        keys[4] = "proformaDate";
        keys[5] = "voyageNr";
        keys[6] = "sailingWeek";
        keys[7] = "vesselName";
        keys[8] = "closeDate";
        keys[9] = "openDate";
        keys[10] = "status";
        keys[11] = "temporaryFlag";
        return keys;
    }


    private String[] getStageFields(String tableName) {
        String[] keys = new String[12];
        keys[0] = tableName; //table name
        keys[1] = "CALL_ID";
        keys[2] = "EXP_AFG_DATO";
        keys[3] = "IMP_ANK_DATO";
        keys[4] = "PROFORMA_DATE";
        keys[5] = "REJSE_NR";
        keys[6] = "S_WEEK";
        keys[7] = "SKIBS_NAVN";
        keys[8] = "SLUT_DATO";
        keys[9] = "START_DATO";
        keys[10] = "STAT";
        keys[11] = "TEMPORARY_FLAG";
        return keys;
    }
}
