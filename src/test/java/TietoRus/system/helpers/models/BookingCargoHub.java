package TietoRus.system.helpers.models;


public class BookingCargoHub {
    private int bookingNumber;
    private int accessCompanyId;
    private int cargoLineNr;
    private int srcSystemId;

    public  BookingCargoHub (int bookingNumber, int accessCompanyId, int cargoLineNr, int srcSystemId) {
        this.bookingNumber = bookingNumber;
        this.accessCompanyId = accessCompanyId;
        this.cargoLineNr = cargoLineNr;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingCargoHub that = (BookingCargoHub) o;

        if (bookingNumber != that.bookingNumber) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (cargoLineNr != that.cargoLineNr) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingNumber;
        result = 31 * result + accessCompanyId;
        result = 31 * result + cargoLineNr;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingCargoHub{" +
                "bookingNumber=" + bookingNumber +
                ", accessCompanyId=" + accessCompanyId +
                ", cargoLineNr=" + cargoLineNr +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
