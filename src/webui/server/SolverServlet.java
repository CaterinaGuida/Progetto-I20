package webui.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import solver.SolverFacade;
import webui.server.exceptions.ConflictingSessionException;
import webui.server.exceptions.SessionExpiredException;

public class SolverServlet extends HttpServlet {
	private SessionManager sm;
	
	public SolverServlet(SessionManager sm) {
		// TODO Auto-generated constructor stub
		this.sm=sm;
		
	}
	
	private SolverFacade getSolver(HttpServletRequest req) {
		String sid= CookieParser.getValueFrom(req.getCookies(), sm.getApplicationID());
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
			sm.destroySession(CookieParser.getValueFrom(req.getCookies(), sm.getApplicationID()));
			resp.getWriter().write("Goodbye");
			resp.sendRedirect("/");
		}else if(req.getPathInfo().equals("/feedback")) {
			resp.getWriter().write(Rythm.render("feedback.rtm",null));
		
		}else {
			String sid=null;
			try {
				sid = sm.genNewSession();
				resp.addCookie(CookieParser.genCookie(sid, sm.getApplicationID()));
			} catch (ConflictingSessionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SolverFacade sf=sm.getSessionFromId(sid);
			String rend= Rythm.render("welcome.rtm",sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
			resp.getWriter().write(rend);
		}
		
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getPathInfo().equals("/genfeedback")) {
			boolean sati = Boolean.parseBoolean(req.getParameter("satisf"));
			SolverFacade sf=getSolver(req);
			sf.sendSolverFeedback(req.getParameter("feedMail"), req.getParameter("feedText"), sati);
			resp.sendRedirect("/suppress");
	}
	}
}
