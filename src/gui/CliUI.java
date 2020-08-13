package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CliUI implements GuiInterface{
	
	private void printQuestion(String questionText) {
		System.out.println(questionText);
	}
	
	private void printPossibleAnswers(ArrayList<String> answers) {
		for(String s: answers) {
			System.out.println(s);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int readAnswer() {
		try {
			return System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	


}
