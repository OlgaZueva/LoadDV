package TietoRus.CountsTests.DiscardAgencyTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*

 */
public class InsAndDelTestData {
    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();


    @Test(enabled = true)
    public void InsertTestDataTest() throws SQLException, IOException {
        String[] inserts = new String[76];
        inserts[0] = "abPost.UNITY_1.insert";
        inserts[1] = "abPost.UNITY_2.insert";
        inserts[2] = "abPost.MSCRUS_1.insert";
        inserts[3] = "abPost.MSCRUS_2.insert";
        inserts[4] = "adresse.UNITY_1.insert";
        inserts[5] = "adresse.MSCRUS_1.insert";
        inserts[6] = "bogfTrans.UNITY_1.insert";
        inserts[7] = "bogfTrans.UNITY_2.insert";
        inserts[8] = "bogfTrans.MSCRUS_1.insert";
        inserts[9] = "bogfTrans.MSCRUS_2.insert";
        inserts[10] = "book.UNITY_1.insert";
        inserts[11] = "book.UNITY_2.insert";
        inserts[12] = "book.MSCRUS_1.insert";
        inserts[13] = "book.MSCRUS_2.insert";
        inserts[14] = "bookDetails.UNITY_1.insert";
        inserts[15] = "bookDetails.MSCRUS_1.insert";
        inserts[16] = "bookDetailsMof.UNITY_1.insert";
        inserts[17] = "bookDetailsMof.MSCRUS_1.insert";
        inserts[18] = "bookDryPort.UNITY_1.insert";
        inserts[19] = "bookDryPort.MSCRUS_1.insert";
        inserts[20] = "bookEvent.UNITY_1.insert";
        inserts[21] = "bookEvent.UNITY_2.insert";
        inserts[22] = "bookEvent.MSCRUS_1.insert";
        inserts[23] = "bookEvent.MSCRUS_2.insert";
        inserts[24] = "bookFak.UNITY_1.insert";
        inserts[25] = "bookFak.MSCRUS_1.insert";
        inserts[26] = "bookGods.UNITY_1.insert";
        inserts[27] = "bookGods.MSCRUS_1.insert";
        inserts[28] = "bookKor.UNITY_1.insert";
        inserts[29] = "bookKor.MSCRUS_1.insert";
        inserts[30] = "bookLin.UNITY_1.insert";
        inserts[31] = "bookLin.MSCRUS_1.insert";
        inserts[32] = "bookbookManifests.UNITY_1.insert";
        inserts[33] = "bookManifests.MSCRUS_1.insert";
        inserts[34] = "bookMftFile.UNITY_1.insert";
        inserts[35] = "bookMftFile.MSCRUS_1.insert";
        inserts[36] = "bookMftRemarks.UNITY_1.insert";
        inserts[37] = "bookMftRemarks.MSCRUS_1.insert";
        inserts[38] = "bookVessel.UNITY_1.insert";
        inserts[39] = "bookVessel.MSCRUS_1.insert";
        inserts[40] = "contHolliday.UNITY_1.insert";
        inserts[41] = "contHolliday.MSCRUS_1.insert";
        inserts[42] = "contType.UNITY_1.insert";
        inserts[43] = "contType.MSCRUS_1.insert";
        inserts[44] = "ediKonv.UNITY_1.insert";
        inserts[45] = "ediKonv.UNITY_2.insert";
        inserts[46] = "ediKonv.MSCRUS_1.insert";
        inserts[47] = "ediKonv.MSCRUS_2.insert";
        inserts[48] = "expVessels.UNITY_1.insert";
        inserts[49] = "expVessels.MSCRUS_1.insert";
        inserts[50] = "faktPost.UNITY_1.insert";
        inserts[51] = "faktPost.UNITY_2.insert";
        inserts[52] = "faktPost.MSCRUS_1.insert";
        inserts[53] = "faktPost.MSCRUS_2.insert";
        inserts[54] = "henvis.UNITY_1.insert";
        inserts[55] = "henvis.MSCRUS_1.insert";
        inserts[56] = "kunde.UNITY_1.insert";
        inserts[57] = "kunde.MSCRUS_1.insert";
        inserts[58] = "ordre.UNITY_1.insert";
        inserts[59] = "ordre.UNITY_2.insert";
        inserts[60] = "ordre.MSCRUS_1.insert";
        inserts[61] = "ordre.MSCRUS_2.insert";
        inserts[62] = "ordreLin.UNITY_1.insert";
        inserts[63] = "ordreLin.MSCRUS_1.insert";
        inserts[64] = "sag.UNITY_1.insert";
        inserts[65] = "sag.UNITY_2.insert";
        inserts[66] = "sag.MSCRUS_1.insert";
        inserts[67] = "sag.MSCRUS_2.insert";
        inserts[68] = "sagKurs.UNITY_1.insert";
        inserts[69] = "sagKurs.MSCRUS_1.insert";
        inserts[70] = "shipKurs.UNITY_1.insert";
        inserts[71] = "shipKurs.MSCRUS_1.insert";
        inserts[72] = "selskab.UNITY_1.insert";
        inserts[73] = "selskab.MSCRUS_1.insert";
        inserts[74] = "getCharges.UNITY_1.insert";
        inserts[75] = "getCharges.MSCRUS_1.insert";


        getPropertiesFile();
        for (int i = 0; i < inserts.length; i++) {
            String query = properties.getProperty(inserts[i]);
            insertTestRowInSA(query, inserts[i]);
        }
    }

