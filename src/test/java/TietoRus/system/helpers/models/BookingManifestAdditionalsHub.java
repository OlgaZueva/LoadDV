package TietoRus.system.helpers.models;


public class BookingManifestAdditionalsHub {
    private int bookingNumber;
    private String segmentCode;
    private int accessCompanyId;
    private int sequenceNr;
    private int srcSystemId;

    public BookingManifestAdditionalsHub(int bookingNumber, String segmentCode, int accessCompanyId, int sequenceNr, int srcSystemId ){
        this.bookingNumber = bookingNumber;
        this.segmentCode = segmentCode;
        this.accessCompanyId = accessCompanyId;
        this.sequenceNr = sequenceNr;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingManifestAdditionalsHub that = (BookingManifestAdditionalsHub) o;

        if (bookingNumber != that.bookingNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (sequenceNr != that.sequenceNr) return false;
        if (srcSystemId != that.srcSystemId) return false;
        return segmentCode != null ? segmentCode.equals(that.segmentCode) : that.segmentCode == null;
    }

    @Override
    public int hashCode() {
        int result = bookingNumber;
        result = 31 * result + (segmentCode != null ? segmentCode.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + sequenceNr;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingManifestAdditionalsHub{" +
                "bookingNumber=" + bookingNumber +
                ", segmentCode='" + segmentCode + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", sequenceNr=" + sequenceNr +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
