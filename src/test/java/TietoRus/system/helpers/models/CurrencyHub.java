package TietoRus.system.helpers.models;


public class CurrencyHub {

    private String dictionaryType;
    private String currencyCode;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;


    public CurrencyHub(String dictionaryType, String currencyCode, int sequenceNumber, int accessCompanyId,  int srcSystemId){
        this.dictionaryType = dictionaryType;
        this.currencyCode = currencyCode;
        this.sequenceNumber = sequenceNumber;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyHub that = (CurrencyHub) o;

        if (sequenceNumber != that.sequenceNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (dictionaryType != null ? !dictionaryType.equals(that.dictionaryType) : that.dictionaryType != null)
            return false;
        return currencyCode != null ? currencyCode.equals(that.currencyCode) : that.currencyCode == null;
    }

    @Override
    public int hashCode() {
        int result = dictionaryType != null ? dictionaryType.hashCode() : 0;
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        result = 31 * result + sequenceNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyHub{" +
                "dictionaryType='" + dictionaryType + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
