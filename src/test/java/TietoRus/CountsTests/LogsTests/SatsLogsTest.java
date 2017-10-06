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
Класс для проверки механизма логгирования сатов при условии что хаб не найден.

ВНИМАНИЕ! Тест НЕ ПРЕДНАЗНАЧЕН для запуска на "боевой" схеме заказчика -  только локальная тестовая схема на стенде Тието.

Сценарий использования:
1. Зачистить DWH (не должно существовать хабов)
2. Запустить тест InsertTestDataTest (в классе InsAndDelTestData)
3. Запустить загрузку сатов (всех)
4. Запустить тест
 Для каждой сущности ( = хабу) должно быть создано по 2 записи - для sat'а и  satStatus'а, исключения:
  1. Три EXCEL-таблицы - по одной записи (SatStatus'ов не создается), а для EXCEL_ControllingOfficeLocationCode две записи (для srcSystemId in (1,2)), т.к. из-за srcSystemId во вьюхе записи множатся
  2. Для book - три записи (satBooking, satBookingStatus, satBookingCustomers (дополнительный сат по буку, сатСтатуса нет для него)
  3. bookDryPort - одна запись (для satBookingNonManifestedHaulage) -  satStatus'а нет т.к. хаб создается по book
  4. contHolliday, ediKonv_ImsChargeLines, ediKonv_OceanVesselService,  ediKonv_OceanVesselStatus и ediKonv_CompanyAgentCode -  по одной записи -  нет sat'ов
  5 Для Kunde и Adresse не посчитать как для остальных - хабы и с саты для них создаются отдельным пакетом и все сразу,
   т.е. сымулировать ситуацию попытки загрузки сатов без хабов нереально
  6. Для satCompanyDemurrageEurDefault ключом является только поле SELSKAB (хабом для записей по условию сата является hubCompany)
 7. Для containerLocationCloc и containerLocationLoc ключом являются FRA и SELSKAB (хабом для записей по условию сата является hubContainerLocation)

   Вся информация выводится на консоль. Проследить, чтобы не было текста, выделенного красным (тест выдает такие записи, если запись для какого то сата  не создана
   в etl.errLogSatDataVault или значение какого-либо из полей ключа хаба в таблице etl.errLogSatDataVault is null)
5. Для зачистки тестовых данных запустить тест DeleteTestDataTest (в классе InsAndDelTestData)

*/
    @Test(enabled = true)
    public void SatsLogsTestDataTest() throws SQLException, IOException {
        String[] select = new String[68];

        select[0] = "cleanUp.sat.select";
        select[1] = "cntrTypeSpecEquip.sat.select";
        select[2] = "ControllingOfficeLocationCode.sat.select";
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
        select[46] = "ediKonv_CompanyAgentCode.sat.select";
        select[47] = "bookBemInternal.sat.select";
        select[48] = "kontor.sat.select";
        select[49] = "commodity.sat.select";
        select[50] = "contRules.sat.select";
        select[51] = "contBev.sat.select";
        select[52] = "ctsContEvent.sat.select";
        select[53] = "vgm.sat.select";
        select[54] = "demurrage.sat.select";
        select[55] = "service.sat.select";
        select[56] = "henvis_SourceSystemUsers.sat.select";
        select[57] = "ediKonv_Terminals.sat.select";
        select[58] = "ediKonv_CompanyDemurrageEurDefault.sat.select";
        select[59] = "ediKonv_ContainerLocationCloc.sat.select";
        select[60] = "ediKonv_ContainerLocationLoc.sat.select";
        select[61] = "ediKonv_WorkingTime.sat.select";
        select[62] = "vgmCodes.sat.select";
        select[63] = "contMaster.sat.select";
        select[64] = "stedBar.sat.select";
        select[65] = "gvaDischargeList.sat.select";
        select[66] = "trpCosts.sat.select";
        select[67] = "femeTrpCosts.sat.select";


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
