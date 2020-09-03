package webui.server;

import java.util.HashMap;

import org.solver.application.SolverApplication;

import controller.SolverFacade;
import solver.Conversation;

public class SessionManager {
	private HashMap<String, SolverFacade> sessions;
	public SessionManager() {
		sessions=new HashMap<String, SolverFacade>();
	}
	
	private String genSessionId() { //genera una stringa formata da sid e 10 cifre casuali
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return "SID" + String.valueOf(number);
	}
	
	public String genNewSession() {
		String sid = genSessionId();
		
		sessions.put(sid, SolverApplication.getIstance().requestNewSolver());
		return sid;
	}
	
	public SolverFacade getSessionFromId(String sid) {
		return sessions.get(sid);
	}
}
