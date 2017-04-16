package TietoRus.system.helpers.models;


public class AccountingTransactionSat {
    private String serviceCode;
    private String activity;
    private Double amountHomeCurr;
    private Double amountOrigCurr;
    private int diaryNum;
    private int uniqueIdentValue;
    private String group;
    private String createdBy;
    private Double nominalNr;
    private int branch;
    private int period;
    private int bookingNr;
    private int fileLinerNr;
    private String text;
    private String transactionType;

    public AccountingTransactionSat ( String serviceCode, String activity, Double amountHomeCurr, Double amountOrigCurr, int diaryNum, int uniqueIdentValue, String group,
            String createdBy, Double nominalNr, int branch,  int period, int bookingNr, int fileLinerNr, String text, String transactionType) {
        this.serviceCode = serviceCode;
        this.activity = activity;
        this.amountHomeCurr =amountHomeCurr;
        this.amountOrigCurr = amountOrigCurr;
        this.diaryNum = diaryNum;
        this.uniqueIdentValue = uniqueIdentValue;
        this.group = group;
        this.createdBy = createdBy;
        this.nominalNr = nominalNr;
        this.branch = branch;
        this.period = period;
        this.bookingNr = bookingNr;
        this.fileLinerNr = fileLinerNr;
        this.text = text;
        this.transactionType =transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountingTransactionSat that = (AccountingTransactionSat) o;

        if (diaryNum != that.diaryNum) return false;
        if (uniqueIdentValue != that.uniqueIdentValue) return false;
        if (branch != that.branch) return false;
        if (period != that.period) return false;
        if (bookingNr != that.bookingNr) return false;
        if (fileLinerNr != that.fileLinerNr) return false;
        if (serviceCode != null ? !serviceCode.equals(that.serviceCode) : that.serviceCode != null) return false;
        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;
        if (amountHomeCurr != null ? !amountHomeCurr.equals(that.amountHomeCurr) : that.amountHomeCurr != null)
            return false;
        if (amountOrigCurr != null ? !amountOrigCurr.equals(that.amountOrigCurr) : that.amountOrigCurr != null)
            return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (nominalNr != null ? !nominalNr.equals(that.nominalNr) : that.nominalNr != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return transactionType != null ? transactionType.equals(that.transactionType) : that.transactionType == null;
    }

    @Override
    public int hashCode() {
        int result = serviceCode != null ? serviceCode.hashCode() : 0;
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (amountHomeCurr != null ? amountHomeCurr.hashCode() : 0);
        result = 31 * result + (amountOrigCurr != null ? amountOrigCurr.hashCode() : 0);
        result = 31 * result + diaryNum;
        result = 31 * result + uniqueIdentValue;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (nominalNr != null ? nominalNr.hashCode() : 0);
        result = 31 * result + branch;
        result = 31 * result + period;
        result = 31 * result + bookingNr;
        result = 31 * result + fileLinerNr;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountingTransactionSat{" +
                "serviceCode='" + serviceCode + '\'' +
                ", activity='" + activity + '\'' +
                ", amountHomeCurr=" + amountHomeCurr +
                ", amountOrigCurr=" + amountOrigCurr +
                ", diaryNum=" + diaryNum +
                ", uniqueIdentValue=" + uniqueIdentValue +
                ", group='" + group + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", nominalNr=" + nominalNr +
                ", branch=" + branch +
                ", period=" + period +
                ", bookingNr=" + bookingNr +
                ", fileLinerNr=" + fileLinerNr +
                ", text='" + text + '\'' +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
