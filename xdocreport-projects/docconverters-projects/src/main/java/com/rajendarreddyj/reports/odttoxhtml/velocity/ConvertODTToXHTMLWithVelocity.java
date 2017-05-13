package com.rajendarreddyj.reports.odttoxhtml.velocity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.rajendarreddyj.model.project.Developer;
import com.rajendarreddyj.model.project.Project;
import com.rajendarreddyj.model.project.Role;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ConvertODTToXHTMLWithVelocity {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        generateXHTMLFromODTVelocity("src/main/resources/ODTToPDFWithVelocity.odt", "target/ODTToPDFWithVelocity.html");
    }

    /**
     * 
     */
    private static void generateXHTMLFromODTVelocity(final String odtFileName, final String targetFilePath) {
        long startTime = System.currentTimeMillis();
        try {
            // 1) Load ODT file by filling Velocity template engine and cache
            // it to the registry
            /*InputStream in = ConvertODTToPDFWithVelocity.class.getResourceAsStream("ODTProjectWithVelocity.odt");*/
            File inputFile = new File(odtFileName);
            InputStream in = new FileInputStream(inputFile);
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            // 2) Create context Java model
            // 2) Create fields metadata to manage lazy loop (#forech velocity)
            // for table row.
            FieldsMetadata metadata = report.createFieldsMetadata();
            // Old API
            // FieldsMetadata metadata = new FieldsMetadata();
            // metadata.addFieldAsList("developers.name");
            // metadata.addFieldAsList("developers.lastName");
            // metadata.addFieldAsList("developers.mail");
            // report.setFieldsMetadata(metadata);
            // New API
            metadata.load("developers", Developer.class, true);
            report.setFieldsMetadata(metadata);
            // 3) Create context Java model
            IContext context = report.createContext();
            Project project = new Project("XDocReport");
            context.put("project", project);
            // Register developers list
            List<Developer> developers = new ArrayList<>();

            developers.add(new Developer("FUSER1", "LUSER1", "USER1@gmail.com").addRole(new Role("Architect")).addRole(new Role("Developer")));
            developers.add(new Developer("FUSER2", "LUSER2", "USER2@gmail.com").addRole(new Role("Architect")).addRole(new Role("Developer")));
            developers.add(new Developer("FUSER3", "LUSER3", "").addRole(new Role("Developer")));

            context.put("developers", developers);

            // 4) Generate report by merging Java model with the ODT
            OutputStream out = new FileOutputStream(new File(targetFilePath));
            // report.process(context, out);
            Options options = Options.getTo(ConverterTypeTo.XHTML).via(ConverterTypeVia.ODFDOM);
            report.convert(context, options, out);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        logger.info("Generate " + targetFilePath + " with " + (System.currentTimeMillis() - startTime) + " ms.");

    }
}
