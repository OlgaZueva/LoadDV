package TietoRus.system.helpers.models;


import java.sql.Date;

public class BookingReportingCustomerSat {
    private String reportingCustomer;
    private String customerType;
    private Date validFrom;

    public BookingReportingCustomerSat(String reportingCustomer, String customerType, Date validFrom) {
        this.reportingCustomer = reportingCustomer;
        this.customerType = customerType;
        this.validFrom = validFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingReportingCustomerSat that = (BookingReportingCustomerSat) o;

        if (reportingCustomer != null ? !reportingCustomer.equals(that.reportingCustomer) : that.reportingCustomer != null)
            return false;
        if (customerType != null ? !customerType.equals(that.customerType) : that.customerType != null) return false;
        return validFrom != null ? validFrom.equals(that.validFrom) : that.validFrom == null;
    }

    @Override
    public int hashCode() {
        int result = reportingCustomer != null ? reportingCustomer.hashCode() : 0;
        result = 31 * result + (customerType != null ? customerType.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingReportingCustomerSat{" +
                "reportingCustomer='" + reportingCustomer + '\'' +
                ", customerType='" + customerType + '\'' +
                ", validFrom=" + validFrom +
                '}';
    }
}
