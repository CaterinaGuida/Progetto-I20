package webui.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import controller.SolverFacade;
import webui.server.exceptions.ConflictingSessionException;
import webui.server.exceptions.SessionExpiredException;

public class SolverServlet extends HttpServlet {
	private SessionManager sm;
	private static final String ID="org.solver.webui.session.id";
	
	public SolverServlet(SessionManager sm) {
		// TODO Auto-generated constructor stub
		this.sm=sm;
	}
	
	private SolverFacade getSolver(HttpServletRequest req) {
		String sid= CookieParser.getValueFrom(req.getCookies(), ID);
		try {
			if(sm.checkExpired(sid))
				return sm.getSessionFromId(sid);
		} catch (SessionExpiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/prevqst")) {
			
			SolverFacade sf=getSolver(req);
			sf.goPrevious();
			System.out.println(sf.retreiveQuestionText());
			String rend= Rythm.render("question.rtm",sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
			resp.getWriter().write(rend);
		}
		else if (req.getPathInfo().equals("/saveqst")) {
			SolverFacade sf=getSolver(req);
			System.out.println(req.getParameter("answ"));
			sf.sendAnswer(req.getParameter("answ"));
			sf.goNext();
			resp.sendRedirect("/nextqst");
		}
		else if (req.getPathInfo().equals("/nextqst")) {
			
			SolverFacade sf=getSolver(req);
			if(sf.isFoundASolution()) {
				resp.sendRedirect("/solution");
			}else {
				String rend= Rythm.render("question.rtm",sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
				resp.getWriter().write(rend);
			}
		}else if(req.getPathInfo().equals("/solution")) {
			
			resp.getWriter().write(Rythm.render("solution.rtm", getSolver(req).getSolution()));
		}else if(req.getPathInfo().equals("/suppress")) {
			sm.destroySession(CookieParser.getValueFrom(req.getCookies(), ID));
			resp.getWriter().write("Goodbye");
		}else if(req.getPathInfo().equals("/feedback")) {
			resp.getWriter().write(Rythm.render("feedback.rtm",null));
		
		}else {
			try {
				resp.addCookie(CookieParser.genCookie(sm.genNewSession(), ID));
			} catch (ConflictingSessionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect("/nextqst");
		}
		
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getPathInfo().equals("/genfeedback")) {
			boolean sati = Boolean.parseBoolean(req.getParameter("satisf"));
			SolverFacade sf=getSolver(req);
			sf.sendSolverFeedback(req.getParameter("feedMail"), req.getParameter("feedText"), sati);
			resp.sendRedirect("/");
	}
	}
}
