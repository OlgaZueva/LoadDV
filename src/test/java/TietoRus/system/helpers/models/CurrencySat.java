package TietoRus.system.helpers.models;

public class CurrencySat {
    private String currencyName;

    public CurrencySat (String currencyName){
        this.currencyName = currencyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencySat that = (CurrencySat) o;

        return currencyName != null ? currencyName.equals(that.currencyName) : that.currencyName == null;
    }

    @Override
    public int hashCode() {
        return currencyName != null ? currencyName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CurrencySat{" +
                "currencyName='" + currencyName + '\'' +
                '}';
    }
}
