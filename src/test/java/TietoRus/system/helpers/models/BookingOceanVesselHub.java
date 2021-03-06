package TietoRus.system.helpers.models;


public class BookingOceanVesselHub {
    private int bookingNumber;
    private int bookVesselId;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingOceanVesselHub(int bookingNumber, int bookVesselId, int accessCompanyId, int srcSystemId) {
        this.bookingNumber = bookingNumber;
        this.bookVesselId = bookVesselId;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingOceanVesselHub that = (BookingOceanVesselHub) o;

        if (bookingNumber != that.bookingNumber) return false;
        if (bookVesselId != that.bookVesselId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingNumber;
        result = 31 * result + bookVesselId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingOceanVesselHub{" +
                "bookingNumber=" + bookingNumber +
                ", bookVesselId=" + bookVesselId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
