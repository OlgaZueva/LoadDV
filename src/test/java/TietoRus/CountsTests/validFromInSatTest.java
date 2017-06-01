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
    public void ValidFromTest() throws SQLException, IOException {
        String[] keys = new String[45];
        keys[0] = "sat.satAccountingTransaction";
        keys[1] = "sat.satBooking";
        //keys[2] = "sat.satBookingAuxReportingCustomer";//delete table
        keys[2] = "sat.satBookingCargo";
        keys[3] = "sat.satBookingCargoCharges";
        keys[4] = "sat.satBookingChargeLines";
        keys[5] = "sat.satBookingCharges";
        keys[6] = "sat.satBookingCustomers";
        keys[7] = "sat.satBookingDTXFile";
        keys[8] = "sat.satBookingEMCRemarks";
        keys[9] = "sat.satBookingEvents";
        keys[10] = "sat.satBookingHaulageDetails";
        keys[11] = "sat.satBookingManifest";
        keys[12] = "sat.satBookingManifestAdditionals";
        keys[13] = "sat.satBookingManifestedHaulage";
        keys[14] = "sat.satBookingNonManifestedHaulage";
        keys[15] = "sat.satBookingOceanVessel";
        keys[16] = "sat.satBookingReportingCustomer";
        keys[17] = "sat.satCompany";
        //keys[18] = "sat.satCompanyAgentCode";
        keys[18] = "sat.satContainerLocation";
        keys[19] = "sat.satContainerMoves";
        keys[20] = "sat.satContainerType";
        //keys[22] = "sat.satContainerTypeAuxSpecEquip"; // delete table
        keys[21] = "sat.satContainerTypeSpecEquip";
        keys[22] = "sat.satControllingOffice";
        keys[23] = "sat.satControllingOfficeAuxLocation";
        keys[24] = "sat.satCorrectorRemarkTypes";
        keys[25] = "sat.satCountry";
        keys[26] = "sat.satCurrency";
        //keys[34] = "sat.satCustomerParty";
        keys[27] = "sat.satCustomers";
        keys[28] = "sat.satDailyRoe";
        keys[29] = "sat.satExportVessels";
        keys[30] = "sat.satFileLiner";
        keys[31] = "sat.satFileROE";
        keys[32] = "sat.satImsChargeLinesStatus";
        keys[33] = "sat.satInvoice";
        keys[34] = "sat.satInvoiceLines";
        keys[35] = "sat.satInvoicePosting";
        keys[36] = "sat.satLocations";
        //keys[38] = "sat.satLocationsAuxPortsOverview";// delete table
        keys[37] = "sat.satLocationsPortsOverview";
        keys[38] = "sat.satOceanVesselStatus";
        keys[39] = "sat.satPayments";
        keys[40] = "sat.satShipItConstants";
        keys[41] = "sat.satSpecialContractTypes";
        keys[42] = "sat.satSublocation";
        keys[43] = "sat.satTransportMode";
        keys[44] = "sat.satVesselRegistry";

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

    public void assertRowCount(String etalon, String DateInDB) {
        assertThat(DateInDB, equalTo(etalon));
    }
}
