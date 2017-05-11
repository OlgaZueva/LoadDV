package TietoRus.system.helpers.models;

import java.sql.Date;

public class ContainerMovesSat {
    private String remarks;
    private int bookingNr;
    private String containerNr;
    private String containerSize;
    private String containerType;
    private String createdBy;
    private Date creationDate;
    private String damaged;
    private Date moveDate;
    private String destination;
    private String moveCode;
    private String depotLocation;
    private String extendedLocation;
    private Date reportedToGvaDate;
    private int reportToGvaNr;
    private String reportStatus;
    private String reportToGvaModule;
    private String status;
    private String moveTime;

    public ContainerMovesSat(String remarks, int bookingNr, String containerNr, String containerSize, String containerType, String createdBy, Date creationDate,
                             String damaged, Date moveDate, String destination, String moveCode, String depotLocation, String extendedLocation,
                             Date reportedToGvaDate, int reportToGvaNr, String reportStatus, String reportToGvaModule, String status, String moveTime) {
        this.remarks = remarks;
        this.bookingNr = bookingNr;
        this.containerNr = containerNr;
        this.containerSize = containerSize;
        this.containerType = containerType;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.damaged = damaged;
        this.moveDate = moveDate;
        this.destination = destination;
        this.moveCode = moveCode;
        this.depotLocation = depotLocation;
        this.extendedLocation = extendedLocation;
        this.reportedToGvaDate = reportedToGvaDate;
        this.reportToGvaNr = reportToGvaNr;
        this.reportStatus = reportStatus;
        this.reportToGvaModule = reportToGvaModule;
        this.status = status;
        this.moveTime = moveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerMovesSat that = (ContainerMovesSat) o;

        if (bookingNr != that.bookingNr) return false;
        if (reportToGvaNr != that.reportToGvaNr) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (containerNr != null ? !containerNr.equals(that.containerNr) : that.containerNr != null) return false;
        if (containerSize != null ? !containerSize.equals(that.containerSize) : that.containerSize != null)
            return false;
        if (containerType != null ? !containerType.equals(that.containerType) : that.containerType != null)
            return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (damaged != null ? !damaged.equals(that.damaged) : that.damaged != null) return false;
        if (moveDate != null ? !moveDate.equals(that.moveDate) : that.moveDate != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (moveCode != null ? !moveCode.equals(that.moveCode) : that.moveCode != null) return false;
        if (depotLocation != null ? !depotLocation.equals(that.depotLocation) : that.depotLocation != null)
            return false;
        if (extendedLocation != null ? !extendedLocation.equals(that.extendedLocation) : that.extendedLocation != null)
            return false;
        if (reportedToGvaDate != null ? !reportedToGvaDate.equals(that.reportedToGvaDate) : that.reportedToGvaDate != null)
            return false;
        if (reportStatus != null ? !reportStatus.equals(that.reportStatus) : that.reportStatus != null) return false;
        if (reportToGvaModule != null ? !reportToGvaModule.equals(that.reportToGvaModule) : that.reportToGvaModule != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return moveTime != null ? moveTime.equals(that.moveTime) : that.moveTime == null;
    }

    @Override
    public int hashCode() {
        int result = remarks != null ? remarks.hashCode() : 0;
        result = 31 * result + bookingNr;
        result = 31 * result + (containerNr != null ? containerNr.hashCode() : 0);
        result = 31 * result + (containerSize != null ? containerSize.hashCode() : 0);
        result = 31 * result + (containerType != null ? containerType.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (damaged != null ? damaged.hashCode() : 0);
        result = 31 * result + (moveDate != null ? moveDate.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (moveCode != null ? moveCode.hashCode() : 0);
        result = 31 * result + (depotLocation != null ? depotLocation.hashCode() : 0);
        result = 31 * result + (extendedLocation != null ? extendedLocation.hashCode() : 0);
        result = 31 * result + (reportedToGvaDate != null ? reportedToGvaDate.hashCode() : 0);
        result = 31 * result + reportToGvaNr;
        result = 31 * result + (reportStatus != null ? reportStatus.hashCode() : 0);
        result = 31 * result + (reportToGvaModule != null ? reportToGvaModule.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (moveTime != null ? moveTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContainerMovesSat{" +
                "remarks='" + remarks + '\'' +
                ", bookingNr=" + bookingNr +
                ", containerNr='" + containerNr + '\'' +
                ", containerSize='" + containerSize + '\'' +
                ", containerType='" + containerType + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", damaged='" + damaged + '\'' +
                ", moveDate=" + moveDate +
                ", destination='" + destination + '\'' +
                ", moveCode='" + moveCode + '\'' +
                ", depotLocation='" + depotLocation + '\'' +
                ", extendedLocation='" + extendedLocation + '\'' +
                ", reportedToGvaDate=" + reportedToGvaDate +
                ", reportToGvaNr=" + reportToGvaNr +
                ", reportStatus='" + reportStatus + '\'' +
                ", reportToGvaModule='" + reportToGvaModule + '\'' +
                ", status='" + status + '\'' +
                ", moveTime='" + moveTime + '\'' +
                '}';
    }
}
