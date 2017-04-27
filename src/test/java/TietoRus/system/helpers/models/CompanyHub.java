package TietoRus.system.helpers.models;


public class CompanyHub {
    private int companyId;
    private int srcSystemId;

    public CompanyHub(int companyId, int srcSystemId){
        this.companyId = companyId;
        this.srcSystemId =srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyHub that = (CompanyHub) o;

        if (companyId != that.companyId) return false;
        return srcSystemId == that.srcSystemId;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "CompanyHub{" +
                "companyId=" + companyId +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}

