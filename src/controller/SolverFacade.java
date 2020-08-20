package controller;

import java.util.ArrayList;

import controller.util.ConversationUtil;
import solver.Conversation;

public class SolverFacade{
	private Conversation conv;
	public SolverFacade(Conversation c) {
		this.conv=c;
	}
	public void sendAnswer(String s) {
		conv.setAnswerReady(ConversationUtil.fromStringToAnswer(s, conv.getAnswersList()));
	}
	
	public void sendSignal(SolverSignals si) {
		if(si.equals(SolverSignals.NEXTQ))
			conv.next();
	}
	
	public String retreiveQuestionText() {
		return conv.getQuestionText();
	}
	
	public ArrayList<String> retreiveQuestionOptions(){
		return ConversationUtil.fromAnswersToStrings(conv.getAnswersList());
	}
}
