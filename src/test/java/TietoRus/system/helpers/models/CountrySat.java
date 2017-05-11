package TietoRus.system.helpers.models;


public class CountrySat {
    private String countryName;

    public CountrySat(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountrySat that = (CountrySat) o;

        return countryName != null ? countryName.equals(that.countryName) : that.countryName == null;
    }

    @Override
    public int hashCode() {
        return countryName != null ? countryName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CountrySat{" +
                "countryName='" + countryName + '\'' +
                '}';
    }
}
