package webui.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SolverFacade;
import controller.SolverSignals;


public class SolverServlet extends HttpServlet {
	private SolverFacade sf;
	
	public SolverServlet(SolverFacade sf) {
		// TODO Auto-generated constructor stub
		this.sf=sf;
	}
	
	private void genSolutionPage(HttpServletResponse resp) throws IOException {
		resp.getWriter().write("<h1>"+sf.getSolution()+"</h1>");
	}
	
	private void buildAnswersForm(HttpServletResponse resp, ArrayList<String> options) throws IOException {
		
		resp.getWriter().write("<form method=\"get\" action=\"/saveqst\"><br>");
		resp.getWriter().write("<select name=\"answ\" id=\"answ\">");
		
		for(String s: options) {
			resp.getWriter().write("<option value=\""+s+"\">"+s+"</option>");
			resp.getWriter().write("<br>");
		}
		resp.getWriter().write("<input type=\"submit\" value=\"Next Question\"/>");
		resp.getWriter().write("</form>");
	}

	private void saveResp(HttpServletRequest req) {
		System.out.println(req.getParameter("answ"));
		sf.sendAnswer(req.getParameter("answ"));
			
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/prevqst")) {
			
		}
		else if (req.getPathInfo().equals("/saveqst")) {
			saveResp(req);
			sf.sendSignal(SolverSignals.NEXTQ);
			resp.sendRedirect("/nextqst");
		}
		else if (req.getPathInfo().equals("/nextqst")) {
			
			
			if(sf.isFoundASolution()) {
				resp.sendRedirect("/solution");
			}else {
				resp.getWriter().write("<h1>"+sf.retreiveQuestionText()+"</h1>");
				buildAnswersForm(resp, sf.retreiveQuestionOptions());
			}
		}else if(req.getPathInfo().equals("/solution")) {
			genSolutionPage(resp);
		}else {
			resp.getWriter().write("<h1>Benvenuto il pennuto</h1><br><a href=\"/nextqst\">Clicca per cominciare</a>");
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
