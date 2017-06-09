package TietoRus.system.helpers.models;

import java.sql.Date;

public class BookingSat {
    private String serviceCode;
    private String polName;
    private Date polDate;
    private String polCode;
    private Double shipperCode;
    private String dischTerminalDepotCode;
    private String podName;
    private Date podDate;
    private String podCode;
    private int controlOffice;
    private int controlCompany;
    private String createdBy;
    private Date creationDate;
    private String originalCrossBookNr;
    private Date blDate;
    private String shipperReference;
    private String directFlag;
    private Date feederDate;
    private String payableAt;
    private String payableAtIMS;
    private String gvaTrade;
    private String importExportСode;
    private int branch;
    private String depot;
    private String depotReference;
    private String terminal;
    private String terminalReference;
    private String manifestType;
    private String oceanTrade;
    private String placeOfDelivery;
    private String placeOfReceipt;
    private String pldHaulageAtDestination;
    private String pldSublocationCode;
    private String pldZipCode;
    private String pldHaulageAtOrigin;
    private String plrSublocationCode;
    private String plrZipCode;
    private Date pldDate;
    private Date plrDate;
    private String quotationRefNr;
    private String blNumber;
    private String specialRefNr;
    private String contractType;
    private String bookingStatus;
    private String inttraReferenceNr;
    private String europeanCargoStatus;

