package TietoRus.system.helpers.models;


public class KundeHub {
    private Double customerNr;
    private int accessCompanyId;
    private int srcSystemId;
    public KundeHub ( Double customerNr, int accessCompanyId, int srcSystemId){
        this.customerNr =customerNr;
        this.accessCompanyId =accessCompanyId;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KundeHub kundeHub = (KundeHub) o;

        if (accessCompanyId != kundeHub.accessCompanyId) return false;
        if (srcSystemId != kundeHub.srcSystemId) return false;
        return customerNr != null ? customerNr.equals(kundeHub.customerNr) : kundeHub.customerNr == null;
    }

    @Override
    public int hashCode() {
        int result = customerNr != null ? customerNr.hashCode() : 0;
        result = 31 * result + accessCompanyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "KundeHub{" +
                "customerNr=" + customerNr +
                ", accessCompanyId=" + accessCompanyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}

