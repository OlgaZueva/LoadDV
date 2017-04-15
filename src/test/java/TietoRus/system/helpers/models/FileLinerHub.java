package TietoRus.system.helpers.models;

public class FileLinerHub {

    private String serviceCode;
    private int fileLinerNr;
    private int accessCompanyId;
    private int srcSystemId;

    public FileLinerHub(String serviceCode, int fileLinerNr, int accessCompanyId, int srcSystemId) {
        this.serviceCode = serviceCode;
        this.fileLinerNr = fileLinerNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileLinerHub fileLinerHub = (FileLinerHub) o;

        if (fileLinerNr != fileLinerHub.fileLinerNr) return false;
        if (accessCompanyId != fileLinerHub.accessCompanyId) return false;
        if (srcSystemId != fileLinerHub.srcSystemId) return false;
        return serviceCode != null ? serviceCode.equals(fileLinerHub.serviceCode) : fileLinerHub.serviceCode == null;
    }

    @Override
    public int hashCode() {
        int result = serviceCode != null ? serviceCode.hashCode() : 0;
        result = 31 * result + fileLinerNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "FileLinerHub{" +
                "serviceCode='" + serviceCode + '\'' +
                ", fileLinerNr=" + fileLinerNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
