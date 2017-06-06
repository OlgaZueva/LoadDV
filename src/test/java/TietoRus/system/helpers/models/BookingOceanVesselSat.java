package TietoRus.system.helpers.models;

import java.sql.Date;

public class BookingOceanVesselSat {
    private String blockStowCode;
    private String createdBy;
    private Date creationDate;

    public BookingOceanVesselSat(String blockStowCode, String createdBy, Date creationDate) {
        this.blockStowCode = blockStowCode;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingOceanVesselSat that = (BookingOceanVesselSat) o;

        if (blockStowCode != null ? !blockStowCode.equals(that.blockStowCode) : that.blockStowCode != null)
            return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return creationDate != null ? creationDate.equals(that.creationDate) : that.creationDate == null;
    }

    @Override
    public int hashCode() {
        int result = blockStowCode != null ? blockStowCode.hashCode() : 0;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingOceanVesselSat{" +
                "blockStowCode='" + blockStowCode + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
