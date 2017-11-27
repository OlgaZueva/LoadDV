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
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.trim;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Тест предназначен для проверки функциональности "RealCustomers".
Перед его запуском нужно заполнить справоники dictExcludedSymbols и dictEmptyCustomerTable (см класс CleaningCustomernames)
 В нем из DWH выбираются записи в порядке, описанном в спеке, очищаются Customersname и вставляются во вспомогательную таблицу.
 Данные из этой таблицы нужно сравнивать с данными из целевой таблицы (в которую пшет пакет).
 При анализе различий следует учесть особенности:
 1. если при переборе patry (ролей) для dwhIdHubBooking первая оказывается в dictEmptyCustomerTable, то запись д.б. пропущена и система должна перейти к слеющуему party
 для dwhIdHubBooking и взять имя от туда, так пока не закончатся party для dwhIdHubBooking и, если это последняя party, то в целевую таблицу д.б. вставлена запись с RealCustomer = null
 2. Если после очистки имени оно оказалось пустым то запись в целевую таблицу не вставляем.
 Есть доп файл с запросами подготовленными, который может помочь в анализе данных, там же инсерты для вставик тестовых данных
 */
public class RealCustomersTests {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();
    private GetDataHelper getDataHelper =  new GetDataHelper();

    @Test(enabled = true)
    public void RealCustomersCounts() throws SQLException, IOException {
        getPropertiesFile();
        CleaningCustomersNames CreatePrecondition = new CleaningCustomersNames();
        CreatePrecondition.FillingDictExcludedSymbols();
        CreatePrecondition.FillingDictEmptyCustomer();
        CreatePrecondition.FillingDictExceptionalCustomer();

        String create = (properties.getProperty("realCustomerTable.create"));
        getDataHelper.executeInDWH(create);
        String truncate = (properties.getProperty("realCustomer.truncate"));
        getDataHelper.executeInDWH(truncate);

        String sqlForExceptionalCustomers = (properties.getProperty("realCustomer.exportBooking.exceptionalCustomers.select"));
        insertExceptionalCustToTestTable(sqlForExceptionalCustomers, "Y", null);

        ArrayList excludedSymbols = getDataFromDict(properties.getProperty("dictExcludedSymbols.DWH.select"));

        String sqlForStatCustomer_E = (properties.getProperty("realCustomer.exportBooking.StatCustomer.select"));
        insertToTestTable(sqlForStatCustomer_E, "N", getPartyId(properties.getProperty("statCustomer.customerPartyID")), excludedSymbols);

        String sqlForBookingParty_E = (properties.getProperty("realCustomer.exportBooking.BookingParty.select"));
        insertToTestTable(sqlForBookingParty_E, "N", getPartyId(properties.getProperty("bookingParty.customerPartyID")), excludedSymbols);

        String sqlForContractHolder_E = (properties.getProperty("realCustomer.exportBooking.ContractHolder.select"));
        insertToTestTable(sqlForContractHolder_E, "N", getPartyId(properties.getProperty("contractHolder.customerPartyID")), excludedSymbols);

        String sqlForForwarder_E = (properties.getProperty("realCustomer.exportBooking.Forwarder.select"));
        insertToTestTable(sqlForForwarder_E, "N", getPartyId(properties.getProperty("forwarder.customerPartyID")), excludedSymbols);

        String sqlForShipper_E = (properties.getProperty("realCustomer.exportBooking.Shipper.select"));
        insertToTestTable(sqlForShipper_E, "N", getPartyId(properties.getProperty("shipper.customerPartyID")), excludedSymbols);

        String sqlForNotify_E = (properties.getProperty("realCustomer.exportBooking.Notify.select"));
        insertToTestTable(sqlForNotify_E, "N", getPartyId(properties.getProperty("notify.customerPartyID")), excludedSymbols);

        String sqlForConsignee_E = (properties.getProperty("realCustomer.exportBooking.Consignee.select"));
        insertToTestTable(sqlForConsignee_E, "N", getPartyId(properties.getProperty("consignee.customerPartyID")), excludedSymbols);

        String sqlForFreightPayer_E = (properties.getProperty("realCustomer.exportBooking.FreightPayer.select"));
        insertToTestTable(sqlForFreightPayer_E, "N", getPartyId(properties.getProperty("freightPayer.customerPartyID")), excludedSymbols);

        String sqlForContractHolder_nonE = (properties.getProperty("realCustomer.nonExportBooking.ContractHolder.select"));
        insertToTestTable(sqlForContractHolder_nonE, "N", getPartyId(properties.getProperty("contractHolder.customerPartyID")), excludedSymbols);

        String sqlForStatCustomer_nonE = (properties.getProperty("realCustomer.nonExportBooking.StatCustomer.select"));
        insertToTestTable(sqlForStatCustomer_nonE, "N", getPartyId(properties.getProperty("statCustomer.customerPartyID")), excludedSymbols);

        String sqlForFreightPayer_nonE = (properties.getProperty("realCustomer.nonExportBooking.FreightPayer.select"));
        insertToTestTable(sqlForFreightPayer_nonE, "N", getPartyId(properties.getProperty("freightPayer.customerPartyID")), excludedSymbols);

        String sqlForNotify_nonE = (properties.getProperty("realCustomer.nonExportBooking.Notify.select"));
        insertToTestTable(sqlForNotify_nonE, "N", getPartyId(properties.getProperty("notify.customerPartyID")), excludedSymbols);

        String sqlForShipper_nonE = (properties.getProperty("realCustomer.nonExportBooking.Shipper.select"));
        insertToTestTable(sqlForShipper_nonE, "N", getPartyId(properties.getProperty("shipper.customerPartyID")), excludedSymbols);

        String sqlForConsignee_nonE = (properties.getProperty("realCustomer.nonExportBooking.Consignee.select"));
        insertToTestTable(sqlForConsignee_nonE, "N", getPartyId(properties.getProperty("consignee.customerPartyID")), excludedSymbols);

        String sqlForForwarder_nonE = (properties.getProperty("realCustomer.nonExportBooking.Forwarder.select"));
        insertToTestTable(sqlForForwarder_nonE, "N", getPartyId(properties.getProperty("forwarder.customerPartyID")), excludedSymbols);

        String sqlForBookingParty_nonE = (properties.getProperty("realCustomer.nonExportBooking.BookingParty.select"));
        insertToTestTable(sqlForBookingParty_nonE, "N", getPartyId(properties.getProperty("bookingParty.customerPartyID")), excludedSymbols);

    }


