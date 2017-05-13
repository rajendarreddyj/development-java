package com.rajendarreddyj.reports.odttoodt.freemarker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ConvertODTToODTWithFreeMarker {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        generateODTFromODTFreeMarker("src/main/resources/ODTToODTWithFreeMarker.odt", "target/ODTToODTWithFreeMarker.odt");
    }

    /**
     * 
     */
    private static void generateODTFromODTFreeMarker(final String odtFileName, final String targetFilePath) {
        long startTime = System.currentTimeMillis();
        try {
            File inputFile = new File(odtFileName);
            /*InputStream in = ConvertODTToODTWithFreeMarker.class.getResourceAsStream( "ODTToODTWithFreeMarker.odt" );*/
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(new FileInputStream(inputFile), TemplateEngineKind.Freemarker);

            // 2) Create context Java model
            IContext context = report.createContext();
            context.put("comments", "aaa\tbbb\nccc\n\nddd\n\teeee\n\t\tfff\n\nggg\t\thhh");
            File outFile = new File(targetFilePath);
            outFile.getParentFile().mkdirs();
            // 3) Generate report by merging Java model with the ODT
            OutputStream out = new FileOutputStream(outFile);
            report.process(context, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        logger.info("Generate " + targetFilePath + " with " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
