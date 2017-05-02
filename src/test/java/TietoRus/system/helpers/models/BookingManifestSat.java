package TietoRus.system.helpers.models;

import java.util.Date;


public class BookingManifestSat {
    private String correctorReason;
    private String correctorType;
    private String createdBy;
    private Date creationDate;
    private String manifestType;
    private int manifestFileId;
    private String rolloverServiceCode;
    private int rolloverFileLiner;
    private String isSplit;
    private String status;

    public BookingManifestSat(String correctorReason, String correctorType, String createdBy, Date creationDate, String manifestType, int manifestFileId, String rolloverServiceCode,
                              int rolloverFileLiner, String isSplit, String status) {
        this.correctorReason = correctorReason;
        this.correctorType = correctorType;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.manifestType = manifestType;
        this.manifestFileId = manifestFileId;
        this.rolloverServiceCode = rolloverServiceCode;
        this.rolloverFileLiner = rolloverFileLiner;
        this.isSplit = isSplit;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingManifestSat that = (BookingManifestSat) o;

        if (manifestFileId != that.manifestFileId) return false;
        if (rolloverFileLiner != that.rolloverFileLiner) return false;
        if (correctorReason != null ? !correctorReason.equals(that.correctorReason) : that.correctorReason != null)
            return false;
        if (correctorType != null ? !correctorType.equals(that.correctorType) : that.correctorType != null)
            return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (manifestType != null ? !manifestType.equals(that.manifestType) : that.manifestType != null) return false;
        if (rolloverServiceCode != null ? !rolloverServiceCode.equals(that.rolloverServiceCode) : that.rolloverServiceCode != null)
            return false;
        if (isSplit != null ? !isSplit.equals(that.isSplit) : that.isSplit != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = correctorReason != null ? correctorReason.hashCode() : 0;
        result = 31 * result + (correctorType != null ? correctorType.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (manifestType != null ? manifestType.hashCode() : 0);
        result = 31 * result + manifestFileId;
        result = 31 * result + (rolloverServiceCode != null ? rolloverServiceCode.hashCode() : 0);
        result = 31 * result + rolloverFileLiner;
        result = 31 * result + (isSplit != null ? isSplit.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingManifestSat{" +
                "correctorReason='" + correctorReason + '\'' +
                ", correctorType='" + correctorType + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", manifestType='" + manifestType + '\'' +
                ", manifestFileId=" + manifestFileId +
                ", rolloverServiceCode='" + rolloverServiceCode + '\'' +
                ", rolloverFileLiner=" + rolloverFileLiner +
                ", isSplit='" + isSplit + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
