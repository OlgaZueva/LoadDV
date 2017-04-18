package TietoRus.system.helpers.models;


public class BookingManifestAdditionalsSat {
    private String valueCode;

    public BookingManifestAdditionalsSat(String valueCode){
        this.valueCode = valueCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingManifestAdditionalsSat that = (BookingManifestAdditionalsSat) o;

        return valueCode != null ? valueCode.equals(that.valueCode) : that.valueCode == null;
    }

    @Override
    public int hashCode() {
        return valueCode != null ? valueCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookingManifestAdditionalsSat{" +
                "valueCode='" + valueCode + '\'' +
                '}';
    }
}

