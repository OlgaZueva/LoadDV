package TietoRus.system.helpers.models;

public class VesselRegistryHub {
    private String dictionaryType;
    private String feederCode;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public VesselRegistryHub(String dictionaryType, String feederCode, int sequenctNumber, int accessCompanyId, int srcSystemId) {
        this.dictionaryType = dictionaryType;
        this.feederCode = feederCode;
        this.sequenceNumber = sequenctNumber;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VesselRegistryHub that = (VesselRegistryHub) o;

        if (sequenceNumber != that.sequenceNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (dictionaryType != null ? !dictionaryType.equals(that.dictionaryType) : that.dictionaryType != null)
            return false;
        return feederCode != null ? feederCode.equals(that.feederCode) : that.feederCode == null;
    }

    @Override
    public int hashCode() {
        int result = dictionaryType != null ? dictionaryType.hashCode() : 0;
        result = 31 * result + (feederCode != null ? feederCode.hashCode() : 0);
        result = 31 * result + sequenceNumber;
        result = 31 * result + accessCompanyId;
        return result;
    }

    @Override
    public String toString() {
        return "VesselRegistryHub{" +
                "dictionaryType='" + dictionaryType + '\'' +
                ", feederCode='" + feederCode + '\'' +
                ", sequenctNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                '}';
    }
}
