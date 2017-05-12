package TietoRus.system.helpers.models;


public class BookingDTXFileHub {
    private int manifestFileId;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingDTXFileHub(int manifestFileId, int accessCompanyId, int srcSystemId) {
        this.manifestFileId = manifestFileId;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingDTXFileHub that = (BookingDTXFileHub) o;

        if (manifestFileId != that.manifestFileId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = manifestFileId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingDTXFileHub{" +
                "manifestFileId=" + manifestFileId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
