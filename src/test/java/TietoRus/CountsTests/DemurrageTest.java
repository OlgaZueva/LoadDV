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
import java.util.*;

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

        String sql = (properties.getProperty("demurrage.allData.MSCRUS.select"));
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

            String sqlDwhIdHubCompany = (properties.getProperty("dwhIdHubCompany.id.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

// В настоящий момент по условиям могут быть выбраны несколько dwhIdHubBookingCargo, поэтому тут выбирается просто первый. Должно быть уточнено и изменено. Могут юыть неточности
            String sqlDwhIdHubBookingCargo = (properties.getProperty("dwhIdHubBookingCargo.id.select.part1") + rsFromDWH.getString("CONT_NO") +
                    (properties.getProperty("dwhIdHubBookingCargo.id.select.part2") + rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                            rsFromDWH.getInt("srcSystemId")));

            String sqlDwhIdHubContainerDemurrageRulesSTD = (properties.getProperty("dwhIdHubContainerDemurrageRules.id.select") +
                    rsFromDWH.getString("STD_RULE_ID") + " AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubContainerDemmurageRulesFACT = (properties.getProperty("dwhIdHubContainerDemurrageRules.id.select") +
                    rsFromDWH.getString("RULE_ID") + " AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

// В настоящий момент по условиям могут быть выбраны несколько dwhIdHubContainerMoveTypes, поэтому тут выбирается просто первый. Должно быть уточнено и изменено. Могут юыть неточности
            String sqlDwhIdHubContainerMoveTypesStartMove = (properties.getProperty("dwhIdHubContainerMoveTypes.id.select") +
                    rsFromDWH.getString("START_MOVE") + "' AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

// В настоящий момент по условиям могут быть выбраны несколько dwhIdHubContainerMoveTypes, поэтому тут выбирается просто первый. Должно быть уточнено и изменено. Могут юыть неточности
            String sqlDwhIdHubContainerMoveTypesEndMove = (properties.getProperty("dwhIdHubContainerMoveTypes.id.select") +
                    rsFromDWH.getString("START_MOVE") + "' AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubCurrencyDemurrCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    rsFromDWH.getString("CURRENCY") + "' AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubCurrencyClientCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    rsFromDWH.getString("CLIENT_CURR") + "' AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubCurrencyStdCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    rsFromDWH.getString("STD_CURRENCY") + "' AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubCustomers = (properties.getProperty("dwhIdHubCustomers.id.select") +
                    rsFromDWH.getString("CLIENT") + " AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdhubInvoice = (properties.getProperty("dwhIdhubInvoice.id.select") +
                    rsFromDWH.getString("ORDRE_NO") + " AND " + properties.getProperty("companyId.value.select") +
                    rsFromDWH.getInt("COMPANY") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

            mapForSource.put("dwhIdHubBookingCargo", getValueHubId(sqlDwhIdHubBookingCargo, "dwhIdHubBookingCargo"));
            mapForSource.put("dwhIdHubCompany", getValueHubId(sqlDwhIdHubCompany, "dwhIdHubCompany"));
            mapForSource.put("dwhIdHubContainerDemurrageRulesSTD", getValueHubId(sqlDwhIdHubContainerDemurrageRulesSTD, "dwhIdHubContainerDemurrageRules"));
            mapForSource.put("dwhIdHubContainerDemmurageRulesFACT", getValueHubId(sqlDwhIdHubContainerDemmurageRulesFACT, "dwhIdHubContainerDemurrageRules"));
            mapForSource.put("dwhIdHubContainerMoveTypesStartMove", getValueHubId(sqlDwhIdHubContainerMoveTypesStartMove, "dwhIdHubContainerMoveTypes"));
            mapForSource.put("dwhIdHubContainerMoveTypesEndMove", getValueHubId(sqlDwhIdHubContainerMoveTypesEndMove, "dwhIdHubContainerMoveTypes"));

            Integer dwhIdHubCurrency_DemurCur = getValueHubId(sqlDwhIdHubCurrencyDemurrCurrency, "dwhIdHubCurrency");
            if (dwhIdHubCurrency_DemurCur == null) {
                mapForSource.put("dwhIdHubCurrencyDemurrCurrency", -1);
            } else {
                mapForSource.put("dwhIdHubCurrencyDemurrCurrency", dwhIdHubCurrency_DemurCur);
            }

            Integer dwhIdHubCurrency_ClientCur = getValueHubId(sqlDwhIdHubCurrencyClientCurrency, "dwhIdHubCurrency");
            if (dwhIdHubCurrency_ClientCur == null) {
                mapForSource.put("dwhIdHubCurrencyClientCurrency", -1);
            } else {
                mapForSource.put("dwhIdHubCurrencyClientCurrency", dwhIdHubCurrency_ClientCur);
            }

            Integer dwhIdHubCurrency_StdCur = getValueHubId(sqlDwhIdHubCurrencyStdCurrency, "dwhIdHubCurrency");
            if (dwhIdHubCurrency_StdCur == null) {
                mapForSource.put("dwhIdHubCurrencyStdCurrency", -1);
            } else {
                mapForSource.put("dwhIdHubCurrencyStdCurrency", dwhIdHubCurrency_StdCur);
            }

            Integer dwhIdHubCustomers = getValueHubId(sqlDwhIdHubCustomers, "dwhIdHubCustomers");
            if (dwhIdHubCustomers == null || dwhIdHubCustomers == 9999) {
                mapForSource.put("dwhIdHubCustomers", -1);
            } else {
                mapForSource.put("dwhIdHubCustomers", dwhIdHubCustomers);
            }

            if (rsFromDWH.getInt("ORDRE_NO") == 0) {
                mapForSource.put("dwhIdhubInvoice", null);
            } else {
                mapForSource.put("dwhIdhubInvoice", getValueHubId(sqlDwhIdhubInvoice, "dwhIdhubInvoice"));
            }

            mapForSource.put("srcSystemId", rsFromDWH.getInt("srcSystemId"));

            if (rsFromDWH.getDate("CdcTimestamp") == null) {
                mapForSource.put("validFrom", "01-01-2000");
            } else {
                mapForSource.put("validFrom", rsFromDWH.getDate("CdcTimestamp"));
            }

            //mapForSource.put("validFrom", rsFromDWH.getDate("CdcTimestamp"));


            String qwe = (properties.getProperty("demurrage.fct.insert") + mapForSource.get("accessCompanyId") + "," + mapForSource.get("bookingNumber") + ",'" +
                    mapForSource.get("containerNr") + "','" + mapForSource.get("demurrageStorageCode") + "',"
                    + mapForSource.get("demurrageId") + ",'"
                    + mapForSource.get("demurrageStorageStatus") + "'," + mapForSource.get("amount") + ","
                    + mapForSource.get("invoicedAmount") + "," + mapForSource.get("theoreticalAmount") + ",'"
                    + mapForSource.get("reissue") + "'," + mapForSource.get("clientRoe") + ","
                    + mapForSource.get("roe") + "," + mapForSource.get("stdCurrencyRoe") + ","
                    + mapForSource.get("daysNumber") + ", CONVERT(datetime, '"
                    + mapForSource.get("startDate") + "', 102) , CONVERT(datetime, '" + mapForSource.get("endDate") + "', 102),'"
                    + mapForSource.get("startMark") + "','" + mapForSource.get("endMark") + "',"
                    + mapForSource.get("startDays") + "," + mapForSource.get("freeDays") + ","
                    + mapForSource.get("stdDays") + "," + mapForSource.get("ruleId") + ","
                    + mapForSource.get("stdRuleId") + "," + mapForSource.get("orderNr") + ",'"
                    + mapForSource.get("clientCurrency") + "','" + mapForSource.get("demurrCurrency") + "','"
                    + mapForSource.get("stdCurrency") + "','" + mapForSource.get("startMoveCode") + "','"
                    + mapForSource.get("endMoveCode") + "'," + mapForSource.get("client") + ","
                    + mapForSource.get("dwhIdHubBookingCargo") + "," + mapForSource.get("dwhIdHubCompany") + ","
                    + mapForSource.get("dwhIdHubContainerDemurrageRulesSTD") + "," + mapForSource.get("dwhIdHubContainerDemmurageRulesFACT") + ","
                    + mapForSource.get("dwhIdHubContainerMoveTypesStartMove") + "," + mapForSource.get("dwhIdHubContainerMoveTypesEndMove") + ","
                    + mapForSource.get("dwhIdHubCurrencyDemurrCurrency") + "," + mapForSource.get("dwhIdHubCurrencyClientCurrency") + ","
                    + mapForSource.get("dwhIdHubCurrencyStdCurrency") + "," + mapForSource.get("dwhIdHubCustomers") + ","
                    + mapForSource.get("dwhIdhubInvoice") + ","
                    + mapForSource.get("srcSystemId") + ",CONVERT(datetime, '" + mapForSource.get("validFrom") + "', 102))");

            //finalSQL.add(qwe);
            System.out.println(qwe);
            //getDataHelper.executeInDWH(qwe);


        }

        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        //String str = String.join(" ", finalSQL);
        //getDataHelper.executeInDWH(str);

        String updateReissue = (properties.getProperty("demurrage.reissue.update"));
        getDataHelper.executeInDWH(updateReissue);
        String updateStartMark = (properties.getProperty("demurrage.startMark.update"));
        getDataHelper.executeInDWH(updateStartMark);
        String updateEndMark = (properties.getProperty("demurrage.endMark.update"));
        getDataHelper.executeInDWH(updateEndMark);
        String updateStdCurrency = (properties.getProperty("demurrage.stdCurrency.update"));
        getDataHelper.executeInDWH(updateStdCurrency);


    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/demurrage.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }


    public Integer getValueHubId(String sql, String nameDwhIdHub) throws SQLException {
        Connection connectionToDWHForValues = db.connToDWH();
        Statement stForDWHForValues = db.stFromConnection(connectionToDWHForValues);
        ResultSet rsFromDWHForValues = db.rsFromDB(stForDWHForValues, sql);
        Integer values = null;
        while (rsFromDWHForValues.next()) {
            values = rsFromDWHForValues.getInt(nameDwhIdHub);
        }
        db.closeConnecions(rsFromDWHForValues, stForDWHForValues, connectionToDWHForValues);
        return values;
    }


}
