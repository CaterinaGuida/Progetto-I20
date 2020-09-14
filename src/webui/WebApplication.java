package webui;

import webui.server.ApplicationServer;
import webui.server.SessionManager;
import webui.server.SolverServlet;

public class WebApplication {	//Singleton consente di attivare solo una istanza del server dell' applicazione
	private static WebApplication istance = null;
	private boolean isServerRunning;
	private static String WEBAPPID = "solver.webui.server.sessions.id";
	
	private WebApplication() {
		isServerRunning= false;
	}
	
	public static WebApplication getIstance() {
		if(istance == null)
			istance = new WebApplication();
		
		
		return istance;
	}
	
	private void serverStart() {
		try {
			ApplicationServer app = new ApplicationServer(8080, new SolverServlet(new SessionManager(WEBAPPID)));
			app.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startWebApplicationServer() {
		if(!isServerRunning) {
			serverStart();
			isServerRunning=true;
		}
	}
	

}
