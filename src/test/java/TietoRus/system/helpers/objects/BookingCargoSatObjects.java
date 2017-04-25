package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.BookingCargoSat;

import java.sql.*;


public class BookingCargoSatObjects {
    private DBHelper db = new DBHelper();

    public BookingCargoSat getSatFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        BookingCargoSat bookingCargoSat = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                String agencyHarmonizedCode = rsFromSA.getString("AGENCY_CCC");
                String atmosphereControl = rsFromSA.getString("ATMOSPHERE_CONTROL");
                String automaticTemperatureFlag = rsFromSA.getString("AUTOMATIC_TEMP_FLAG");
                Double automaticTemperatureValue = rsFromSA.getDouble("AUTOMATIC_TEMP_VALUE");
                Double cargoWeight = rsFromSA.getDouble("BRUTTO");
                String bulbMode = rsFromSA.getString("BULB_MODE");
                String coldTreatment = rsFromSA.getString("COLD_TREATMENT");
                int containerCount = rsFromSA.getInt("CONT_ANT");
                String containerNr = rsFromSA.getString("CONT_NR");
                String containerTypeName = rsFromSA.getString("CONT_TEKST");
                String containerTypeCode = rsFromSA.getString("CONT_TYPE");
                String dehumidificationFlag = rsFromSA.getString("DEHUMIDIFICATION_FLAG");
                Double dehumidificationValue = rsFromSA.getDouble("DEHUMIDIFICATION_VALUE");
                String imoClass = rsFromSA.getString("IMCO_KL");
                String imsTransportCode = rsFromSA.getString("IMS_TRANSPORT_CODE");
                String typeOfPackages = rsFromSA.getString("KOLLI_ENHED");
                String manualTemperatureFlag = rsFromSA.getString("MANUAL_TEMP_FLAG");
                Double manualTemperatureValue = rsFromSA.getDouble("MANUAL_TEMP_VALUE");
                String marksNumbers = rsFromSA.getString("MRK_NR");
                String isOperatingReefer = rsFromSA.getString("OPERATING_REEFER_INDICATOR");
                String partLoadFlag = rsFromSA.getString("PART_LOAD_FLAG");
                String precarriageVessel = rsFromSA.getString("PRECARRIAGE");
                String sealNumber = rsFromSA.getString("SEAL_NO");
                int tareWeight = rsFromSA.getInt("TARA");
                String temperatureUnits = rsFromSA.getString("TEMP_ENH");
                Double minTemperature = rsFromSA.getDouble("TEMP1");
                Double maxTemperature = rsFromSA.getDouble("TEMP2");
                String unNumber = rsFromSA.getString("UN_NR");
                String cargoDescr = rsFromSA.getString("VAREART");
                Double volume = rsFromSA.getDouble("VOL");
                String volumeUnits = rsFromSA.getString("VOL_ENHED");
                bookingCargoSat = new BookingCargoSat(agencyHarmonizedCode, atmosphereControl, automaticTemperatureFlag, automaticTemperatureValue, cargoWeight,
                        bulbMode, coldTreatment, containerCount, containerNr, containerTypeName, containerTypeCode, dehumidificationFlag, dehumidificationValue,
                        imoClass, imsTransportCode, typeOfPackages, manualTemperatureFlag, manualTemperatureValue, marksNumbers, isOperatingReefer, partLoadFlag,
                        precarriageVessel, sealNumber, tareWeight, temperatureUnits, minTemperature, maxTemperature, unNumber, cargoDescr, volume, volumeUnits);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("BookingCargoSat from SA: " + bookingCargoSat);
        return bookingCargoSat;
    }

    public BookingCargoSat getSatFromDWH(String satSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + satSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, satSQL);
        BookingCargoSat bookingCargoSat = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                String agencyHarmonizedCode = rsFromDWH.getString("agencyHarmonizedCode");
                String atmosphereControl = rsFromDWH.getString("atmosphereControl");
                String automaticTemperatureFlag = rsFromDWH.getString("automaticTemperatureFlag");
                Double automaticTemperatureValue = rsFromDWH.getDouble("automaticTemperatureValue");
                Double cargoWeight = rsFromDWH.getDouble("cargoWeight");
                String bulbMode = rsFromDWH.getString("bulbMode");
                String coldTreatment = rsFromDWH.getString("coldTreatment");
                int containerCount = rsFromDWH.getInt("containerCount");
                String containerNr = rsFromDWH.getString("containerNr");
                String containerTypeName = rsFromDWH.getString("containerTypeName");
                String containerTypeCode = rsFromDWH.getString("containerTypeCode");
                String dehumidificationFlag = rsFromDWH.getString("dehumidificationFlag");
                Double dehumidificationValue = rsFromDWH.getDouble("dehumidificationValue");
                String imoClass = rsFromDWH.getString("imoClass");
                String imsTransportCode = rsFromDWH.getString("imsTransportCode");
                String typeOfPackages = rsFromDWH.getString("typeOfPackages");
                String manualTemperatureFlag = rsFromDWH.getString("manualTemperatureFlag");
                Double manualTemperatureValue = rsFromDWH.getDouble("manualTemperatureValue");
                String marksNumbers = rsFromDWH.getString("marksNumbers");
                String isOperatingReefer = rsFromDWH.getString("isOperatingReefer");
                String partLoadFlag = rsFromDWH.getString("partLoadFlag");
                String precarriageVessel = rsFromDWH.getString("precarriageVessel");
                String sealNumber = rsFromDWH.getString("sealNumber");
                int tareWeight = rsFromDWH.getInt("tareWeight");
                String temperatureUnits = rsFromDWH.getString("temperatureUnits");
                Double minTemperature = rsFromDWH.getDouble("minTemperature");
                Double maxTemperature = rsFromDWH.getDouble("maxTemperature");
                String unNumber = rsFromDWH.getString("unNumber");
                String cargoDescr = rsFromDWH.getString("cargoDescr");
                Double volume = rsFromDWH.getDouble("volume");
                String volumeUnits = rsFromDWH.getString("volumeUnits");
                bookingCargoSat = new BookingCargoSat(agencyHarmonizedCode, atmosphereControl, automaticTemperatureFlag, automaticTemperatureValue, cargoWeight,
                        bulbMode, coldTreatment, containerCount, containerNr, containerTypeName, containerTypeCode, dehumidificationFlag, dehumidificationValue,
                        imoClass, imsTransportCode, typeOfPackages, manualTemperatureFlag, manualTemperatureValue, marksNumbers, isOperatingReefer, partLoadFlag,
                        precarriageVessel, sealNumber, tareWeight, temperatureUnits, minTemperature, maxTemperature, unNumber, cargoDescr, volume, volumeUnits);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("BookingCargoSat from DWH: " + bookingCargoSat);
        return bookingCargoSat;
    }


}
