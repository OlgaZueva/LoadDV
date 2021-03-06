package TietoRus.system.helpers.models;


public class LocationsSat {
    private String ovTradeNumberExp;
    private String ovTradeNumberImp;
    private String ovTradeName;
    private String gvaTrade;

    private String countryCode;
    private String locationName;

    public LocationsSat(String ovTradeNumberExp, String ovTradeNumberImp, String ovTradeName, String gvaTrade, String countryCode, String locationName) {
        this.ovTradeNumberExp = ovTradeNumberExp;
        this.ovTradeNumberImp = ovTradeNumberImp;
        this.ovTradeName = ovTradeName;
        this.gvaTrade = gvaTrade;
        this.countryCode = countryCode;
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationsSat that = (LocationsSat) o;

        if (ovTradeNumberExp != null ? !ovTradeNumberExp.equals(that.ovTradeNumberExp) : that.ovTradeNumberExp != null)
            return false;
        if (ovTradeNumberImp != null ? !ovTradeNumberImp.equals(that.ovTradeNumberImp) : that.ovTradeNumberImp != null)
            return false;
        if (ovTradeName != null ? !ovTradeName.equals(that.ovTradeName) : that.ovTradeName != null) return false;
        if (gvaTrade != null ? !gvaTrade.equals(that.gvaTrade) : that.gvaTrade != null) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        return locationName != null ? locationName.equals(that.locationName) : that.locationName == null;
    }

    @Override
    public int hashCode() {
        int result = ovTradeNumberExp != null ? ovTradeNumberExp.hashCode() : 0;
        result = 31 * result + (ovTradeNumberImp != null ? ovTradeNumberImp.hashCode() : 0);
        result = 31 * result + (ovTradeName != null ? ovTradeName.hashCode() : 0);
        result = 31 * result + (gvaTrade != null ? gvaTrade.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (locationName != null ? locationName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LocationsSat{" +
                "ovTradeNumberExp='" + ovTradeNumberExp + '\'' +
                ", ovTradeNumberImp='" + ovTradeNumberImp + '\'' +
                ", ovTradeName='" + ovTradeName + '\'' +
                ", gvaTrade='" + gvaTrade + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
