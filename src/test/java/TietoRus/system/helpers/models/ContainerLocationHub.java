package TietoRus.system.helpers.models;

public class ContainerLocationHub {
    private String dictionaryType;
    private String containerLocationCode;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public ContainerLocationHub(String dictionaryType, String containerLocationCode, int sequenceNumber, int accessCompanyId, int srcSystemId) {
        this.dictionaryType = dictionaryType;
        this.containerLocationCode = containerLocationCode;
        this.sequenceNumber = sequenceNumber;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerLocationHub that = (ContainerLocationHub) o;

        if (sequenceNumber != that.sequenceNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (dictionaryType != null ? !dictionaryType.equals(that.dictionaryType) : that.dictionaryType != null)
            return false;
        return containerLocationCode != null ? containerLocationCode.equals(that.containerLocationCode) : that.containerLocationCode == null;
    }

    @Override
    public int hashCode() {
        int result = dictionaryType != null ? dictionaryType.hashCode() : 0;
        result = 31 * result + (containerLocationCode != null ? containerLocationCode.hashCode() : 0);
        result = 31 * result + sequenceNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerLocationHub{" +
                "dictionaryType='" + dictionaryType + '\'' +
                ", containerLocationCode='" + containerLocationCode + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
