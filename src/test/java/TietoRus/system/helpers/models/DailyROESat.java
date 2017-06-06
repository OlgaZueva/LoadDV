package TietoRus.system.helpers.models;

public class DailyROESat {
    private Double rate;
    private String currencyCode;

    public DailyROESat(Double rate, String currencyCode) {
        this.rate = rate;
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyROESat that = (DailyROESat) o;

        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        return currencyCode != null ? currencyCode.equals(that.currencyCode) : that.currencyCode == null;
    }

    @Override
    public int hashCode() {
        int result = rate != null ? rate.hashCode() : 0;
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DailyROESat{" +
                "rate=" + rate +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
