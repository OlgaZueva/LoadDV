package TietoRus.system.helpers.models;


public class ContainerMovesHub {
    private int moveId;
    private int accessCompanyId;
    private int srcSystemId;

    public ContainerMovesHub(int moveId, int accessCompanyId, int srcSystemId) {
        this.moveId = moveId;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerMovesHub that = (ContainerMovesHub) o;

        if (moveId != that.moveId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = moveId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerMovesHub{" +
                "moveId=" + moveId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
