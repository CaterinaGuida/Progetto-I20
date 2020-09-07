package org.solver.application;

import java.util.HashMap;

import controller.SolverFacade;
import solver.Conversation;

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
