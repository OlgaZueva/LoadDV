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


public class HubsLogsTest {
    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();
    /*
     Для каждой SA-таблицы должно быть создано по одной записи, исключение hubBooking, записи в котором создаются из двух таблиц (BOOK и BOOK_DRY_PORT)
     10.07.2017 -  Предпринята попытка проверить логирование хабов путем рассинхронизации размерностей одного из ключей (SELSKAB где это применимо) в SA и DWH
     (В SA увеличен (со smallint на int) размер поля ключа -> записано значение -> совершена попытка загрузки хабов).
Сие привело к тому, что пакет "понимает" проблему на уровне запуска и завершает свою работу с результатом fail. Что принято достаточным.
Т.о. тест на суррогатных данных не провести.
    */
    @Test(enabled = true)
    public void HubsLogsTestDataTest() throws SQLException, IOException {
        String[] select = new String[45];
        select[0] = "cleanUp.hub.select";
        select[1] = "cntrTypeSpecEquip.hub.select";
        select[2] = "portsOverview.hub.select";
        select[3] = "abPost.hub.select";
        select[4] = "adresse.hub.select";
        select[5] = "bogfTrans.hub.select";
        select[6] = "book.hub.select";
        select[7] = "bookDetails.hub.select";
        select[8] = "bookDetailsMof.hub.select";
        select[9] = "bookDryPort.hub.select";
        select[10] = "bookEvent.hub.select";
        select[11] = "bookFak.hub.select";
        select[12] = "bookGods.hub.select";
        select[13] = "bookKor.hub.select";
        select[14] = "bookLin.hub.select";
        select[15] = "bookManifests.hub.select";
        select[16] = "bookMftFile.hub.select";
        select[17] = "bookMftRemarks.hub.select";
        select[18] = "bookVessel.hub.select";
        select[19] = "contHolliday.hub.select";
        select[20] = "contRep.hub.select";
        select[21] = "contType.hub.select";
        select[22] = "controlOffice.hub.select";
        select[23] = "ediKonv_ImsChargeLines.hub.select";
        select[24] = "ediKonv_OceanVesselService.hub.select";
        select[25] = "ediKonv_OceanVesselStatus.hub.select";
        select[26] = "ediKonv_TransportMode.hub.select";
        select[27] = "ediKonv_CorrectorRemarkTypes.hub.select";
        select[28] = "ediKonv_Sublocation.hub.select";
        select[29] = "ediKonv_SpecialContractTypes.hub.select";
        select[30] = "expVessels.hub.select";
        select[31] = "faktPost.hub.select";
        select[32] = "henvis_ContainerLocation.hub.select";
        select[33] = "henvis_Country.hub.select";
        select[34] = "henvis_Locations.hub.select";
        select[35] = "henvis_VesselRegistry.hub.select";
        select[36] = "henvis_Currency.hub.select";
        select[37] = "kunde.hub.select";
        select[38] = "ordre.hub.select";
        select[39] = "ordreLin.hub.select";
        select[40] = "sag.hub.select";
        select[41] = "sagKurs.hub.select";
        select[42] = "selskab.hub.select";
        select[43] = "shipKurs.hub.select";
        select[44] = "utsConstants.hub.select";


        getPropertiesFile();
        for (int i = 0; i < select.length; i++) {
            String query = properties.getProperty(select[i]);
            System.out.println("-------");
            selectTestRowFromDWH(query, select[i]);
            mapForSource.clear();
        }
    }

    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/LogsTestDataQuery.properties"))));
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
