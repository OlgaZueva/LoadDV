package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class validFromInSatTest {
    private DBHelper db = new DBHelper();

    @Test(enabled = true)
    public void ValidFromInCommonSatsTest() throws SQLException, IOException {
        String[] keys = new String[35];
        keys[0] = "sat.satAccountingTransaction";
        keys[1] = "sat.satBooking";
        //keys[2] = "sat.satBookingAuxReportingCustomer";//delete table
        keys[2] = "sat.satBookingCargo";
        keys[3] = "sat.satBookingCargoCharges";
        keys[4] = "sat.satBookingChargeLines";
        keys[5] = "sat.satBookingCharges";
        keys[6] = "sat.satBookingCustomers";
        keys[7] = "sat.satBookingHaulageDetails";
        keys[8] = "sat.satBookingManifestAdditionals";
        keys[9] = "sat.satBookingNonManifestedHaulage";
        keys[10] = "sat.satBookingOceanVessel";
        keys[11] = "sat.satCompany";
        //keys[18] = "sat.satCompanyAgentCode";
        keys[12] = "sat.satContainerLocation";
        keys[13] = "sat.satContainerMoves";
        keys[14] = "sat.satContainerType";
        //keys[22] = "sat.satContainerTypeAuxSpecEquip"; // delete table
        keys[15] = "sat.satControllingOffice";
        keys[16] = "sat.satControllingOfficeAuxLocation";
        keys[17] = "sat.satCorrectorRemarkTypes";
        keys[18] = "sat.satCountry";
        keys[19] = "sat.satCurrency";
        //keys[34] = "sat.satCustomerParty";
        keys[20] = "sat.satCustomers";
        keys[21] = "sat.satDailyRoe";
        keys[22] = "sat.satExportVessels";
        keys[23] = "sat.satFileLiner";
        keys[24] = "sat.satFileROE";
        keys[25] = "sat.satImsChargeLinesStatus";
        keys[26] = "sat.satInvoice";
        keys[27] = "sat.satInvoiceLines";
        keys[28] = "sat.satLocations";
        //keys[38] = "sat.satLocationsAuxPortsOverview";// delete table
        keys[29] = "sat.satPayments";
        keys[30] = "sat.satShipItConstants";
        keys[31] = "sat.satSpecialContractTypes";
        keys[32] = "sat.satSublocation";
        keys[33] = "sat.satTransportMode";
        keys[34] = "sat.satVesselRegistry";
        // sat.satOceanVesselStatus delete

        for (int i = 0; i < keys.length; i++) {
            String sql = "select distinct validFrom c from " + keys[i];
            String validDate = getDataFromDWH(sql);
            if (validDate.equals("No data found")) {
                System.err.println(sql + " [" + validDate + "]");
            } else
                System.out.println(sql + " [" + validDate + "]");

            //assertRowCount("2000-01-01 00:00:00.0", validDate);
        }
    }

    @Test(enabled = true)
    public void ValidFromInExcelSatsTest() throws SQLException, IOException {
        String[] sats = new String[3];
        // в satControllingOficeAuxLocation  поле validFrom заполняется по общему алгоритму, т.к. хаб этого EXCEL-файла не создается (используется hubControllinOffice).
        sats[0] = "sat.satBookingReportingCustomer";
        sats[1] = "sat.satContainerTypeSpecEquip";
        sats[2] = "sat.satLocationsPortsOverview";

        String[] excelTables = new String[3];
        excelTables[0] = "stg.EXCEL_CleanUp";
        excelTables[1] = "stg.EXCEL_CntrTypeSpecEquip";
        excelTables[2] = "stg.EXCEL_PortsOverview";
//Порядок перечисления таблиц в объявлении массивов имеет значение. Долны соответствовать по порядку
        for (int i = 0; i < sats.length; i++) {
            String sql = "select distinct validFrom c from " + sats[i];
            System.out.println("sql: " + sql);
            String validDateInSat = getDataFromDWH(sql);
            System.out.println("validDateInSat:" + validDateInSat);
            if (validDateInSat.equals("No data found")) {
                System.err.println(sql + " [" + validDateInSat + "]");
            } else {
                String sqlInSA = "select distinct valid_From c from " + excelTables[i];
                System.out.println("sqlInSA: " + sqlInSA);
                String validDateInEXCEL = getDataFromSA(sqlInSA);
                if (validDateInEXCEL.equals("No data found")) {
                    System.err.println(sqlInSA + " [" + validDateInEXCEL + "]");
                } else
                    System.out.println("validDateInEXCEL: " + validDateInEXCEL);
                assertRowCount(validDateInEXCEL, validDateInSat);
                System.out.println("------------------------");
            }
        }
    }


    @Test(enabled = true)
    public void ValidFromInDeletedSatsTest() throws SQLException, IOException {
        String[] keys = new String[6];
        keys[0] = "sat.satBookingEvents";
        keys[1] = "sat.satBookingManifest";
        keys[2] = "sat.satInvoicePosting";
        keys[3] = "sat.satBookingDTXFile";
        keys[4] = "sat.satBookingEMCRemarks";
        keys[5] = "sat.satBookingManifestedHaulage";

        for (int i = 0; i < keys.length; i++) {
            String sql = "select distinct validFrom c from " + keys[i];
            String validDate = getDataFromDWH(sql);
            if (validDate.equals("No data found")) {
                System.err.println(sql + " [" + validDate + "]");
            } else
                System.out.println(sql + " [" + validDate + "]");

            //assertRowCount("2000-01-01 00:00:00.000", validDate);
        }
    }


    public String getDataFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        String validDate = "No data found";
        while (rsFromDWH.next()) {
            validDate = rsFromDWH.getString("c");
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return validDate;
    }


    public String getDataFromSA(String hubSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, hubSQL);
        String validDate = "No data found";
        while (rsFromSA.next()) {
            validDate = rsFromSA.getString("c");
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return validDate;
    }

    public void assertRowCount(String etalon, String DateInDB) {
        assertThat(DateInDB, equalTo(etalon));
    }
}
