package TietoRus.system.helpers.models;

public class LocationsHub {
    private String dictionaryType;
    private String locationCode;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public LocationsHub(String dictionaryType, String locationCode, int sequenceNumber, int accessCompanyId, int srcSystemId) {
        this.dictionaryType = dictionaryType;
        this.locationCode = locationCode;
        this.sequenceNumber = sequenceNumber;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationsHub that = (LocationsHub) o;

        if (sequenceNumber != that.sequenceNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (dictionaryType != null ? !dictionaryType.equals(that.dictionaryType) : that.dictionaryType != null)
            return false;
        return locationCode != null ? locationCode.equals(that.locationCode) : that.locationCode == null;
    }

    @Override
    public int hashCode() {
        int result = dictionaryType != null ? dictionaryType.hashCode() : 0;
        result = 31 * result + (locationCode != null ? locationCode.hashCode() : 0);
        result = 31 * result + sequenceNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "LocationsHub{" +
                "dictionaryType='" + dictionaryType + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
