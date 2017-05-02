package TietoRus.system.helpers.models;


public class BookingEMCRemarksSat {
    String remarkCode;
    String remarkText;

    public BookingEMCRemarksSat(String remarkCode, String remarkText){
        this.remarkCode = remarkCode;
        this.remarkText = remarkText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEMCRemarksSat that = (BookingEMCRemarksSat) o;

        if (remarkCode != null ? !remarkCode.equals(that.remarkCode) : that.remarkCode != null) return false;
        return remarkText != null ? remarkText.equals(that.remarkText) : that.remarkText == null;
    }

    @Override
    public int hashCode() {
        int result = remarkCode != null ? remarkCode.hashCode() : 0;
        result = 31 * result + (remarkText != null ? remarkText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingEMCRemarksSat{" +
                "remarkCode='" + remarkCode + '\'' +
                ", remarkText='" + remarkText + '\'' +
                '}';
    }
}
