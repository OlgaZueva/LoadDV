package TietoRus.system.helpers.models;


public class VesselRegistrySat {
    private Double lloydsNr;
    private String callSign;
    private String vesselName;

    public VesselRegistrySat (Double lloydsNr, String callSign, String vesselName){
     this.lloydsNr =lloydsNr;
     this.callSign = callSign;
     this.vesselName = vesselName;
 }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VesselRegistrySat that = (VesselRegistrySat) o;

        if (lloydsNr != null ? !lloydsNr.equals(that.lloydsNr) : that.lloydsNr != null) return false;
        if (callSign != null ? !callSign.equals(that.callSign) : that.callSign != null) return false;
        return vesselName != null ? vesselName.equals(that.vesselName) : that.vesselName == null;
    }

    @Override
    public int hashCode() {
        int result = lloydsNr != null ? lloydsNr.hashCode() : 0;
        result = 31 * result + (callSign != null ? callSign.hashCode() : 0);
        result = 31 * result + (vesselName != null ? vesselName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VesselRegistrySat{" +
                "lloydsNr=" + lloydsNr +
                ", callSign='" + callSign + '\'' +
                ", vesselName='" + vesselName + '\'' +
                '}';
    }
}
