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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Тест сравнивает данные, сформированные контрольным запросом с загруженными в DM данными.
Кол-во строк для проверки задается в properties-файле параметром system.PercentOfRows. Задается в процентах от общего кол-ва записей, пригодных для загрузки из DWH
Допускается нецелое число, разделитель- точка.
Если записей в таблице мало, то для сравнения берется кол-во записей, заданное парамером  system.default.RowsForMatch -1
Если тест найдет отличие в каком либо из полей, или обнаружит дубли в DM-таблице, то на консоль будет выведено сообщение об ошибке.
Тест не упадет при этом, т.е. нужно контролировать глазами!
Тесты неодинаковые - в некоторых dim'ах (и, соотвественно, тестах) есть особенности.
 */
public class DimsDataMatching {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapFromDV = new HashMap<String, Object>();
    private Map<String, Object> mapFromDM = new HashMap<String, Object>();


    @Test(enabled = true)
    public void dimCustomers_matchData() throws SQLException, IOException {
        getPropertiesFile();
// тест пригоден к использованию в текущем варианте (версия 1)
        int countRowInDV = getCountRowInDV(properties.getProperty("customers.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("customers.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("customers.dataInDM.RowByKeys") + " where dwhIdHubCustomers = " +
                        rsFromDWH.getInt("dwhIdHubCustomers") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimFileLiner_matchData() throws SQLException, IOException {
        getPropertiesFile();
// тест пригоден к использованию в текущем варианте (версия 1)
        int countRowInDV = getCountRowInDV(properties.getProperty("fileLiner.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("fileLiner.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("fileLiner.dataInDM.RowByKeys") + " where dwhIdHubFileLiner = " +
                        rsFromDWH.getInt("dwhIdHubFileLiner") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimCountry_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("country.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("country.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("country.dataInDM.RowByKeys") + " where dwhIdHubCountry = " +
                        rsFromDWH.getInt("dwhIdHubCountry") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimBookingCargo_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("bookingCargo.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);
/*
        System.out.println("Before Sorting:");
        for(Object counter: arrayRows){
            System.out.println(counter);
        }

        Collections.sort(arrayRows);

	   /* ArrayList after sorting
        System.out.println("After Sorting:");
        for(Object counter: arrayRows){
            System.out.println(counter);
        }
        */

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("bookingCargo.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingCargo.dataInDM.RowByKeys") + " where dwhIdHubBookingCargo = " +
                        rsFromDWH.getInt("dwhIdHubBookingCargo") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void fctBooking_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("fctBookingCargo.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("fctBookingCargo.dataInDV.RowByRowNum") + arrayRows.get(i));
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
    public void dimCompany_matchData() throws SQLException, IOException {
        getPropertiesFile();

        int countRowInDV = getCountRowInDV(properties.getProperty("company.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("company.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("company.dataInDM.RowByKeys") + " where dwhIdHubCompany = " +
                        rsFromDWH.getInt("dwhIdHubCompany") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimTransshipmentPorts_matchData() throws SQLException, IOException {
        getPropertiesFile();
        /*
        ВНИМАНИЕ!. Dim специфичен. Перед запуском нужно вставить тестовые данные:
        INSERT INTO DataVaultTest.fct.fctLoopSearch (liveScheduleName, loopLeg, locationCode,countryCode,portName, serialPortNumber, isTransshipmentPort, validFromBusiness, validToBusiness, deleteDate)
                  VALUES ('OZLoop1', 'OZ2', 'TEST', 'TE', 'TestNAme2', 8, 'Y', CONVERT(datetime,'2017-10-21', 102),CONVERT(datetime,'2019-01-01', 102), null)
        INSERT INTO DataVaultTest.fct.fctLoopSearch (liveScheduleName, loopLeg, locationCode,countryCode,portName, serialPortNumber, isTransshipmentPort, validFromBusiness, validToBusiness, deleteDate)
                  VALUES ('OZLoop1', 'OZ2', 'TEST', 'TE', 'TestNAme2', 8, 'Y', CONVERT(datetime,'2017-10-21', 102),CONVERT(datetime,'2019-01-01', 102), null)

        Эти записи одинаковые по ключу, но с разными potrName. В dim должна попысть одна из них- первая.
         */
        System.err.println("См комментарий к тесту!");
        int countRowInDV = getCountRowInDV(properties.getProperty("transshipmentPorts.dwh.CountRows"));
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("transshipmentPorts.dataInDV.RowByRowNum") + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("transshipmentPorts.dataInDM.RowByKeys") + " where locationCode = '" +
                        rsFromDWH.getInt("locationCode") + "' and validFromBusiness = '" + rsFromDWH.getString("validFromBusiness")
                        + "' and validToBusiness = '" + rsFromDWH.getString("validToBusiness") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimCompanyLocation_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("companyLocation.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubCompany) AS RowNumber, * " +
                    properties.getProperty("companyLocation.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("companyLocation.dataInDM.RowByKeys") + " where dwhIdHubCompany = " +
                        rsFromDWH.getInt("dwhIdHubCompany") + " and dwhIdHubLocations = " +
                        rsFromDWH.getInt("dwhIdHubLocations") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimBookingManifest_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("bookingManifest.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubBookingManifest) AS RowNumber, * " +
                    properties.getProperty("bookingManifest.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingManifest.dataInDM.RowByKeys") + " where dwhIdHubBookingManifest = " +
                        rsFromDWH.getInt("dwhIdHubBookingManifest") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);

        }
    }

    @Test(enabled = true)
    public void dimContainerType_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("containerType.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubContainerType) AS RowNumber, * " +
                    properties.getProperty("containerType.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("containerType.dataInDM.RowByKeys") + " where dwhIdHubContainerType = " +
                        rsFromDWH.getInt("dwhIdHubContainerType") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimBookingHaulageDetails_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("bookingHaulageDetails.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdhubBookingHaulageDetails) AS RowNumber, * " +
                    properties.getProperty("bookingHaulageDetails.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingHaulageDetails.dataInDM.RowByKeys") + " where dwhIdhubBookingHaulageDetails = " +
                        rsFromDWH.getInt("dwhIdhubBookingHaulageDetails") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimCompanyRegion_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("companyRegion.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubCompany) AS RowNumber, * " +
                    properties.getProperty("companyRegion.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("companyRegion.dataInDM.RowByKeys") + " where dwhIdHubCompany = " +
                        rsFromDWH.getInt("dwhIdHubCompany") + " and regionName = '" + rsFromDWH.getString("regionName")
                        + "' and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimControllingOffice_matchData() throws SQLException, IOException {
        getPropertiesFile();
        System.err.println("Запрос привыборе записей не учитывает значение в validFrom и не выбирает саты и статусы, соотвественно этому значению, поэтому неhub-полях, которые менялись могут быть расхождения");
        System.err.println("Например если при первоначальной загрузке поле_1 не было заполнено, а при загрузке изменений оно уже заполнено, то запрос вернет две записи с одинаково заполненным полем_1");
        System.err.println("тогда как на самом деле у одной из них (с более ранним validFrom оно должно быть пустм - как  и было на эту дату)");
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("controllingOffice.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubControllingOffice) AS RowNumber, * " +
                    properties.getProperty("controllingOffice.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("controllingOffice.dataInDM.RowByKeys") + " where dwhIdHubControllingOffice = " +
                        rsFromDWH.getInt("dwhIdHubControllingOffice") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimLocationDestinationRegion_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("locationDestinationRegion.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubLocations) AS RowNumber, * " +
                    properties.getProperty("locationDestinationRegion.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("locationDestinationRegion.dataInDM.RowByKeys") + " where dwhIdHubLocations = " +
                        rsFromDWH.getInt("dwhIdHubLocations") + " and destinationRegionName = '" + rsFromDWH.getString("destinationRegionName")
                        + "' and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimLocationRegion_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("locationRegion.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubLocations) AS RowNumber, * " +
                    properties.getProperty("locationRegion.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("locationRegion.dataInDM.RowByKeys") + " where dwhIdHubLocations = " +
                        rsFromDWH.getInt("dwhIdHubLocations") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimOceanVesselService_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("oceanVesselService.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubOceanVesselService) AS RowNumber, * " +
                    properties.getProperty("oceanVesselService.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("oceanVesselService.dataInDM.RowByKeys") + " where dwhIdHubOceanVesselService = " +
                        rsFromDWH.getInt("dwhIdHubOceanVesselService") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimOceanVesselStatus_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("oceanVesselStatus.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubOceanVesselStatus) AS RowNumber, * " +
                    properties.getProperty("oceanVesselStatus.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("oceanVesselStatus.dataInDM.RowByKeys") + " where dwhIdHubOceanVesselStatus = " +
                        rsFromDWH.getInt("dwhIdHubOceanVesselStatus") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimTradeForEmedStat_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("tradeForEmedStat.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " tradeNameGvaForEmedStat) AS RowNumber, * " +
                    properties.getProperty("tradeForEmedStat.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("tradeForEmedStat.dataInDM.RowByKeys") + " where tradeNameGvaForEmedStat = '" +
                        rsFromDWH.getString("tradeNameGvaForEmedStat") + "' and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimOvTradeName_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("ovTradeName.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " ovTradeName) AS RowNumber, * " +
                    properties.getProperty("ovTradeName.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("ovTradeName.dataInDM.RowByKeys") + " where ovTradeName = '" +
                        rsFromDWH.getString("ovTradeName") + "' and accessCompanyId = " + rsFromDWH.getString("accessCompanyId"));
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimOvTradeNumber_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("ovTradeNumber.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " ovTradeNumber) AS RowNumber, * " +
                    properties.getProperty("ovTradeNumber.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("ovTradeNumber.dataInDM.RowByKeys") + " where ovTradeNumber = '" +
                        rsFromDWH.getString("ovTradeNumber") + "' and  ovTradeDirection = '" + rsFromDWH.getString("ovTradeDirection") +
                        "' and accessCompanyId = " + rsFromDWH.getString("accessCompanyId"));
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimLocations_matchData() throws SQLException, IOException {
            /*
        Контрольный запрос учитывает validFrom и выбирает саты и статусы соотвественно. Плюс, процедура getDimLocations вне зависимости от результата
трех внешних соединений для  dwhIdHubLocations берет validFrom из satLocationsPortsOverview. Что порождает лишние записи Это решили оставить - не мешает.
Контрольный запрос это учитывает.
         */
        getPropertiesFile();
        System.out.println("dimLocations_matchData. В случае падения теста см комментарий к нему");
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("locations.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubLocations) AS RowNumber, * " +
                    properties.getProperty("locations.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                mapFromDV.remove("dwhIdHubLocationsPortsOverview");
                String sqlForDM = (properties.getProperty("locations.dataInDM.RowByKeys") + " where dwhIdHubLocations = " +
                        rsFromDWH.getInt("dwhIdHubLocations") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }


    @Test(enabled = true)
    public void dimGvaTrade_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("gvaTrade.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " gvaTrade) AS RowNumber, * " +
                    properties.getProperty("gvaTrade.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("gvaTrade.dataInDM.RowByKeys") + " where gvaTrade = '" +
                        rsFromDWH.getString("gvaTrade") + "' and accessCompanyId = " + rsFromDWH.getInt("accessCompanyId"));
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimTransportMode_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("transportMode.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdhubTransportMode) AS RowNumber, * " +
                    properties.getProperty("transportMode.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("transportMode.dataInDM.RowByKeys") + " where dwhIdhubTransportMode = " +
                        rsFromDWH.getInt("dwhIdhubTransportMode") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);

            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimVesselRegistry_matchData() throws SQLException, IOException {
        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("vesselRegistry.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubVesselRegistry) AS RowNumber, * " +
                    properties.getProperty("vesselRegistry.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("vesselRegistry.dataInDM.RowByKeys") + " where dwhIdHubVesselRegistry = " +
                        rsFromDWH.getInt("dwhIdHubVesselRegistry") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);
        }
    }

    @Test(enabled = true)
    public void dimBooking_matchData() throws SQLException, IOException {
/*
Запрос на проверку данных составлен только на выборку validFrom из satBooking, из остальных таблиц в него включать будет слишком громоздко и решила этого не делать
Т.о. данный тест не покажет истинное положение и егго можно назвать некорректным. Запрос соотвествует спеке версии 1.5.
Контрольный запрос booking.dwh.CountRows составлен как должно быть и если этот тест упадет- нужно искать проблему при помощи booking.dwh.CountRows -запроса.

 */
        getPropertiesFile();
        System.err.println("Cм коммент к тесту");
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("booking.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);
        for (int i = 0; i < arrayRows.size(); i++) {
            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubBooking) AS RowNumber, * " +
                    properties.getProperty("booking.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("booking.dataInDM.RowByKeys") + " where dwhIdHubBooking = " +
                        rsFromDWH.getInt("dwhIdHubBooking") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);

        }
    }

    @Test(enabled = true)
    public void dimBookingOceanVessel_matchData() throws SQLException, IOException {

        getPropertiesFile();
        String query = properties.getProperty("common.sql.forCount") + " " + properties.getProperty("bookingOceanVessel.dataInDV.commonPart");
        int countRowInDV = getCountRowInDV(query);
        ArrayList arrayRows = getArray(countRowInDV);

        for (int i = 0; i < arrayRows.size(); i++) {

            String sqlFromDV = (properties.getProperty("common.sql.byRownum") + " dwhIdHubBookingOceanVessel) AS RowNumber, * " +
                    properties.getProperty("bookingOceanVessel.dataInDV.commonPart") + ") q where RowNumber =" + arrayRows.get(i));
            System.out.println("sqlFromDV: " + sqlFromDV);
            Connection connectionToDWH = db.connToDWH();
            Statement stForDWH = db.stFromConnection(connectionToDWH);
            ResultSet rsFromDWH = db.rsFromDB(stForDWH, sqlFromDV);
            while (rsFromDWH.next()) {
                mapFromDV = getMapFromDV(rsFromDWH);
                String sqlForDM = (properties.getProperty("bookingOceanVessel.dataInDM.RowByKeys") + " where dwhIdHubBookingOceanVessel = " +
                        rsFromDWH.getInt("dwhIdHubBookingOceanVessel") + " and validFrom = '" + rsFromDWH.getString("validFrom") + "\'");
                System.out.println("sqlForDM: " + sqlForDM);
                mapFromDM = getMapFromDM(mapFromDV.size(), sqlForDM);
            }
            db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
            matchMaps(mapFromDV, mapFromDM);

        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/dimsCountsSQL.properties"))));
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
        //System.out.println("Map from DV = " + mapDV);
        //System.out.println("Map from DM = " + mapDM);

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



