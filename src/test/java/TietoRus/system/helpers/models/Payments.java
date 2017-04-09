package TietoRus.system.helpers.models;


public class Payments {

    private String F_TYPE;
    private String FAKTURANR;
    private String K_TYPE;
    private double KUNDE;
    private int LOBE_NR;
    private int SELSKAB;
    private int srcSystemId;

    public Payments (String F_TYPE, String FAKTURANR, String K_TYPE, double KUNDE, int LOBE_NR, int SELSKAB, int srcSystemId){
        this.F_TYPE = F_TYPE;
        this.FAKTURANR = FAKTURANR;
        this.K_TYPE = K_TYPE;
        this.KUNDE = KUNDE;
        this.LOBE_NR = LOBE_NR;
        this.SELSKAB = SELSKAB;
        this.srcSystemId = srcSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payments payments = (Payments) o;

        if (Double.compare(payments.KUNDE, KUNDE) != 0) return false;
        if (LOBE_NR != payments.LOBE_NR) return false;
        if (SELSKAB != payments.SELSKAB) return false;
        if (srcSystemId != payments.srcSystemId) return false;
        if (F_TYPE != null ? !F_TYPE.equals(payments.F_TYPE) : payments.F_TYPE != null) return false;
        if (FAKTURANR != null ? !FAKTURANR.equals(payments.FAKTURANR) : payments.FAKTURANR != null) return false;
        return K_TYPE != null ? K_TYPE.equals(payments.K_TYPE) : payments.K_TYPE == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = F_TYPE != null ? F_TYPE.hashCode() : 0;
        result = 31 * result + (FAKTURANR != null ? FAKTURANR.hashCode() : 0);
        result = 31 * result + (K_TYPE != null ? K_TYPE.hashCode() : 0);
        temp = Double.doubleToLongBits(KUNDE);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + LOBE_NR;
        result = 31 * result + SELSKAB;
        result = 31 * result + srcSystemId;
        return result;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "F_TYPE='" + F_TYPE + '\'' +
                ", FAKTURANR='" + FAKTURANR + '\'' +
                ", K_TYPE='" + K_TYPE + '\'' +
                ", KUNDE=" + KUNDE +
                ", LOBE_NR=" + LOBE_NR +
                ", SELSKAB=" + SELSKAB +
                ", srcSystemId=" + srcSystemId +
                '}';
    }
}