    private void insertExceptionalCustToTestTable(String sqlForStatCustomer_E, String isExceptional, Integer role) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlForStatCustomer_E);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String qwe = (properties.getProperty("realCustomer.insert") + (mapForSource.get("dwhIdHubBooking")) + "," + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + mapForSource.get("customerName") + "', '" + isExceptional + "'," + role + ")");
            getDataHelper.executeInDWH(qwe);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
    }

    private void insertToTestTable(String sqlForStatCustomer_E, String isExceptional, int role, ArrayList excludedSymbols) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlForStatCustomer_E);
        while (rsFromDWH.next()) {
            mapForSource = getMapFromSource(rsFromDWH);
            String originalCustomerName = String.valueOf(mapForSource.get("customerName"));
            for (int i = 0; i < excludedSymbols.size(); i++) {
                originalCustomerName = trim(originalCustomerName.replaceAll("(^|\\s)(" + Pattern.quote(String.valueOf(excludedSymbols.get(i))) + ")(\\s|$)", "$1$3"));
                originalCustomerName =  originalCustomerName.replace("  ", " ");


            }
            mapForSource.put("customerName", originalCustomerName);
            String qwe = (properties.getProperty("realCustomer.insert") + (mapForSource.get("dwhIdHubBooking")) + "," + (mapForSource.get("dwhIdHubCustomers"))
                    + ",'" + originalCustomerName.replace("'", "''") + "', '" + isExceptional + "'," + role + ")");
            getDataHelper.executeInDWH(qwe);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
    }


    public ArrayList getDataFromDict(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        ArrayList excludedSymbols = new ArrayList();
        String template = null;
        while (rsFromDWH.next()) {
            template = rsFromDWH.getString("name");
            excludedSymbols.add(template);
            //System.out.println("Template [" + template + "]");
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return excludedSymbols;
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DWHtoMDS.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getPartyId(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        int partyId = 0;
        while (rsFromDWH.next()) {
            partyId = Integer.parseInt(rsFromDWH.getString("dwhIdHubCustomerParty"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return partyId;
    }

    public Map<String, Object> getMapFromSource(ResultSet rsFromSource) throws SQLException {
        for (int k = 1; k <= rsFromSource.getMetaData().getColumnCount(); k++) {
            mapForSource.put(rsFromSource.getMetaData().getColumnName(k), rsFromSource.getObject(k));
        }
        return mapForSource;
    }

}

