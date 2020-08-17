package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.util.ConversationUtil;
import gui.GuiInterface;
import solver.Answer;
import solver.Conversation;

public class GuiController implements Observer{
	private GuiInterface interfaccia;
	private Conversation conversazione;
	public GuiController(GuiInterface interfaccia, Conversation conversazione) {
		super();
		this.interfaccia = interfaccia;
		this.conversazione = conversazione;
		
		beginControl();
	}
	
	@SuppressWarnings("deprecation")
	private void beginControl() {
		conversazione.addObserver(this);
		
		
	}
	
	private void startDialog() {
		if(conversazione.isFoundASolution() == true)
			interfaccia.printSolution(conversazione.getSolution());
		else {
			interfaccia.printQuestion(conversazione.getQuestionText()); //da definire passaggiodi parametri
			ArrayList<String> stringOptionList=ConversationUtil.fromAnswersToStrings(
					conversazione.getAnswersList());
			interfaccia.printOptions(stringOptionList);  // da definire il passaggio di parametri
			int index = interfaccia.readAnswer();
			String re=stringOptionList.get(index); 
			Answer a= ConversationUtil.fromStringToAnswer(re, conversazione.getAnswersList());
			
			
			conversazione.setAnswerReady(a);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		startDialog();
	}
	
	
}