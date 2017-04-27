package TietoRus.system.helpers.models;


public class ContainerTypeSpecEquipHub {
    private int contFeet;
    private String contSize;
    private String contType;
    private String contTypeName;
    private int srcSystemId;

    public ContainerTypeSpecEquipHub (int contFeet, String contSize, String contType, String contTypeName, int srcSystemId){
        this.contFeet = contFeet;
        this.contSize = contSize;
        this.contType = contType;
        this.contTypeName = contTypeName;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerTypeSpecEquipHub that = (ContainerTypeSpecEquipHub) o;

        if (contFeet != that.contFeet) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (contSize != null ? !contSize.equals(that.contSize) : that.contSize != null) return false;
        if (contType != null ? !contType.equals(that.contType) : that.contType != null) return false;
        return contTypeName != null ? contTypeName.equals(that.contTypeName) : that.contTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = contFeet;
        result = 31 * result + (contSize != null ? contSize.hashCode() : 0);
        result = 31 * result + (contType != null ? contType.hashCode() : 0);
        result = 31 * result + (contTypeName != null ? contTypeName.hashCode() : 0);
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerTypeSpecEquipHub{" +
                "contFeet=" + contFeet +
                ", contSize='" + contSize + '\'' +
                ", contType='" + contType + '\'' +
                ", contTypeName='" + contTypeName + '\'' +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
