package solver.factory;

import java.util.HashMap;

import solver.Conversation;
import solver.SolverFacade;


/// solver Application gestisce le istanze del solver è un singleton

public class SolverFactory {
	private static SolverFactory istance = null;
	
	private SolverFactory() {}
	
	public static SolverFactory getIstance() {
		if(istance == null) {
			istance = new SolverFactory();
		}
		return istance;
	}
	
	public SolverFacade requestNewSolver(String name) {		
		return new SolverFacade(new Conversation());
	}
	
}
