package TietoRus.CountsTests.LogsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class SatsLogsTest {
    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();
/*
 Для каждой сущности ( = хабу) должно быть создано по 2 записи - для sat'а и  satStatus'а, исключением являются EXCEL-таблицы или таблицы /сущности по которым SatStatus'ов 
не создается и таблицы (или сущности), по которым нет сатов, но есть сатСтатусы).
*/
    @Test(enabled = true)
    public void SatsLogsTestDataTest() throws SQLException, IOException {
        String[] select = new String[46];
        select[0] = "cleanUp.sat.select";
        select[1] = "cntrTypeSpecEquip.sat.select";
        select[2] = "controllingOffice.sat.select";
        select[3] = "portsOverview.sat.select";
        select[4] = "abPost.sat.select";
        select[5] = "adresse.sat.select";
        select[6] = "bogfTrans.sat.select";
        select[7] = "book.sat.select";
        select[8] = "bookDetails.sat.select";
        select[9] = "bookDetailsMof.sat.select";
        select[10] = "bookDryPort.sat.select";
        select[11] = "bookEvent.sat.select";
        select[12] = "bookFak.sat.select";
        select[13] = "bookGods.sat.select";
        select[14] = "bookKor.sat.select";
        select[15] = "bookLin.sat.select";
        select[16] = "bookManifests.sat.select";
        select[17] = "bookMftFile.sat.select";
        select[18] = "bookMftRemarks.sat.select";
        select[19] = "bookVessel.sat.select";
        select[20] = "contHolliday.sat.select";
        select[21] = "contRep.sat.select";
        select[22] = "contType.sat.select";
        select[23] = "controlOffice.sat.select";
        select[24] = "ediKonv_ImsChargeLines.sat.select";
        select[25] = "ediKonv_OceanVesselService.sat.select";
        select[26] = "ediKonv_OceanVesselStatus.sat.select";
        select[27] = "ediKonv_TransportMode.sat.select";
        select[28] = "ediKonv_CorrectorRemarkTypes.sat.select";
        select[29] = "ediKonv_Sublocation.sat.select";
        select[30] = "ediKonv_SpecialContractTypes.sat.select";
        select[31] = "expVessels.sat.select";
        select[32] = "faktPost.sat.select";
        select[33] = "henvis_ContainerLocation.sat.select";
        select[34] = "henvis_Country.sat.select";
        select[35] = "henvis_Locations.sat.select";
        select[36] = "henvis_VesselRegistry.sat.select";
        select[37] = "henvis_Currency.sat.select";
        select[38] = "kunde.sat.select";
        select[39] = "ordre.sat.select";
        select[40] = "ordreLin.sat.select";
        select[41] = "sag.sat.select";
        select[42] = "sagKurs.sat.select";
        select[43] = "selskab.sat.select";
        select[44] = "shipKurs.sat.select";
        select[45] = "utsConstants.sat.select";


        getPropertiesFile();
        for (int i = 0; i < select.length; i++) {
            String query = properties.getProperty(select[i]);
            System.out.println("-------");
            selectTestRowFromDWH(query, select[i]);
            mapForSource.clear();
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/testDataQuery.properties"))));
    }

    public Map<String, Object> selectTestRowFromDWH(String query, String s) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, query);
        if (rsFromDWH.next()) {
            do {
                for (int k = 1; k <= rsFromDWH.getMetaData().getColumnCount(); k++) {
                    mapForSource.put(rsFromDWH.getMetaData().getColumnName(k), rsFromDWH.getObject(k));
                    if (rsFromDWH.wasNull()) {
                        System.err.println("In [" + s + "] column " + rsFromDWH.getMetaData().getColumnName(k) + " is null!");
                    }
                }
                System.out.println(s + ": " + mapForSource);
            }
            while (rsFromDWH.next());
        } else {
            System.err.println("No data found for " + s + "!");
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return mapForSource;

    }
}
