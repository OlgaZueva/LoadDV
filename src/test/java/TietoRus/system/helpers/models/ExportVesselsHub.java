package TietoRus.system.helpers.models;


public class ExportVesselsHub {
    private int expVesselId;
    private int accessCompanyId;
    private int srcSystemId;

    public ExportVesselsHub(int expVesselId, int accessCompanyId, int srcSystemId) {
        this.expVesselId = expVesselId;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportVesselsHub that = (ExportVesselsHub) o;

        if (expVesselId != that.expVesselId) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = expVesselId;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ExportVesselsHub{" +
                "expVesselId=" + expVesselId +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
