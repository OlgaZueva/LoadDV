package TietoRus.system.helpers.models;

public class KundeSat {
    private String address1;
    private String address2;
    private String address3;
    private String cityName;
    private String cityCode;
    private String TMScustomerNr;
    private String countryCode;
    private String clientReference;
    private String email;
    private String shortName;
    private String customerName;
    private String fax;
    private String phone;

    public KundeSat(String address1, String address2, String address3, String cityName, String cityCode, String TMScustomerNr, String countryCode,
                    String clientReference, String email, String shortName, String customerName, String fax, String phone) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.TMScustomerNr = TMScustomerNr;
        this.countryCode = countryCode;
        this.clientReference = clientReference;
        this.email = email;
        this.shortName = shortName;
        this.customerName = customerName;
        this.fax = fax;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KundeSat kundeSat = (KundeSat) o;

        if (address1 != null ? !address1.equals(kundeSat.address1) : kundeSat.address1 != null) return false;
        if (address2 != null ? !address2.equals(kundeSat.address2) : kundeSat.address2 != null) return false;
        if (address3 != null ? !address3.equals(kundeSat.address3) : kundeSat.address3 != null) return false;
        if (cityName != null ? !cityName.equals(kundeSat.cityName) : kundeSat.cityName != null) return false;
        if (cityCode != null ? !cityCode.equals(kundeSat.cityCode) : kundeSat.cityCode != null) return false;
        if (TMScustomerNr != null ? !TMScustomerNr.equals(kundeSat.TMScustomerNr) : kundeSat.TMScustomerNr != null)
            return false;
        if (countryCode != null ? !countryCode.equals(kundeSat.countryCode) : kundeSat.countryCode != null)
            return false;
        if (clientReference != null ? !clientReference.equals(kundeSat.clientReference) : kundeSat.clientReference != null)
            return false;
        if (email != null ? !email.equals(kundeSat.email) : kundeSat.email != null) return false;
        if (shortName != null ? !shortName.equals(kundeSat.shortName) : kundeSat.shortName != null) return false;
        if (customerName != null ? !customerName.equals(kundeSat.customerName) : kundeSat.customerName != null)
            return false;
        if (fax != null ? !fax.equals(kundeSat.fax) : kundeSat.fax != null) return false;
        return phone != null ? phone.equals(kundeSat.phone) : kundeSat.phone == null;
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
        result = 31 * result + (clientReference != null ? clientReference.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KundeSat{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", TMScustomerNr='" + TMScustomerNr + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", clientReference='" + clientReference + '\'' +
                ", email='" + email + '\'' +
                ", shortName='" + shortName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", fax='" + fax + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
