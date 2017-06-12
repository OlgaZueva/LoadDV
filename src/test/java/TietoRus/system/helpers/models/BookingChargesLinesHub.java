package TietoRus.system.helpers.models;


public class BookingChargesLinesHub {
    private int bookingNumber;
    private int positionNr;
    private int lineNr;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingChargesLinesHub(int bookingNumber, int positionNr, int lineNr, int accessCompanyId, int srcSystemId) {
        this.bookingNumber = bookingNumber;
        this.positionNr = positionNr;
        this.lineNr = lineNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingChargesLinesHub that = (BookingChargesLinesHub) o;

        if (bookingNumber != that.bookingNumber) return false;
        if (positionNr != that.positionNr) return false;
        if (lineNr != that.lineNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingNumber;
        result = 31 * result + positionNr;
        result = 31 * result + lineNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingChargesLinesHub{" +
                "bookingNumber=" + bookingNumber +
                ", positionNr=" + positionNr +
                ", lineNr=" + lineNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
