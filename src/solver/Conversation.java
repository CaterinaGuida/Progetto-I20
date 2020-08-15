package solver;

import java.util.Observable;
import java.util.Scanner;

public class Conversation extends Observable{
	private String currentCode;
	private boolean foundASolution;
	private String solution;


	public Conversation() {
		//Costruttore inutile, lasciato bianco a posta.
	}
	
	/*Pre-condizione: FirstQuestion è una domanda non vuota.
	 *Implementazione: Il costruttore prende come parametro un oggetto di tipo Question, ne legge il codice e lo assegna alla variabile currentCode.
	 *Post-condizione: L'oggetto conversazione così istanziato "punterà" alla prima domanda".
	 */
	
	public Conversation(Question FirstQuestion) {
		this.currentCode = FirstQuestion.getCode();
		this.foundASolution = false;
	}
	
	//Metodi getter e setter
	public String getCurrentCode() {
		return currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}

	public boolean isFoundASolution() {
		return foundASolution;
	}

	public void setFoundASolution(boolean foundASolution) {
		this.foundASolution = foundASolution;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public void nextQuestion(Question question, String optionCode) { //permette di modificare il contenuto della domanda in base a
		String newCode=question.getCode()+optionCode;				//cosa ha scelto l'utente, se ha scelto di rispondere
		question.setCode(newCode);
		this.setCurrentCode(newCode);

		//notifica agli observer

	}
	public void prevQuestion(Question question) {
		String oldCode=question.getCode().substring(0, question.getCode().length());
		question.setCode(oldCode);		//se l'utente ha deciso di non rispondere e di tornare indietro, modifica il codice 
		this.setCurrentCode(oldCode);		//togliendo l'ultima cifra, restituendo cosi la domanda precedente

		//notifica agli observer
	}

	private String leggiInput() {
		Scanner tastiera = new Scanner(System.in);
		String optionCode = tastiera.next();
		return optionCode;
	}

}