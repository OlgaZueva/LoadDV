package TietoRus.system.helpers.models;


public class ContainerTypeSat {
    private int feet;
    private String size;
    private String typeCode;
    private String shiperOwned;
    private String typeName;
    private String isReefer;

    public ContainerTypeSat (int feet, String size, String typeCode, String shiperOwned, String typeName, String isReefer){
        this.feet =feet;
        this.size =size;
        this.typeCode =typeCode;
        this.shiperOwned = shiperOwned;
        this.typeName = typeName;
        this.isReefer = isReefer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerTypeSat that = (ContainerTypeSat) o;

        if (feet != that.feet) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (typeCode != null ? !typeCode.equals(that.typeCode) : that.typeCode != null) return false;
        if (shiperOwned != null ? !shiperOwned.equals(that.shiperOwned) : that.shiperOwned != null) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;
        return isReefer != null ? isReefer.equals(that.isReefer) : that.isReefer == null;
    }

    @Override
    public int hashCode() {
        int result = feet;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (typeCode != null ? typeCode.hashCode() : 0);
        result = 31 * result + (shiperOwned != null ? shiperOwned.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (isReefer != null ? isReefer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContainerTypeSat{" +
                "feet=" + feet +
                ", size='" + size + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", shiperOwned='" + shiperOwned + '\'' +
                ", typeName='" + typeName + '\'' +
                ", isReefer='" + isReefer + '\'' +
                '}';
    }
}
