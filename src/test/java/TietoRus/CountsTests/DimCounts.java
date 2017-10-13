package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DimCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();



    @Test(enabled = true)
    public void dimCustomers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInDV = getCountRowInDV(properties.getProperty("customers.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("customers.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }


    @Test(enabled = true)
    public void dimCustomers_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowsInSource = Integer.parseInt(properties.getProperty("system.RownumPool"));
        ArrayList arrayRows = getArray(countRowsInSource);



        int countRowInDV = getCountRowInDV(properties.getProperty("customers.dwh.CountRows"));
        int countRowInDim = getCountRowInDM(properties.getProperty("customers.dim.CountRows"));
        assertRowCount(countRowInDV, countRowInDim);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/dimsCountsSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowInDM(String saSQL) throws SQLException {
        Connection connectionToDM = db.connToDM();
        Statement stForDM = db.stFromConnection(connectionToDM);
        ResultSet rsFromDM = db.rsFromDB(stForDM, saSQL);
        int countRowSA = 0;
        while (rsFromDM.next()) {
            countRowSA = Integer.parseInt(rsFromDM.getString("c"));
        }
        db.closeConnecions(rsFromDM, stForDM, connectionToDM);
        return countRowSA;
    }

    public int getCountRowInDV(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        int countRowHub = 0;
        while (rsFromDWH.next()) {
            countRowHub = Integer.parseInt(rsFromDWH.getString("c"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return countRowHub;
    }

    public ArrayList getArray(int countRowsInTable) throws IOException {
        getPropertiesFile();
        int percent = Integer.parseInt(properties.getProperty("system.PercentOfRows"));
        int countRowsForMatch = (countRowsInTable * percent) / 100;
        int increment = countRowsInTable / countRowsForMatch;
        ArrayList arrayRows = new ArrayList();
        for (int i = 1; i < (countRowsInTable - increment); i = i + increment) {
            arrayRows.add(i);
        }
        System.out.println("Кол-во записей пула, которые будут сравниваться: " + arrayRows.size());
        return arrayRows;
    }

    public void matchMaps(Map<String, Object> mapDV, Map<String, Object> mapDM) {
        System.out.println("Map from DV = " + mapDV);
        System.out.println("Map from DM = " + mapDM);

        if (mapDM.size() == 0) {
            System.err.println("Record in DataMart not found!");
        } else {
            for (Map.Entry entry : mapDV.entrySet()) {
                Object q1 = entry.getKey();
                Object q2 = entry.getValue();
                if (q2 == null) {
                    if (mapDM.get(q1) != null) {
                        System.err.println("Column [" + q1 + "] expected null but was [" + mapDM.get(q1).toString() + "]");
                    } else if (!mapDM.keySet().contains(q1)) {
                        System.err.println("Column [" + q1 + "] not exist");
                    }
                } else {
                    if (!q2.equals(mapDM.get(q1))) {
                        Object secondValue = mapDM.get(q1);
                        if (!q2.toString().equals(secondValue != null ? secondValue.toString() : null)) {
                            System.err.println("Column [" + q1.toString() + "] does not match. Expected [" + q2 + "], actual - [" + mapDM.get(q1) + "]");
                        }
                    }
                }
            }
        }
    }
}



