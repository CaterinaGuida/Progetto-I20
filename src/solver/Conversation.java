package solver;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.HashMap;
import daos.FeedbackDAO;
import daos.QuestionDAO;
import daos.SolutionDAO;

@SuppressWarnings("deprecation")
public class Conversation extends Observable{
	private String currentCode;
	private boolean foundASolution;
	private String solution;
	//question e answer vengono aggiornate 
	//ogni volta dai vari metodi
	private QuestionTable applianceTable;
	private Question qst;
	private Answer ans;
	
	public static final String QUESTION_ZERO = "0";
	
	public Conversation() {
		this.currentCode = QUESTION_ZERO;
		this.foundASolution = false;
		this.solution = null;
		this.applianceTable = new QuestionTable();
		this.qst = applianceTable.questionTable.get(currentCode);
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

	/*Pre-condizione : question e optionCode sono inizializzati.
	 *Implementazione: Questa rivisitazione del metodo nextQuestion calcola il codice della domanda successiva, lo assegna alla
	 *variabile currentCode della classe Conversation e, sempre col medesimo codice appena calcolato, interroga la tabella di Hash
	 *affinch� restituisca l'oggetto Question pertinente.*/
	public void nextQuestion() {
		String newCode = qst.getCode() + ans.getCode();
		if (!isLastQuestion(ans)) {
			this.currentCode = newCode;
			this.qst = applianceTable.questionTable.get(newCode);
		}
		else
			setFoundASolution(true);
	}

	/*Se l'utente ha deciso di non rispondere e di tornare indietro, modifica il codice 
	 *togliendo l'ultima cifra, restituendo così la domanda precedente*/
	
	public void prevQuestion() {
		String oldCode = qst.getCode().substring(0, qst.getCode().length() - 1);
		this.currentCode = oldCode;
		this.qst = applianceTable.questionTable.get(oldCode);
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
		for (Answer answerIterator : qst.getOptions()) {
			if (answerIterator.getCode().contains(optionCode)) //Condizione per decretare effettivamente quale risposta ha scelto l'utente
				givenAnswer = answerIterator;
				break;
		}
		return givenAnswer;
	}  //questa l ho commentata perchè è una funziona risarvata alle interfacce
	
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
			
			//manda notifica agli observers
			setChanged();
			notifyObservers();
			//automaticamente ans è l' ultima risposta
			
			
			//Verifico se è l'ultima domanda
			if (!isLastQuestion(ans)) 
				nextQuestion(); //In caso non lo fosse, aggiorno la domanda
			else
				setFoundASolution(true);
		}
		
		setChanged();
		notifyObservers();
		
		//Qui o in un altro metodo da richiamare sempre qua, ci andrebbe il codice per mostrare la soluzione.
	}
	

	
	public void callFeedback(boolean sat, String sugg, String mail) {
		String prod_id = qst.getProduct();
		Feedback fb = new Feedback(sat, sugg, mail, prod_id);
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
	
	
	//Metodo per mostrare le opzioni della domanda in questione
	private void displayOptions(Question qst) {
		for (Answer a:qst.getOptions()) 
			System.out.println(a.toString());
	}
	
	//DEBUG
	public static void main(String[] args) {
		Conversation conv = new Conversation();
		System.out.println(conv.getQuestionText());
		conv.displayOptions(conv.qst); //@TODO: Non so perché ma stampa le domande in ordine questo ordine: 1 - 3 - 2. o.O
		
		conv.readAnswer();
	}

}
