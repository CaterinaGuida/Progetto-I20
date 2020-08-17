package solver;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import daos.SolutionDAO;

@SuppressWarnings("deprecation")
public class Conversation extends Observable{
	private String currentCode;
	private boolean foundASolution;
	private String solution;
	//question e answer vengono aggiornate 
	//ogni volta dai vari metodi
	private Question qst;
	private Answer ans;


	public Conversation() {
		//Costruttore inutile, lasciato bianco a posta.
	} //comunicare con fra molte cose non chiare 
	
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
	public String getSolution(String code) {
		SolutionDAO solution=new SolutionDAO();
		return solution.getText(code);
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
		String newCode = question.getCode() + optionCode;				//cosa ha scelto l'utente, se ha scelto di risondere
		question.setCode(newCode);
		this.currentCode = newCode;
		question.setText(); //Aggiunto questo metodo così da aggiornare anche il testo della domanda oltre al codice.
		question.setOptions(); //Aggiunto questo metodo così da aggiornare anche le risposte possibili associate alla domanda.
		//notifica agli observer

	}

	public void prevQuestion(Question question) {
		String oldCode=question.getCode().substring(0, question.getCode().length());
		question.setCode(oldCode);		//se l'utente ha deciso di non rispondere e di tornare indietro, modifica il codice 
		this.setCurrentCode(oldCode);		//togliendo l'ultima cifra, restituendo cosi la domanda precedente

		//notifica agli observer
	}

	/*Pre-condizione: l'attributo qst di tipo Question è stato già inizializzato.
	 *Implementazione: Banalmente questo metodo restituisce un oggetto di tipo risposta che è effettivamente la risposta dell'utente. Dal momento che 
	 *l'input dell'utente è una string, il metodo si occupa anche, tramite il ciclo for, di "ricondurre" l'input ad una Answer vera e propria tra quelle che
	 *l'oggetto Question qst gli propone. Per farlo, confronto l'opzione data dall'utente con il codice di ogni risposta. Una volta trovato interrompo 
	 *l'iterazione e restituisco la Answer a cui il codice fornito si riferisce.
	 *Post-condizione: adesso sappiamo quale risposta (Answer) ha dato l'utente.*/
	private Answer readAnswer() {
		Scanner tastiera = new Scanner(System.in);
		String optionCode = tastiera.next();
		Answer givenAnswer = new Answer();
		for (int i = 0; i < qst.getOptions().size(); i++) {
			Answer answerIterator = qst.getOptions().get(i); //Una variabile che contiene l'i-esima risposta
			if (answerIterator.getCode().contains(optionCode))
				givenAnswer = answerIterator;
				break;
		}
		return givenAnswer;
	}
	
	//Metodo per verificare se la conversazione è giunta praticamente al termine
	private boolean isLastQuestion(Answer answerGiven) {
		if (answerGiven.getCode().contains("S"))
			return true;
		else
			return false;
	}
	
	/*Questo metodo gestisce essenzialmente il botta e risposta tra client e server.*/
	private void problemFinder() {
		while (!foundASolution) {
			System.out.println(qst.getText());
			System.out.println(qst.getOptions());
			Answer userChoice = readAnswer();
			//Verifico se è l'ultima domanda
			if (!isLastQuestion(userChoice)) 
				nextQuestion(qst, userChoice.getCode()); //In caso non lo fosse, aggiorno la domanda
			else
				setFoundASolution(true);
			
		}
		
		//Qui o in un altro metodo da richiamare sempre qua, ci andrebbe il codice per mostrare la soluzione.
	}
	
	//Commentare questi metodi.
	public void setAnswerReady(Answer ans){
		this.ans=ans;
	}
	
	public ArrayList<Answer> getAnswersList(){
		return qst.getOptions();
	}
	
	public String getQuestionText() {
		return qst.getText();
	}
}
