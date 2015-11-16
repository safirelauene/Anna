package br.com.fatec.anna.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTeste extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<title> pqp!!! </title>");
		resp.getWriter().println("<h7 style= 'color: Red'>Aeee CARAAAAIIIIIIII!!!!!</h7>");
		resp.getWriter().println("<marquee direction= behavior style= 'color: Red'>VOU pirocar ESSA PORRA EM TUDOOO VEIIIIIIIIIIIIIIIIIIIIIIIIIII</h7>");
		resp.getWriter().println("</html>");
	}
	

}
