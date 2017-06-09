package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingSat;

import java.sql.*;


public class BookingSatObjects {
    private DBHelper db = new DBHelper();

    public BookingSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingSat bookingSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String serviceCode = rsFromSA.getString("AFDELING");
                String polName = rsFromSA.getString("AFG_BY");
                Date polDate = rsFromSA.getDate("AFG_DATO");
                String polCode = rsFromSA.getString("AFG_ZONE");
                Double shipperCode = rsFromSA.getDouble("AFSENDER");
                String dischTerminalDepotCode = rsFromSA.getString("AIRLINECODE_3");
                String podName = rsFromSA.getString("ANK_BY");
                Date podDate = rsFromSA.getDate("ANK_DATO");
                String podCode = rsFromSA.getString("ANK_ZONE");
                int controlOffice = rsFromSA.getInt("CONTROL_KONTOR");
                int controlCompany = rsFromSA.getInt("CONTROL_SELSKAB");
                String createdBy = rsFromSA.getString("CREATED_BY");
                Date creationDate = rsFromSA.getDate("CREATION_DATE");
                String originalCrossBookNr = rsFromSA.getString("CROSS_BOOK");
                Date blDate = rsFromSA.getDate("DATO");
                String shipperReference = rsFromSA.getString("DER_REF");
                String directFlag = rsFromSA.getString("DIRECT_FLAG");
                Date feederDate = rsFromSA.getDate("FEEDER_DATO");
                String payableAt = rsFromSA.getString("FREIGHT_PAY");
                String payableAtIMS = rsFromSA.getString("FREIGHT_PAY_IMS");
                String gvaTrade = rsFromSA.getString("GVA_AFDELING");
                String importExportСode = rsFromSA.getString("IMP_EXP");
                int branch = rsFromSA.getInt("KONTOR");
                String depot = rsFromSA.getString("LOC");
                String depotReference = rsFromSA.getString("LOC_REF");
                String terminal = rsFromSA.getString("LOC2");
                String terminalReference = rsFromSA.getString("LOC2_REF");
                String manifestType = rsFromSA.getString("MANIFEST_TYPE");
                String oceanTrade = rsFromSA.getString("OCEAN_AFDELING");
                String placeOfDelivery = rsFromSA.getString("PL_O_DELIVER");
                String placeOfReceipt = rsFromSA.getString("PL_O_RECEIPT");
                String pldHaulageAtDestination = rsFromSA.getString("PLD_HAULAGE_AT_DESTINATION");
                String pldSublocationCode = rsFromSA.getString("PLD_SUBLOCATION_CODE1");
                String pldZipCode = rsFromSA.getString("PLD_ZIPCODE");
                String pldHaulageAtOrigin = rsFromSA.getString("PLR_HAULAGE_AT_ORIGIN");
                String plrSublocationCode = rsFromSA.getString("PLR_SUBLOCATION_CODE1");
                String plrZipCode = rsFromSA.getString("PLR_ZIPCODE");
                Date pldDate = rsFromSA.getDate("POD_DATO");
                Date plrDate = rsFromSA.getDate("POR_DATO");
                String quotationRefNr = rsFromSA.getString("QUOTATION_REF_NR");
                String blNumber = rsFromSA.getString("REF_NR");
                String specialRefNr = rsFromSA.getString("SPEC_REF_NUMBER");
                String contractType = rsFromSA.getString("SPECIAL_CONTRACT_REF");
                String bookingStatus = rsFromSA.getString("STAT");
                String inttraReferenceNr = rsFromSA.getString("TIDL_GODS");
                String europeanCargoStatus = rsFromSA.getString("UDF_ANG2");
                bookingSat = new BookingSat(serviceCode, polName, polDate, polCode, shipperCode, dischTerminalDepotCode, podName, podDate, podCode, controlOffice, controlCompany,
                        createdBy, creationDate, originalCrossBookNr, blDate, shipperReference, directFlag, feederDate, payableAt, payableAtIMS, gvaTrade, importExportСode,
                        branch, depot, depotReference, terminal, terminalReference, manifestType, oceanTrade, placeOfDelivery, placeOfReceipt, pldHaulageAtDestination,
                        pldSublocationCode, pldZipCode, pldHaulageAtOrigin, plrSublocationCode, plrZipCode, pldDate, plrDate, quotationRefNr, blNumber, specialRefNr, contractType,
                        bookingStatus, inttraReferenceNr, europeanCargoStatus);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingSat from SA: " + bookingSat);
        return bookingSat;
    }

    public BookingSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingSat bookingSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String serviceCode = rsFromDWH.getString("serviceCode");
                String polName = rsFromDWH.getString("polName");
                Date polDate = rsFromDWH.getDate("polDate");
                String polCode = rsFromDWH.getString("polCode");
                Double shipperCode = rsFromDWH.getDouble("shipperCode");
                String dischTerminalDepotCode = rsFromDWH.getString("dischTerminalDepotCode");
                String podName = rsFromDWH.getString("podName");
                Date podDate = rsFromDWH.getDate("podDate");
                String podCode = rsFromDWH.getString("podCode");
                int controlOffice = rsFromDWH.getInt("controlOffice");
                int controlCompany = rsFromDWH.getInt("controlCompany");
                String createdBy = rsFromDWH.getString("createdBy");
                Date creationDate = rsFromDWH.getDate("creationDate");
                String originalCrossBookNr = rsFromDWH.getString("originalCrossBookNr");
                Date blDate = rsFromDWH.getDate("blDate");
                String shipperReference = rsFromDWH.getString("shipperReference");
                String directFlag = rsFromDWH.getString("directFlag");
                Date feederDate = rsFromDWH.getDate("feederDate");
                String payableAt = rsFromDWH.getString("payableAt");
                String payableAtIMS = rsFromDWH.getString("payableAtIMS");
                String gvaTrade = rsFromDWH.getString("gvaTrade");
                String importExportСode = rsFromDWH.getString("importExportСode");
                int branch = rsFromDWH.getInt("branch");
                String depot = rsFromDWH.getString("depot");
                String depotReference = rsFromDWH.getString("depotReference");
                String terminal = rsFromDWH.getString("terminal");
                String terminalReference = rsFromDWH.getString("terminalReference");
                String manifestType = rsFromDWH.getString("manifestType");
                String oceanTrade = rsFromDWH.getString("oceanTrade");
                String placeOfDelivery = rsFromDWH.getString("placeOfDelivery");
                String placeOfReceipt = rsFromDWH.getString("placeOfReceipt");
                String pldHaulageAtDestination = rsFromDWH.getString("pldHaulageAtDestination");
                String pldSublocationCode = rsFromDWH.getString("pldSublocationCode");
                String pldZipCode = rsFromDWH.getString("pldZipCode");
                String pldHaulageAtOrigin = rsFromDWH.getString("pldHaulageAtOrigin");
                String plrSublocationCode = rsFromDWH.getString("plrSublocationCode");
                String plrZipCode = rsFromDWH.getString("plrZipCode");
                Date pldDate = rsFromDWH.getDate("pldDate");
                Date plrDate = rsFromDWH.getDate("plrDate");
                String quotationRefNr = rsFromDWH.getString("quotationRefNr");
                String blNumber = rsFromDWH.getString("blNumber");
                String specialRefNr = rsFromDWH.getString("specialRefNr");
                String contractType = rsFromDWH.getString("contractType");
                String bookingStatus = rsFromDWH.getString("bookingStatus");
                String inttraReferenceNr = rsFromDWH.getString("inttraReferenceNr");
                String europeanCargoStatus = rsFromDWH.getString("europeanCargoStatus");
                bookingSat = new BookingSat(serviceCode, polName, polDate, polCode, shipperCode, dischTerminalDepotCode, podName, podDate, podCode, controlOffice, controlCompany,
                        createdBy, creationDate, originalCrossBookNr, blDate, shipperReference, directFlag, feederDate, payableAt, payableAtIMS, gvaTrade, importExportСode,
                        branch, depot, depotReference, terminal, terminalReference, manifestType, oceanTrade, placeOfDelivery, placeOfReceipt, pldHaulageAtDestination,
                        pldSublocationCode, pldZipCode, pldHaulageAtOrigin, plrSublocationCode, plrZipCode, pldDate, plrDate, quotationRefNr, blNumber, specialRefNr, contractType,
                        bookingStatus, inttraReferenceNr, europeanCargoStatus);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingSat from DWH: " + bookingSat);
        return bookingSat;
    }


}


