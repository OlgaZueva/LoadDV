package TietoRus.CountsTests.LogsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Класс для добавления и удаления суррогатных данных для проверки логгирования. Тесты в нем доожны выполняться по-отдельности.
Сценарий использования нижеприведеных тестов:
1. Запустить тест InsertTestDataTest
2. запустить загрузку проверямых пакетов
3. Запустить тест (в отдельном классе реализован), проверяющий работу пакетов
4. Запустить тест DeleteTestDataTest для зачистки тестовых данных
 */

public class InsAndDelTestData {
    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();


    @Test(enabled = true)
    public void InsertTestDataTest() throws SQLException, IOException {
        String[] inserts = new String[48];
        inserts[0] = "cleanUp.insert";
        inserts[1] = "cntrTypeSpecEquip.insert";
        inserts[2] = "controllingOffice.insert";
        inserts[3] = "portsOverview.insert";
        inserts[4] = "abPost.insert";
        inserts[5] = "adresse.insert";
        inserts[6] = "bogfTrans.insert";
        inserts[7] = "book.insert";
        inserts[8] = "bookDetails.insert";
        inserts[9] = "bookDetailsMof.insert";
        inserts[10] = "bookDryPort.insert";
        inserts[11] = "bookEvent.insert";
        inserts[12] = "bookFak.insert";
        inserts[13] = "bookGods.insert";
        inserts[14] = "bookKor.insert";
        inserts[15] = "bookLin.insert";
        inserts[16] = "bookManifests.insert";
        inserts[17] = "bookMftFile.insert";
        inserts[18] = "bookMftRemarks.insert";
        inserts[19] = "bookVessel.insert";
        inserts[20] = "contHolliday.insert";
        inserts[21] = "contRep.insert";
        inserts[22] = "contType.insert";
        inserts[23] = "controlOffice.insert";
        inserts[24] = "ediKonv_ImsChargeLines.insert";
        inserts[25] = "ediKonv_OceanVesselService.insert";
        inserts[26] = "ediKonv_OceanVesselStatus.insert";
        inserts[27] = "ediKonv_TransportMode.insert";
        inserts[28] = "ediKonv_CorrectorRemarkTypes.insert";
        inserts[29] = "ediKonv_Sublocation.insert";
        inserts[30] = "ediKonv_SpecialContractTypes.insert";
        inserts[31] = "expVessels.insert";
        inserts[32] = "faktPost.insert";
        inserts[33] = "getCharges.insert";
        inserts[34] = "henvis_ContainerLocation.insert";
        inserts[35] = "henvis_Country.insert";
        inserts[36] = "henvis_Locations.insert";
        inserts[37] = "henvis_VesselRegistry.insert";
        inserts[38] = "henvis_Currency.insert";
        inserts[39] = "kunde.insert";
        inserts[40] = "ordre.insert";
        inserts[41] = "ordreLin.insert";
        inserts[42] = "sag.insert";
        inserts[43] = "sagKurs.insert";
        inserts[44] = "selskab.insert";
        inserts[45] = "shipKurs.insert";
        inserts[46] = "utsConstants.insert";
        inserts[47] = "ediKonv_CompanyAgentCode.insert";

        getPropertiesFile();
        for (int i = 0; i < inserts.length; i++) {
            String query = properties.getProperty(inserts[i]);
            insertTestRowInSA(query, inserts[i]);
        }
    }

    @Test(enabled = true)
    public void DeleteTestDataTest() throws SQLException, IOException {
        String[] delete = new String[48];
        delete[0] = "cleanUp.delete";
        delete[1] = "cntrTypeSpecEquip.delete";
        delete[2] = "controllingOffice.delete";
        delete[3] = "portsOverview.delete";
        delete[4] = "abPost.delete";
        delete[5] = "adresse.delete";
        delete[6] = "bogfTrans.delete";
        delete[7] = "book.delete";
        delete[8] = "bookDetails.delete";
        delete[9] = "bookDetailsMof.delete";
        delete[10] = "bookDryPort.delete";
        delete[11] = "bookEvent.delete";
        delete[12] = "bookFak.delete";
        delete[13] = "bookGods.delete";
        delete[14] = "bookKor.delete";
        delete[15] = "bookLin.delete";
        delete[16] = "bookManifests.delete";
        delete[17] = "bookMftFile.delete";
        delete[18] = "bookMftRemarks.delete";
        delete[19] = "bookVessel.delete";
        delete[20] = "contHolliday.delete";
        delete[21] = "contRep.delete";
        delete[22] = "contType.delete";
        delete[23] = "controlOffice.delete";
        delete[24] = "ediKonv_ImsChargeLines.delete";
        delete[25] = "ediKonv_OceanVesselService.delete";
        delete[26] = "ediKonv_OceanVesselStatus.delete";
        delete[27] = "ediKonv_TransportMode.delete";
        delete[28] = "ediKonv_CorrectorRemarkTypes.delete";
        delete[29] = "ediKonv_Sublocation.delete";
        delete[30] = "ediKonv_SpecialContractTypes.delete";
        delete[31] = "expVessels.delete";
        delete[32] = "faktPost.delete";
        delete[33] = "getCharges.delete";
        delete[34] = "henvis_ContainerLocation.delete";
        delete[35] = "henvis_Country.delete";
        delete[36] = "henvis_Locations.delete";
        delete[37] = "henvis_VesselRegistry.delete";
        delete[38] = "henvis_Currency.delete";
        delete[39] = "kunde.delete";
        delete[40] = "ordre.delete";
        delete[41] = "ordreLin.delete";
        delete[42] = "sag.delete";
        delete[43] = "sagKurs.delete";
        delete[44] = "selskab.delete";
        delete[45] = "shipKurs.delete";
        delete[46] = "utsConstants.delete";
        delete[47] = "ediKonv_CompanyAgentCode.delete";


        getPropertiesFile();
        for (int i = 0; i < delete.length; i++) {
            String query = properties.getProperty(delete[i]);
            deleteTestRowFromSA(query, delete[i]);
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/LogsTestDataQuery.properties"))));
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



