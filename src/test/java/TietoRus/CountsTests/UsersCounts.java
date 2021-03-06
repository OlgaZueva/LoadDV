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
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UsersCounts {
    /*
В Adgang встречаются случаи, когда в обоих таблицах есть записи с одним и тем же VOR_REF, но разными AD_Login
В AdgangLin для них приходит разное кол-во записей. Контрольный зпрос задваивает такие. Но система создает все верно
Поэтому падение теста не считать ошибкой;
      */
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void users() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("users.union.CountRows"));
        int countRowInDWH = getCountRowInDWH(properties.getProperty("users.destinationTable.CountRows"));
        assertRowCount(countRowInSA, countRowInDWH);
    }

    @Test(enabled = true)
    public void usersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowFromAdgangLinOnly = getCountRowInDWH(properties.getProperty("usersCompany.conditionForAdgangLinOnly.CountRows"));
        int countRowFromAdgangOnly = getCountRowInDWH(properties.getProperty("usersCompany.conditionForAdgangOnly.CountRows"));
        int countRowByCondition = getCountRowInDWH(properties.getProperty("usersCompany.condition.CountRows"));
        System.out.println("Столько должно быть записей из AdgangLin (без учета уже существующих в stUserCompany): " + countRowFromAdgangLinOnly);
        System.out.println("Столько записей д.б. создано из Adgang: " + countRowFromAdgangOnly);
        System.out.println("Столько записей д.б. в stUserCompany с учетом загруженого из Adgand: " + countRowByCondition);
        int countRowInDestination = getCountRowInDWH(properties.getProperty("usersCompany.destinationTable.CountRows"));
        assertRowCount(countRowByCondition, countRowInDestination);
    }

    @Test(enabled = true)
    public void deletedUsers() throws SQLException, IOException {
        getPropertiesFile();
        int countRowInSA = getCountRowInSA(properties.getProperty("deletesUsers.union.CountRows"));
        int countRowInDWH = getCountRowInDWH(properties.getProperty("deletesUsers.destinationTable.CountRows"));
        assertRowCount(countRowInSA, countRowInDWH);
    }

    @Test(enabled = true)
    public void deletedUsersCompany() throws SQLException, IOException {
        getPropertiesFile();
        int countRowByCondition = getCountRowInDWH(properties.getProperty("deletedUsersCompany.condition.CountRows"));
        int countRowInDestination = getCountRowInDWH(properties.getProperty("deletedUsersCompany.destinationTable.CountRows"));
        assertRowCount(countRowByCondition, countRowInDestination);
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/usersCountsSQL.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowInSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        int countRowSA = 0;
        while (rsFromSA.next()) {
            countRowSA = Integer.parseInt(rsFromSA.getString("c"));
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return countRowSA;
    }

    public int getCountRowInDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        int countRowHub = 0;
        while (rsFromDWH.next()) {
            countRowHub = Integer.parseInt(rsFromDWH.getString("c"));
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        //System.out.println("countRowHub in DWH: " + countRowHub);
        return countRowHub;
    }
}
