package TietoRus.system.helpers.objects;

import TietoRus.system.helpers.helpers.DBHelper;
import TietoRus.system.helpers.models.IsoCodeHub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Данный хаб "специфичен". Для одной записив таблице -источнике может быть создано до 6 хабов (по каждому из полей ISO_KODE*_*).
 * Hub'ы будут использованы для создания link'ов lnkContainerTypeIsoCode
 */
public class IsoCodeHubObjects {
    private DBHelper db = new DBHelper();

    public IsoCodeHub getHubFromSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        //System.out.println("SQL из SA: " + sql);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        IsoCodeHub isoCodeHub = null;
        while (rsFromSA.next()) {
            if (rsFromSA.getRow() == 1) {
                // переделать, полей ISO_KODE 6 штук в таблице - источнике
                String isoCode = rsFromSA.getString("ISO_KODE");
                int accessCompanyId = rsFromSA.getInt("SELSKAB");
                int srcSystemId = rsFromSA.getInt("srcSystemId");
                isoCodeHub = new IsoCodeHub(isoCode, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record nor found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        System.out.println("IsoCodeHub from SA: " + isoCodeHub);
        return isoCodeHub;
    }

    public IsoCodeHub getHubFromDWH(String hubSQL) throws SQLException {
        Connection connectionToDWH = db.connToDWH();
        Statement stForDWH = db.stFromConnection(connectionToDWH);
        //System.out.println("SQL из DWH: " + hubSQL);
        ResultSet rsFromDWH = db.rsFromDB(stForDWH, hubSQL);
        IsoCodeHub isoCodeHub = null;
        while (rsFromDWH.next()) {
            if (rsFromDWH.getRow() == 1) {
                // переделать, полей ISO_KODE 6 штук в таблице - источнике
                String isoCode = rsFromDWH.getString("isoCode");
                int accessCompanyId = rsFromDWH.getInt("SELSKAB");
                int srcSystemId = rsFromDWH.getInt("srcSystemId");
                isoCodeHub = new IsoCodeHub(isoCode, accessCompanyId, srcSystemId);
            } else {
                System.err.println("Record not found or more one!");
                return null;
            }
        }
        db.closeConnecions(rsFromDWH, stForDWH, connectionToDWH);
        System.out.println("IsoCodeHub from DWH: " + isoCodeHub);
        return isoCodeHub;
    }


}
