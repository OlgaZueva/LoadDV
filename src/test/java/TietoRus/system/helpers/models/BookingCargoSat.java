package TietoRus.system.helpers.models;


public class BookingCargoSat {
    private String agencyHarmonizedCode;
    private String atmosphereControl;
    private String automaticTemperatureFlag;
    private Double automaticTemperatureValue;
    private Double cargoWeight;
    private String bulbMode;
    private String coldTreatment;
    private int containerCount;
    private String containerNr;
    private String containerTypeName;
    private String containerTypeCode;
    private String dehumidificationFlag;
    private Double dehumidificationValue;
    private String imoClass;
    private String imsTransportCode;
    private String typeOfPackages;

    public BookingCargoSat(String agencyHarmonizedCode, String atmosphereControl, String automaticTemperatureFlag, Double automaticTemperatureValue,
                           Double cargoWeight, String bulbMode, String coldTreatment, int containerCount, String containerNr, String containerTypeName, String containerTypeCode,
                           String dehumidificationFlag, Double dehumidificationValue, String imoClass, String imsTransportCode, String typeOfPackages) {
        this.agencyHarmonizedCode = agencyHarmonizedCode;
        this.atmosphereControl = atmosphereControl;
        this.automaticTemperatureFlag = automaticTemperatureFlag;
        this.automaticTemperatureValue = automaticTemperatureValue;
        this.cargoWeight = cargoWeight;
        this.bulbMode = bulbMode;
        this.coldTreatment = coldTreatment;
        this.containerCount = containerCount;
        this.containerNr = containerNr;
        this.containerTypeName = containerTypeName;
        this.containerTypeCode = containerTypeCode;
        this.dehumidificationFlag = dehumidificationFlag;
        this.dehumidificationValue = dehumidificationValue;
        this.imoClass = imoClass;
        this.imsTransportCode = imsTransportCode;
        this.typeOfPackages = typeOfPackages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingCargoSat that = (BookingCargoSat) o;

        if (containerCount != that.containerCount) return false;
        if (agencyHarmonizedCode != null ? !agencyHarmonizedCode.equals(that.agencyHarmonizedCode) : that.agencyHarmonizedCode != null)
            return false;
        if (atmosphereControl != null ? !atmosphereControl.equals(that.atmosphereControl) : that.atmosphereControl != null)
            return false;
        if (automaticTemperatureFlag != null ? !automaticTemperatureFlag.equals(that.automaticTemperatureFlag) : that.automaticTemperatureFlag != null)
            return false;
        if (automaticTemperatureValue != null ? !automaticTemperatureValue.equals(that.automaticTemperatureValue) : that.automaticTemperatureValue != null)
            return false;
        if (cargoWeight != null ? !cargoWeight.equals(that.cargoWeight) : that.cargoWeight != null) return false;
        if (bulbMode != null ? !bulbMode.equals(that.bulbMode) : that.bulbMode != null) return false;
        if (coldTreatment != null ? !coldTreatment.equals(that.coldTreatment) : that.coldTreatment != null)
            return false;
        if (containerNr != null ? !containerNr.equals(that.containerNr) : that.containerNr != null) return false;
        if (containerTypeName != null ? !containerTypeName.equals(that.containerTypeName) : that.containerTypeName != null)
            return false;
        if (containerTypeCode != null ? !containerTypeCode.equals(that.containerTypeCode) : that.containerTypeCode != null)
            return false;
        if (dehumidificationFlag != null ? !dehumidificationFlag.equals(that.dehumidificationFlag) : that.dehumidificationFlag != null)
            return false;
        if (dehumidificationValue != null ? !dehumidificationValue.equals(that.dehumidificationValue) : that.dehumidificationValue != null)
            return false;
        if (imoClass != null ? !imoClass.equals(that.imoClass) : that.imoClass != null) return false;
        if (imsTransportCode != null ? !imsTransportCode.equals(that.imsTransportCode) : that.imsTransportCode != null)
            return false;
        return typeOfPackages != null ? typeOfPackages.equals(that.typeOfPackages) : that.typeOfPackages == null;
    }

    @Override
    public int hashCode() {
        int result = agencyHarmonizedCode != null ? agencyHarmonizedCode.hashCode() : 0;
        result = 31 * result + (atmosphereControl != null ? atmosphereControl.hashCode() : 0);
        result = 31 * result + (automaticTemperatureFlag != null ? automaticTemperatureFlag.hashCode() : 0);
        result = 31 * result + (automaticTemperatureValue != null ? automaticTemperatureValue.hashCode() : 0);
        result = 31 * result + (cargoWeight != null ? cargoWeight.hashCode() : 0);
        result = 31 * result + (bulbMode != null ? bulbMode.hashCode() : 0);
        result = 31 * result + (coldTreatment != null ? coldTreatment.hashCode() : 0);
        result = 31 * result + containerCount;
        result = 31 * result + (containerNr != null ? containerNr.hashCode() : 0);
        result = 31 * result + (containerTypeName != null ? containerTypeName.hashCode() : 0);
        result = 31 * result + (containerTypeCode != null ? containerTypeCode.hashCode() : 0);
        result = 31 * result + (dehumidificationFlag != null ? dehumidificationFlag.hashCode() : 0);
        result = 31 * result + (dehumidificationValue != null ? dehumidificationValue.hashCode() : 0);
        result = 31 * result + (imoClass != null ? imoClass.hashCode() : 0);
        result = 31 * result + (imsTransportCode != null ? imsTransportCode.hashCode() : 0);
        result = 31 * result + (typeOfPackages != null ? typeOfPackages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingCargoSat{" +
                "agencyHarmonizedCode='" + agencyHarmonizedCode + '\'' +
                ", atmosphereControl='" + atmosphereControl + '\'' +
                ", automaticTemperatureFlag='" + automaticTemperatureFlag + '\'' +
                ", automaticTemperatureValue=" + automaticTemperatureValue +
                ", cargoWeight=" + cargoWeight +
                ", bulbMode='" + bulbMode + '\'' +
                ", coldTreatment='" + coldTreatment + '\'' +
                ", containerCount=" + containerCount +
                ", containerNr='" + containerNr + '\'' +
                ", containerTypeName='" + containerTypeName + '\'' +
                ", containerTypeCode='" + containerTypeCode + '\'' +
                ", dehumidificationFlag='" + dehumidificationFlag + '\'' +
                ", dehumidificationValue=" + dehumidificationValue +
                ", imoClass='" + imoClass + '\'' +
                ", imsTransportCode='" + imsTransportCode + '\'' +
                ", typeOfPackages='" + typeOfPackages + '\'' +
                '}';
    }
}
