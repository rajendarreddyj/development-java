package com.rajendarreddyj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailServlet
 * 
 * @author rajendarreddy
 *
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DetailServlet() {
        super();
    }

    ServletContext servletcontext;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        this.servletcontext = config.getServletContext();
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String user = (String) this.servletcontext.getAttribute("user");
        PrintWriter out = response.getWriter();
        out.print("<html><body><center>");
        out.print("<h1>WELCOME ! " + user + "</h1><br>");
        out.print("<h1>Login successful</h1>");
        out.print("</center></body></html>");
    }

}
