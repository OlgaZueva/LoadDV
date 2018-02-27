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
        import java.util.*;

/*
Тест сравнивает данные, сформированные контрольным запросом с загруженными в DM данными.
Кол-во строк для проверки задается в properties-файле параметром system.PercentOfRows. Задается в процентах от общего кол-ва записей, пригодных для загрузки из DWH
Допускается нецелое число, разделитель- точка.
Если записей в таблице мало, то для сравнения берется кол-во записей, заданное парамером  system.default.RowsForMatch -1
Если тест найдет отличие в каком либо из полей, или обнаружит дубли в DM-таблице, то на консоль будет выведено сообщение об ошибке.
Тест не упадет при этом, т.е. нужно контролировать глазами!
 */
public class FctDataMatching {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapFromDV = new HashMap<String, Object>();
    private Map<String, Object> mapFromDM = new HashMap<String, Object>();


    @Test(enabled = true)
    public void fctBookingCargo_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("fctBookingCargo.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubBookingCargo) AS RowNumber, * " +
                    properties.getProperty("fctBookingCargo.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("fctBookingCargo.dataInDM.RowByKeys") + " where dwhIdHubBookingCargo = " +
                        rsFromDWH.getInt("dwhIdHubBookingCargo") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void factBookingChargeLines_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factBookingChargeLines.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubBookingChargeLines) AS RowNumber, * " +
                    properties.getProperty("fctBookingCargo.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("factBookingChargeLines.dataInDM.RowByKeys") + " where dwhIdHubBookingChargeLines = " +
                        rsFromDWH.getInt("dwhIdHubBookingChargeLines") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void factBookingCharges_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factBookingCharges.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubBookingCharges) AS RowNumber, * " +
                    properties.getProperty("factBookingCharges.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("factBookingCharges.dataInDM.RowByKeys") + " where dwhIdHubBookingCharges = " +
                        rsFromDWH.getInt("dwhIdHubBookingCharges") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void factBookingEvents_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factBookingEvents.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdhubBookingEvents) AS RowNumber, * " +
                    properties.getProperty("factBookingEvents.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("factBookingEvents.dataInDM.RowByKeys") + " where dwhIdhubBookingEvents = " +
                        rsFromDWH.getInt("dwhIdhubBookingEvents") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void factContainerMoves_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factContainerMoves.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubContainerMoves) AS RowNumber, * " +
                    properties.getProperty("factContainerMoves.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("factContainerMoves.dataInDM.RowByKeys") + " where dwhIdHubContainerMoves = " +
                        rsFromDWH.getInt("dwhIdHubContainerMoves") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void fctDemurrageStorageMscRus_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("fctDemurrageStorageMscRus.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdFctDemurrageStorage) AS RowNumber, * " +
                    properties.getProperty("fctDemurrageStorageMscRus.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("fctDemurrageStorageMscRus.dataInDM.RowByKeys") + " where dwhIdFctDemurrageStorage = " +
                        rsFromDWH.getInt("dwhIdFctDemurrageStorage"));
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void fctDemurrageStorageUnity_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("fctDemurrageStorageUnity.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdFctDemurrageStorage) AS RowNumber, * " +
                    properties.getProperty("fctDemurrageStorageUnity.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("fctDemurrageStorageUnity.dataInDM.RowByKeys") + " where dwhIdFctDemurrageStorage = " +
                        rsFromDWH.getInt("dwhIdFctDemurrageStorage"));
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void factFileRoe_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factFileRoe.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdhubFileROE) AS RowNumber, * " +
                    properties.getProperty("factFileRoe.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("factFileRoe.dataInDM.RowByKeys") + " where dwhIdhubFileROE = " +
                        rsFromDWH.getInt("dwhIdhubFileROE") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void factInvoiceLines_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("factInvoiceLines.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdhubInvoiceLines) AS RowNumber, * " +
                    properties.getProperty("factInvoiceLines.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("factInvoiceLines.dataInDM.RowByKeys") + " where dwhIdhubInvoiceLines = " +
                        rsFromDWH.getInt("dwhIdhubInvoiceLines") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/fctCountsSQL.properties"))));
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
        ArrayList arrayRows = new ArrayList();
        int defaultRowsForMatch = Integer.parseInt(properties.getProperty("system.default.RowsForMatch"));
        Double percent = Double.valueOf((properties.getProperty("system.PercentOfRows")));
        int countRowsForMatch = (int) Math.round(((countRowsInTable * percent) / 100));
        if (countRowsForMatch != 0) {
            int increment = Math.round(countRowsInTable / countRowsForMatch);
            for (int i = 1; i < (countRowsInTable - increment); i = i + increment) {
                arrayRows.add(i);
            }
            System.out.println("Кол-во записей пула, которые будут сравниваться: " + arrayRows.size());
            return arrayRows;
        } else
            for (int i = 1; i < (defaultRowsForMatch); i++) {
                arrayRows.add(i);
            }

        System.out.println("В DV мало записей, будет сравниваться " + arrayRows.size());
        return arrayRows;
    }


    public Map<String, Object> getMapFromDV(ResultSet rsFromSource) throws SQLException {
        for (int k = 1; k <= rsFromSource.getMetaData().getColumnCount(); k++) {
            mapFromDV.put(rsFromSource.getMetaData().getColumnName(k), rsFromSource.getObject(k));
            mapFromDV.remove("RowNumber");
        }
        return mapFromDV;
    }

    public Map<String, Object> getMapFromDM(int mapForRTestSize, String sql) throws SQLException {
        Connection connectionToDM = db.connToDM();
        Statement stForDM = db.stFromConnection(connectionToDM);
        ResultSet rsFromDM = db.rsFromDB(stForDM, sql);
        Map<String, Object> mapForDM = new HashMap<String, Object>();

        while (rsFromDM.next()) {
            if (rsFromDM.getRow() > 1) {
                System.err.println("Count rows got from DM: " + rsFromDM.getRow()
                        + ". If its > 1 in table exist double rows - it's wrong! Or check the unique key in sql query to DM! SQL: " + sql);
                System.err.println("Or check the unique key in sql query to DM! SQL: " + sql);
            } else {
                for (int l = 1; l <= mapForRTestSize; l++) {
                    mapForDM.put(rsFromDM.getMetaData().getColumnName(l), rsFromDM.getObject(l));
                }
            }
        }
        db.closeConnecions(rsFromDM, stForDM, connectionToDM);
        return mapForDM;
    }


    public void matchMaps(Map<String, Object> mapDV, Map<String, Object> mapDM) {
        // System.out.println("Map from DV = " + mapDV);
        // System.out.println("Map from DM = " + mapDM);

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



