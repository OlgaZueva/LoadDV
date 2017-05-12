package TietoRus.system.helpers.models;

public class InvoicePostingHub {
    private String invoiceType;
    private String invoiceNr;
    private String debitCredit;
    private Double customerCode;
    private int accessCompanyId;
    private int srcSystemId;

    public InvoicePostingHub(String invoiceType, String invoiceNr, String debitCredit, Double customerCode, int accessCompanyId, int srcSystemId) {
        this.invoiceType = invoiceType;
        this.invoiceNr = invoiceNr;
        this.debitCredit = debitCredit;
        this.customerCode = customerCode;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoicePostingHub that = (InvoicePostingHub) o;

        if (accessCompanyId != that.accessCompanyId) return false;
        if (srcSystemId != that.srcSystemId) return false;
        if (invoiceType != null ? !invoiceType.equals(that.invoiceType) : that.invoiceType != null) return false;
        if (invoiceNr != null ? !invoiceNr.equals(that.invoiceNr) : that.invoiceNr != null) return false;
        if (debitCredit != null ? !debitCredit.equals(that.debitCredit) : that.debitCredit != null) return false;
        return customerCode != null ? customerCode.equals(that.customerCode) : that.customerCode == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceType != null ? invoiceType.hashCode() : 0;
        result = 31 * result + (invoiceNr != null ? invoiceNr.hashCode() : 0);
        result = 31 * result + (debitCredit != null ? debitCredit.hashCode() : 0);
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "InvoicePostingHub{" +
                "invoiceType='" + invoiceType + '\'' +
                ", invoiceNr='" + invoiceNr + '\'' +
                ", debitCredit='" + debitCredit + '\'' +
                ", customerCode=" + customerCode +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
