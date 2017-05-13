package com.rajendarreddyj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 * 
 * @author rajendarreddy
 *
 */
@WebServlet("/registerprocess")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getAnonymousLogger();
    @Override
    public void init(final ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
    }

    @Override
    protected void service(final HttpServletRequest arg0, final HttpServletResponse arg1) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.service(arg0, arg1);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");
        String commode = request.getParameter("commode");
        logger.info("FIRSTNAME :" + firstname);
        logger.info("LASTNAME :" + lastname);
        logger.info("GENDER :" + gender);
        logger.info("CITY :" + city);
        logger.info("COMMUNICATION MODE :" + commode);

        PrintWriter out = response.getWriter();
        out.println("<html><body><center>");
        out.println("<h1> Welcome !");
        out.print(firstname);
        out.print("<h1 >Registration processed successfully");
        out.println("</center</body></html>");

    }

}
