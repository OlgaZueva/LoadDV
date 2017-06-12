package TietoRus.system.helpers.models;


public class BookingChargesLinesSat {
    private Double count;
    private String paymentTermCode;
    private String contNr;
    private String units;
    private String chargeGroup;
    private Double currencyRoe;
    private String currency;
    private String chargeName;
    private Double amount;
    private int chargeNr;

    public BookingChargesLinesSat(Double count, String paymentTermCode, String contNr, String units, String chargeGroup, Double currencyRoe, String currency,
                                  String chargeName, Double amount, int chargeNr) {
        this.count = count;
        this.paymentTermCode = paymentTermCode;
        this.contNr = contNr;
        this.units = units;
        this.chargeGroup = chargeGroup;
        this.currencyRoe = currencyRoe;
        this.currency = currency;
        this.chargeName = chargeName;
        this.amount = amount;
        this.chargeNr = chargeNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingChargesLinesSat that = (BookingChargesLinesSat) o;

        if (chargeNr != that.chargeNr) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (paymentTermCode != null ? !paymentTermCode.equals(that.paymentTermCode) : that.paymentTermCode != null)
            return false;
        if (contNr != null ? !contNr.equals(that.contNr) : that.contNr != null) return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;
        if (chargeGroup != null ? !chargeGroup.equals(that.chargeGroup) : that.chargeGroup != null) return false;
        if (currencyRoe != null ? !currencyRoe.equals(that.currencyRoe) : that.currencyRoe != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (chargeName != null ? !chargeName.equals(that.chargeName) : that.chargeName != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (paymentTermCode != null ? paymentTermCode.hashCode() : 0);
        result = 31 * result + (contNr != null ? contNr.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (chargeGroup != null ? chargeGroup.hashCode() : 0);
        result = 31 * result + (currencyRoe != null ? currencyRoe.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (chargeName != null ? chargeName.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + chargeNr;
        return result;
    }

    @Override
    public String toString() {
        return "BookingChargesLinesSat{" +
                "count=" + count +
                ", paymentTermCode='" + paymentTermCode + '\'' +
                ", contNr='" + contNr + '\'' +
                ", units='" + units + '\'' +
                ", chargeGroup='" + chargeGroup + '\'' +
                ", currencyRoe=" + currencyRoe +
                ", currency='" + currency + '\'' +
                ", chargeName='" + chargeName + '\'' +
                ", amount=" + amount +
                ", chargeNr=" + chargeNr +
                '}';
    }
}
