package TietoRus.system.helpers.models;

public class BookingManifestedHaulageHub{
    private int bookingNumber;
    private int sequenceNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingManifestedHaulageHub (int bookingNumber, int sequenceNumber, int accessCompanyId, int srcSystemId){
        this.bookingNumber =bookingNumber;
        this.sequenceNumber =sequenceNumber;
        this.accessCompanyId=accessCompanyId;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingManifestedHaulageHub that = (BookingManifestedHaulageHub) o;

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
        return "BookingManifestedHaulageHub{" +
                "bookingNumber=" + bookingNumber +
                ", sequenceNumber=" + sequenceNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
