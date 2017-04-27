package TietoRus.system.helpers.models;


import java.util.Date;

public class ContainerTypeSpecEquipSat {

    private String masterContType;
    private String isTank;
    private String isSpecialWithRef;
    private String isSpecialWoRef;
    private String isFlatrack;
    private String isPlatform;
    private String isOpentop;
    private Date fromDate;

    public ContainerTypeSpecEquipSat (String masterContType, String isTank, String isSpecialWithRef, String isSpecialWoRef, String isFlatrack, String isPlatform,
                                      String isOpentop, Date fromDate){
        this.masterContType = masterContType;
        this.isTank = isTank;
        this.isSpecialWithRef = isSpecialWithRef;
        this.isSpecialWoRef = isSpecialWoRef;
        this.isFlatrack = isFlatrack;
        this.isPlatform = isPlatform;
        this.isOpentop = isOpentop;
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerTypeSpecEquipSat that = (ContainerTypeSpecEquipSat) o;

        if (masterContType != null ? !masterContType.equals(that.masterContType) : that.masterContType != null)
            return false;
        if (isTank != null ? !isTank.equals(that.isTank) : that.isTank != null) return false;
        if (isSpecialWithRef != null ? !isSpecialWithRef.equals(that.isSpecialWithRef) : that.isSpecialWithRef != null)
            return false;
        if (isSpecialWoRef != null ? !isSpecialWoRef.equals(that.isSpecialWoRef) : that.isSpecialWoRef != null)
            return false;
        if (isFlatrack != null ? !isFlatrack.equals(that.isFlatrack) : that.isFlatrack != null) return false;
        if (isPlatform != null ? !isPlatform.equals(that.isPlatform) : that.isPlatform != null) return false;
        if (isOpentop != null ? !isOpentop.equals(that.isOpentop) : that.isOpentop != null) return false;
        return fromDate != null ? fromDate.equals(that.fromDate) : that.fromDate == null;
    }

    @Override
    public int hashCode() {
        int result = masterContType != null ? masterContType.hashCode() : 0;
        result = 31 * result + (isTank != null ? isTank.hashCode() : 0);
        result = 31 * result + (isSpecialWithRef != null ? isSpecialWithRef.hashCode() : 0);
        result = 31 * result + (isSpecialWoRef != null ? isSpecialWoRef.hashCode() : 0);
        result = 31 * result + (isFlatrack != null ? isFlatrack.hashCode() : 0);
        result = 31 * result + (isPlatform != null ? isPlatform.hashCode() : 0);
        result = 31 * result + (isOpentop != null ? isOpentop.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContainerTypeSpecEquipSat{" +
                "masterContType='" + masterContType + '\'' +
                ", isTank='" + isTank + '\'' +
                ", isSpecialWithRef='" + isSpecialWithRef + '\'' +
                ", isSpecialWoRef='" + isSpecialWoRef + '\'' +
                ", isFlatrack='" + isFlatrack + '\'' +
                ", isPlatform='" + isPlatform + '\'' +
                ", isOpentop='" + isOpentop + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }
}
