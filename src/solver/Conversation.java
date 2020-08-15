package solver;

import java.util.ArrayList;
import java.util.Observable;

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
		this.currentCode="0";	//ho messo 0 ma sara' uguale al codice della prima domanda in assoluto
		this.foundASolution=false;
	}

<<<<<<< HEAD
public void start() {
	Question qst = new Question(currentCode);
	while(!foundASolution) { //il ciclo di ripete finche' non viene chiamata dal DB una domanda che in realt� � una soluzione
		System.out.println(qst.getText());
		for (Answer option : qst.getOptions()) { //mosta all'utente domanda e opzioni
			System.out.println(option.getText());
=======
	public void start() {
		qst = new Question(currentCode);
		while(!foundASolution) { //il ciclo di ripete finch� non viene chiamata dal DB una domanda che in realt� � una soluzione
			System.out.println(qst.getText());
			for (Answer option : qst.getOptions()) { //mosta all'utente domanda e opzioni
				System.out.println(option.getText());
>>>>>>> branch 'master' of https://github.com/IngSW-unipv/Progetto-I20.git
			}

			{
				//!!!!!!ASPETTA L'INPUT DELL'UTENTE!!!!!!
			}

			String input="quello che ha messo l'utente";

			{
				//qui ci dovr� essere una parte dedicata allo studio dell'input in base al quale si decide se chiamare nextQuestion o
				//prevQuestion
			}
			{
				//qui invece un check sul database per vedere se la domanda e' una soluzione, nel caso lo fosse si modifica
				//foundASOlution in true e si arresta il ciclo
			}
		}
	}
	public void nextQuestion(Question question, String optioncode) { //permette di modificare il contenuto della domanda in base a
		String newcode=question.getCode()+optioncode;				//cosa ha scelto l'utente, se ha scelto di risondere
		question.setCode(newcode);
		this.currentCode=newcode;

		//notifica agli observer

	}
	public void prevQuestion(Question question) {
		String oldcode=question.getCode().substring(0, question.getCode().length());
		question.setCode(oldcode);		//se l'utente ha deciso di non rispondere e di tornare indietro, modifica il codice 
		this.currentCode=oldcode;		//togliendo l'ultima cifra, restituendo cosi la domanda precedente

		//notifica agli observer
	}

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