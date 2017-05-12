package TietoRus.system.helpers.models;

import java.sql.Date;

public class BookingDTXFileSat {
    private Date fileDate;

    public BookingDTXFileSat(Date fileDate) {
        this.fileDate = fileDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingDTXFileSat that = (BookingDTXFileSat) o;

        return fileDate != null ? fileDate.equals(that.fileDate) : that.fileDate == null;
    }

    @Override
    public int hashCode() {
        return fileDate != null ? fileDate.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookingDTXFileSat{" +
                "fileDate=" + fileDate +
                '}';
    }
}
