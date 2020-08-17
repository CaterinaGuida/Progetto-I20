package solver;

import controller.GuiController;
import gui.CliUI;

public class Test {

	public static void main(String[] args) {
		Question q= new Question("F12");
		q.setText();
		q.setOptions();
		
		Conversation conv= new Conversation(q);
		GuiController cnt= new GuiController(new CliUI(), conv);
		conv.addObserver(cnt);
		
		conv.beginConversation();
	}
}
