package solver.application;

import java.util.HashMap;

import solver.solver.Conversation;
import solver.solver.SolverFacade;

/// solver Application gestisce le istanze del solver è un singleton

public class SolverApplication {
	private static SolverApplication istance = null;
	private static int solverIstancesCount = 0;
	private static HashMap<String, SolverFacade> threadList = null;
	
	private SolverApplication() {}
	
	public static SolverApplication getIstance() {
		if(istance == null) {
			istance = new SolverApplication();
			threadList= new HashMap<String, SolverFacade>();
			solverIstancesCount=0;
		}
		return istance;
	}
	
	public SolverFacade requestSolver(String sid) {
		return threadList.get(sid);
	}
	
	public SolverFacade requestNewSolver(String sid){
		threadList.put(sid, new SolverFacade(new Conversation()));
		return threadList.get(sid);
	}
	
}
