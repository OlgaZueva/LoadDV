package TietoRus.system.helpers.models;


import java.sql.Date;

public class DailyROEHub {
    private Date roeDate;
    private int sequenceNr;
    private int accessCompanyId;
    private int srcSystemId;

    public DailyROEHub(Date roeDate, int sequenceNr, int accessCompanyId, int srcSystemId) {
        this.roeDate = roeDate;
        this.sequenceNr = sequenceNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyROEHub that = (DailyROEHub) o;

        if (sequenceNr != that.sequenceNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        return roeDate != null ? roeDate.equals(that.roeDate) : that.roeDate == null;
    }

    @Override
    public int hashCode() {
        int result = roeDate != null ? roeDate.hashCode() : 0;
        result = 31 * result + sequenceNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "DailyROEHub{" +
                "roeDate=" + roeDate +
                ", sequenceNr=" + sequenceNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
