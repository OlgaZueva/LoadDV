package TietoRus.system.helpers.models;

public class BookingChargesSat {
    private String invoiceType;
    private Double customerCode;
    private String invoiceRelationType;
    private String invoiceStatus;

    public BookingChargesSat(String invoiceType, Double customerCode, String invoiceRelationType, String invoiceStatus) {
        this.invoiceType = invoiceType;
        this.customerCode = customerCode;
        this.invoiceRelationType = invoiceRelationType;
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingChargesSat that = (BookingChargesSat) o;

        if (invoiceType != null ? !invoiceType.equals(that.invoiceType) : that.invoiceType != null) return false;
        if (customerCode != null ? !customerCode.equals(that.customerCode) : that.customerCode != null) return false;
        if (invoiceRelationType != null ? !invoiceRelationType.equals(that.invoiceRelationType) : that.invoiceRelationType != null)
            return false;
        return invoiceStatus != null ? invoiceStatus.equals(that.invoiceStatus) : that.invoiceStatus == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceType != null ? invoiceType.hashCode() : 0;
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        result = 31 * result + (invoiceRelationType != null ? invoiceRelationType.hashCode() : 0);
        result = 31 * result + (invoiceStatus != null ? invoiceStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingChargesSat{" +
                "invoiceType='" + invoiceType + '\'' +
                ", customerCode=" + customerCode +
                ", invoiceRelationType='" + invoiceRelationType + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                '}';
    }
}
