package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.enitities.Note;
import com.helper.FactoryProvider;


/**
 * Servlet implementation class SaveNoteServlet
 */
@WebServlet("/SaveNoteServlet")
public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try { 
//			title content fetch
			String title= request.getParameter("title");
			String content= request.getParameter("content");
			
			Note note = new Note(title,content ,new Date());
//			System.out.println(note.getId()+ " : " +note.getTitle());
//			Hibernate : save() there is no need for make a controller ,dao hibernate write a all query
		Session s= FactoryProvider.getFactory().openSession();
		Transaction tx= s.beginTransaction();
		s.save(note);
		tx.commit();
		s.close();
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		out.println("<h1 style = 'text-align:center;'>Note is added successfully</h1>");
		
		
		
		}
		
	
		catch(Exception e){
			e.printStackTrace();
		}

}
}
