package TietoRus.system.helpers.models;

public class ContainerLocationSat {
    private String contLocationName;

    public ContainerLocationSat(String contLocationName) {
        this.contLocationName = contLocationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerLocationSat that = (ContainerLocationSat) o;

        return contLocationName != null ? contLocationName.equals(that.contLocationName) : that.contLocationName == null;
    }

    @Override
    public int hashCode() {
        return contLocationName != null ? contLocationName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ContainerLocationSat{" +
                "contLocationName='" + contLocationName + '\'' +
                '}';
    }
}
