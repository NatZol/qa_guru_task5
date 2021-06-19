package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.*;
import static utils.Zip.unzip;

public class FileTests {

    @Test
    public void checkTextInTxt() throws IOException {
        String txtFilePath = "./src/test/resources/files/1.txt";
        String expectedData = "I like qa-guru.";

        String actualData = readTextFromPath(txtFilePath);
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    public void checkTextInPdf() throws IOException {
        String pdfFilePath = "./src/test/resources/files/hebrew.pdf";
        String expectedData = "עזרא תודה על היותך אדם הגיוני";

        PDF pdf = getPdf(pdfFilePath);
        assertThat(pdf, PDF.containsText(expectedData));
    }

    @Test
    public void checkTextInXls() {
        String xlsFilePath = "./src/test/resources/files/test.xls";
        String expectedData = "Natalia";

        XLS xls = getXls(xlsFilePath);
        assertThat(xls, XLS.containsText(expectedData));
    }

    @Test
    public void checkTextInCellXls() {
        String xlsFilePath = "./src/test/resources/files/test.xls";
        String expectedData1 = "Natalia";
        String expectedData2 = "qa guru student";

        XLS xls = getXls(xlsFilePath);
        String actualData1 = xls.excel.getSheetAt(0).getRow(1).getCell(0).toString();
        String actualData2 = xls.excel.getSheetAt(0).getRow(1).getCell(1).toString();

        assertThat(actualData1, containsString(expectedData1));
        assertThat(actualData2, containsString(expectedData2));
    }

    @Test
    public void checkTextInXlsx() {
        String xlsxFilePath = "./src/test/resources/files/test.xlsx";
        String expectedData = "Natalia";

        String actualData = readXlsxFromPath(xlsxFilePath);
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    public void checkArchive() throws ZipException, IOException {
        String zipFilePath = "./src/test/resources/files/1.zip";
        String unzipFolderPath = "./src/test/resources/files/unzip";
        String zipPassword = "Natalia123!";
        String unzipTxtFilePath = "./src/test/resources/files/unzip/1.txt";
        String expectedData = "I like qa-guru.";

        unzip(zipFilePath, unzipFolderPath, zipPassword);

        String actualData = readTextFromPath(unzipTxtFilePath);
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    public void checkTextInDoc() throws IOException {
        String docFilePath = "./src/test/resources/files/test.doc";
        String expectedData = "Natalia likes qa guru course.";

        String actualData = readDocFromPath(docFilePath);
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    public void checkTextInDocx() throws IOException {
        String docxFilePath = "./src/test/resources/files/test.docx";
        String expectedData = "Natalia likes qa guru course.";

        String actualData = readDocxFromPath(docxFilePath);
        assertThat(actualData, containsString(expectedData));
    }
}
