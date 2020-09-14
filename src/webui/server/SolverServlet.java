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
	// Metodi Di Utilità
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
	
	private void createNewSession(HttpServletResponse resp) throws IOException {
		String sid=null;
		try {
			sid = sm.genNewSession();
			resp.addCookie(CookieParser.genCookie(sid, sm.getApplicationID()));
		} catch (ConflictingSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Metodi per Rendering e salvataggio
	
	private void renderQuestionPage(SolverFacade sf, HttpServletResponse resp) throws IOException {
		String defaultPage = "question.rtm";
		if(sf.isTheFirstQuestion()) {
			defaultPage="fquestion.rtm";
		}
		String rend= Rythm.render(defaultPage,sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
		resp.getWriter().write(rend);
	}
	
	private void saveQuestionMethodGet(SolverFacade sf, HttpServletRequest req) {
		sf.sendAnswer(req.getParameter("answ"));
		sf.goNext();
	}
	
	private void renderSolutionPage(SolverFacade sf, HttpServletResponse resp) throws IOException {
		resp.getWriter().write(Rythm.render("solution.rtm", sf.getSolution()));
	}
	
	private void renderFeedbackPage(HttpServletResponse resp) throws IOException {
		resp.getWriter().write(Rythm.render("feedback.rtm",null));
	}
	
	private void suppressSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		sm.destroySession(CookieParser.getValueFrom(req.getCookies(), sm.getApplicationID()));
		resp.getWriter().write("Goodbye");
		resp.sendRedirect("/");
	}
	
	private void saveFeedbackMethodPost(SolverFacade sf, HttpServletRequest req) {
		boolean sati = Boolean.parseBoolean(req.getParameter("satisf"));
		sf.sendSolverFeedback(req.getParameter("feedMail"), req.getParameter("feedText"), sati);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/prevqst")) {
			
			SolverFacade sf=getSolver(req);
			sf.goPrevious();
			renderQuestionPage(sf, resp);
		}
		else if (req.getPathInfo().equals("/saveqst")) {
			saveQuestionMethodGet(getSolver(req), req);
			resp.sendRedirect("/nextqst");
		}
		else if (req.getPathInfo().equals("/nextqst")) {
			SolverFacade sf=getSolver(req);
			if(sf.isFoundASolution()) {
				resp.sendRedirect("/solution");
				
			}else {
				renderQuestionPage(sf, resp);
			}
		}else if(req.getPathInfo().equals("/solution")) {
			renderSolutionPage(getSolver(req), resp);
			
		}else if(req.getPathInfo().equals("/suppress")) {
			suppressSession(req, resp);
			
		}else if(req.getPathInfo().equals("/feedback")) {
			renderFeedbackPage(resp);
		
		}else {
			createNewSession(resp);
			resp.sendRedirect("/nextqst");
		}
		
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getPathInfo().equals("/genfeedback")) {
			saveFeedbackMethodPost(getSolver(req), req);
			resp.sendRedirect("/suppress");
	}
	}
}
