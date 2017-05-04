package TietoRus.system.helpers.models;

import java.sql.Date;

public class BookingEventsSat {
    private String serviceCode;
    private int bookingNr;
    private int eventCode;
    private String eventStatus;
    private Date eventTime;
    private String eventUser;
    private String remark;
    private String remark2;
    private String remark3;
    private int fileLinerNr;

    public BookingEventsSat(String serviceCode, int bookingNr, int eventCode, String eventStatus, Date eventTime, String eventUser, String remark, String remark2,
            String remark3, int fileLinerNr){
        this.serviceCode =serviceCode;
        this.bookingNr= bookingNr;
        this.eventCode = eventCode;
        this.eventStatus = eventStatus;
        this.eventTime = eventTime;
        this.eventUser = eventUser;
        this.remark = remark;
        this.remark2 = remark2;
        this.remark3 = remark3;
        this.fileLinerNr = fileLinerNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEventsSat that = (BookingEventsSat) o;

        if (bookingNr != that.bookingNr) return false;
        if (eventCode != that.eventCode) return false;
        if (fileLinerNr != that.fileLinerNr) return false;
        if (serviceCode != null ? !serviceCode.equals(that.serviceCode) : that.serviceCode != null) return false;
        if (eventStatus != null ? !eventStatus.equals(that.eventStatus) : that.eventStatus != null) return false;
        if (eventTime != null ? !eventTime.equals(that.eventTime) : that.eventTime != null) return false;
        if (eventUser != null ? !eventUser.equals(that.eventUser) : that.eventUser != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (remark2 != null ? !remark2.equals(that.remark2) : that.remark2 != null) return false;
        return remark3 != null ? remark3.equals(that.remark3) : that.remark3 == null;
    }

    @Override
    public int hashCode() {
        int result = serviceCode != null ? serviceCode.hashCode() : 0;
        result = 31 * result + bookingNr;
        result = 31 * result + eventCode;
        result = 31 * result + (eventStatus != null ? eventStatus.hashCode() : 0);
        result = 31 * result + (eventTime != null ? eventTime.hashCode() : 0);
        result = 31 * result + (eventUser != null ? eventUser.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (remark2 != null ? remark2.hashCode() : 0);
        result = 31 * result + (remark3 != null ? remark3.hashCode() : 0);
        result = 31 * result + fileLinerNr;
        return result;
    }

    @Override
    public String toString() {
        return "BookingEventsSat{" +
                "serviceCode='" + serviceCode + '\'' +
                ", bookingNr=" + bookingNr +
                ", eventCode=" + eventCode +
                ", eventStatus=" + eventStatus +
                ", eventTime='" + eventTime + '\'' +
                ", eventUser='" + eventUser + '\'' +
                ", remark='" + remark + '\'' +
                ", remark2='" + remark2 + '\'' +
                ", remark3='" + remark3 + '\'' +
                ", fileLinerNr=" + fileLinerNr +
                '}';
    }
}
