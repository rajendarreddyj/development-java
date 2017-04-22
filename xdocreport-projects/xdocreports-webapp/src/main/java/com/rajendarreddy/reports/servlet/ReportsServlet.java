package com.rajendarreddy.reports.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rajendarreddyj.model.project.Developer;
import com.rajendarreddyj.model.project.Project;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.XDocReportNotFoundException;
import fr.opensagres.xdocreport.document.web.AbstractProcessXDocReportServlet;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class ReportsServlet extends AbstractProcessXDocReportServlet {

    private static final long serialVersionUID = 3993221341284875152L;

    private static final String ODT_PROJECT_WITH_VELOCITY_LIST = "ODTProjectWithVelocityList";

    private static final String DOCX_PROJECT_WITH_VELOCITY_LIST = "DocxProjectWithVelocityList";

    @Override
    protected InputStream getSourceStream(final String reportId, final HttpServletRequest request) throws IOException, XDocReportException {
        if (ODT_PROJECT_WITH_VELOCITY_LIST.equals(reportId)) {
            return ReportsServlet.class.getResourceAsStream("ODTProjectWithVelocityList.odt");
        }
        if (DOCX_PROJECT_WITH_VELOCITY_LIST.equals(reportId)) {
            return ReportsServlet.class.getResourceAsStream("DocxProjectWithVelocityList.docx");
        }
        throw new XDocReportNotFoundException(reportId);
    }

    @Override
    protected void populateContext(final IContext context, final String reportId, final HttpServletRequest request) throws XDocReportException {
        if (ODT_PROJECT_WITH_VELOCITY_LIST.equals(reportId) || DOCX_PROJECT_WITH_VELOCITY_LIST.equals(reportId)) {
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
        } else {
            throw new XDocReportNotFoundException(reportId);
        }
    }

    @Override
    protected String getTemplateEngineKind(final String reportId, final HttpServletRequest request) {
        if (ODT_PROJECT_WITH_VELOCITY_LIST.equals(reportId) || DOCX_PROJECT_WITH_VELOCITY_LIST.equals(reportId)) {
            return TemplateEngineKind.Velocity.name();
        }
        return super.getTemplateEngineKind(reportId, request);
    }

    @Override
    protected FieldsMetadata getFieldsMetadata(final String reportId, final HttpServletRequest request) {
        if (ODT_PROJECT_WITH_VELOCITY_LIST.equals(reportId) || DOCX_PROJECT_WITH_VELOCITY_LIST.equals(reportId)) {
            FieldsMetadata metadata = new FieldsMetadata();
            metadata.addFieldAsList("developers.Name");
            metadata.addFieldAsList("developers.LastName");
            metadata.addFieldAsList("developers.Mail");
            return metadata;
        }
        return super.getFieldsMetadata(reportId, request);
    }
}
