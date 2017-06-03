package TietoRus.system.helpers.models;

public class FileROESat {
    private Double rate;
    public  FileROESat (Double rate){
        this.rate =rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileROESat that = (FileROESat) o;

        return rate != null ? rate.equals(that.rate) : that.rate == null;
    }

    @Override
    public int hashCode() {
        return rate != null ? rate.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FileROESat{" +
                "rate=" + rate +
                '}';
    }
}
