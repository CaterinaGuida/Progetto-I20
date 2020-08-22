package webui.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SolverFacade;
import controller.SolverSignals;
import org.rythmengine.Rythm;

public class SolverServlet extends HttpServlet {
	private SolverFacade sf;
	
	public SolverServlet(SolverFacade sf) {
		// TODO Auto-generated constructor stub
		this.sf=sf;
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
				String rend= Rythm.render("question.rtm",sf.retreiveQuestionText(),sf.retreiveQuestionOptions());
				resp.getWriter().write(rend);
			}
		}else if(req.getPathInfo().equals("/solution")) {
			resp.getWriter().write(Rythm.render("solution.rtm", sf.getSolution()));
		}else {
			resp.getWriter().write(Rythm.render("welcome.rtm",null));
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
