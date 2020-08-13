package solver;

import java.util.Observable;

public class Conversation extends Observable{
	private String currentCode;
	private boolean foundASolution;
	private String solution;


public Conversation() {
	this.currentCode="0";	//ho messo 0 ma sara' uguale al codice della prima domanda in assoluto
	this.foundASolution=false;
	}

public void start() {
	Question qst = new Question(currentCode);
	while(!foundASolution) { //il ciclo di ripete finch� non viene chiamata dal DB una domanda che in realt� � una soluzione
		System.out.println(qst.getText());
		for (Answer option : qst.getOptions()) { //mosta all'utente domanda e opzioni
			System.out.println(option.getText());
			}
		
		{
		//!!!!!!ASPETTA L'IMPUT DELL'UTENTE!!!!!!
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


}