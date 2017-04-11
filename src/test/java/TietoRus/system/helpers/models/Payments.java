package TietoRus.system.helpers.models;


public class Payments {

    private String invoiceType;
    private String invoiceNr;
    private String debitCredit;
    private double customerCode;
    private int sequenceNr;
    private int accessCompanyId;
    private int srcSystemId;


    public Payments (String invoiceType, String invoiceNr, String debitCredit, double customerCode, int sequenceNr, int accessCompanyId, int srcSystemId){
        this.invoiceType = invoiceType;
        this.invoiceNr = invoiceNr;
        this.debitCredit = debitCredit;
        this.customerCode = customerCode;
        this.sequenceNr = sequenceNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payments payments = (Payments) o;

        if (Double.compare(payments.customerCode, customerCode) != 0) return false;
        if (sequenceNr != payments.sequenceNr) return false;
        if (accessCompanyId != payments.accessCompanyId) return false;
        if (srcSystemId != payments.srcSystemId) return false;
        if (invoiceType != null ? !invoiceType.equals(payments.invoiceType) : payments.invoiceType != null)
            return false;
        if (invoiceNr != null ? !invoiceNr.equals(payments.invoiceNr) : payments.invoiceNr != null) return false;
        return debitCredit != null ? debitCredit.equals(payments.debitCredit) : payments.debitCredit == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = invoiceType != null ? invoiceType.hashCode() : 0;
        result = 31 * result + (invoiceNr != null ? invoiceNr.hashCode() : 0);
        result = 31 * result + (debitCredit != null ? debitCredit.hashCode() : 0);
        temp = Double.doubleToLongBits(customerCode);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + sequenceNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "invoiceType='" + invoiceType + '\'' +
                ", invoiceNr='" + invoiceNr + '\'' +
                ", debitCredit='" + debitCredit + '\'' +
                ", customerCode=" + customerCode +
                ", sequenceNr=" + sequenceNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
