package solver;


import controller.GuiController;
import controller.SolverFacade;
import gui.CliUI;
import webui.server.ApplicationServer;
import webui.server.SolverServlet;


public class Test {

	public static void main(String[] args) {
		Boolean b=true;
		System.out.println(b.toString());
		Conversation conv= new Conversation();
		
		/*
		GuiController cnt= new GuiController(new CliUI(), conv);
		conv.addObserver(cnt);
		
		conv.beginConversation();*/
		
		try {
			new ApplicationServer(8080,new SolverServlet(new SolverFacade(conv))).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
