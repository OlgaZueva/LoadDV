package TietoRus.system.helpers.models;


public class BookingHub {
    private int bookingNumber;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingHub(int bookingNr, int accessCompanyId, int srcSystemId){
        this.bookingNumber = bookingNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingHub booking = (BookingHub) o;

        if (bookingNumber != booking.bookingNumber) return false;
        if (accessCompanyId != booking.accessCompanyId) return false;
        return srcSystemId == booking.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingHub{" +
                "bookingNr=" + bookingNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
