package TietoRus.system.helpers.models;


public class ShipItConstantsHub {
    private String cName;
    private int srcSystemId;

    public ShipItConstantsHub(String cName, int srcSystemId) {
        this.cName = cName;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipItConstantsHub that = (ShipItConstantsHub) o;

        if (srcSystemId != that.srcSystemId) return false;
        return cName != null ? cName.equals(that.cName) : that.cName == null;
    }

    @Override
    public int hashCode() {
        int result = cName != null ? cName.hashCode() : 0;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ShipItConstantsHub{" +
                "cName='" + cName + '\'' +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
