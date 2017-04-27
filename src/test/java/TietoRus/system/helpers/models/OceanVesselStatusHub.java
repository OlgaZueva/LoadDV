package TietoRus.system.helpers.models;

public class OceanVesselStatusHub {

    private String carrierCode;
    private String serviceCode;
    private String segmentCode;
    private int accessCompanyId;
    private String statusCode;
    private int srcSystemId;

    public OceanVesselStatusHub(String carrierCode, String serviceCode, String segmentCode, int accessCompanyId, String statusCode, int srcSystemId) {
        this.carrierCode = carrierCode;
        this.serviceCode = serviceCode;
        this.segmentCode = segmentCode;
        this.accessCompanyId = accessCompanyId;
        this.statusCode = statusCode;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OceanVesselStatusHub oceanVesselStatusHub = (OceanVesselStatusHub) o;

        if (accessCompanyId != oceanVesselStatusHub.accessCompanyId) return false;
        if (srcSystemId != oceanVesselStatusHub.srcSystemId) return false;
        if (carrierCode != null ? !carrierCode.equals(oceanVesselStatusHub.carrierCode) : oceanVesselStatusHub.carrierCode != null) return false;
        if (serviceCode != null ? !serviceCode.equals(oceanVesselStatusHub.serviceCode) : oceanVesselStatusHub.serviceCode != null) return false;
        if (segmentCode != null ? !segmentCode.equals(oceanVesselStatusHub.segmentCode) : oceanVesselStatusHub.segmentCode != null) return false;
        return statusCode != null ? statusCode.equals(oceanVesselStatusHub.statusCode) : oceanVesselStatusHub.statusCode == null;
    }

    @Override
    public int hashCode() {
        int result = carrierCode != null ? carrierCode.hashCode() : 0;
        result = 31 * result + (serviceCode != null ? serviceCode.hashCode() : 0);
        result = 31 * result + (segmentCode != null ? segmentCode.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "OceanVesselStatusHub{" +
                "carrierCode='" + carrierCode + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", segmentCode='" + segmentCode + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", statusCode='" + statusCode + '\'' +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
