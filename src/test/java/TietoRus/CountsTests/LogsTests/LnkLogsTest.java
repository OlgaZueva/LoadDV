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


public class LnkLogsTest {
    private DBHelper db = new DBHelper();
    private Properties properties = new Properties();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    /*
Класс для проверки механизма логгирования линков при условии что первый хаб не найден.
ВНИМАНИЕ! Тест НЕ ПРЕДНАЗНАЧЕН для запуска на "боевой" схеме заказчика -  только локальная тестовая схема на стенде Тието.
Сценарий использования:
1. Зачистить DWH (не должно существовать хабов)
2. Запустить тест InsertTestDataTest (в классе InsAndDelTestData)
3. Запустить загрузку линков (всех)
4. Запустить тест
5. В консоли проследить, чтобы не было текста, выделенного красным (тест выдает такие записи, если запись для какого то линка не создана в etl.errLogLnkDataVault
или значение какого-либо из полей ключа хаба в таблице etl.errLogLnkDataVault is null)
6. Для зачистки тестовых данных запустить тест DeleteTestDataTest (в классе InsAndDelTestData)
    */

    @Test(enabled = true)
    public void LinksLogsTestDataTest() throws SQLException, IOException {
        String[] select = new String[15];
        /*
        select[0] = "book.lnkBookingBooking.select";
        select[1] = "book.lnkBookingBookingChargeLines.select";
        select[2] = "book.lnkBookingBookingHaulageDetails.select";
        select[3] = "book.lnkBookingBookingReportingCustomer.select";
        select[4] = "book.lnkBookingCompany.select";
        select[5] = "book.lnkBookingContainerLocation.select";
        select[6] = "book.lnkBookingControllingOffice.select";
        select[7] = "book.lnkBookingCrossBookingType.select";
        select[8] = "book.lnkBookingCustomers.select";
        select[9] = "book.lnkBookingFileLiner.select";
        select[10] = "book.lnkBookingImportExport.select";
        select[11] = "book.lnkBookingPPCCEE.select";
        select[12] = "book.lnkBookingLocations.select";
        select[13] = "bookDryPort.lnkBookingLocations.select";
        select[14] = "abPost.lnkPaymentsInvoicePosting.select";
        select[15] = "abPost.lnkPaymentsCompany.select";
        select[16] = "adresse.lnkCustomersCountry.select";
        select[17] = "adresse.lnkCustomersCompany.select";
        select[18] = "adresse.lnkCustomersLocations.select";
        select[19] = "kunde.lnkCustomersLocations.select";
        select[20] = "bogfTrans.lnkAccountingTransactionBooking.select";
        select[21] = "bogfTrans.lnkAccountingTransactionCurrency.select";
        select[22] = "bogfTrans.lnkAccountingTransactionCompany.select";
        select[23] = "bookDetails.lnkBookingBookingManifestAdditionals.select";
        select[24] = "bookDetails.lnkBookingManifestAdditionalsCompany.select";
        select[25] = "bookDetailsMof.lnkBookingBookingManifestedHaulage.select";
        select[26] = "bookDetailsMof.lnkBookingManifestedHaulageTransportMode.select";
        select[27] = "bookDetailsMof.lnkBookingManifestedHaulageCompany.select";
        select[28] = "bookEvent.lnkBookingBookingEvents.select";
        select[29] = "bookEvent.lnkBookingEventsCompany.select";
        select[30] = "bookFak.lnkBookingBookingCharges.select";
        select[31] = "bookFak.lnkBookingChargesCompany.select";
        select[32] = "bookGods.lnkBookingBookingCargo.select";
        select[33] = "bookGods.lnkBookingCargoBookingHaulageDetails.select";
        select[34] = "bookGods.lnkBookingCargoContainerType.select";
        select[35] = "bookGods.lnkBookingCargoFullEmpty.select";
        select[36] = "bookGods.lnkBookingCargoLocations.select";
        select[37] = "bookGods.lnkBookingCargoCompany.select";
        select[38] = "bookKor.lnkBookingHaulageDetailsCustomers.select";
        select[39] = "bookKor.lnkBookingHaulageDetailsCompany.select";
        select[40] = "bookLin.lnkBookingChargeLinesCurrency.select";
        select[41] = "bookLin.lnkBookingChargesBookingChargeLines.select";
        select[42] = "bookLin.lnkBookingChargeLinesCompany.select";
        select[43] = "bookManifest.lnkBookingBookingManifest.select";
        select[44] = "bookManifest.lnkBookingBookingManifestShadowCopy.select";
        select[45] = "bookManifest.lnkBookingManifestBookingDTXFile.select";
        select[46] = "bookManifest.lnkBookingManifestCompany.select";
        select[47] = "bookMftFile.lnkBookingDTXFileCompany.select";
        select[48] = "bookMftRemarks.lnkBookingEMCRemarksBookingManifest.select";
        select[49] = "bookMftRemarks.lnkBookingEMCRemarksCompany.select";
        select[50] = "bookVessel.lnkBookingBookingOceanVessel.select";
        select[51] = "bookVessel.lnkBookingOceanVesselExportVessels.select";
        select[52] = "bookVessel.lnkBookingOceanVesselOceanVesselStatus.select";
        select[53] = "bookVessel.lnkBookingOceanVesselCompany.select";
        select[54] = "contHolliday.lnkWeekendsHolidaysCompany.select";
        select[55] = "contRep.lnkContainerMovesBooking.select";
        select[56] = "contRep.lnkContainerMovesContainerLocation.select";
        select[57] = "contRep.lnkContainerMovesCompany.select";
        select[58] = "controllingOfficeLocationCode.lnkControllingOfficeLocations.select";
        select[59] = "contType.lnkContainerTypeContainerTypeSpecEquip.select";
        select[60] = "contType.lnkContainerTypeIsoCode.select";
        select[61] = "contType.lnkContainerTypeCompany.select";
        select[62] = "ediKonv.lnkCorrectorRemarkTypesCompany.select";
        select[63] = "ediKonv.lnkImsChargeLinesCompany.select";
        select[64] = "ediKonv.lnkOceanVesselServiceCompany.select";
        select[65] = "ediKonv.lnkSpecialContractTypesCompany.select";
        select[66] = "ediKonv.lnkSublocationCompany.select";
        select[67] = "ediKonv.lnkTransportModeCompany.select";
        select[68] = "eExpVessels.lnkExportVesselsLocations.select";
        select[69] = "eExpVessels.lnkExportVesselsOceanVesselService.select";
        select[70] = "eExpVessels.lnkExportVesselsVesselRegistry.select";
        select[71] = "eExpVessels.lnkExportVesselsCompany.select";
        select[72] = "fakPost.lnkInvoicePostingAccountingTransaction.select";
        select[73] = "fakPost.lnkInvoicePostingCurrency.select";
        select[74] = "fakPost.lnkInvoicePostingCompany.select";
        select[75] = "getCharges.lnkBookingCargoCharges.select";
        select[76] = "henvis.lnkLocationsCountry.select";
        select[77] = "henvis.lnkLocationsLocationsPortsOverview.select";
        select[78] = "henvis.lnkContainerLocationCompany.select";
        select[79] = "henvis.lnkCountryCompany.select";
        select[80] = "henvis.lnkCurrencyCompany.select";
        select[81] = "henvis.lnkLocationsCompany.select";
        select[82] = "henvis.lnkVesselRegistryCompany.select";
        select[83] = "ordre.lnkInvoiceBooking.select";
        select[84] = "ordre.lnkInvoiceBookingCharges.select";
        select[85] = "ordre.lnkInvoiceCurrency.select";
        select[86] = "ordre.lnkInvoiceCustomers.select";
        select[87] = "ordre.lnkInvoiceInvoice.select";
        select[88] = "ordre.lnkInvoiceInvoiceLines.select";
        select[89] = "ordre.lnkInvoiceInvoicePosting.select";
        select[90] = "ordre.lnkInvoiceCompany.select";
        select[91] = "ordreLin.lnkInvoiceLinesCurrency.select";
        select[92] = "ordreLin.lnkInvoiceLinesCompany.select";
        select[93] = "sag.lnkFileLinerFileROE.select";
        select[94] = "sag.lnkFileLinerLocations.select";
        select[95] = "sag.lnkFileLinerCompany.select";
        select[96] = "sagKurs.lnkFileROECompany.select";
        select[97] = "shipKurs.lnkDailyRoeCompany.select";
        select[98] = "utsConstants.lnkShipItConstantsCompany.select";
*/
        select[0] = "bookBemInternal.lnkBookingInternalRemarksCompany.select";
        select[1] = "kontor.lnkBranchCompany.select";
        select[2] = "commodity.lnkCommodityHsCodesCompany.select";
        select[3] = "contRules.lnkContainerDemurrageRulesCompany.select";
        select[4] = "contBev.lnkContainerMoveTypesCompany.select";
        select[5] = "ctsContEvent.lnkContainerStatusEventsGVACompany.select";
        select[6] = "ctsContEvent.lnkContainerStatusEventsGVAFullEmpty.select";
        select[7] = "ctsContEvent.lnkContainerStatusEventsGVALocations.select";
        select[8] = "vgm.lnkContainerVgmCompany.select";
        select[9] = "vgm.lnkContainerVgmVgmCode.select";
       // select[10] = "demurrage.lnkDemurrageStorageCompany.select";
        //select[11] = "demurrage.lnkDemurrageStorageContainerDemurrageRules.select";
        //select[12] = "demurrage.lnkDemurrageStorageContainerMoveTypes.select";
        //select[13] = "demurrage.lnkDemurrageStorageInvoice.select";
        //select[14] = "demurrage.lnkDemurrageStorageCurrency.select";
        select[10] = "service.lnkServiceCompany.select";
        select[11] = "henvis.lnkSourceSystemUsersCompany.select";
        select[12] = "ediKonv.lnkTerminalsCompany.select";
        select[13] = "vgmCodes.lnkVgmCodeCompany.select";
        select[14] = "stedBar.lnkServiceNamesCompany.select";

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
