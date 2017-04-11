package TietoRus.system.helpers.models;

public class AccountingTransaction {
    private int itemNr;
    private int sequenceNr;
    private int accessCompanyId;
    private int srcSystemId;

    public AccountingTransaction(int itemNr, int sequenceNr, int accessCompanyId, int srcSystemId) {
        this.itemNr = itemNr;
        this.sequenceNr = sequenceNr;
        this.accessCompanyId = accessCompanyId;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountingTransaction that = (AccountingTransaction) o;

        if (itemNr != that.itemNr) return false;
        if (sequenceNr != that.sequenceNr) return false;
        if (accessCompanyId != that.accessCompanyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = itemNr;
        result = 31 * result + sequenceNr;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "AccountingTransaction{" +
                "itemNr=" + itemNr +
                ", sequenceNr=" + sequenceNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}

