package TietoRus.CountsTests;

import TietoRus.system.helpers.helpers.DBHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Класс, содержащий тесты для проверки механизма "Обработка одновременных удалений и вставок".
Каждый тест "считает" кол-во записей, которые должны быть удалены согласно условиям и сравнивает это кол-во с фактическим.
Условия: первый запрос выбирает общее число записей, группированных по ключу у которых есть в один момент времени есть изменения.
Второй запрос выбирает записи с максимальным значением cdcRsid в каждой группе (группа формируется по ключу).
Для каждой группы должны быть помечены на удаление все записи, кроме последней (значение cdcRsid это однозначно определяет -  максимальное последнее).
Число удаленных записей считается как раздница между кол-вом, котрое вернулпервый запрос  и кол-вом, которое вернул второй запрос.

Не написаны тесты проверки EdiKonv и Henvis -  там нужно больше запросов из-за того, что по этим таблицам строится несколько хабов
*/


public class MultiIUDCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void AbPost_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("abPost.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("abPost.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void AbPost_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("abPost.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("abPost.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("book.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("book.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("book.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("book.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("book.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("book.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifest_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifest_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookManifest.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookManifest.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifest.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("adresse.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("adresse.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("adresse.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("adresse.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetails.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookDetails.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftRemarks_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookMftRemarks.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookMftRemarks.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftRemarks.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftRemarks_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookMftRemarks.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookMftRemarks.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftRemarks.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookEvent_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookEvent_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookEvent.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookEvent.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void BogfTrans_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BogfTrans_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetailsMof_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetailsMof_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookFak.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookFak.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookLin.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookLin.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookVessel.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("bookVessel.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRep_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contRep.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("contRep.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRep_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contRep.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("contRep.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ExpVessels_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ExpVessels_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("expVessels.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("expVessels.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("expVessels.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void FaktPost_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void FaktPost_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("faktPost.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("faktPost.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("faktPost.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Kunde_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("kunde.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("kunde.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("kunde.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Kunde_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("kunde.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("kunde.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("kunde.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Ordre_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordre.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("ordre.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("ordre.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Ordre_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordre.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("ordre.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("ordre.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void OrdreLin_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void OrdreLin_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordreLin.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("ordreLin.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("ordreLin.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void Sag_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sag.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("sag.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sag.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("sag.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SagKurs_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SagKurs_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sagKurs.UNITY.allInMultyMoments.counts"));
        int countMaxRsid = getCountRowInSA(properties.getProperty("sagKurs.UNITY.maxRsid.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMaxRsid);
        int countRowInSA = getCountRowInSA(properties.getProperty("sagKurs.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ShipKurs_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ShipKurs_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("shipKurs.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("shipKurs.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("shipKurs.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Vgm_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("vgm.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("vgm.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("vgm.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("vgm.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("vgm.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Vgm_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("vgm.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("vgm.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("vgm.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("vgm.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("vgm.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookBemInternal_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookBemInternal.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookBemInternal.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookBemInternal.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("bookBemInternal.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookBemInternal.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookBemInternal_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookBemInternal.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookBemInternal.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookBemInternal.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("bookBemInternal.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("bookBemInternal.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void CtsContEvent_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("ctsContEvent.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ctsContEvent.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("ctsContEvent.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("ctsContEvent.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("ctsContEvent.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void CtsContEvent_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("ctsContEvent.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ctsContEvent.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("ctsContEvent.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("ctsContEvent.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("ctsContEvent.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void Commodity_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("commodity.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("commodity.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("commodity.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("commodity.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("commodity.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Commodity_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("commodity.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("commodity.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("commodity.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("commodity.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("commodity.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContMaster_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("contMaster.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contMaster.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("contMaster.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("contMaster.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("contMaster.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContMaster_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("contMaster.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contMaster.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("contMaster.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("contMaster.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("contMaster.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRules_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("contRules.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contRules.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("contRules.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("contRules.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("contRules.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRules_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("contRules.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contRules.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("contRules.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("contRules.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("contRules.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = false)
    /*
    Работа с таблицей demurrage изменена из-за большого обхема данных по изменениям.
    Таблица исключена из обработки механизмом "удаление одновеременных вставок и удалений (DeleteMUI)"
    Тест неактуален
    */
    public void Demurrage_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("demurrage.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("demurrage.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("demurrage.MSCRUS.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("demurrage.MSCRUS.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("demurrage.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = false) // обработка Demurage изменеена. Из функционаа MultyIUD таблица исклбчена. тест неактуален.
    public void Demurrage_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("demurrage.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("demurrage.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("demurrage.UNITY.InDWithHub.counts"));
        int countAllD = getCountRowInSA(properties.getProperty("demurrage.UNITY.InDAll.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + (countAllD - countDWithHub);
        int countRowInSA = getCountRowInSA(properties.getProperty("demurrage.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    private void getPropertiesFile() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/MultiIUD.properties"))));
    }

    public void assertRowCount(int countInSource, int countInDest) {
        System.out.println("Count rows in Source [" + countInSource + "], in Destination [" + countInDest + "]");
        assertThat(countInDest, equalTo(countInSource));
    }

    public int getCountRowInSA(String saSQL) throws SQLException {
        Connection connectionToSA = db.connToSA();
        Statement stForSA = db.stFromConnection(connectionToSA);
        ResultSet rsFromSA = db.rsFromDB(stForSA, saSQL);
        int countRowSA = 0;
        while (rsFromSA.next()) {
            countRowSA = Integer.parseInt(rsFromSA.getString("c"));
        }
        db.closeConnecions(rsFromSA, stForSA, connectionToSA);
        return countRowSA;
    }
}
