package TietoRus.system.helpers.models;


public class ControllingOfficeHub {
    private int officeId;
    private int companyId;
    private int srcSystemId;

    public ControllingOfficeHub(int officeId, int companyId, int srcSystemId) {
        this.officeId = officeId;
        this.companyId = companyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControllingOfficeHub that = (ControllingOfficeHub) o;

        if (officeId != that.officeId) return false;
        if (companyId != that.companyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = officeId;
        result = 31 * result + companyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ControllingOfficeHub{" +
                "officeId=" + officeId +
                ", companyId=" + companyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
