package com.rajendarreddyj.spring.web.metric;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class MetricFilter implements Filter {

    @Autowired
    private IMetricService metricService;

    @Autowired
    private ICustomActuatorMetricService actMetricService;

    @Override
    public void init(final FilterConfig config) throws ServletException {
        if ((this.metricService == null) || (this.actMetricService == null)) {
            this.metricService = (IMetricService) WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext())
                    .getBean("metricService");
            this.actMetricService = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext())
                    .getBean(CustomActuatorMetricService.class);
        }
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws java.io.IOException, ServletException {
        final HttpServletRequest httpRequest = ((HttpServletRequest) request);
        final String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        chain.doFilter(request, response);

        final int status = ((HttpServletResponse) response).getStatus();
        this.metricService.increaseCount(req, status);
        this.actMetricService.increaseCount(status);
    }

    @Override
    public void destroy() {

    }
}
