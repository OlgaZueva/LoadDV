package TietoRus.system.helpers.models;

import java.sql.Date;

public class PaymentsSat {
    private Double invoiceAmountHomeCurr;
    private Double invoiceAmountOrigCurr;
    private int itemNr;
    private Date paymentDate;
    private String text;
    private String invoiceOrigCurr;

    public PaymentsSat(Double invoiceAmountHomeCurr, Double invoiceAmountOrigCurr, int itemNr, Date paymentDate, String text, String invoiceOrigCurr) {
        this.invoiceAmountHomeCurr = invoiceAmountHomeCurr;
        this.invoiceAmountOrigCurr = invoiceAmountOrigCurr;
        this.itemNr = itemNr;
        this.paymentDate = paymentDate;
        this.text = text;
        this.invoiceOrigCurr = invoiceOrigCurr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentsSat that = (PaymentsSat) o;

        if (itemNr != that.itemNr) return false;
        if (invoiceAmountHomeCurr != null ? !invoiceAmountHomeCurr.equals(that.invoiceAmountHomeCurr) : that.invoiceAmountHomeCurr != null)
            return false;
        if (invoiceAmountOrigCurr != null ? !invoiceAmountOrigCurr.equals(that.invoiceAmountOrigCurr) : that.invoiceAmountOrigCurr != null)
            return false;
        if (paymentDate != null ? !paymentDate.equals(that.paymentDate) : that.paymentDate != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return invoiceOrigCurr != null ? invoiceOrigCurr.equals(that.invoiceOrigCurr) : that.invoiceOrigCurr == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceAmountHomeCurr != null ? invoiceAmountHomeCurr.hashCode() : 0;
        result = 31 * result + (invoiceAmountOrigCurr != null ? invoiceAmountOrigCurr.hashCode() : 0);
        result = 31 * result + itemNr;
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (invoiceOrigCurr != null ? invoiceOrigCurr.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaymentsSat{" +
                "invoiceAmountHomeCurr=" + invoiceAmountHomeCurr +
                ", invoiceAmountOrigCurr=" + invoiceAmountOrigCurr +
                ", itemNr=" + itemNr +
                ", paymentDate=" + paymentDate +
                ", text='" + text + '\'' +
                ", invoiceOrigCurr='" + invoiceOrigCurr + '\'' +
                '}';
    }
}
