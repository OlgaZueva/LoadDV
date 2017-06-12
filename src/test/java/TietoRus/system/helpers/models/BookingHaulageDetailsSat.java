package TietoRus.system.helpers.models;



import java.sql.Date;

public class BookingHaulageDetailsSat {
    private String city;
    private Date haulageDate;
    private String haulageType;

    public BookingHaulageDetailsSat(String city, Date haulageDate, String haulageType) {
        this.city = city;
        this.haulageDate = haulageDate;
        this.haulageType = haulageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingHaulageDetailsSat that = (BookingHaulageDetailsSat) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (haulageDate != null ? !haulageDate.equals(that.haulageDate) : that.haulageDate != null) return false;
        return haulageType != null ? haulageType.equals(that.haulageType) : that.haulageType == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (haulageDate != null ? haulageDate.hashCode() : 0);
        result = 31 * result + (haulageType != null ? haulageType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingHaulageDetailsSat{" +
                "city='" + city + '\'' +
                ", haulageDate=" + haulageDate +
                ", haulageType='" + haulageType + '\'' +
                '}';
    }
}
