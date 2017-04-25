package TietoRus.system.helpers.models;

// Для BookingCargoCharges hub'ом является BookingCargoHub, а источником данных таблица saGetCharges
public class BookingCargoChargesSat {
    private Double price;
    private Double unitCount;
    private String allPaymentTerms;
    private String units;
    private int positionNr;
    private String chargeGroup;
    private Double roe;
    private Double amount;

 public BookingCargoChargesSat (Double price, Double unitCount, String allPaymentTerms, String units, int positionNr, String chargeGroup, Double roe, Double amount){
     this.price = price;
     this.unitCount = unitCount;
     this.allPaymentTerms = allPaymentTerms;
     this.units = units;
     this.positionNr = positionNr;
     this.chargeGroup = chargeGroup;
     this.roe = roe;
     this.amount =amount;
 }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingCargoChargesSat that = (BookingCargoChargesSat) o;

        if (positionNr != that.positionNr) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (unitCount != null ? !unitCount.equals(that.unitCount) : that.unitCount != null) return false;
        if (allPaymentTerms != null ? !allPaymentTerms.equals(that.allPaymentTerms) : that.allPaymentTerms != null)
            return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;
        if (chargeGroup != null ? !chargeGroup.equals(that.chargeGroup) : that.chargeGroup != null) return false;
        if (roe != null ? !roe.equals(that.roe) : that.roe != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (unitCount != null ? unitCount.hashCode() : 0);
        result = 31 * result + (allPaymentTerms != null ? allPaymentTerms.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + positionNr;
        result = 31 * result + (chargeGroup != null ? chargeGroup.hashCode() : 0);
        result = 31 * result + (roe != null ? roe.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingCargoChargesSat{" +
                "price=" + price +
                ", unitCount=" + unitCount +
                ", allPaymentTerms='" + allPaymentTerms + '\'' +
                ", units='" + units + '\'' +
                ", positionNr=" + positionNr +
                ", chargeGroup='" + chargeGroup + '\'' +
                ", roe=" + roe +
                ", amount=" + amount +
                '}';
    }
}

