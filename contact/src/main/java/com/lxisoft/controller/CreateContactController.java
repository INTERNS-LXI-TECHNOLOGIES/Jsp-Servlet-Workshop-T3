package com.lxisoft.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lxisoft.model.ContactDetails;
import com.lxisoft.repo.ContactRepo;
@WebServlet("/addcontacts")
public class CreateContactController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ContactRepo contactRepo = new ContactRepo();
		ContactDetails contact = new ContactDetails();
		contact.setName(req.getParameter("name"));
		contact.setEmailid(req.getParameter("emailid"));
		contact.setNumber(req.getParameter("number"));
		HttpSession ses = req.getSession();
		String username = (String)ses.getAttribute("username");
		
		try {
			contactRepo.addContact(contact,username);
			res.sendRedirect("contacts");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		};
	}
	
}
