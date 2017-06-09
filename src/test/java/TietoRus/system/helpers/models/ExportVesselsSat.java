package TietoRus.system.helpers.models;


import java.sql.Date;

public class ExportVesselsSat {
    private Date actualSailDate;
    private String gvaTrade;
    private String oceanTrade;
    private String vesselCode;
    private String vesselName;
    private String voyageNr;
    private int week;

    public ExportVesselsSat(Date actualSailDate, String gvaTrade, String oceanTrade, String vesselCode, String vesselName, String voyageNr, int week) {
        this.actualSailDate =actualSailDate;
        this.gvaTrade = gvaTrade;
        this.oceanTrade = oceanTrade;
        this.vesselCode = vesselCode;
        this.vesselName = vesselName;
        this.voyageNr = voyageNr;
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportVesselsSat that = (ExportVesselsSat) o;

        if (week != that.week) return false;
        if (actualSailDate != null ? !actualSailDate.equals(that.actualSailDate) : that.actualSailDate != null)
            return false;
        if (gvaTrade != null ? !gvaTrade.equals(that.gvaTrade) : that.gvaTrade != null) return false;
        if (oceanTrade != null ? !oceanTrade.equals(that.oceanTrade) : that.oceanTrade != null) return false;
        if (vesselCode != null ? !vesselCode.equals(that.vesselCode) : that.vesselCode != null) return false;
        if (vesselName != null ? !vesselName.equals(that.vesselName) : that.vesselName != null) return false;
        return voyageNr != null ? voyageNr.equals(that.voyageNr) : that.voyageNr == null;
    }

    @Override
    public int hashCode() {
        int result = actualSailDate != null ? actualSailDate.hashCode() : 0;
        result = 31 * result + (gvaTrade != null ? gvaTrade.hashCode() : 0);
        result = 31 * result + (oceanTrade != null ? oceanTrade.hashCode() : 0);
        result = 31 * result + (vesselCode != null ? vesselCode.hashCode() : 0);
        result = 31 * result + (vesselName != null ? vesselName.hashCode() : 0);
        result = 31 * result + (voyageNr != null ? voyageNr.hashCode() : 0);
        result = 31 * result + week;
        return result;
    }

    @Override
    public String toString() {
        return "ExportVesselsSat{" +
                "actualSailDate=" + actualSailDate +
                ", gvaTrade='" + gvaTrade + '\'' +
                ", oceanTrade='" + oceanTrade + '\'' +
                ", vesselCode='" + vesselCode + '\'' +
                ", vesselName='" + vesselName + '\'' +
                ", voyageNr='" + voyageNr + '\'' +
                ", week=" + week +
                '}';
    }
}
