package TietoRus.system.helpers.models;

/**
 * Хаба GetCharges нет. Тут поля, которые использутся для поиска записи  из SA_GetCharges в hubBookingCargo
 */
public class GetChargesHub {

    private int SELSKAB;
    private int BOOK_NR;
    private int VAREPOST_NR;
    private String OMR_VALUTA;

    public GetChargesHub(int SELSKAB, int BOOK_NR, int VAREPOST_NR, String OMR_VALUTA) {
        this.SELSKAB = SELSKAB;
        this.BOOK_NR = BOOK_NR;
        this.VAREPOST_NR = VAREPOST_NR;
        this.OMR_VALUTA = OMR_VALUTA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetChargesHub that = (GetChargesHub) o;

        if (SELSKAB != that.SELSKAB) return false;
        if (BOOK_NR != that.BOOK_NR) return false;
        if (VAREPOST_NR != that.VAREPOST_NR) return false;
        return OMR_VALUTA != null ? OMR_VALUTA.equals(that.OMR_VALUTA) : that.OMR_VALUTA == null;
    }

    @Override
    public int hashCode() {
        int result = SELSKAB;
        result = 31 * result + BOOK_NR;
        result = 31 * result + VAREPOST_NR;
        result = 31 * result + (OMR_VALUTA != null ? OMR_VALUTA.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GetChargesHub{" +
                "SELSKAB=" + SELSKAB +
                ", BOOK_NR=" + BOOK_NR +
                ", VAREPOST_NR=" + VAREPOST_NR +
                ", OMR_VALUTA='" + OMR_VALUTA + '\'' +
                '}';
    }
}
