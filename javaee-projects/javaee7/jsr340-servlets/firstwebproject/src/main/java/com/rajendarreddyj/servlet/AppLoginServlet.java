package com.rajendarreddyj.servlet;


import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AppLoginServlet")
public class AppLoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getAnonymousLogger();
    public AppLoginServlet() {
        super();
 
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String user=request.getParameter("username");
		String password=request.getParameter("password");
	
		//Validate the user login information
		if((user.equals("rajendar"))&&(password.equals("rajendar"))){
			 RequestDispatcher requestDispatcher=request.getRequestDispatcher("Successful.html");
		        request.setAttribute("user", user);
		        requestDispatcher.include(request, response);
				logger.info("Username :"  +user+ " login successful");
		}		
		else
		{
			 RequestDispatcher requestDispatcher=request.getRequestDispatcher("UnSuccessful.html");
		        request.setAttribute("user", user);
		        requestDispatcher.include(request, response);
				logger.info("Username :"  +user+ " login unsuccessful");
				
		}
		
       
	
	}

}
