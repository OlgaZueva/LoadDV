package TietoRus.system.helpers.models;

public class InvoiceLinesSat {
    private Double price;
    private String activity;
    private Double unitCount;
    private String containerNr;
    private String containerType;
    private String units;
    private Double roe;
    private String invoiceCurrency;
    private String chargeName;
    private Double invoicedAmount;
    private int chargeNr;

    public InvoiceLinesSat(Double price, String activity, Double unitCount, String containerNr, String containerType, String units, Double roe, String invoiceCurrency,
                           String chargeName, Double invoicedAmount, int chargeNr) {
        this.price = price;
        this.activity = activity;
        this.unitCount = unitCount;
        this.containerNr = containerNr;
        this.containerType = containerType;
        this.units = units;
        this.roe = roe;
        this.invoiceCurrency = invoiceCurrency;
        this.chargeName = chargeName;
        this.invoicedAmount = invoicedAmount;
        this.chargeNr = chargeNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLinesSat that = (InvoiceLinesSat) o;

        if (chargeNr != that.chargeNr) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;
        if (unitCount != null ? !unitCount.equals(that.unitCount) : that.unitCount != null) return false;
        if (containerNr != null ? !containerNr.equals(that.containerNr) : that.containerNr != null) return false;
        if (containerType != null ? !containerType.equals(that.containerType) : that.containerType != null)
            return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;
        if (roe != null ? !roe.equals(that.roe) : that.roe != null) return false;
        if (invoiceCurrency != null ? !invoiceCurrency.equals(that.invoiceCurrency) : that.invoiceCurrency != null)
            return false;
        if (chargeName != null ? !chargeName.equals(that.chargeName) : that.chargeName != null) return false;
        return invoicedAmount != null ? invoicedAmount.equals(that.invoicedAmount) : that.invoicedAmount == null;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (unitCount != null ? unitCount.hashCode() : 0);
        result = 31 * result + (containerNr != null ? containerNr.hashCode() : 0);
        result = 31 * result + (containerType != null ? containerType.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (roe != null ? roe.hashCode() : 0);
        result = 31 * result + (invoiceCurrency != null ? invoiceCurrency.hashCode() : 0);
        result = 31 * result + (chargeName != null ? chargeName.hashCode() : 0);
        result = 31 * result + (invoicedAmount != null ? invoicedAmount.hashCode() : 0);
        result = 31 * result + chargeNr;
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceLinesSat{" +
                "price=" + price +
                ", activity='" + activity + '\'' +
                ", unitCount=" + unitCount +
                ", containerNr='" + containerNr + '\'' +
                ", containerType='" + containerType + '\'' +
                ", units='" + units + '\'' +
                ", roe=" + roe +
                ", invoiceCurrency='" + invoiceCurrency + '\'' +
                ", chargeName='" + chargeName + '\'' +
                ", invoicedAmount=" + invoicedAmount +
                ", chargeNr=" + chargeNr +
                '}';
    }
}
