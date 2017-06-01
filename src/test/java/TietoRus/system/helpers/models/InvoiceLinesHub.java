package TietoRus.system.helpers.models;


public class InvoiceLinesHub {
    private int invoiceLineNr;
    private int orderNr;
    private int accessCompanyId;
    private int srcSystemId;

    public InvoiceLinesHub(int invoiceLineNr, int orderNr, int accessCompanyId, int srcSystemId) {
        this.invoiceLineNr = invoiceLineNr;
        this.orderNr = orderNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLinesHub that = (InvoiceLinesHub) o;

        if (invoiceLineNr != that.invoiceLineNr) return false;
        if (orderNr != that.orderNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = invoiceLineNr;
        result = 31 * result + orderNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceLinesHub{" +
                "invoiceLineNr=" + invoiceLineNr +
                ", orderNr=" + orderNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
