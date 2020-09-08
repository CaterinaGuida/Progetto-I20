package solver.solver;

import webui.server.ApplicationServer;
import webui.server.SessionManager;
import webui.server.SolverServlet;

public class Test {

	public static void main(String[] args) {
		
		try {
			new ApplicationServer(8080,new SolverServlet(new SessionManager())).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
