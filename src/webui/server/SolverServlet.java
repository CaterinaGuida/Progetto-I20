package webui.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SolverFacade;
import org.rythmengine.Rythm;

public class SolverServlet extends HttpServlet {
	private SessionManager sm;
	private static final String ID="org.solver.webui.session.id";
	
	public SolverServlet(SessionManager sm) {
		// TODO Auto-generated constructor stub
		this.sm=sm;
	}
	
	private Cookie genSessionCookie() {
		return new Cookie(ID, sm.genNewSession());
	}
	
	private String getSid(HttpServletRequest req) {
		Cookie[] cookieList=req.getCookies();
		
		for(Cookie c : cookieList) {
			if(c.getName().equals(ID)) {
				return c.getValue();
			}
		}
		return null;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/prevqst")) {
			
			SolverFacade sf=sm.getSessionFromId(getSid(req));
			sf.goPrevious();
			System.out.println(sf.retreiveQuestionText());
			String rend= Rythm.render("question.rtm",sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
			resp.getWriter().write(rend);
		}
		else if (req.getPathInfo().equals("/saveqst")) {
			
			SolverFacade sf=sm.getSessionFromId(getSid(req));
			System.out.println(req.getParameter("answ"));
			sf.sendAnswer(req.getParameter("answ"));
			sf.goNext();
			resp.sendRedirect("/nextqst");
		}
		else if (req.getPathInfo().equals("/nextqst")) {
			
			SolverFacade sf=sm.getSessionFromId(getSid(req));
			System.out.println(getSid(req));
			if(sf.isFoundASolution()) {
				resp.sendRedirect("/solution");
			}else {
				String rend= Rythm.render("question.rtm",sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
				resp.getWriter().write(rend);
			}
		}else if(req.getPathInfo().equals("/solution")) {
			
			resp.getWriter().write(Rythm.render("solution.rtm", sm.getSessionFromId(getSid(req)).getSolution()));
		}else if(req.getPathInfo().equals("/feedback")) {
			
			resp.getWriter().write(Rythm.render("feedback.rtm",null));
		}else if(req.getPathInfo().equals("/genfeedback")) {
			boolean sati = Boolean.parseBoolean(req.getParameter("satisf"));
			
			SolverFacade sf=sm.getSessionFromId(getSid(req));
			sf.sendSolverFeedback(req.getParameter("feedMail"), req.getParameter("feedText"), sati);
			resp.sendRedirect("/");
		}else {
			
			resp.addCookie(genSessionCookie());
			resp.sendRedirect("/nextqst");
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
