package TietoRus.system.helpers.models;

public class AdresseSat {
    private String address1;
    private String address2;
    private String address3;
    private String cityName;
    private String cityCode;
    private String TMScustomerNr;
    private String countryCode;
    private String email;
    private String customerName;
    private String fax;
    private String phone;

    public AdresseSat(String address1, String address2, String address3, String cityName, String cityCode, String TMScustomerNr, String countryCode,
                     String email, String customerName, String fax, String phone) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.TMScustomerNr = TMScustomerNr;
        this.countryCode = countryCode;
        this.email = email;
        this.customerName = customerName;
        this.fax = fax;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdresseSat that = (AdresseSat) o;

        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (cityCode != null ? !cityCode.equals(that.cityCode) : that.cityCode != null) return false;
        if (TMScustomerNr != null ? !TMScustomerNr.equals(that.TMScustomerNr) : that.TMScustomerNr != null)
            return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;
    }

    @Override
    public int hashCode() {
        int result = address1 != null ? address1.hashCode() : 0;
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        result = 31 * result + (TMScustomerNr != null ? TMScustomerNr.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AdresseSat{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", TMScustomerNr='" + TMScustomerNr + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", email='" + email + '\'' +
                ", customerName='" + customerName + '\'' +
                ", fax='" + fax + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
