package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

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
		Scanner sca= new Scanner(System.in);
		System.out.print("scegli ==>> ");
		return sca.nextInt();

	}

	@Override
	public void printSolution(String solutionText) {
		// TODO Auto-generated method stub
		System.out.println("Soluzioneeee!!!!!");
		System.out.println(solutionText);
	}
	
	


}