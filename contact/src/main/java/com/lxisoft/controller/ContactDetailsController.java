package com.lxisoft.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxisoft.repo.ContactRepo;

@WebServlet("/contacts")
public class ContactDetailsController extends HttpServlet {
	

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		
		ContactRepo contactRepo = new ContactRepo();
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		try {
			req.setAttribute("contacts",contactRepo.getContact(username));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher reqD = req.getRequestDispatcher("/Contact.jsp");
		reqD.forward(req,res);
	}
	
}
