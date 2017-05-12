package TietoRus.system.helpers.models;


import java.sql.Date;

public class InvoicePostingSat {
    private Double invoiceAmountHomeCurr;
    private Double invoiceAmountOrigCurr;
    private Date paymentDate;
    private String paymentMethod;
    private String paymentTerms;
    private String paymentReference;
    private int itemNr;
    private int diaryNum;
    private Date postingDate;
    private Date dueDate;
    private int uniqueIdentValue;
    private String createdBy;
    private Double roe;
    private String invoiceOrigCurr;
    private Double relatedInvoiceNr;
    private Double remainingAmountHomeCurr;
    private String remainingAmountOrigCurr;
    private String sapDocumentNr;
    private String sourceModule;
    private String text;

    public InvoicePostingSat(Double invoiceAmountHomeCurr, Double invoiceAmountOrigCurr, Date paymentDate, String paymentMethod, String paymentTerms,
                             String paymentReference, int itemNr, int diaryNum, Date postingDate, Date dueDate, int uniqueIdentValue, String createdBy, Double roe,
                             String invoiceOrigCurr, Double relatedInvoiceNr, Double remainingAmountHomeCurr, String remainingAmountOrigCurr, String sapDocumentNr,
                             String sourceModule, String text) {
        this.invoiceAmountHomeCurr = invoiceAmountHomeCurr;
        this.invoiceAmountOrigCurr = invoiceAmountOrigCurr;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentTerms = paymentTerms;
        this.paymentReference = paymentReference;
        this.itemNr = itemNr;
        this.diaryNum = diaryNum;
        this.postingDate = postingDate;
        this.dueDate = dueDate;
        this.uniqueIdentValue = uniqueIdentValue;
        this.createdBy = createdBy;
        this.roe = roe;
        this.invoiceOrigCurr = invoiceOrigCurr;
        this.relatedInvoiceNr = relatedInvoiceNr;
        this.remainingAmountHomeCurr = remainingAmountHomeCurr;
        this.remainingAmountOrigCurr = remainingAmountOrigCurr;
        this.sapDocumentNr = sapDocumentNr;
        this.sourceModule = sourceModule;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoicePostingSat that = (InvoicePostingSat) o;

        if (itemNr != that.itemNr) return false;
        if (diaryNum != that.diaryNum) return false;
        if (uniqueIdentValue != that.uniqueIdentValue) return false;
        if (invoiceAmountHomeCurr != null ? !invoiceAmountHomeCurr.equals(that.invoiceAmountHomeCurr) : that.invoiceAmountHomeCurr != null)
            return false;
        if (invoiceAmountOrigCurr != null ? !invoiceAmountOrigCurr.equals(that.invoiceAmountOrigCurr) : that.invoiceAmountOrigCurr != null)
            return false;
        if (paymentDate != null ? !paymentDate.equals(that.paymentDate) : that.paymentDate != null) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;
        if (paymentTerms != null ? !paymentTerms.equals(that.paymentTerms) : that.paymentTerms != null) return false;
        if (paymentReference != null ? !paymentReference.equals(that.paymentReference) : that.paymentReference != null)
            return false;
        if (postingDate != null ? !postingDate.equals(that.postingDate) : that.postingDate != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (roe != null ? !roe.equals(that.roe) : that.roe != null) return false;
        if (invoiceOrigCurr != null ? !invoiceOrigCurr.equals(that.invoiceOrigCurr) : that.invoiceOrigCurr != null)
            return false;
        if (relatedInvoiceNr != null ? !relatedInvoiceNr.equals(that.relatedInvoiceNr) : that.relatedInvoiceNr != null)
            return false;
        if (remainingAmountHomeCurr != null ? !remainingAmountHomeCurr.equals(that.remainingAmountHomeCurr) : that.remainingAmountHomeCurr != null)
            return false;
        if (remainingAmountOrigCurr != null ? !remainingAmountOrigCurr.equals(that.remainingAmountOrigCurr) : that.remainingAmountOrigCurr != null)
            return false;
        if (sapDocumentNr != null ? !sapDocumentNr.equals(that.sapDocumentNr) : that.sapDocumentNr != null)
            return false;
        if (sourceModule != null ? !sourceModule.equals(that.sourceModule) : that.sourceModule != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceAmountHomeCurr != null ? invoiceAmountHomeCurr.hashCode() : 0;
        result = 31 * result + (invoiceAmountOrigCurr != null ? invoiceAmountOrigCurr.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (paymentTerms != null ? paymentTerms.hashCode() : 0);
        result = 31 * result + (paymentReference != null ? paymentReference.hashCode() : 0);
        result = 31 * result + itemNr;
        result = 31 * result + diaryNum;
        result = 31 * result + (postingDate != null ? postingDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + uniqueIdentValue;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (roe != null ? roe.hashCode() : 0);
        result = 31 * result + (invoiceOrigCurr != null ? invoiceOrigCurr.hashCode() : 0);
        result = 31 * result + (relatedInvoiceNr != null ? relatedInvoiceNr.hashCode() : 0);
        result = 31 * result + (remainingAmountHomeCurr != null ? remainingAmountHomeCurr.hashCode() : 0);
        result = 31 * result + (remainingAmountOrigCurr != null ? remainingAmountOrigCurr.hashCode() : 0);
        result = 31 * result + (sapDocumentNr != null ? sapDocumentNr.hashCode() : 0);
        result = 31 * result + (sourceModule != null ? sourceModule.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InvoicePostingSat{" +
                "invoiceAmountHomeCurr=" + invoiceAmountHomeCurr +
                ", invoiceAmountOrigCurr=" + invoiceAmountOrigCurr +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentTerms='" + paymentTerms + '\'' +
                ", paymentReference='" + paymentReference + '\'' +
                ", itemNr=" + itemNr +
                ", diaryNum=" + diaryNum +
                ", postingDate=" + postingDate +
                ", dueDate=" + dueDate +
                ", uniqueIdentValue=" + uniqueIdentValue +
                ", createdBy='" + createdBy + '\'' +
                ", roe=" + roe +
                ", invoiceOrigCurr='" + invoiceOrigCurr + '\'' +
                ", relatedInvoiceNr=" + relatedInvoiceNr +
                ", remainingAmountHomeCurr=" + remainingAmountHomeCurr +
                ", remainingAmountOrigCurr='" + remainingAmountOrigCurr + '\'' +
                ", sapDocumentNr='" + sapDocumentNr + '\'' +
                ", sourceModule='" + sourceModule + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
