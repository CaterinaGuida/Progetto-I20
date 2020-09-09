package application;

import webui.server.ApplicationServer;
import webui.server.SessionManager;
import webui.server.SolverServlet;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new ApplicationServer(8080,new SolverServlet(new SessionManager())).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
