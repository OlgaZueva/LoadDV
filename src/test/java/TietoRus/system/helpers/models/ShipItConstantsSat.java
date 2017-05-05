package TietoRus.system.helpers.models;


public class ShipItConstantsSat {
    private String cVal;

    public ShipItConstantsSat(String cVal) {
        this.cVal = cVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipItConstantsSat that = (ShipItConstantsSat) o;

        return cVal != null ? cVal.equals(that.cVal) : that.cVal == null;
    }

    @Override
    public int hashCode() {
        return cVal != null ? cVal.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ShipItConstantsSat{" +
                "cVal='" + cVal + '\'' +
                '}';
    }
}
