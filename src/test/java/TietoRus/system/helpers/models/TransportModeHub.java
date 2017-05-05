package TietoRus.system.helpers.models;


public class TransportModeHub {
    private String carrierCode;
    private String dtxCode;
    private String segmentCode;
    private int accessCompanyId;
    private String transportModeCode;
    private int srcSystemId;

    public TransportModeHub(String carrierCode, String dtxCode, String segmentCode, int accessCompanyId, String transportModeCode, int srcSystemId) {
        this.carrierCode = carrierCode;
        this.dtxCode = dtxCode;
        this.segmentCode = segmentCode;
        this.accessCompanyId = accessCompanyId;
        this.transportModeCode = transportModeCode;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportModeHub that = (TransportModeHub) o;

        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (carrierCode != null ? !carrierCode.equals(that.carrierCode) : that.carrierCode != null) return false;
        if (dtxCode != null ? !dtxCode.equals(that.dtxCode) : that.dtxCode != null) return false;
        if (segmentCode != null ? !segmentCode.equals(that.segmentCode) : that.segmentCode != null) return false;
        return transportModeCode != null ? transportModeCode.equals(that.transportModeCode) : that.transportModeCode == null;
    }

    @Override
    public int hashCode() {
        int result = carrierCode != null ? carrierCode.hashCode() : 0;
        result = 31 * result + (dtxCode != null ? dtxCode.hashCode() : 0);
        result = 31 * result + (segmentCode != null ? segmentCode.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + (transportModeCode != null ? transportModeCode.hashCode() : 0);
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "TransportModeHub{" +
                "carrierCode='" + carrierCode + '\'' +
                ", dtxCode='" + dtxCode + '\'' +
                ", segmentCode='" + segmentCode + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", transportModeCode='" + transportModeCode + '\'' +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
