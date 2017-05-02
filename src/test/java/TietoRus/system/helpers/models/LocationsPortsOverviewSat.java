package TietoRus.system.helpers.models;


import java.util.Date;

public class LocationsPortsOverviewSat {
    private String liner;
    private String linerAssistant;
    private String destinationRegion;
    private String tradeNameGvaForEmedStat;
    private String isLandlockedCountry;
    private String agencyRegion;
    private String agencyCode;
    private String ctsTier5;
    private String ctsTier4Location;
    private String ctsTier4Country;
    private String dkExpTeam;
    private String isDeepSea;
    private Date fromDate;

    public LocationsPortsOverviewSat(String liner, String linerAssistant, String destinationRegion, String tradeNameGvaForEmedStat, String isLandlockedCountry, String agencyRegion,
            String agencyCode, String ctsTier5, String ctsTier4Location,  String ctsTier4Country, String dkExpTeam, String isDeepSea, Date fromDate){
        this.liner = liner;
        this.linerAssistant =linerAssistant;
        this.destinationRegion = destinationRegion;
        this.tradeNameGvaForEmedStat =tradeNameGvaForEmedStat;
        this.isLandlockedCountry = isLandlockedCountry;
        this.agencyRegion = agencyRegion;
        this.agencyCode = agencyCode;
        this.ctsTier5 =ctsTier5;
        this.ctsTier4Location =ctsTier4Location;
        this.ctsTier4Country = ctsTier4Country;
        this.dkExpTeam = dkExpTeam;
        this.isDeepSea =isDeepSea;
        this.fromDate =fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationsPortsOverviewSat that = (LocationsPortsOverviewSat) o;

        if (liner != null ? !liner.equals(that.liner) : that.liner != null) return false;
        if (linerAssistant != null ? !linerAssistant.equals(that.linerAssistant) : that.linerAssistant != null)
            return false;
        if (destinationRegion != null ? !destinationRegion.equals(that.destinationRegion) : that.destinationRegion != null)
            return false;
        if (tradeNameGvaForEmedStat != null ? !tradeNameGvaForEmedStat.equals(that.tradeNameGvaForEmedStat) : that.tradeNameGvaForEmedStat != null)
            return false;
        if (isLandlockedCountry != null ? !isLandlockedCountry.equals(that.isLandlockedCountry) : that.isLandlockedCountry != null)
            return false;
        if (agencyRegion != null ? !agencyRegion.equals(that.agencyRegion) : that.agencyRegion != null) return false;
        if (agencyCode != null ? !agencyCode.equals(that.agencyCode) : that.agencyCode != null) return false;
        if (ctsTier5 != null ? !ctsTier5.equals(that.ctsTier5) : that.ctsTier5 != null) return false;
        if (ctsTier4Location != null ? !ctsTier4Location.equals(that.ctsTier4Location) : that.ctsTier4Location != null)
            return false;
        if (ctsTier4Country != null ? !ctsTier4Country.equals(that.ctsTier4Country) : that.ctsTier4Country != null)
            return false;
        if (dkExpTeam != null ? !dkExpTeam.equals(that.dkExpTeam) : that.dkExpTeam != null) return false;
        if (isDeepSea != null ? !isDeepSea.equals(that.isDeepSea) : that.isDeepSea != null) return false;
        return fromDate != null ? fromDate.equals(that.fromDate) : that.fromDate == null;
    }

    @Override
    public int hashCode() {
        int result = liner != null ? liner.hashCode() : 0;
        result = 31 * result + (linerAssistant != null ? linerAssistant.hashCode() : 0);
        result = 31 * result + (destinationRegion != null ? destinationRegion.hashCode() : 0);
        result = 31 * result + (tradeNameGvaForEmedStat != null ? tradeNameGvaForEmedStat.hashCode() : 0);
        result = 31 * result + (isLandlockedCountry != null ? isLandlockedCountry.hashCode() : 0);
        result = 31 * result + (agencyRegion != null ? agencyRegion.hashCode() : 0);
        result = 31 * result + (agencyCode != null ? agencyCode.hashCode() : 0);
        result = 31 * result + (ctsTier5 != null ? ctsTier5.hashCode() : 0);
        result = 31 * result + (ctsTier4Location != null ? ctsTier4Location.hashCode() : 0);
        result = 31 * result + (ctsTier4Country != null ? ctsTier4Country.hashCode() : 0);
        result = 31 * result + (dkExpTeam != null ? dkExpTeam.hashCode() : 0);
        result = 31 * result + (isDeepSea != null ? isDeepSea.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LocationsPortsOverviewSat{" +
                "liner='" + liner + '\'' +
                ", linerAssistant='" + linerAssistant + '\'' +
                ", destinationRegion='" + destinationRegion + '\'' +
                ", tradeNameGvaForEmedStat='" + tradeNameGvaForEmedStat + '\'' +
                ", isLandlockedCountry='" + isLandlockedCountry + '\'' +
                ", agencyRegion='" + agencyRegion + '\'' +
                ", agencyCode='" + agencyCode + '\'' +
                ", ctsTier5='" + ctsTier5 + '\'' +
                ", ctsTier4Location='" + ctsTier4Location + '\'' +
                ", ctsTier4Country='" + ctsTier4Country + '\'' +
                ", dkExpTeam='" + dkExpTeam + '\'' +
                ", isDeepSea='" + isDeepSea + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }
}
