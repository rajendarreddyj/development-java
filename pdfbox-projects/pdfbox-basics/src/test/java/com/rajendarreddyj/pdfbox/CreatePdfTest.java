package com.rajendarreddyj.pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class CreatePdfTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    @Test
    public void createPDF() {
        // Create a new document
        PDDocument document = new PDDocument();
        // Create a new page and add to the document
        PDPage page = new PDPage();
        document.addPage(page);
        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.TIMES_ROMAN;
        try {
            // Start a new content stream which will hold content to be created
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Define a text content stream using the selected font, moving the
            // cursor and showing the text "Hello World"
            contentStream.beginText();
            contentStream.setFont(font, 12);
            // Move to the start of the next line, offset from the start of the
            // current line by (150, 700).
            contentStream.newLineAtOffset(150, 700);
            // Shows the given text at the location specified by the current
            // text matrix.
            contentStream.showText("This is a PDF document created by PDFBox library");
            contentStream.endText();

            // Make sure that the content stream is closed.
            contentStream.close();
            document.save("SamplePDF1.pdf");
            // finally make sure that the document is properly closed.
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readPdf() {
        File pdffile = new File("SamplePDF1.pdf");
        try {
            // Parse the PDF. Unrestricted main memory will be used for buffering PDF
            // streams.
            PDDocument pd = PDDocument.load(pdffile);
            logger.info("Number of pages in the document: " + pd.getNumberOfPages());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pd);
            logger.info(text);
            pd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPdfWithFont() {
        // Create a new document
        PDDocument document = new PDDocument();
        // Create a new page and add to the document
        PDPage page = new PDPage();
        document.addPage(page);
        File fontfile = new File("src/main/resources/arial.ttf");
        try {
            // Create a new font object by loading a TrueType font into the document
            PDFont font = PDType0Font.load(document, fontfile);
            // Start a new content stream which will hold content to be created
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            // Define a text content stream using the selected font, moving the
            // cursor and showing the text "Hello World"
            contentStream.beginText();
            contentStream.setFont(font, 12);
            // Move to the start of the next line, offset from the start of the
            // current line by (150, 700).
            contentStream.newLineAtOffset(150, 700);
            // Shows the given text at the location specified by the current
            // text matrix.
            contentStream.showText("This is a PDF document created by PDFBox library");
            contentStream.endText();
            // Make sure that the content stream is closed.
            contentStream.close();
            document.save("SamplePDF2.pdf");
            // finally make sure that the document is properly closed.
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPDFWithImage() {
        // Create a new document
        PDDocument document = new PDDocument();
        // Create a new page and add to the document
        PDPage page = new PDPage();
        document.addPage(page);
        File fontfile = new File("src/main/resources/arial.ttf");
        try {
            // Create a new font object by loading a TrueType font into the
            // document
            PDFont font = PDType0Font.load(document, fontfile);
            // Start a new content stream which will hold content to be created
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Define a text content stream using the selected font, moving the
            // cursor and showing the text "Hello World"
            contentStream.beginText();
            contentStream.setFont(font, 12);
            // Move to the start of the next line, offset from the start of the
            // current line by (150, 700).
            contentStream.newLineAtOffset(150, 700);
            // Shows the given text at the location specified by the current
            // text matrix.
            contentStream.showText("This is a PDF document created by PDFBox library");
            contentStream.endText();
            PDImageXObject image = PDImageXObject.createFromFileByExtension(new File("src/main/resources/cuteicecream.jpg"), document);
            // draw an image of width 300 and height 200. Here 100,480 represents x,y coordinates from where the image
            // needs to be drawn
            contentStream.drawImage(image, 100, 480, 300, 200);
            // Make sure that the content stream is closed.
            contentStream.close();
            document.save("SamplePDF3.pdf");
            // finally make sure that the document is properly closed.
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
