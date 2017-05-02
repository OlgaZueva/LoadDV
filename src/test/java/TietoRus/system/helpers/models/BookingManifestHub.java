package TietoRus.system.helpers.models;


public class BookingManifestHub {
    private int bookMftId;
    private int accessCompanyId;
    private int srcSystemId;
    public BookingManifestHub (int bookMftId, int accessCompanyId, int srcSystemId){
        this.bookMftId=bookMftId;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingManifestHub that = (BookingManifestHub) o;

        if (bookMftId != that.bookMftId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookMftId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingManifestHub{" +
                "bookMftId=" + bookMftId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
