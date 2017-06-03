package TietoRus.system.helpers.models;


public class FileROEHub {
    private String serviceCode;
    private int fileLinerNr;
    private String currencyCode;
    private int accessCompanyId;
    private int srcSystemId;

    public FileROEHub(String serviceCode, int fileLinerNr, String currencyCode, int accessCompanyId, int srcSystemId) {
        this.serviceCode = serviceCode;
        this.fileLinerNr = fileLinerNr;
        this.currencyCode = currencyCode;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileROEHub that = (FileROEHub) o;

        if (fileLinerNr != that.fileLinerNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (serviceCode != null ? !serviceCode.equals(that.serviceCode) : that.serviceCode != null) return false;
        return currencyCode != null ? currencyCode.equals(that.currencyCode) : that.currencyCode == null;
    }

    @Override
    public int hashCode() {
        int result = serviceCode != null ? serviceCode.hashCode() : 0;
        result = 31 * result + fileLinerNr;
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "FileROEHub{" +
                "serviceCode='" + serviceCode + '\'' +
                ", fileLinerNr=" + fileLinerNr +
                ", currencyCode='" + currencyCode + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
