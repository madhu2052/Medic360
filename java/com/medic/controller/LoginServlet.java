package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.dao.LoginDao;
import com.emp.model.LoginModel;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		LoginModel lm=new LoginModel();
		lm.setUsername(username);
		lm.setPassword(password);
		
		LoginDao ldao=new LoginDao();
		try {
			boolean status=ldao.checkCredentials(lm);
			if(status)
			{
				HttpSession session=request.getSession(true);
				session.setAttribute("uname", username);
				response.sendRedirect("index.jsp");
			}else
			{
				pw.println("<font color=red>Either username or password is wrong.</font>");
				RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


