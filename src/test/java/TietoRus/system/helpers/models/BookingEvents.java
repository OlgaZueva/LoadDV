package TietoRus.system.helpers.models;


public class BookingEvents {
    private int bookingEventId;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingEvents (int bookingEventId, int accessCompanyId, int srcSystemId){
        this.bookingEventId = bookingEventId;
        this.accessCompanyId =accessCompanyId;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEvents that = (BookingEvents) o;

        if (bookingEventId != that.bookingEventId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = bookingEventId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingEvents{" +
                "bookingEventId=" + bookingEventId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
