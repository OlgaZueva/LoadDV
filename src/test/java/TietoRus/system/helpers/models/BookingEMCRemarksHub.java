package TietoRus.system.helpers.models;


public class BookingEMCRemarksHub {
    private int manifestFileId;
    private int remarkId;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingEMCRemarksHub (int manifestFileIdId, int remarkId,int accessCompanyId,int srcSystemId){
        this.manifestFileId = manifestFileIdId;
        this.remarkId = remarkId;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEMCRemarksHub that = (BookingEMCRemarksHub) o;

        if (manifestFileId != that.manifestFileId) return false;
        if (remarkId != that.remarkId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = manifestFileId;
        result = 31 * result + remarkId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingEMCRemarksHub{" +
                "manifestFileId=" + manifestFileId +
                ", remarkId=" + remarkId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
