package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CliUI implements GuiInterface{
	
	public void printQuestion(String questionText) {
		System.out.println(questionText);
	}
	
	public void printOptions(ArrayList<String> answers) {
		for(String s: answers) {
			int index= answers.indexOf(s);
			System.out.println(index + ") "+s);
		}
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

	@Override
	public void printSolution(String solutionText) {
		// TODO Auto-generated method stub
		System.out.println("Soluzioneeee!!!!!");
		System.out.println(solutionText);
	}
	
	


}