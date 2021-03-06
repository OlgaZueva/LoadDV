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
Тест по условиям, описанным в специфиакации собирает данные для каждой записи, формирует запрос на вставку в тестовую таблицу.
После завершения работы теста данные нужно сравнить вручную.
Количество записей тест проверит.
Выполняется с учетом кол-ва записей Demurrage ооочень долго. Ждать окончания не имеет смысла  - проверка файктически одноразовая.
Достаточно дождаться загрузки нескольких сотен записей и сравнить загруженное с целевой таблицей вручную.
 */

public class DemurrageTest {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private GetDataHelper getDataHelper = new GetDataHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    List<String> finalSQL = new ArrayList<String>();

    @Test(enabled = true)
    public void MSCRUS_FillingDemurrageTestTable() throws SQLException, IOException {
        getPropertiesFile();
        // Создание и зачистка тестовой таблицы
        String create = (properties.getProperty("demurrage.linkTableMSCRUS.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("demurrage.linkTableMSCRUS.truncate"));
        getDataHelper.executeInDWH(truncate);
//-----------Выбираем данные из SA-таблицы с учетом условий и формируем MAP, в котрой каждый элемент соотвествуюет полю dwh-таблицы
        String sql = (properties.getProperty("demurrage.allData.MSCRUS.select"));
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource.put("accessCompanyId", rsFromDWH.getInt("COMPANY"));
            mapForSource.put("bookingNumber", rsFromDWH.getLong("BOOK_NO"));
            mapForSource.put("containerNr", rsFromDWH.getString("CONT_NO"));
            mapForSource.put("demurrageStorageCode", rsFromDWH.getString("CODE"));
            mapForSource.put("demurrageId", rsFromDWH.getInt("DEM_ID"));
            mapForSource.put("demurrageStorageStatus", rsFromDWH.getString("STATUS"));
            Double amount = rsFromDWH.getDouble("AMOUNT");
            putDoubleValues(amount, "amount");

            Double invoicedAmount = rsFromDWH.getDouble("INVOICED_AMOUNT");
            putDoubleValues(invoicedAmount, "invoicedAmount");

            Double theoreticalAmount = rsFromDWH.getDouble("THEORETICAL_AMOUNT");
            putDoubleValues(theoreticalAmount, "theoreticalAmount");
            mapForSource.put("reissue", rsFromDWH.getString("REISSUE"));
            Double clientRoe = rsFromDWH.getDouble("CLIENT_ROE");
            putDoubleValues(clientRoe, "clientRoe");

            Double roe = rsFromDWH.getDouble("ROE");
            putDoubleValues(roe, "roe");

            Double stdCurrencyRoe = rsFromDWH.getDouble("STD_ROE");
            putDoubleValues(stdCurrencyRoe, "stdCurrencyRoe");

            int daysNumber = rsFromDWH.getInt("DAYS");
            putIntValues(daysNumber, "daysNumber");
            mapForSource.put("startDate", rsFromDWH.getDate("START_DATE"));
            mapForSource.put("endDate", rsFromDWH.getDate("END_DATE"));
            mapForSource.put("startMark", rsFromDWH.getString("START_MARK"));
            mapForSource.put("endMark", rsFromDWH.getString("END_MARK"));

            int startDays = rsFromDWH.getInt("START_DAYS");
            putIntValues(startDays, "startDays");

            int freeDays = rsFromDWH.getInt("FREE_DAYS");
            putIntValues(freeDays, "freeDays");

            int stdDays = rsFromDWH.getInt("STD_DAYS");
            putIntValues(stdDays, "stdDays");
            mapForSource.put("ruleId", rsFromDWH.getInt("RULE_ID"));
            mapForSource.put("stdRuleId", rsFromDWH.getInt("STD_RULE_ID"));
            mapForSource.put("orderNr", rsFromDWH.getInt("ORDRE_NO"));
            mapForSource.put("clientCurrency", rsFromDWH.getString("CLIENT_CURR"));
            mapForSource.put("demurrCurrency", rsFromDWH.getString("CURRENCY"));
            mapForSource.put("stdCurrency", rsFromDWH.getString("STD_CURRENCY"));
            mapForSource.put("startMoveCode", rsFromDWH.getString("START_MOVE"));
            mapForSource.put("endMoveCode", rsFromDWH.getString("END_MOVE"));
            mapForSource.put("client", rsFromDWH.getInt("CLIENT"));
            mapForSource.put("srcSystemId", rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubCompany = (properties.getProperty("dwhIdHubCompany.id.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

// В настоящий момент по условиям могут быть выбраны несколько dwhIdHubBookingCargo, поэтому тут выбирается просто первый. Должно быть уточнено и изменено. Могут юыть неточности
            String sqlDwhIdHubBookingCargo = (properties.getProperty("dwhIdHubBookingCargo.id.select.part1") + mapForSource.get("containerNr") +
                    (properties.getProperty("dwhIdHubBookingCargo.id.select.part2") + mapForSource.get("accessCompanyId") + " AND " +
                            (properties.getProperty("dwhIdHubBookingCargo.id.select.part3") + mapForSource.get("bookingNumber") + " AND " +
                                    properties.getProperty("srcSystemId.value.select") + mapForSource.get("srcSystemId") + " " +
                                    (properties.getProperty("dwhIdHubBookingCargo.id.select.part4")))));

            String sqlDwhIdHubContainerDemurrageRulesSTD = (properties.getProperty("dwhIdHubContainerDemurrageRules.id.select") +
                    mapForSource.get("stdRuleId") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubContainerDemurrageRulesFACT = (properties.getProperty("dwhIdHubContainerDemurrageRules.id.select") +
                    mapForSource.get("ruleId") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCurrencyDemurrCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    mapForSource.get("demurrCurrency") + "' AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCurrencyClientCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    mapForSource.get("clientCurrency") + "' AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCurrencyStdCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    mapForSource.get("stdCurrency") + "' AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCustomers = (properties.getProperty("dwhIdHubCustomers.id.select") +
                    mapForSource.get("client") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdhubInvoice = (properties.getProperty("dwhIdhubInvoice.id.select") +
                    mapForSource.get("orderNr") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            mapForSource.put("dwhIdHubBookingCargo", getValueHubId(sqlDwhIdHubBookingCargo, "dwhIdHubBookingCargo"));
            mapForSource.put("dwhIdHubCompany", getValueHubId(sqlDwhIdHubCompany, "dwhIdHubCompany"));
            mapForSource.put("dwhIdHubContainerDemurrageRulesSTD", getValueHubId(sqlDwhIdHubContainerDemurrageRulesSTD, "dwhIdHubContainerDemurrageRules"));
            mapForSource.put("dwhIdHubContainerDemurrageRulesFACT", getValueHubId(sqlDwhIdHubContainerDemurrageRulesFACT, "dwhIdHubContainerDemurrageRules"));

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
            Object client = mapForSource.get("client");
            if (dwhIdHubCustomers == null|| client.equals(9999) ) {
                mapForSource.put("dwhIdHubCustomers", -1);
            } else {
                mapForSource.put("dwhIdHubCustomers", dwhIdHubCustomers);
            }

            if (mapForSource.get("orderNr").equals(0)) {
                mapForSource.put("dwhIdhubInvoice", null);
            } else {
                mapForSource.put("dwhIdhubInvoice", getValueHubId(sqlDwhIdhubInvoice, "dwhIdhubInvoice"));
            }

            if (rsFromDWH.getDate("CdcTimestamp") == null) {
                mapForSource.put("validFrom", "01-01-2000");
            } else {
                mapForSource.put("validFrom", rsFromDWH.getDate("CdcTimestamp"));
            }

            //--- конец блока сбора и формирования данных
            // --- собираем запрос на вставкузаписей с тестовую таблицу

            String qwe = (properties.getProperty("demurrage.fctMSCRUS.insert") + mapForSource.get("accessCompanyId") + "," + mapForSource.get("bookingNumber") + ",'" +
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
                    + mapForSource.get("dwhIdHubContainerDemurrageRulesSTD") + "," + mapForSource.get("dwhIdHubContainerDemurrageRulesFACT") + ","
                    + mapForSource.get("dwhIdHubCurrencyDemurrCurrency") + "," + mapForSource.get("dwhIdHubCurrencyClientCurrency") + ","
                    + mapForSource.get("dwhIdHubCurrencyStdCurrency") + "," + mapForSource.get("dwhIdHubCustomers") + ","
                    + mapForSource.get("dwhIdhubInvoice") + ","
                    + mapForSource.get("srcSystemId") + ",CONVERT(datetime, '" + mapForSource.get("validFrom") + "', 102))");

            finalSQL.add(qwe);
            //System.out.println(finalSQL.size());
            getDataHelper.executeInDWH(qwe);
        }

        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        //String str = String.join(" ", finalSQL);
        //getDataHelper.executeInDWH(str);
//--- конец блока формирования и вставки записей в тестовую таблицу

        //-- ---- обновление тестовой таблицы - замена текстовых значение null на пустые значения
        String updateReissue = (properties.getProperty("demurrage.reissue.update"));
        getDataHelper.executeInDWH(updateReissue);
        String updateStartMark = (properties.getProperty("demurrage.startMark.update"));
        getDataHelper.executeInDWH(updateStartMark);
        String updateEndMark = (properties.getProperty("demurrage.endMark.update"));
        getDataHelper.executeInDWH(updateEndMark);
        String updateStdCurrency = (properties.getProperty("demurrage.stdCurrency.update"));
        getDataHelper.executeInDWH(updateStdCurrency);
        String updateClientCurrency = (properties.getProperty("demurrage.clientCurrency.update"));
        getDataHelper.executeInDWH(updateClientCurrency);
        String updateDemurrCurrency = (properties.getProperty("demurrage.demurrCurrency.update"));
        getDataHelper.executeInDWH(updateDemurrCurrency);
    }


    @Test(enabled = true)
    public void UNITY_FillingDemurrageTestTable() throws SQLException, IOException {
        getPropertiesFile();
        // Создание и зачистка тестовой таблицы
        String create = (properties.getProperty("demurrage.linkTableUNITY.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("demurrage.linkTableUNITY.truncate"));
        getDataHelper.executeInDWH(truncate);
//-----------Выбираем данные из SA-таблицы с учетом условий и формируем MAP, в котрой каждый элемент соотвествуюет полю dwh-таблицы
        String sql = (properties.getProperty("demurrage.allData.UNITY.select"));
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource.put("accessCompanyId", rsFromDWH.getInt("COMPANY"));
            mapForSource.put("bookingNumber", rsFromDWH.getLong("BOOK_NO"));
            mapForSource.put("containerNr", rsFromDWH.getString("CONT_NO"));
            mapForSource.put("demurrageStorageCode", rsFromDWH.getString("CODE"));
            mapForSource.put("demurrageId", rsFromDWH.getInt("DEM_ID"));
            mapForSource.put("demurrageStorageStatus", rsFromDWH.getString("STATUS"));
            Double amount = rsFromDWH.getDouble("AMOUNT");
            putDoubleValues(amount, "amount");

            Double invoicedAmount = rsFromDWH.getDouble("INVOICED_AMOUNT");
            putDoubleValues(invoicedAmount, "invoicedAmount");

            Double theoreticalAmount = rsFromDWH.getDouble("THEORETICAL_AMOUNT");
            putDoubleValues(theoreticalAmount, "theoreticalAmount");
            mapForSource.put("reissue", rsFromDWH.getString("REISSUE"));
            Double clientRoe = rsFromDWH.getDouble("CLIENT_ROE");
            putDoubleValues(clientRoe, "clientRoe");

            Double roe = rsFromDWH.getDouble("ROE");
            putDoubleValues(roe, "roe");

            Double stdCurrencyRoe = rsFromDWH.getDouble("STD_ROE");
            putDoubleValues(stdCurrencyRoe, "stdCurrencyRoe");

            int daysNumber = rsFromDWH.getInt("DAYS");
            putIntValues(daysNumber, "daysNumber");
            mapForSource.put("startDate", rsFromDWH.getDate("START_DATE"));
            mapForSource.put("endDate", rsFromDWH.getDate("END_DATE"));
            mapForSource.put("startMark", rsFromDWH.getString("START_MARK"));
            mapForSource.put("endMark", rsFromDWH.getString("END_MARK"));

            int startDays = rsFromDWH.getInt("START_DAYS");
            putIntValues(startDays, "startDays");

            int freeDays = rsFromDWH.getInt("FREE_DAYS");
            putIntValues(freeDays, "freeDays");

            int stdDays = rsFromDWH.getInt("STD_DAYS");
            putIntValues(stdDays, "stdDays");
            mapForSource.put("ruleId", rsFromDWH.getInt("RULE_ID"));
            mapForSource.put("stdRuleId", rsFromDWH.getInt("STD_RULE_ID"));
            mapForSource.put("orderNr", rsFromDWH.getInt("ORDRE_NO"));
            mapForSource.put("clientCurrency", rsFromDWH.getString("CLIENT_CURR"));
            mapForSource.put("demurrCurrency", rsFromDWH.getString("CURRENCY"));
            mapForSource.put("stdCurrency", rsFromDWH.getString("STD_CURRENCY"));
            mapForSource.put("startMoveCode", rsFromDWH.getString("START_MOVE"));
            mapForSource.put("endMoveCode", rsFromDWH.getString("END_MOVE"));
            mapForSource.put("client", rsFromDWH.getInt("CLIENT"));
            mapForSource.put("srcSystemId", rsFromDWH.getInt("srcSystemId"));

            String sqlDwhIdHubCompany = (properties.getProperty("dwhIdHubCompany.id.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    rsFromDWH.getInt("srcSystemId"));

// В настоящий момент по условиям могут быть выбраны несколько dwhIdHubBookingCargo, поэтому тут выбирается просто первый. Должно быть уточнено и изменено. Могут юыть неточности
            String sqlDwhIdHubBookingCargo = (properties.getProperty("dwhIdHubBookingCargo.id.select.part1") + mapForSource.get("containerNr") +
                    (properties.getProperty("dwhIdHubBookingCargo.id.select.part2") + mapForSource.get("accessCompanyId") + " AND " +
                            (properties.getProperty("dwhIdHubBookingCargo.id.select.part3") + mapForSource.get("bookingNumber") + " AND " +
                                    properties.getProperty("srcSystemId.value.select") + mapForSource.get("srcSystemId") + " " +
                                    (properties.getProperty("dwhIdHubBookingCargo.id.select.part4")))));

            String sqlDwhIdHubContainerDemurrageRulesSTD = (properties.getProperty("dwhIdHubContainerDemurrageRules.id.select") +
                    mapForSource.get("stdRuleId") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubContainerDemurrageRulesFACT = (properties.getProperty("dwhIdHubContainerDemurrageRules.id.select") +
                    mapForSource.get("ruleId") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCurrencyDemurrCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    mapForSource.get("demurrCurrency") + "' AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCurrencyClientCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    mapForSource.get("clientCurrency") + "' AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCurrencyStdCurrency = (properties.getProperty("dwhIdHubCurrency.id.select") +
                    mapForSource.get("stdCurrency") + "' AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdHubCustomers = (properties.getProperty("dwhIdHubCustomers.id.select") +
                    mapForSource.get("client") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            String sqlDwhIdhubInvoice = (properties.getProperty("dwhIdhubInvoice.id.select") +
                    mapForSource.get("orderNr") + " AND " + properties.getProperty("companyId.value.select") +
                    mapForSource.get("accessCompanyId") + " AND " + properties.getProperty("srcSystemId.value.select") +
                    mapForSource.get("srcSystemId"));

            mapForSource.put("dwhIdHubBookingCargo", getValueHubId(sqlDwhIdHubBookingCargo, "dwhIdHubBookingCargo"));
            mapForSource.put("dwhIdHubCompany", getValueHubId(sqlDwhIdHubCompany, "dwhIdHubCompany"));
            mapForSource.put("dwhIdHubContainerDemurrageRulesSTD", getValueHubId(sqlDwhIdHubContainerDemurrageRulesSTD, "dwhIdHubContainerDemurrageRules"));
            mapForSource.put("dwhIdHubContainerDemurrageRulesFACT", getValueHubId(sqlDwhIdHubContainerDemurrageRulesFACT, "dwhIdHubContainerDemurrageRules"));

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
            Object client = mapForSource.get("client");
            if (dwhIdHubCustomers == null|| client.equals(9999) ) {
                mapForSource.put("dwhIdHubCustomers", -1);
            } else {
                mapForSource.put("dwhIdHubCustomers", dwhIdHubCustomers);
            }

            if (mapForSource.get("orderNr").equals(0)) {
                mapForSource.put("dwhIdhubInvoice", null);
            } else {
                mapForSource.put("dwhIdhubInvoice", getValueHubId(sqlDwhIdhubInvoice, "dwhIdhubInvoice"));
            }

            if (rsFromDWH.getDate("CdcTimestamp") == null) {
                mapForSource.put("validFrom", "01-01-2000");
            } else {
                mapForSource.put("validFrom", rsFromDWH.getDate("CdcTimestamp"));
            }

            //--- конец блока сбора и формирования данных
            // --- собираем запрос на вставкузаписей с тестовую таблицу

            String qwe = (properties.getProperty("demurrage.fctUNITY.insert") + mapForSource.get("accessCompanyId") + "," + mapForSource.get("bookingNumber") + ",'" +
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
                    + mapForSource.get("dwhIdHubContainerDemurrageRulesSTD") + "," + mapForSource.get("dwhIdHubContainerDemurrageRulesFACT") + ","
                    + mapForSource.get("dwhIdHubCurrencyDemurrCurrency") + "," + mapForSource.get("dwhIdHubCurrencyClientCurrency") + ","
                    + mapForSource.get("dwhIdHubCurrencyStdCurrency") + "," + mapForSource.get("dwhIdHubCustomers") + ","
                    + mapForSource.get("dwhIdhubInvoice") + ","
                    + mapForSource.get("srcSystemId") + ",CONVERT(datetime, '" + mapForSource.get("validFrom") + "', 102))");

            finalSQL.add(qwe);
            //System.out.println(finalSQL.size());
            getDataHelper.executeInDWH(qwe);
        }

        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        //String str = String.join(" ", finalSQL);
        //getDataHelper.executeInDWH(str);
//--- конец блока формирования и вставки записей в тестовую таблицу

        //-- ---- обновление тестовой таблицы - замена текстовых значение null на пустые значения
        String updateReissue = (properties.getProperty("demurrage.reissueUNITY.update"));
        getDataHelper.executeInDWH(updateReissue);
        String updateStartMark = (properties.getProperty("demurrage.startMarkUNITY.update"));
        getDataHelper.executeInDWH(updateStartMark);
        String updateEndMark = (properties.getProperty("demurrage.endMarkUNTIY.update"));
        getDataHelper.executeInDWH(updateEndMark);
        String updateStdCurrency = (properties.getProperty("demurrage.stdCurrencyUNITY.update"));
        getDataHelper.executeInDWH(updateStdCurrency);
        String updateClientCurrency = (properties.getProperty("demurrage.clientCurrencyUNITY.update"));
        getDataHelper.executeInDWH(updateClientCurrency);
        String updateDemurrCurrency = (properties.getProperty("demurrage.demurrCurrencyUNITY.update"));
        getDataHelper.executeInDWH(updateDemurrCurrency);
    }

    private void putIntValues(int variable, String mapFieldName) {
        if (variable == 0) {
            mapForSource.put(mapFieldName, null);
        } else {
            mapForSource.put(mapFieldName, variable);
        }
    }

    private void putDoubleValues(Double variable, String mapFieldName) {
        if (variable == 0) {
            mapForSource.put(mapFieldName, null);
        } else {
            mapForSource.put(mapFieldName, variable);
        }
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