    public BookingSat(String serviceCode, String polName, Date polDate, String polCode, Double shipperCode, String dischTerminalDepotCode, String podName, Date podDate,
                      String podCode, int controlOffice, int controlCompany, String createdBy, Date creationDate, String originalCrossBookNr, Date blDate, String shipperReference,
                      String directFlag, Date feederDate, String payableAt, String payableAtIMS, String gvaTrade, String importExportСode, int branch, String depot, String depotReference,
                      String terminal, String terminalReference, String manifestType, String oceanTrade, String placeOfDelivery, String placeOfReceipt, String pldHaulageAtDestination,
                      String pldSublocationCode, String pldZipCode, String pldHaulageAtOrigin, String plrSublocationCode, String plrZipCode, Date pldDate, Date plrDate, String quotationRefNr,
                      String blNumber, String specialRefNr, String contractType, String bookingStatus, String inttraReferenceNr, String europeanCargoStatus) {

        this.serviceCode = serviceCode;
        this.polName = polName;
        this.polDate = polDate;
        this.polCode = polCode;
        this.shipperCode = shipperCode;
        this.dischTerminalDepotCode = dischTerminalDepotCode;
        this.podName = podName;
        this.podDate = podDate;
        this.podCode = podCode;
        this.controlOffice = controlOffice;
        this.controlCompany = controlCompany;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.originalCrossBookNr = originalCrossBookNr;
        this.blDate = blDate;
        this.shipperReference = shipperReference;
        this.directFlag = directFlag;
        this.feederDate = feederDate;
        this.payableAt = payableAt;
        this.payableAtIMS = payableAtIMS;
        this.gvaTrade = gvaTrade;
        this.importExportСode = importExportСode;
        this.branch = branch;
        this.depot = depot;
        this.depotReference = depotReference;
        this.terminal = terminal;
        this.terminalReference = terminalReference;
        this.manifestType = manifestType;
        this.oceanTrade = oceanTrade;
        this.placeOfDelivery = placeOfDelivery;
        this.placeOfReceipt = placeOfReceipt;
        this.pldHaulageAtDestination = pldHaulageAtDestination;
        this.pldSublocationCode = pldSublocationCode;
        this.pldZipCode = pldZipCode;
        this.pldHaulageAtOrigin = pldHaulageAtOrigin;
        this.plrSublocationCode = plrSublocationCode;
        this.plrZipCode = plrZipCode;
        this.pldDate = pldDate;
        this.plrDate =plrDate;
        this.quotationRefNr =quotationRefNr;
        this.blNumber = blNumber;
        this.specialRefNr = specialRefNr;
        this.contractType = contractType;
        this.bookingStatus = bookingStatus;
        this.inttraReferenceNr= inttraReferenceNr;
        this.europeanCargoStatus = europeanCargoStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingSat that = (BookingSat) o;

        if (controlOffice != that.controlOffice) return false;
        if (controlCompany != that.controlCompany) return false;
        if (branch != that.branch) return false;
        if (serviceCode != null ? !serviceCode.equals(that.serviceCode) : that.serviceCode != null) return false;
        if (polName != null ? !polName.equals(that.polName) : that.polName != null) return false;
        if (polDate != null ? !polDate.equals(that.polDate) : that.polDate != null) return false;
        if (polCode != null ? !polCode.equals(that.polCode) : that.polCode != null) return false;
        if (shipperCode != null ? !shipperCode.equals(that.shipperCode) : that.shipperCode != null) return false;
        if (dischTerminalDepotCode != null ? !dischTerminalDepotCode.equals(that.dischTerminalDepotCode) : that.dischTerminalDepotCode != null)
            return false;
        if (podName != null ? !podName.equals(that.podName) : that.podName != null) return false;
        if (podDate != null ? !podDate.equals(that.podDate) : that.podDate != null) return false;
        if (podCode != null ? !podCode.equals(that.podCode) : that.podCode != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (originalCrossBookNr != null ? !originalCrossBookNr.equals(that.originalCrossBookNr) : that.originalCrossBookNr != null)
            return false;
        if (blDate != null ? !blDate.equals(that.blDate) : that.blDate != null) return false;
        if (shipperReference != null ? !shipperReference.equals(that.shipperReference) : that.shipperReference != null)
            return false;
        if (directFlag != null ? !directFlag.equals(that.directFlag) : that.directFlag != null) return false;
        if (feederDate != null ? !feederDate.equals(that.feederDate) : that.feederDate != null) return false;
        if (payableAt != null ? !payableAt.equals(that.payableAt) : that.payableAt != null) return false;
        if (payableAtIMS != null ? !payableAtIMS.equals(that.payableAtIMS) : that.payableAtIMS != null) return false;
        if (gvaTrade != null ? !gvaTrade.equals(that.gvaTrade) : that.gvaTrade != null) return false;
        if (importExportСode != null ? !importExportСode.equals(that.importExportСode) : that.importExportСode != null)
            return false;
        if (depot != null ? !depot.equals(that.depot) : that.depot != null) return false;
        if (depotReference != null ? !depotReference.equals(that.depotReference) : that.depotReference != null)
            return false;
        if (terminal != null ? !terminal.equals(that.terminal) : that.terminal != null) return false;
        if (terminalReference != null ? !terminalReference.equals(that.terminalReference) : that.terminalReference != null)
            return false;
        if (manifestType != null ? !manifestType.equals(that.manifestType) : that.manifestType != null) return false;
        if (oceanTrade != null ? !oceanTrade.equals(that.oceanTrade) : that.oceanTrade != null) return false;
        if (placeOfDelivery != null ? !placeOfDelivery.equals(that.placeOfDelivery) : that.placeOfDelivery != null)
            return false;
        if (placeOfReceipt != null ? !placeOfReceipt.equals(that.placeOfReceipt) : that.placeOfReceipt != null)
            return false;
        if (pldHaulageAtDestination != null ? !pldHaulageAtDestination.equals(that.pldHaulageAtDestination) : that.pldHaulageAtDestination != null)
            return false;
        if (pldSublocationCode != null ? !pldSublocationCode.equals(that.pldSublocationCode) : that.pldSublocationCode != null)
            return false;
        if (pldZipCode != null ? !pldZipCode.equals(that.pldZipCode) : that.pldZipCode != null) return false;
        if (pldHaulageAtOrigin != null ? !pldHaulageAtOrigin.equals(that.pldHaulageAtOrigin) : that.pldHaulageAtOrigin != null)
            return false;
        if (plrSublocationCode != null ? !plrSublocationCode.equals(that.plrSublocationCode) : that.plrSublocationCode != null)
            return false;
        if (plrZipCode != null ? !plrZipCode.equals(that.plrZipCode) : that.plrZipCode != null) return false;
        if (pldDate != null ? !pldDate.equals(that.pldDate) : that.pldDate != null) return false;
        if (plrDate != null ? !plrDate.equals(that.plrDate) : that.plrDate != null) return false;
        if (quotationRefNr != null ? !quotationRefNr.equals(that.quotationRefNr) : that.quotationRefNr != null)
            return false;
        if (blNumber != null ? !blNumber.equals(that.blNumber) : that.blNumber != null) return false;
        if (specialRefNr != null ? !specialRefNr.equals(that.specialRefNr) : that.specialRefNr != null) return false;
        if (contractType != null ? !contractType.equals(that.contractType) : that.contractType != null) return false;
        if (bookingStatus != null ? !bookingStatus.equals(that.bookingStatus) : that.bookingStatus != null)
            return false;
        if (inttraReferenceNr != null ? !inttraReferenceNr.equals(that.inttraReferenceNr) : that.inttraReferenceNr != null)
            return false;
        return europeanCargoStatus != null ? europeanCargoStatus.equals(that.europeanCargoStatus) : that.europeanCargoStatus == null;
    }

    @Override
    public int hashCode() {
        int result = serviceCode != null ? serviceCode.hashCode() : 0;
        result = 31 * result + (polName != null ? polName.hashCode() : 0);
        result = 31 * result + (polDate != null ? polDate.hashCode() : 0);
        result = 31 * result + (polCode != null ? polCode.hashCode() : 0);
        result = 31 * result + (shipperCode != null ? shipperCode.hashCode() : 0);
        result = 31 * result + (dischTerminalDepotCode != null ? dischTerminalDepotCode.hashCode() : 0);
        result = 31 * result + (podName != null ? podName.hashCode() : 0);
        result = 31 * result + (podDate != null ? podDate.hashCode() : 0);
        result = 31 * result + (podCode != null ? podCode.hashCode() : 0);
        result = 31 * result + controlOffice;
        result = 31 * result + controlCompany;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (originalCrossBookNr != null ? originalCrossBookNr.hashCode() : 0);
        result = 31 * result + (blDate != null ? blDate.hashCode() : 0);
        result = 31 * result + (shipperReference != null ? shipperReference.hashCode() : 0);
        result = 31 * result + (directFlag != null ? directFlag.hashCode() : 0);
        result = 31 * result + (feederDate != null ? feederDate.hashCode() : 0);
        result = 31 * result + (payableAt != null ? payableAt.hashCode() : 0);
        result = 31 * result + (payableAtIMS != null ? payableAtIMS.hashCode() : 0);
        result = 31 * result + (gvaTrade != null ? gvaTrade.hashCode() : 0);
        result = 31 * result + (importExportСode != null ? importExportСode.hashCode() : 0);
        result = 31 * result + branch;
        result = 31 * result + (depot != null ? depot.hashCode() : 0);
        result = 31 * result + (depotReference != null ? depotReference.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + (terminalReference != null ? terminalReference.hashCode() : 0);
        result = 31 * result + (manifestType != null ? manifestType.hashCode() : 0);
        result = 31 * result + (oceanTrade != null ? oceanTrade.hashCode() : 0);
        result = 31 * result + (placeOfDelivery != null ? placeOfDelivery.hashCode() : 0);
        result = 31 * result + (placeOfReceipt != null ? placeOfReceipt.hashCode() : 0);
        result = 31 * result + (pldHaulageAtDestination != null ? pldHaulageAtDestination.hashCode() : 0);
        result = 31 * result + (pldSublocationCode != null ? pldSublocationCode.hashCode() : 0);
        result = 31 * result + (pldZipCode != null ? pldZipCode.hashCode() : 0);
        result = 31 * result + (pldHaulageAtOrigin != null ? pldHaulageAtOrigin.hashCode() : 0);
        result = 31 * result + (plrSublocationCode != null ? plrSublocationCode.hashCode() : 0);
        result = 31 * result + (plrZipCode != null ? plrZipCode.hashCode() : 0);
        result = 31 * result + (pldDate != null ? pldDate.hashCode() : 0);
        result = 31 * result + (plrDate != null ? plrDate.hashCode() : 0);
        result = 31 * result + (quotationRefNr != null ? quotationRefNr.hashCode() : 0);
        result = 31 * result + (blNumber != null ? blNumber.hashCode() : 0);
        result = 31 * result + (specialRefNr != null ? specialRefNr.hashCode() : 0);
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (bookingStatus != null ? bookingStatus.hashCode() : 0);
        result = 31 * result + (inttraReferenceNr != null ? inttraReferenceNr.hashCode() : 0);
        result = 31 * result + (europeanCargoStatus != null ? europeanCargoStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingSat{" +
                "serviceCode='" + serviceCode + '\'' +
                ", polName='" + polName + '\'' +
                ", polDate=" + polDate +
                ", polCode='" + polCode + '\'' +
                ", shipperCode=" + shipperCode +
                ", dischTerminalDepotCode='" + dischTerminalDepotCode + '\'' +
                ", podName='" + podName + '\'' +
                ", podDate=" + podDate +
                ", podCode='" + podCode + '\'' +
                ", controlOffice=" + controlOffice +
                ", controlCompany=" + controlCompany +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", originalCrossBookNr='" + originalCrossBookNr + '\'' +
                ", blDate=" + blDate +
                ", shipperReference='" + shipperReference + '\'' +
                ", directFlag='" + directFlag + '\'' +
                ", feederDate=" + feederDate +
                ", payableAt='" + payableAt + '\'' +
                ", payableAtIMS='" + payableAtIMS + '\'' +
                ", gvaTrade='" + gvaTrade + '\'' +
                ", importExportСode='" + importExportСode + '\'' +
                ", branch=" + branch +
                ", depot='" + depot + '\'' +
                ", depotReference='" + depotReference + '\'' +
                ", terminal='" + terminal + '\'' +
                ", terminalReference='" + terminalReference + '\'' +
                ", manifestType='" + manifestType + '\'' +
                ", oceanTrade='" + oceanTrade + '\'' +
                ", placeOfDelivery='" + placeOfDelivery + '\'' +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", pldHaulageAtDestination='" + pldHaulageAtDestination + '\'' +
                ", pldSublocationCode='" + pldSublocationCode + '\'' +
                ", pldZipCode='" + pldZipCode + '\'' +
                ", pldHaulageAtOrigin='" + pldHaulageAtOrigin + '\'' +
                ", plrSublocationCode='" + plrSublocationCode + '\'' +
                ", plrZipCode='" + plrZipCode + '\'' +
                ", pldDate=" + pldDate +
                ", plrDate=" + plrDate +
                ", quotationRefNr='" + quotationRefNr + '\'' +
                ", blNumber='" + blNumber + '\'' +
                ", specialRefNr='" + specialRefNr + '\'' +
                ", contractType='" + contractType + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", inttraReferenceNr='" + inttraReferenceNr + '\'' +
                ", europeanCargoStatus='" + europeanCargoStatus + '\'' +
                '}';
    }
}
