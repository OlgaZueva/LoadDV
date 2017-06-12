package TietoRus.system.helpers.models;

public class BookingReportingCustomerHub {
    private String blNr;
    private int accessCompanyId;
    private int srcSystemId;

    public BookingReportingCustomerHub(String blNr, int accessCompanyId, int srcSystemId) {
        this.blNr = blNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingReportingCustomerHub that = (BookingReportingCustomerHub) o;

        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        return blNr != null ? blNr.equals(that.blNr) : that.blNr == null;
    }

    @Override
    public int hashCode() {
        int result = blNr != null ? blNr.hashCode() : 0;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "BookingReportingCustomerHub{" +
                "blNr='" + blNr + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
