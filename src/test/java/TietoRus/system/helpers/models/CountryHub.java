package TietoRus.system.helpers.models;

public class CountryHub {
    private String dictionaryType;
    private String countryCode;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public CountryHub(String dictionaryType, String countryCode, int sequenceNumber, int accessCompanyId, int srcSystemId) {
        this.dictionaryType = dictionaryType;
        this.countryCode = countryCode;
        this.sequenceNumber = sequenceNumber;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryHub that = (CountryHub) o;

        if (sequenceNumber != that.sequenceNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (dictionaryType != null ? !dictionaryType.equals(that.dictionaryType) : that.dictionaryType != null)
            return false;
        return countryCode != null ? countryCode.equals(that.countryCode) : that.countryCode == null;
    }

    @Override
    public int hashCode() {
        int result = dictionaryType != null ? dictionaryType.hashCode() : 0;
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + sequenceNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "CountryHub{" +
                "dictionaryType='" + dictionaryType + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
