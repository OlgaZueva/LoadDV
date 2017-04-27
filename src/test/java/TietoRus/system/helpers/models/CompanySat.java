package TietoRus.system.helpers.models;


public class CompanySat {
    private String address1;
    private String address2;
    private String address3;
    private String companyName;
    private String zipCode;

    public CompanySat (String address1, String address2, String address3, String companyName, String zipCode){
        this.address1 = address1;
        this.address2 =address2;
        this.address3 = address3;
        this.companyName = companyName;
        this.zipCode =zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySat that = (CompanySat) o;

        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        return zipCode != null ? zipCode.equals(that.zipCode) : that.zipCode == null;
    }

    @Override
    public int hashCode() {
        int result = address1 != null ? address1.hashCode() : 0;
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanySat{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", companyName='" + companyName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
