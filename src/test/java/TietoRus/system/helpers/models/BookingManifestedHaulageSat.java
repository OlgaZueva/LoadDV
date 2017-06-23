package TietoRus.system.helpers.models;


public class BookingManifestedHaulageSat {
    String precarriageMode;

    public BookingManifestedHaulageSat(String precarriageMode){
        this.precarriageMode =precarriageMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingManifestedHaulageSat that = (BookingManifestedHaulageSat) o;

        return precarriageMode != null ? precarriageMode.equals(that.precarriageMode) : that.precarriageMode == null;
    }

    @Override
    public int hashCode() {
        return precarriageMode != null ? precarriageMode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookingManifestedHaulageSat{" +
                "precarriageMode='" + precarriageMode + '\'' +
                '}';
    }
}
