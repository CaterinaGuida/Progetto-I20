package gui;

import java.util.ArrayList;
import java.util.Observer;

import solver.Answer;

@SuppressWarnings("deprecation")
public interface GuiInterface{
	
	public abstract int readAnswer();
	
	public abstract void printQuestion(String questionText);
	
	public abstract void printOptions(ArrayList<String> answers);
	
	public abstract void printSolution(String solutionText);
}