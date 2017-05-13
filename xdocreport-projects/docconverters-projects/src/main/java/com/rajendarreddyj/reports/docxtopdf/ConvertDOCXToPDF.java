package com.rajendarreddyj.reports.docxtopdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ConvertDOCXToPDF {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        generatePDF("src/main/resources/DOCXToPDF.docx", "target/DOCXToPDF.pdf");
    }

    /**
     * 
     */
    private static void generatePDF(final String odtFileName, final String targetFilePath) {
        long startTime = System.currentTimeMillis();
        try {
            File inputFile = new File(odtFileName);
            XWPFDocument document = new XWPFDocument(new FileInputStream(inputFile));
            /*InputStream in = ConvertODTToPDF.class.getResourceAsStream( "ODTToPDF.odt" );*/
            File outFile = new File(targetFilePath);
            outFile.getParentFile().mkdirs();
            OutputStream out = new FileOutputStream(outFile);
            PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
            PdfConverter.getInstance().convert(document, out, options);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("Generate " + targetFilePath + " with " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
