package TietoRus.system.helpers.models;

public class AdreseeHub {
    private int number;
    private int bookingNumber;
    private String refType;
    private int accessCompanyId;
    private int srcSystemId;

    public AdreseeHub(int number, int bookingNumber, String refType, int accessCompanyId, int srcSystemId){
        this.number = number;
        this.bookingNumber = bookingNumber;
        this.refType = refType;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdreseeHub customers = (AdreseeHub) o;

        if (number != customers.number) return false;
        if (bookingNumber != customers.bookingNumber) return false;
        if (accessCompanyId != customers.accessCompanyId) return false;
        if (srcSystemId != customers.srcSystemId) return false;
        return refType != null ? refType.equals(customers.refType) : customers.refType == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + bookingNumber;
        result = 31 * result + (refType != null ? refType.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "AdreseeHub{" +
                "number=" + number +
                ", bookingNumber=" + bookingNumber +
                ", refType='" + refType + '\'' +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
