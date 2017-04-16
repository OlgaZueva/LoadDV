package TietoRus.system.helpers.models;

import java.util.Date;

public class FileLinerSat {
    private int callId;
    private Date expPolDate;
    private Date impPodDate;
    private Date proformaDate;
    private String voyageNr;
    private int sailingWeek;
    private String vesselName;
    private Date closeDate;
    private Date openDate;
    private String status;
    private String temporaryFlag;

    public FileLinerSat (int callId, Date expPolDate, Date impPodDate, Date proformaDate, String voyageNr, int sailingWeek, String vesselName, Date closeDate, Date openDate,
            String status, String temporaryFlag) {
        this.callId =callId;
        this.expPolDate =expPolDate;
        this.impPodDate = impPodDate;
        this.proformaDate = proformaDate;
        this.voyageNr = voyageNr;
        this.sailingWeek = sailingWeek;
        this.vesselName = vesselName;
        this.closeDate = closeDate;
        this.openDate = openDate;
        this.status = status;
        this.temporaryFlag = temporaryFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileLinerSat that = (FileLinerSat) o;

        if (callId != that.callId) return false;
        if (sailingWeek != that.sailingWeek) return false;
        if (expPolDate != null ? !expPolDate.equals(that.expPolDate) : that.expPolDate != null) return false;
        if (impPodDate != null ? !impPodDate.equals(that.impPodDate) : that.impPodDate != null) return false;
        if (proformaDate != null ? !proformaDate.equals(that.proformaDate) : that.proformaDate != null) return false;
        if (voyageNr != null ? !voyageNr.equals(that.voyageNr) : that.voyageNr != null) return false;
        if (vesselName != null ? !vesselName.equals(that.vesselName) : that.vesselName != null) return false;
        if (closeDate != null ? !closeDate.equals(that.closeDate) : that.closeDate != null) return false;
        if (openDate != null ? !openDate.equals(that.openDate) : that.openDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return temporaryFlag != null ? temporaryFlag.equals(that.temporaryFlag) : that.temporaryFlag == null;
    }

    @Override
    public int hashCode() {
        int result = callId;
        result = 31 * result + (expPolDate != null ? expPolDate.hashCode() : 0);
        result = 31 * result + (impPodDate != null ? impPodDate.hashCode() : 0);
        result = 31 * result + (proformaDate != null ? proformaDate.hashCode() : 0);
        result = 31 * result + (voyageNr != null ? voyageNr.hashCode() : 0);
        result = 31 * result + sailingWeek;
        result = 31 * result + (vesselName != null ? vesselName.hashCode() : 0);
        result = 31 * result + (closeDate != null ? closeDate.hashCode() : 0);
        result = 31 * result + (openDate != null ? openDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (temporaryFlag != null ? temporaryFlag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileLinerSat{" +
                "callId=" + callId +
                ", expPolDate=" + expPolDate +
                ", impPodDate=" + impPodDate +
                ", proformaDate=" + proformaDate +
                ", voyageNr='" + voyageNr + '\'' +
                ", sailingWeek=" + sailingWeek +
                ", vesselName='" + vesselName + '\'' +
                ", closeDate=" + closeDate +
                ", openDate=" + openDate +
                ", status='" + status + '\'' +
                ", temporaryFlag='" + temporaryFlag + '\'' +
                '}';
    }
}
