package TietoRus.system.helpers.models;


public class ContainerTypeHub {
    private String containerTypeCode;
    private int accessCompanyId;
    private int srcSystemId;

    public ContainerTypeHub (String containerTypeCode,  int accessCompanyId, int srcSystemId){
        this.containerTypeCode = containerTypeCode;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerTypeHub that = (ContainerTypeHub) o;

        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        return containerTypeCode != null ? containerTypeCode.equals(that.containerTypeCode) : that.containerTypeCode == null;
    }

    @Override
    public int hashCode() {
        int result = containerTypeCode != null ? containerTypeCode.hashCode() : 0;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerTypeHub{" +
                "containerTypeCode='" + containerTypeCode + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
