package TietoRus.system.helpers.models;


public class BookingHaulageDetailsHub {
    private int bookingNumber;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingHaulageDetailsHub(int bookingNumber, int sequenceNr, int accessCompanyId, int srcSystemId) {
        this.bookingNumber = bookingNumber;
        this.sequenceNumber = sequenceNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingHaulageDetailsHub that = (BookingHaulageDetailsHub) o;

        if (bookingNumber != that.bookingNumber) return false;
        if (sequenceNumber != that.sequenceNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingNumber;
        result = 31 * result + sequenceNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingHaulageDetailsHub{" +
                "bookingNumber=" + bookingNumber +
                ", sequenceNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