    @Test(enabled = true)
    public void DeleteTestDataTest() throws SQLException, IOException {
        String[] delete = new String[60];
        delete[0] = "abPost.UNITY.delete";
        delete[1] = "abPost.MSCRUS.delete";
        delete[2] = "adresse.UNITY.delete";
        delete[3] = "adresse.MSCRUS.delete";
        delete[4] = "bogfTrans.UNITY.delete";
        delete[5] = "bogfTrans.MSCRUS.delete";
        delete[6] = "book.UNITY.delete";
        delete[7] = "book.MSCRUS.delete";
        delete[8] = "bookDetails.UNITY.delete";
        delete[9] = "bookDetails.MSCRUS.delete";
        delete[10] = "bookDetailsMof.UNITY.delete";
        delete[11] = "bookDetailsMof.MSCRUS.delete";
        delete[12] = "bookDryPort.UNITY.delete";
        delete[13] = "bookDryPort.MSCRUS.delete";
        delete[14] = "bookEvent.UNITY.delete";
        delete[15] = "bookEvent.MSCRUS.delete";
        delete[16] = "bookFak.UNITY.delete";
        delete[17] = "bookFak.MSCRUS.delete";
        delete[18] = "bookGods.UNITY.delete";
        delete[19] = "bookGods.MSCRUS.delete";
        delete[20] = "bookKor.UNITY.delete";
        delete[21] = "bookKor.MSCRUS.delete";
        delete[22] = "bookLin.UNITY.delete";
        delete[23] = "bookLin.MSCRUS.delete";
        delete[24] = "bookManifests.UNITY.delete";
        delete[25] = "bookManifests.MSCRUS.delete";
        delete[26] = "bookMftFile.UNITY.delete";
        delete[27] = "bookMftFile.MSCRUS.delete";
        delete[28] = "bookMftRemarks.UNITY.delete";
        delete[29] = "bookMftRemarks.MSCRUS.delete";
        delete[30] = "bookVessel.UNITY.delete";
        delete[31] = "bookVessel.MSCRUS.delete";
        delete[32] = "contHolliday.UNITY.delete";
        delete[33] = "contHolliday.MSCRUS.delete";
        delete[34] = "contType.UNITY.delete";
        delete[35] = "contType.MSCRUS.delete";
        delete[36] = "ediKonv.UNITY.delete";
        delete[37] = "ediKonv.MSCRUS.delete";
        delete[38] = "expVessels.UNITY.delete";
        delete[39] = "expVessels.MSCRUS.delete";
        delete[40] = "faktPost.UNITY.delete";
        delete[41] = "faktPost.MSCRUS.delete";
        delete[42] = "henvis.UNITY.delete";
        delete[43] = "henvis.MSCRUS.delete";
        delete[44] = "kunde.UNITY.delete";
        delete[45] = "kunde.MSCRUS.delete";
        delete[46] = "ordre.UNITY.delete";
        delete[47] = "ordre.MSCRUS.delete";
        delete[48] = "ordreLin.UNITY.delete";
        delete[49] = "ordreLin.MSCRUS.delete";
        delete[50] = "sag.UNITY.delete";
        delete[51] = "sag.MSCRUS.delete";
        delete[52] = "sagKurs.UNITY.delete";
        delete[53] = "sagKurs.MSCRUS.delete";
        delete[54] = "shipKurs.UNITY.delete";
        delete[55] = "shipKurs.MSCRUS.delete";
        delete[56] = "selskab.UNITY.delete";
        delete[57] = "selskab.MSCRUS.delete";
        delete[58] = "getCharges.UNITY.delete";
        delete[59] = "getCharges.MSCRUS.delete";

        getPropertiesFile();
        for (int i = 0; i < delete.length; i++) {
            String query = properties.getProperty(delete[i]);
            deleteTestRowFromSA(query, delete[i]);
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DiscardAgency.properties"))));
    }

    public void insertTestRowInSA(String query, String tableName) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(query);
        System.out.println("Insert test row in " + tableName + " complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }

    public void deleteTestRowFromSA(String delete, String tableName) throws SQLException {
        System.out.println("SQL for Delete from SA: " + delete);
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        stForSA.execute(delete);
        System.out.println("Delete test row from " + tableName + " complete!");
        db.closeConnecions(null, stForSA, connectionToSA);
    }



}



