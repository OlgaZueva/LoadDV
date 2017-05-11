package TietoRus.system.helpers.models;


public class BookingChargesHub {
    private int bookingNr;
    private int positionNr;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingChargesHub(int bookingNr, int positionNr, int accessCompanyId, int srcSystemId) {
        this.bookingNr = bookingNr;
        this.positionNr = positionNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingChargesHub that = (BookingChargesHub) o;

        if (bookingNr != that.bookingNr) return false;
        if (positionNr != that.positionNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingNr;
        result = 31 * result + positionNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingChargesHub{" +
                "bookingNr=" + bookingNr +
                ", positionNr=" + positionNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}

