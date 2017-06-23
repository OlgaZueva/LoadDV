package TietoRus.system.helpers.models;

import java.sql.Date;

/**
 * Sat'а нет.
 */
public class WeekendsHolidaysHub {
    private String serviceCode;
    private  Date date;
    private int accessCompanyId;
    private int srcSystemId;

    public  WeekendsHolidaysHub (String serviceCode,  Date date, int accessCompanyId, int srcSystemId) {
        this.serviceCode =serviceCode;
        this.date =date;
        this.accessCompanyId =accessCompanyId;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeekendsHolidaysHub that = (WeekendsHolidaysHub) o;

        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (serviceCode != null ? !serviceCode.equals(that.serviceCode) : that.serviceCode != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = serviceCode != null ? serviceCode.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "WeekendsHolidaysHub{" +
                "serviceCode='" + serviceCode + '\'' +
                ", date=" + date +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
