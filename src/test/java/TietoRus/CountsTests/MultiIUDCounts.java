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

ВНИМАНИЕ! Подстчет не верен в части подсчета записей с cdcOperation ='D' (запросы SA-table.MSCRUS.InDWithHub.counts (предварительная проверка на существование хаба).
Запросы будут переделаны (после отпуска моего)
Запросы SA-table.countMutiMoments.counts и SA-table.MomentsMultiIUD.notD.counts показывают все верно

не написаны тесты проверки EdiKonv и Henvis -  там нужно больше запросов из-за того, что по этим таблицам строится несколько хабов
*/


public class MultiIUDCounts {
    private Properties properties = new Properties();
    private DBHelper db = new DBHelper();


    @Test(enabled = true)
    public void AbPost_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("abPost.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("abPost.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("abPost.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void AbPost_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("abPost.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("abPost.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("abPost.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("abPost.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("book.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("book.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("book.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("book.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Book_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("book.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("book.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("book.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("book.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDryPort_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDryPort.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifest_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifest.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookManifest_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookManifest.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookManifest.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookManifest.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookManifest.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("adresse.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("adresse.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("adresse.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Adresse_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("adresse.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("adresse.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("adresse.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("adresse.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetails_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookDetails.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetails.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookDetails.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetails.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookEvent_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookEvent_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookEvent.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookEvent.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookEvent.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookEvent.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookMftFile_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookMftFile.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void BogfTrans_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bogfTrans.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void  BogfTrans_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bogfTrans.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetailsMof_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetailsMof.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookDetailsMof_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookDetailsMof.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookFak_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookFak.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookFak.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookFak.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookFak.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookLin_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookLin.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookLin.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookLin.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookLin.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void BookVessel_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("bookVessel.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("bookVessel.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("bookVessel.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("bookVessel.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRep_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("contRep.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contRep.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("contRep.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ContRep_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("contRep.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("contRep.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("contRep.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("contRep.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ExpVessels_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("expVessels.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ExpVessels_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("expVessels.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("expVessels.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("expVessels.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("expVessels.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void FaktPost_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("faktPost.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void FaktPost_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("faktPost.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("faktPost.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("faktPost.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("faktPost.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }


    @Test(enabled = true)
    public void Kunde_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("kunde.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("kunde.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("kunde.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("kunde.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Kunde_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("kunde.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("kunde.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("kunde.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("kunde.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Ordre_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("ordre.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordre.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("ordre.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("ordre.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Ordre_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("ordre.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordre.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("ordre.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("ordre.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void OrdreLin_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("ordreLin.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void OrdreLin_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("ordreLin.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("ordreLin.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("ordreLin.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("ordreLin.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("sag.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sag.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("sag.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void Sag_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("sag.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sag.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("sag.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("sag.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SagKurs_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("sagKurs.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SagKurs_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("sagKurs.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("sagKurs.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("sagKurs.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("sagKurs.UNITY.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void ShipKurs_MSCRUS() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("shipKurs.MSCRUS.delete.count"));
        assertRowCount(countRowByCondition, countRowInSA);
    }

    @Test(enabled = true)
    public void SipKurs_UNITY() throws SQLException, IOException {
        getPropertiesFile();
        int countMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.UNITY.countMutiMoments.counts"));
        int countAllForMutiMoments = getCountRowInSA(properties.getProperty("shipKurs.UNITY.MomentsMultiIUD.notD.counts"));
        int countDWithHub = getCountRowInSA(properties.getProperty("shipKurs.UNITY.InDWithHub.counts"));
        int countRowByCondition = (countAllForMutiMoments - countMutiMoments) + countDWithHub;
        int countRowInSA = getCountRowInSA(properties.getProperty("shipKurs.UNITY.delete.count"));
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
