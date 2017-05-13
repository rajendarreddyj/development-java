package com.rajendarreddyj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rajendarreddy
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getAnonymousLogger();
    public LoginServlet() {
        super();
    }

    ServletContext servletcontext;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        this.servletcontext = config.getServletContext();
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the user login information
        if ((user.equals("rajendar")) && (password.equals("rajendar"))) {
            this.servletcontext.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("DetailServlet");
            requestDispatcher.forward(request, response);
            logger.info("Username :" + user + " login successful");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<html><body><center>");
            out.print("<h1>Login Unsuccessful</h1><br>");
            out.print("<h2>We do not recognize your username and/or password.</h2><br>");
            out.print("<a href=" + "login.html" + ">Please try again</a>");
            out.print("</center></body></html>");
            logger.info("Username :" + user + " login unsuccessful");
        }
    }
}
