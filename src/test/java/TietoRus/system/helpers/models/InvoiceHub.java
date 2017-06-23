package TietoRus.system.helpers.models;


public class InvoiceHub {
    private int orderNr;
    private int accessCompanyId;
    private int srcSystemId;

    public InvoiceHub(int orderNr, int accessCompanyId, int srcSystemId) {
        this.orderNr = orderNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceHub that = (InvoiceHub) o;

        if (orderNr != that.orderNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = orderNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceHub{" +
                "orderNr=" + orderNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
