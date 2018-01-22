package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.helpers.GetDataHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Тест для проверки механизма загрузки Demurrage

 */

public class DemurrageTest {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private GetDataHelper getDataHelper = new GetDataHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    ArrayList finalSQL = new ArrayList();

    @Test(enabled = true)
    public void MSCRUS_FillingDemurrageTestTable() throws SQLException, IOException {
        getPropertiesFile();
        String create = (properties.getProperty("demurrage.linkTable.create"));
        getDataHelper.executeInDWH(create);
        //executeInDWH(create);
        String truncate = (properties.getProperty("demurrage.linkTable.truncate"));
        //executeInDWH(truncate);
        getDataHelper.executeInDWH(truncate);

        String sql = (properties.getProperty("demurrage.allData.select"));
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource.put("accessCompanyId", rsFromDWH.getInt("COMPANY"));
            mapForSource.put("bookingNumber", rsFromDWH.getInt("BOOK_NO"));
            mapForSource.put("containerNr", rsFromDWH.getString("CONT_NO"));
            mapForSource.put("demurrageStorageCode", rsFromDWH.getString("CODE"));
            mapForSource.put("demurrageId", rsFromDWH.getInt("DEM_ID"));
            mapForSource.put("demurrageStorageStatus", rsFromDWH.getString("STATUS"));
            mapForSource.put("amount", rsFromDWH.getDouble("AMOUNT"));
            mapForSource.put("invoicedAmount", rsFromDWH.getDouble("INVOICED_AMOUNT"));
            mapForSource.put("theoreticalAmount", rsFromDWH.getDouble("THEORETICAL_AMOUNT"));
            mapForSource.put("reissue", rsFromDWH.getString("REISSUE"));
            mapForSource.put("clientRoe", rsFromDWH.getDouble("CLIENT_ROE"));
            mapForSource.put("roe", rsFromDWH.getDouble("ROE"));
            mapForSource.put("stdCurrencyRoe", rsFromDWH.getDouble("STD_ROE"));
            mapForSource.put("daysNumber", rsFromDWH.getInt("DAYS"));
            mapForSource.put("startDate", rsFromDWH.getDate("START_DATE"));
            mapForSource.put("endDate", rsFromDWH.getDate("END_DATE"));
            mapForSource.put("startMark", rsFromDWH.getString("START_MARK"));
            mapForSource.put("endMark", rsFromDWH.getString("END_MARK"));
            mapForSource.put("startDays", rsFromDWH.getInt("START_DAYS"));
            mapForSource.put("freeDays", rsFromDWH.getInt("FREE_DAYS"));
            mapForSource.put("stdDays", rsFromDWH.getInt("STD_DAYS"));
            mapForSource.put("ruleId", rsFromDWH.getInt("RULE_ID"));
            mapForSource.put("stdRuleId", rsFromDWH.getInt("STD_RULE_ID"));
            mapForSource.put("orderNr", rsFromDWH.getInt("ORDRE_NO"));
            mapForSource.put("clientCurrency", rsFromDWH.getString("CLIENT_CURR"));
            mapForSource.put("demurrCurrency", rsFromDWH.getString("CURRENCY"));
            mapForSource.put("stdCurrency", rsFromDWH.getString("STD_CURRENCY"));
            mapForSource.put("startMoveCode", rsFromDWH.getString("START_MOVE"));
            mapForSource.put("endMoveCode", rsFromDWH.getString("END_MOVE"));
            mapForSource.put("client", rsFromDWH.getInt("CLIENT"));
            String sqlDwhIdHubCompany = (properties.getProperty("dwhIdHubCompany.id.select") +  " " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));
            Connection connectionToDWHForValues = db.connToDWH();
            Statement stForDWHForValues = db.stFromConnection(connectionToDWHForValues);
            ResultSet rsFromDWHForValues = db.rsFromDB(stForDWHForValues, sqlDwhIdHubCompany);
            while (rsFromDWHForValues.next()) {
                mapForSource.put("dwhIdHubCompany", rsFromDWHForValues.getInt("dwhIdHubCompany"));
                db.closeConnecions(rsFromDWHForValues, stForDWHForValues, connectionToDWHForValues);
                mapForSource.put("srcSystemId", rsFromDWH.getInt("srcSystemId"));
                mapForSource.put("validFrom", rsFromDWH.getDate("CdcTimestamp"));


            /*

            if (rsFromDWH.getString("PAYM_TERMS") == (null)) {
                dwhIdHubPPCCEE = -1;
            } else {
                String sqlDwhIdHubPPCCEE = ("select " + nameDwhIdHubPPCCEE + " " + properties.getProperty("iBox.hubPPCCEE.select")
                        + rsFromDWH.getString("PAYM_TERMS") + "\'");
                dwhIdHubPPCCEE = getDataFromHub(sqlDwhIdHubPPCCEE, nameDwhIdHubPPCCEE);
            }
            mapForSource.put(nameDwhIdHubPPCCEE, dwhIdHubPPCCEE);

            if (rsFromDWH.getString("FULL_EMPTY") == (null)) {
                dwhIdHubFullEmpty = -1;
            } else {
                String sqlDwhIdHubFullEmpty = ("select " + nameDwhIdHubFullEmpty + " " + properties.getProperty("iBox.hubFullEmpty.select")
                        + rsFromDWH.getString("FULL_EMPTY") + "\'");
                dwhIdHubFullEmpty = getDataFromHub(sqlDwhIdHubFullEmpty, nameDwhIdHubFullEmpty);
            }
            mapForSource.put(nameDwhIdHubFullEmpty, dwhIdHubFullEmpty);

//---------------------------
            if (rsFromDWH.getString("PORT_LOAD_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPOL = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("PORT_LOAD_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPOL, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPOL, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("PORT_DISCH_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPOD = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("PORT_DISCH_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPOD, nameDwhIdHubLocations);
            }


//---------------------------
            if (rsFromDWH.getString("BOOK_PARTY") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersBOOK_PARTY = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("BOOK_PARTY").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersBOOK_PARTY, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersBOOK_PARTY, dwhIdHubCustomers);

            if (rsFromDWH.getString("LQ_FORWARDER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersFORWARDER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("LQ_FORWARDER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersFORWARDER, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersFORWARDER, dwhIdHubCustomers);

//--
            if (rsFromDWH.getString("FREIGHTPAYER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersFREIGHTPAYER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("FREIGHTPAYER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersFREIGHTPAYER, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersFREIGHTPAYER, dwhIdHubCustomers);

//--
            if (rsFromDWH.getString("STAT_CUSTOMER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersSTAT_CUSTOMER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("STAT_CUSTOMER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersSTAT_CUSTOMER, nameForMapDwhIdHubCustomers);
            }

            if (rsFromDWH.getString("C_OV_STATUS") == (null)) {
                dwhIdHubOceanVesselStatus = -1;
            } else {
                String sqlDwhIdHubOceanVesselStatus = ("select " + nameDwhIdHubOceanVesselStatus + " " + properties.getProperty("iBox.hubOceanVesselStatus.select")
                        + rsFromDWH.getString("C_OV_STATUS") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubOceanVesselStatus = getDataFromHub(sqlDwhIdHubOceanVesselStatus, nameDwhIdHubOceanVesselStatus);
            }
            mapForSource.put(nameDwhIdHubOCEAN_VESSEL_STATUS, dwhIdHubOceanVesselStatus);

            //--
            if (rsFromDWH.getString("C_BL_NR_PREFIX") == (null)) {
                dwhIdHubCrossBookingType = getDataFromHub(properties.getProperty("iBox.hubCrossBookingType_NA.select"), nameDwhIdHubCrossBookingType);
            } else {
                String sqlDwhIdHubCrossBookingType = ("select " + nameDwhIdHubCrossBookingType + " " + properties.getProperty("iBox.hubCrossBookingType.select")
                        + rsFromDWH.getString("C_BL_NR_PREFIX") + "\'");
                dwhIdHubCrossBookingType = getDataFromHub(sqlDwhIdHubCrossBookingType, nameDwhIdHubCrossBookingType);
                if (dwhIdHubCrossBookingType == null) {
                    dwhIdHubCrossBookingType = getDataFromHub(properties.getProperty("iBox.hubCrossBookingType_NA.select"), nameDwhIdHubCrossBookingType);
                }
            }
            mapForSource.put(nameDwhIdHubCROSS_BOOKING_TYPE, dwhIdHubCrossBookingType);
*/
                String qwe = (properties.getProperty("demurrage.fct.insert") + mapForSource.get("accessCompanyId") + "," + mapForSource.get("bookingNumber") + ",'" +
                        mapForSource.get("containerNr") + "','" + mapForSource.get("demurrageStorageCode") + "',"
                        + mapForSource.get("demurrageId") + ",'"
                        + mapForSource.get("demurrageStorageStatus") + "'," + mapForSource.get("amount") + ","
                        + mapForSource.get("invoicedAmount") + "," + mapForSource.get("theoreticalAmount") + ",'"
                        + mapForSource.get("reissue") + "'," + mapForSource.get("clientRoe") + ","
                        + mapForSource.get("roe") + "," + mapForSource.get("stdCurrencyRoe") + ","
                        + mapForSource.get("daysNumber") + ",'"
                        + mapForSource.get("startDate") + "','" + mapForSource.get("endDate") + "','"
                        + mapForSource.get("startMark") + "','" + mapForSource.get("endMark") + "',"
                        + mapForSource.get("startDays") + "," + mapForSource.get("freeDays") + ","
                        + mapForSource.get("stdDays") + "," + mapForSource.get("ruleId") + ","
                        + mapForSource.get("stdRuleId") + "," + mapForSource.get("orderNr") + ",'"
                        + mapForSource.get("clientCurrency") + "','" + mapForSource.get("demurrCurrency") + "','"
                        + mapForSource.get("stdCurrency") + "','" + mapForSource.get("startMoveCode") + "','"
                        + mapForSource.get("endMoveCode") + "'," + mapForSource.get("client") + ","
                        + mapForSource.get("dwhIdHubCompany") + ","
                        + "null" + "," + "null" + "," + "null" + "," + "null" + "," + "null" + "," + "null" + "," + "null" + "," + "null" + "," + "null" + "," + "null" + ","
                        + mapForSource.get("srcSystemId") + "," + mapForSource.get("validFrom") + ")");

                //finalSQL.add(qwe);
                System.out.println(qwe);
                getDataHelper.executeInDWH(qwe);


            }

            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            //String str = String.join(" ", finalSQL);
            //getDataHelper.executeInDWH(str);
        }

    }
        private void getPropertiesFile () throws IOException {
            properties.load(new FileReader(new File(String.format("src/test/resources/demurrage.properties"))));
        }

        public void assertRowCount ( int countInSource, int countInDest){
            System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
            assertThat(countInDest, equalTo(countInSource));
        }

        public Integer getDataFromHub (String sql, String nameDwhIdHub) throws SQLException {
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
            Integer template = null;
            while (rsFromDWH.next()) {
                template = rsFromDWH.getInt(nameDwhIdHub);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            return template;
        }


    }
