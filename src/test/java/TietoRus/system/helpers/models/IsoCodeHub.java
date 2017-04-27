package TietoRus.system.helpers.models;

/**
 * SAT'а для IsoCode нет. Данный хаб "специфичен". Он будет использован для создания link'а lnkContainerTypeIsoCode
 */
public class IsoCodeHub {
    private String isoCode;
    private int accessCompanyId;
    private int srcSystemId;

    public IsoCodeHub(String isoCode, int accessCompanyId, int srcSystemId) {
        this.isoCode = isoCode;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsoCodeHub that = (IsoCodeHub) o;

        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        return isoCode != null ? isoCode.equals(that.isoCode) : that.isoCode == null;
    }

    @Override
    public int hashCode() {
        int result = isoCode != null ? isoCode.hashCode() : 0;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "IsoCodeHub{" +
                "isoCode='" + isoCode + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
