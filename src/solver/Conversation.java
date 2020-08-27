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
	
	public Conversation(Question firstQuestion) {
		this.currentCode = firstQuestion.getCode(); //cambiare costruttore 
		this.qst=firstQuestion;
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
		SolutionDAO solution=new SolutionDAO();
		this.solution=solution.getText(this.currentCode+this.ans.getCode());
		return this.solution;
	}

	public void nextQuestion(Question question, String optionCode) { //permette di modificare il contenuto della domanda in base a
		String newCode = question.getCode() + optionCode;				//cosa ha scelto l'utente, se ha scelto di risondere
		question.setCode(newCode);
		this.currentCode = newCode;
		question.setProduct();
		question.setText(); //Aggiunto questo metodo così da aggiornare anche il testo della domanda oltre al codice.
		question.setOptions(); //Aggiunto questo metodo così da aggiornare anche le risposte possibili associate alla domanda.
		this.qst=question;

	}

	public void prevQuestion(Question question) {
		String oldCode=question.getCode().substring(0, question.getCode().length());
		question.setCode(oldCode);		//se l'utente ha deciso di non rispondere e di tornare indietro, modifica il codice 
		this.setCurrentCode(oldCode);
		question.setProduct();
		question.setText(); //Aggiunto questo metodo così da aggiornare anche il testo della domanda oltre al codice.
		question.setOptions();//togliendo l'ultima cifra, restituendo cosi la domanda precedente
		this.qst=question;
	}

	/*Pre-condizione: l'attributo qst di tipo Question è stato già inizializzato.
	 *Implementazione: Banalmente questo metodo restituisce un oggetto di tipo risposta che è effettivamente la risposta dell'utente. Dal momento che 
	 *l'input dell'utente è una string, il metodo si occupa anche, tramite il ciclo for, di "ricondurre" l'input ad una Answer vera e propria tra quelle che
	 *l'oggetto Question qst gli propone. Per farlo, confronto l'opzione data dall'utente con il codice di ogni risposta. Una volta trovato interrompo 
	 *l'iterazione e restituisco la Answer a cui il codice fornito si riferisce.
	 *Post-condizione: adesso sappiamo quale risposta (Answer) ha dato l'utente.*/
	/*private Answer readAnswer() {
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
	}*/  //questa l ho commentata perchè è una funziona risarvata alle interfacce
	
	//Metodo per verificare se la conversazione è giunta praticamente al termine
	private boolean isLastQuestion(Answer answerGiven) {
		if (answerGiven.getCode().contains("S"))
			return true;
		else
			return false;
	}
	
	/*questo metodo fa partire la conversazione*/
	public void beginConversation() {
		problemFinder();
	}
	
	/*Questo metodo gestisce essenzialmente il botta e risposta tra client e server.*/
	private void problemFinder() {
		while (!foundASolution) {
			
			//manda notifica agli observers
			setChanged();
			notifyObservers();
			//automaticamente ans è l' ultima risposta
			
			
			//Verifico se è l'ultima domanda
			if (!isLastQuestion(ans)) 
				nextQuestion(qst, ans.getCode()); //In caso non lo fosse, aggiorno la domanda
			else
				setFoundASolution(true);
		}
		
		setChanged();
		notifyObservers();
		
		//Qui o in un altro metodo da richiamare sempre qua, ci andrebbe il codice per mostrare la soluzione.
	}
	
	public void next() {
		if (!isLastQuestion(ans)) 
			nextQuestion(qst, ans.getCode()); //In caso non lo fosse, aggiorno la domanda
		else
			setFoundASolution(true);
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
