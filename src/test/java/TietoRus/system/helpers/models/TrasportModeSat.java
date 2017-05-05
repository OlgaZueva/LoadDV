package TietoRus.system.helpers.models;


public class TrasportModeSat {
    String transportModeName;

    public TrasportModeSat(String transportModeName) {
        this.transportModeName = transportModeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrasportModeSat that = (TrasportModeSat) o;

        return transportModeName != null ? transportModeName.equals(that.transportModeName) : that.transportModeName == null;
    }

    @Override
    public int hashCode() {
        return transportModeName != null ? transportModeName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TrasportModeSat{" +
                "transportModeName='" + transportModeName + '\'' +
                '}';
    }
}
