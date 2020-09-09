package solver;

import java.util.ArrayList;

import solver.utils.ConversationUtil;



public class SolverFacade{
	private Conversation conv;
	public SolverFacade(Conversation c) {
		this.conv=c;
	}
	public void sendAnswer(String s) {
		conv.setAnswerReady(ConversationUtil.fromStringToAnswer(s, conv.getAnswersList()));
	}
	
	public void goNext() {
			conv.nextQuestion();
		
	}
	
	public void goPrevious() {
		conv.prevQuestion();
	}
	
	public String retreiveQuestionText() {
		return conv.getQuestionText();
	}
	
	public ArrayList<String> retreiveQuestionOptions(){
		return ConversationUtil.fromAnswersToStrings(conv.getAnswersList());
	}
	
	public boolean isFoundASolution() {
		return conv.isFoundASolution();
	}
	
	public String getSolution() {
		return conv.getSolution();
	}
	
	public void sendSolverFeedback(String contact, String feedText, boolean sati) {
		conv.callFeedback(sati, feedText, contact);
	}
}
