package org.voxmail.struts;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.voxmail.Voxmail;
import org.voxmail.VoxmailException;

public class VoxmailHibernateManagement implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        try {
            Voxmail.getInstance().setBasePath(filterConfig.getServletContext().getRealPath("/"));
            Voxmail.getInstance().init();
        } catch (VoxmailException e) {
            e.printStackTrace();
            throw new ServletException("Voxmail Initialization Failed.", e);
        }
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            try {
                Voxmail.getInstance().releaseSession();
            } catch (VoxmailException e) {
                throw new ServletException("Unable to cleanup session.", e);
            }
        }
    }

    @Override
    public void destroy() {
        try {
            Voxmail.getInstance().destroy();
        } catch (VoxmailException e) {
            e.printStackTrace();
        }
    }

}