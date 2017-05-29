package org.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import beans.Student;

/**
 * Servlet implementation class StudentRegServelet
 */
@WebServlet("/reg")
public class StudentRegServelet extends HttpServlet {
	
	
	
	SessionFactory sf;
	@Override
	public void init(ServletConfig config) throws ServletException {
		Configuration cfg=new Configuration(); 
		cfg.configure("resources/student.hbm.xml");
		sf=cfg.buildSessionFactory();
		System.out.println("Session factory init");	
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		int marks=Integer.parseInt(req.getParameter("marks"));
		
		
		Student st= new Student(0, name, email, marks);
		
		Session session=sf.openSession();
		Transaction transactio=session.beginTransaction();
		int pk=(Integer)session.save(st);
		transactio.commit();
		session.close();
		resp.getWriter().println("Reg success id ="+pk);
		
		
		
		
		
		super.doGet(req, resp);
	}
       
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		sf.close();
		super.destroy();
	}
   
}
