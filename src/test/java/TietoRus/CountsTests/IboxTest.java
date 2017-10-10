
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Тест для проверки механизма загрузки IBox
 */

public class IboxTest {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();
    private Map<String, Object> mapForSource = new HashMap<String, Object>();

    @Test(enabled = true)
    public void iBox() throws SQLException, IOException {
        getPropertiesFile();
        String truncate = (properties.getProperty("iBox.linkTable.truncate"));
        executeInDWH(truncate);

        String nameDwhIdHubPPCCEE = "dwhIdHubPPCCEE";
        String nameDwhIdHubFullEmpty = "dwhIdHubFullEmpty";
        String nameDwhIdHubLocations = "dwhIdHubLocations";
        String nameDwhIdHubLocationsPOL = "dwhIdHubLocationsPOL";
        String nameDwhIdHubLocationsPOD = "dwhIdHubLocationsPOD";
        String nameDwhIdHubLocationsPORT_TRANS = "dwhIdHubLocationsPORT_TRANS";
        String nameDwhIdHubLocationsPORT_TRANS2 = "dwhIdHubLocationsPORT_TRANS2";
        String nameDwhIdHubLocationsPLR = "dwhIdHubLocationsPLR";
        String nameDwhIdHubLocationsPLD = "dwhIdHubLocationsPLD";
        String nameDwhIdHubLocationsOCEAN_TS_PORT = "dwhIdHubLocationsOCEAN_TS_PORT";
        String nameDwhIdHubCustomers = "hub.dwhIdHubCustomers";
        String nameForMapDwhIdHubCustomers = "dwhIdHubCustomers";
        String nameDwhIdHubCustomersBOOK_PARTY = "dwhIdHubCustomersBOOK_PARTY";
        String nameDwhIdHubCustomersSHIPPER = "dwhIdHubCustomersSHIPPER";
        String nameDwhIdHubCustomersNOTIFY = "dwhIdHubCustomersNOTYFY";
        String nameDwhIdHubCustomersCONSIGNEE = "dwhIdHubCustomersCONSIGNEE";
        String nameDwhIdHubCustomersFORWARDER = "dwhIdHubCustomersFORWARDER";
        String nameDwhIdHubCustomersFREIGHTPAYER = "dwhIdHubCustomersFRAIGHT_PAYER";
        String nameDwhIdHubCustomersSTAT_CUSTOMER = "dwhIdHubCustomersSTAT_CUSTOMER";
        String nameDwhIdHubCONTAINER_TYPE = "dwhIdHubCONTAINER_TYPE";
        String nameDwhIdHubContainerType = "dwhIdHubContainerType";
        String nameDwhIdHubIMPORT_EXPORT = "dwhIdHubIMPORT_EXPORT";
        String nameDwhIdHubImportExport = "dwhIdHubImportExport";
        String nameDwhIdHubSPECIAL_CONTRACT_TYPES = "dwhIdHubSPECIAL_CONTRACT_TYPES";
        String nameDwhIdHubSpecialContractType = "hub.dwhIdHubSpecialContractTypes";
        String nameDwhIdHubForMapSpecialContractType = "dwhIdHubSpecialContractTypes";
        String nameDwhIdHubOCEAN_VESSEL_STATUS = "dwhIdHubOCEAN_VESSEL_STATUS";
        String nameDwhIdHubOceanVesselStatus = "dwhIdHubOceanVesselStatus";
        String nameDwhIdHubCROSS_BOOKING_TYPE = "dwhIdHubCROSS_BOOKING_TYPE";
        String nameDwhIdHubCrossBookingType = "dwhIdHubCrossBookingType";


        int accesCompanyId = Integer.parseInt((properties.getProperty("iBox.accesCompanyId.const")));

        Integer dwhIdHubPPCCEE;
        Integer dwhIdHubFullEmpty;
        Integer dwhIdHubLocations;
        Integer dwhIdHubCustomers;
        Integer dwhIdHubContainerType;
        Integer dwhIdHubImportExport;
        Integer dwhIdHubSpecialContractTypes;
        Integer dwhIdHubOceanVesselStatus;
        Integer dwhIdHubCrossBookingType;


        String sql = (properties.getProperty("iBox.allData.select"));
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        while (rsFromDWH.next()) {
            mapForSource.put("dwhIdHubIboxData", rsFromDWH.getInt("dwhIdHubIboxData"));

            if (rsFromDWH.getString("PAYM_TERMS") == (null)) {
                dwhIdHubPPCCEE = -1;
            } else {
                String sqlDwhIdHubPPCCEE = ("select " + nameDwhIdHubPPCCEE + " " + properties.getProperty("iBox.hubPPCCEE.select")
                        + rsFromDWH.getString("PAYM_TERMS") + "\'");
                dwhIdHubPPCCEE = getDataFromHub(sqlDwhIdHubPPCCEE, nameDwhIdHubPPCCEE);
            }
            mapForSource.put(nameDwhIdHubPPCCEE, dwhIdHubPPCCEE);

            if (rsFromDWH.getString("FULL_EMPTY") == (null)) {
                dwhIdHubFullEmpty = -1;
            } else {
                String sqlDwhIdHubFullEmpty = ("select " + nameDwhIdHubFullEmpty + " " + properties.getProperty("iBox.hubFullEmpty.select")
                        + rsFromDWH.getString("FULL_EMPTY") + "\'");
                dwhIdHubFullEmpty = getDataFromHub(sqlDwhIdHubFullEmpty, nameDwhIdHubFullEmpty);
            }
            mapForSource.put(nameDwhIdHubFullEmpty, dwhIdHubFullEmpty);

//---------------------------
            if (rsFromDWH.getString("PORT_LOAD_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPOL = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("PORT_LOAD_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPOL, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPOL, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("PORT_DISCH_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPOD = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("PORT_DISCH_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPOD, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPOD, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("TS_PORT1_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPORT_TRANS = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("TS_PORT1_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPORT_TRANS, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPORT_TRANS, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("TS_PORT2_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPORT_TRANS2 = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("TS_PORT2_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPORT_TRANS2, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPORT_TRANS2, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("PLR_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPLR = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("PLR_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPLR, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPLR, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("PLACE_OF_DELIVERY") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsPLD = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("PLACE_OF_DELIVERY") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsPLD, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsPLD, dwhIdHubLocations);
//--
            if (rsFromDWH.getString("OCEAN_TS_PORT_CODE") == (null)) {
                dwhIdHubLocations = -1;
            } else {
                String sqlDwhIdHubLocationsOCEAN_TS_PORT = ("select " + nameDwhIdHubLocations + " " + properties.getProperty("iBox.hubLocations.select")
                        + rsFromDWH.getString("OCEAN_TS_PORT_CODE") + "\'" + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubLocations = getDataFromHub(sqlDwhIdHubLocationsOCEAN_TS_PORT, nameDwhIdHubLocations);
            }
            mapForSource.put(nameDwhIdHubLocationsOCEAN_TS_PORT, dwhIdHubLocations);
//---------------------------
            if (rsFromDWH.getString("BOOK_PARTY") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersBOOK_PARTY = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("BOOK_PARTY").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersBOOK_PARTY, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersBOOK_PARTY, dwhIdHubCustomers);
//--
            if (rsFromDWH.getString("LQ_SHIPPER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersSHIPPER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("LQ_SHIPPER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersSHIPPER, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersSHIPPER, dwhIdHubCustomers);
//--
            if (rsFromDWH.getString("LQ_SLUTSPED") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersNOTIFY = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("LQ_SLUTSPED").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersNOTIFY, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersNOTIFY, dwhIdHubCustomers);
//--
            if (rsFromDWH.getString("LQ_CONSIGNEE") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                //String qwe = (properties.getProperty("insert.testTable") + (mapForSource.get("dwhIdHubCustomers")) + ",'" + originalCustomerName.replace("'", "''") + "')");
                String sqlDwhIdHubCustomersCONSIGNEE = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("LQ_CONSIGNEE").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersCONSIGNEE, nameForMapDwhIdHubCustomers);

            }
            mapForSource.put(nameDwhIdHubCustomersCONSIGNEE, dwhIdHubCustomers);
            //--
            if (rsFromDWH.getString("LQ_FORWARDER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersFORWARDER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("LQ_FORWARDER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersFORWARDER, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersFORWARDER, dwhIdHubCustomers);

//--
            if (rsFromDWH.getString("FREIGHTPAYER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersFREIGHTPAYER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("FREIGHTPAYER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersFREIGHTPAYER, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersFREIGHTPAYER, dwhIdHubCustomers);

//--
            if (rsFromDWH.getString("STAT_CUSTOMER") == (null)) {
                dwhIdHubCustomers = -1;
            } else {
                String sqlDwhIdHubCustomersSTAT_CUSTOMER = ("select " + nameDwhIdHubCustomers + " " + properties.getProperty("iBox.hubCustomersPart1.select")
                        + rsFromDWH.getString("STAT_CUSTOMER").replace("'", "''") + properties.getProperty("iBox.hubCustomersPart2.select")
                        + rsFromDWH.getString("BL_NUMBER") + "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubCustomers = getDataFromHub(sqlDwhIdHubCustomersSTAT_CUSTOMER, nameForMapDwhIdHubCustomers);
            }
            mapForSource.put(nameDwhIdHubCustomersSTAT_CUSTOMER, dwhIdHubCustomers);
//---------------------------
            if (rsFromDWH.getString("CONT_TYPE_CODE") == (null)) {
                dwhIdHubContainerType = -1;
            } else {
                String sqlDwhIdHubContainerType = ("select " + nameDwhIdHubContainerType + " " + properties.getProperty("iBox.hubContainerType.select")
                        + rsFromDWH.getString("CONT_TYPE_CODE") +  "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubContainerType = getDataFromHub(sqlDwhIdHubContainerType, nameDwhIdHubContainerType);
            }
            mapForSource.put(nameDwhIdHubCONTAINER_TYPE, dwhIdHubContainerType);
//--
            if (rsFromDWH.getString("IMP_EXP") == (null)) {
                dwhIdHubImportExport = -1;
            } else {
                String sqlDwhIdHubImportExport = ("select " + nameDwhIdHubImportExport + " " + properties.getProperty("iBox.hubImportExport.select")
                        + rsFromDWH.getString("IMP_EXP") + "\'");
                dwhIdHubImportExport = getDataFromHub(sqlDwhIdHubImportExport, nameDwhIdHubImportExport);
            }
            mapForSource.put(nameDwhIdHubIMPORT_EXPORT, dwhIdHubImportExport);
//--
            if (rsFromDWH.getString("SPEC_CONTR_REF") == (null)) {
                dwhIdHubSpecialContractTypes = -1;
            } else {
                String sqlDwhIdHubSpecialContractType = ("select " + nameDwhIdHubSpecialContractType + " " + properties.getProperty("iBox.hubSpecialContractTypesPart1.select")
                        + rsFromDWH.getString("SPEC_CONTR_REF") + properties.getProperty("iBox.hubSpecialContractTypesPart2.select")
                        + " and accessCompanyId= " + accesCompanyId);
                dwhIdHubSpecialContractTypes = getDataFromHub(sqlDwhIdHubSpecialContractType, nameDwhIdHubForMapSpecialContractType);
            }
            mapForSource.put(nameDwhIdHubSPECIAL_CONTRACT_TYPES, dwhIdHubSpecialContractTypes);
//--
            if (rsFromDWH.getString("C_OV_STATUS") == (null)) {
                dwhIdHubOceanVesselStatus = -1;
            } else {
                String sqlDwhIdHubOceanVesselStatus = ("select " + nameDwhIdHubOceanVesselStatus + " " + properties.getProperty("iBox.hubOceanVesselStatus.select")
                        + rsFromDWH.getString("C_OV_STATUS") +  "' and accessCompanyId= " + accesCompanyId);
                dwhIdHubOceanVesselStatus = getDataFromHub(sqlDwhIdHubOceanVesselStatus, nameDwhIdHubOceanVesselStatus);
            }
            mapForSource.put(nameDwhIdHubOCEAN_VESSEL_STATUS, dwhIdHubOceanVesselStatus);

            //--
            if (rsFromDWH.getString("C_BL_NR_PREFIX") == (null)) {
                dwhIdHubCrossBookingType = getDataFromHub(properties.getProperty("iBox.hubCrossBookingType_NA.select"), nameDwhIdHubCrossBookingType);
            } else {
                String sqlDwhIdHubCrossBookingType = ("select " + nameDwhIdHubCrossBookingType + " " + properties.getProperty("iBox.hubCrossBookingType.select")
                        + rsFromDWH.getString("C_BL_NR_PREFIX") + "\'");
                dwhIdHubCrossBookingType = getDataFromHub(sqlDwhIdHubCrossBookingType, nameDwhIdHubCrossBookingType);
                if (dwhIdHubCrossBookingType == null) {
                    dwhIdHubCrossBookingType = getDataFromHub(properties.getProperty("iBox.hubCrossBookingType_NA.select"), nameDwhIdHubCrossBookingType);
                }
            }
            mapForSource.put(nameDwhIdHubCROSS_BOOKING_TYPE, dwhIdHubCrossBookingType);

            String qwe = (properties.getProperty("iBox.insert") + mapForSource.get("dwhIdHubIboxData") + "," + mapForSource.get(nameDwhIdHubPPCCEE) + "," +
                    mapForSource.get(nameDwhIdHubFullEmpty) + "," + mapForSource.get(nameDwhIdHubLocationsPOL) + "," + mapForSource.get(nameDwhIdHubLocationsPOD) + ","
                    + mapForSource.get(nameDwhIdHubLocationsPORT_TRANS) + "," + mapForSource.get(nameDwhIdHubLocationsPORT_TRANS2) + ","
                    + mapForSource.get(nameDwhIdHubLocationsPLR) + "," + mapForSource.get(nameDwhIdHubLocationsPLD) + ","
                    + mapForSource.get(nameDwhIdHubLocationsOCEAN_TS_PORT) + "," + mapForSource.get(nameDwhIdHubCustomersBOOK_PARTY) + ","
                    + mapForSource.get(nameDwhIdHubCustomersSHIPPER) + "," + mapForSource.get(nameDwhIdHubCustomersNOTIFY) + ","
                    + mapForSource.get(nameDwhIdHubCustomersCONSIGNEE) + "," + mapForSource.get(nameDwhIdHubCustomersFORWARDER) + ","
                    + mapForSource.get(nameDwhIdHubCustomersFREIGHTPAYER) + "," + mapForSource.get(nameDwhIdHubCustomersSTAT_CUSTOMER) + ","
                    + mapForSource.get(nameDwhIdHubCONTAINER_TYPE) + "," + mapForSource.get(nameDwhIdHubIMPORT_EXPORT) + ","
                    + mapForSource.get(nameDwhIdHubSPECIAL_CONTRACT_TYPES) + "," + mapForSource.get(nameDwhIdHubOCEAN_VESSEL_STATUS) + ","
                    + mapForSource.get(nameDwhIdHubCROSS_BOOKING_TYPE) + ")");

            executeInDWH(qwe);
        }

        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/DWHtoMDS.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public Integer getDataFromHub(String sql, String nameDwhIdHub) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, sql);
        Integer template = null;
        while (rsFromDWH.next()) {
            template = rsFromDWH.getInt(nameDwhIdHub);
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        return template;
    }


    public void executeInDWH(String sql) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        stForDWH.execute(sql);
        //System.out.println("SQL for Insert in DWH: " + sql);
        //System.out.println("Executing query in DWH complete!");
        db.closeConnecions(null, stForDWH, connectionToDWH);
    }


}
