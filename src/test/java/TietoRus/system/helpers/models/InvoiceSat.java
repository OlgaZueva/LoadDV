package TietoRus.system.helpers.models;


import java.sql.Date;

public class InvoiceSat {
    private String paymentTerms;
    private String invoiceType;
    private String invoiceNr;
    private Date invoiceDate;
    private Date dueDate;
    private String importExportCode;
    private Double invoiceRoe;
    private Date printDate;
    private String isRelatedType;
    private Date registrationDate;
    private int bookingNr;
    private Date serviceDate;
    private String status;
    private String responsibleUser;

    public InvoiceSat(String paymentTerms, String invoiceType, String invoiceNr, Date invoiceDate, Date dueDate, String importExportCode, Double invoiceRoe, Date printDate,
                      String isRelatedType, Date registrationDate, int bookingNr, Date serviceDate, String status, String responsibleUser) {
        this.paymentTerms = paymentTerms;
        this.invoiceType = invoiceType;
        this.invoiceNr = invoiceNr;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.importExportCode = importExportCode;
        this.invoiceRoe = invoiceRoe;
        this.printDate = printDate;
        this.isRelatedType = isRelatedType;
        this.registrationDate = registrationDate;
        this.bookingNr = bookingNr;
        this.serviceDate = serviceDate;
        this.status = status;
        this.responsibleUser = responsibleUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceSat that = (InvoiceSat) o;

        if (bookingNr != that.bookingNr) return false;
        if (paymentTerms != null ? !paymentTerms.equals(that.paymentTerms) : that.paymentTerms != null) return false;
        if (invoiceType != null ? !invoiceType.equals(that.invoiceType) : that.invoiceType != null) return false;
        if (invoiceNr != null ? !invoiceNr.equals(that.invoiceNr) : that.invoiceNr != null) return false;
        if (invoiceDate != null ? !invoiceDate.equals(that.invoiceDate) : that.invoiceDate != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (importExportCode != null ? !importExportCode.equals(that.importExportCode) : that.importExportCode != null)
            return false;
        if (invoiceRoe != null ? !invoiceRoe.equals(that.invoiceRoe) : that.invoiceRoe != null) return false;
        if (printDate != null ? !printDate.equals(that.printDate) : that.printDate != null) return false;
        if (isRelatedType != null ? !isRelatedType.equals(that.isRelatedType) : that.isRelatedType != null)
            return false;
        if (registrationDate != null ? !registrationDate.equals(that.registrationDate) : that.registrationDate != null)
            return false;
        if (serviceDate != null ? !serviceDate.equals(that.serviceDate) : that.serviceDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return responsibleUser != null ? responsibleUser.equals(that.responsibleUser) : that.responsibleUser == null;
    }

    @Override
    public int hashCode() {
        int result = paymentTerms != null ? paymentTerms.hashCode() : 0;
        result = 31 * result + (invoiceType != null ? invoiceType.hashCode() : 0);
        result = 31 * result + (invoiceNr != null ? invoiceNr.hashCode() : 0);
        result = 31 * result + (invoiceDate != null ? invoiceDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (importExportCode != null ? importExportCode.hashCode() : 0);
        result = 31 * result + (invoiceRoe != null ? invoiceRoe.hashCode() : 0);
        result = 31 * result + (printDate != null ? printDate.hashCode() : 0);
        result = 31 * result + (isRelatedType != null ? isRelatedType.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + bookingNr;
        result = 31 * result + (serviceDate != null ? serviceDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (responsibleUser != null ? responsibleUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceSat{" +
                "paymentTerms='" + paymentTerms + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", invoiceNr='" + invoiceNr + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                ", importExportCode='" + importExportCode + '\'' +
                ", invoiceRoe=" + invoiceRoe +
                ", printDate=" + printDate +
                ", isRelatedType='" + isRelatedType + '\'' +
                ", registrationDate=" + registrationDate +
                ", bookingNr=" + bookingNr +
                ", serviceDate=" + serviceDate +
                ", status='" + status + '\'' +
                ", responsibleUser='" + responsibleUser + '\'' +
                '}';
    }
}
