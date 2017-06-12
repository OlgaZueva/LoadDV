package TietoRus.system.helpers.models;


public class ControllingOfficeSat {
    private String companyName;
    private String controllingOfficeFullName;
    private String officeName;

    public ControllingOfficeSat(String companyName, String controllingOfficeFullName, String officeName) {
        this.companyName = companyName;
        this.controllingOfficeFullName = controllingOfficeFullName;
        this.officeName = officeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControllingOfficeSat that = (ControllingOfficeSat) o;

        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (controllingOfficeFullName != null ? !controllingOfficeFullName.equals(that.controllingOfficeFullName) : that.controllingOfficeFullName != null)
            return false;
        return officeName != null ? officeName.equals(that.officeName) : that.officeName == null;
    }

    @Override
    public int hashCode() {
        int result = companyName != null ? companyName.hashCode() : 0;
        result = 31 * result + (controllingOfficeFullName != null ? controllingOfficeFullName.hashCode() : 0);
        result = 31 * result + (officeName != null ? officeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ControllingOfficeSat{" +
                "companyName='" + companyName + '\'' +
                ", controllingOfficeFullName='" + controllingOfficeFullName + '\'' +
                ", officeName='" + officeName + '\'' +
                '}';
    }
}
