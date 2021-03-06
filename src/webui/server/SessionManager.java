package webui.server;

import java.util.HashMap;

import javax.websocket.SessionException;

import solver.Conversation;
import solver.SolverFacade;
import solver.factory.SolverFactory;
import webui.server.exceptions.ConflictingSessionException;
import webui.server.exceptions.SessionExpiredException;

public class SessionManager {
	private HashMap<String, SolverFacade> sessions;
	private String applicationID;
	public SessionManager(String appId) {
		sessions=new HashMap<String, SolverFacade>();
		applicationID=appId;
	}
	
	private String genSessionId() { //genera una stringa formata da sid e 10 cifre casuali
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return "SID" + String.valueOf(number);
	}
	
	
	
	public String getApplicationID() {
		return applicationID;
	}

	public String genNewSession() throws ConflictingSessionException {
		String sid = genSessionId();
		
		if(sessions.containsKey(sid)) {
			throw new ConflictingSessionException(sid);
		}
		
		sessions.put(sid, SolverFactory.getIstance().requestNewSolver(sid));
		return sid;
	}
	
	public void destroySession(String sid) {
		sessions.remove(sid);
	}
	
	public boolean checkExpired(String sid) throws SessionExpiredException {
		for(String s: sessions.keySet()) {
			if(s.equals(sid)) {
				return true;
			}
		}
		throw new SessionExpiredException();
	}
	
	public SolverFacade getSessionFromId(String sid) {
		return sessions.get(sid);
	}
}
