package TietoRus.system.helpers.models;


public class LocationsPortsOverviewHub {
    private String locationCode;
    private int srcSystemId;

    public LocationsPortsOverviewHub (String locationCode,  int srcSystemId){
        this.locationCode=locationCode;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationsPortsOverviewHub that = (LocationsPortsOverviewHub) o;

        if (srcSystemId != that.srcSystemId) return false;
        return locationCode != null ? locationCode.equals(that.locationCode) : that.locationCode == null;
    }

    @Override
    public int hashCode() {
        int result = locationCode != null ? locationCode.hashCode() : 0;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "LocationsPortsOverviewHub{" +
                "locationCode='" + locationCode + '\'' +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
