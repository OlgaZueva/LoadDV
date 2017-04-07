package TietoRus.models;

public class FileLiner {

    private String serviceCode;
    private int fileLinerNr;
    private int accessCompanyId;
    private int srcSystemId;

    public FileLiner(String serviceCode, int fileLinerNr, int accessCompanyId, int srcSystemId) {
        this.serviceCode = serviceCode;
        this.fileLinerNr = fileLinerNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileLiner fileLiner = (FileLiner) o;

        if (fileLinerNr != fileLiner.fileLinerNr) return false;
        if (accessCompanyId != fileLiner.accessCompanyId) return false;
        if (srcSystemId != fileLiner.srcSystemId) return false;
        return serviceCode != null ? serviceCode.equals(fileLiner.serviceCode) : fileLiner.serviceCode == null;
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
        return "FileLiner{" +
                "serviceCode='" + serviceCode + '\'' +
                ", fileLinerNr=" + fileLinerNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
