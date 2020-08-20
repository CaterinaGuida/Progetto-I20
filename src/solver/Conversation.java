package solver;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.HashMap;

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

	
	public Conversation() {
		this.currentCode = "0";
		this.foundASolution = false;
		this.solution = null;
		currentCode = applianceSelector();
		this.applianceTable = new QuestionTable(currentCode);
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
	 *affinchè restituisca l'oggetto Question pertinente.*/
	private void nextQuestion(Question question, String optionCode) { 
		String newCode = question.getCode() + optionCode;
		this.currentCode = newCode;
		this.qst = applianceTable.questionTable.get(newCode);
	}

	private void prevQuestion(Question question) {
		String oldCode=question.getCode().substring(0, question.getCode().length());
		question.setCode(oldCode);		//se l'utente ha deciso di non rispondere e di tornare indietro, modifica il codice 
		this.setCurrentCode(oldCode);		//togliendo l'ultima cifra, restituendo cosi la domanda precedente

		this.qst=question;
	}

	/*Pre-condizione: l'attributo qst di tipo Question Ã¨ stato giÃ  inizializzato.
	 *Implementazione: Banalmente questo metodo restituisce un oggetto di tipo risposta che Ã¨ effettivamente la risposta dell'utente. Dal momento che 
	 *l'input dell'utente Ã¨ una string, il metodo si occupa anche, tramite il ciclo for, di "ricondurre" l'input ad una Answer vera e propria tra quelle che
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
	}  //questa l ho commentata perchÃ¨ Ã¨ una funziona risarvata alle interfacce
	
	//Metodo per verificare se la conversazione Ã¨ giunta praticamente al termine
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
			//automaticamente ans Ã¨ l' ultima risposta
			
			
			//Verifico se Ã¨ l'ultima domanda
			if (!isLastQuestion(ans)) 
				nextQuestion(qst, ans.getCode()); //In caso non lo fosse, aggiorno la domanda
			else
				setFoundASolution(true);
		}
		
		setChanged();
		notifyObservers();
		
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
	
	
	/*Metodo privato da richiamare nel costruttore per far selezionare all'utente l'elettrodomestico.
	 *Restituisce una stringa che rappresenta il codice del prodotto. Questa quindi andrà a sovrascrivere il currentCode, che all'inizio della conversazione
	 *è impostato a 0, codice della prima domanda in assoluto.
	 *F per frigo, L per lavatrice...*/
	private String applianceSelector() {
		//Per il momento è vuoto perché ci occupiamo solo del frigo.		
		return "F"; //Hard-code del valore F(rigo).
	}
	
	
	
	//DEBUG
	public static void main(String[] args) {
		Conversation conv = new Conversation();
		System.out.println(conv.getQuestionText());
		System.out.println(conv.qst.getOptions().size());
		conv.readAnswer();
	}

}
