package com.rajendarreddy.reports.dispatcher;

import fr.opensagres.xdocreport.document.dispatcher.BasicXDocReportDispatcher;
import fr.opensagres.xdocreport.document.web.dispatcher.IXDocReportWEBController;

public class ReportsDispatcher extends BasicXDocReportDispatcher<IXDocReportWEBController> {

    public ReportsDispatcher() {
        super.register(ODTProjectWithVelocityListController.REPORT_ID, new ODTProjectWithVelocityListController());
        super.register(DocxProjectWithVelocityListController.REPORT_ID, new DocxProjectWithVelocityListController());
    }
}
