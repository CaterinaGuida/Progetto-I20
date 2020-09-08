package solver.util;

import java.util.ArrayList;

import solver.solver.Answer;


public class ConversationUtil {
	public static ArrayList<String> fromAnswersToStrings(ArrayList<Answer> options){
		ArrayList<String> strOp = new ArrayList<String>();
		for(Answer a: options) {
			strOp.add(a.getText());
		}
		
		return strOp;
	}
	
	public static Answer fromStringToAnswer(String s, ArrayList<Answer> options) {// necessita della lista answer originale
		
		for(Answer a: options) {
			if(a.getText().equals(s))
				return a;
		}
		return null;
	}
}

