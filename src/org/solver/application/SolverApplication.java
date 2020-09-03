package org.solver.application;

import controller.SolverFacade;
import solver.Conversation;

/// solver Application gestisce le istanze del solver è un singleton

public class SolverApplication {
	private static SolverApplication istance = null;
	
	private SolverApplication() {}
	
	public static SolverApplication getIstance() {
		if(istance == null) {
			istance = new SolverApplication();
		}
		return istance;
	}
	
	public SolverFacade requestNewSolver() {		
		return new SolverFacade(new Conversation());
	}
	
}
