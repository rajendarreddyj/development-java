package com.rajendarreddy.reports.dispatcher;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rajendarreddyj.model.project.Developer;
import com.rajendarreddyj.model.project.Project;

import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.web.dispatcher.AbstractXDocReportWEBController;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DocxProjectWithVelocityListController extends AbstractXDocReportWEBController {

    public static final String REPORT_ID = "DocxProjectWithVelocityList";

    public DocxProjectWithVelocityListController() {
        super(TemplateEngineKind.Velocity, DocumentKind.ODT);
    }

    @Override
    public InputStream getSourceStream() {
        return DocxProjectWithVelocityListController.class.getResourceAsStream("DocxProjectWithVelocityList.docx");
    }

    @Override
    protected FieldsMetadata createFieldsMetadata() {
        FieldsMetadata metadata = new FieldsMetadata();
        metadata.addFieldAsList("developers.Name");
        metadata.addFieldAsList("developers.LastName");
        metadata.addFieldAsList("developers.Mail");
        return metadata;
    }

    @Override
    public void populateContext(final IContext context, final IXDocReport report, final HttpServletRequest request) {
        String name = request.getParameter("name");
        Project project = new Project(name);
        context.put("project", project);

        int nbDevelopers = 0;
        try {
            nbDevelopers = Integer.parseInt(request.getParameter("nbDevelopers"));
        } catch (Throwable e) {

        }
        List<Developer> developers = new ArrayList<>(nbDevelopers);
        for (int i = 0; i < nbDevelopers; i++) {
            developers.add(new Developer("Name" + i, "LastName" + i, "Mail" + i));
        }
        context.put("developers", developers);
    }

}
