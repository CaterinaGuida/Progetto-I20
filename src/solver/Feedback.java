package solver;

public class Feedback {
	private boolean satisfied;
	private String suggestion;
	
	private Feedback() {
		
	}
	
	private boolean askSatisfaction(boolean input) {
		//imposta satisfied in base a input utente (l'input dovra' essere convertito in un boolean per passarlo in questo metodo)
		this.satisfied=input;
		return satisfied;
	}
	
	private String beginFeedback() {
		//metodo per gestire il feedback a seconda di satisfied
		//se satisfied sara' true, manda un messaggio predefinito e chiude la conversazione
		//se satified sara' false aspetta l'imput dell'utente e poi ricomincia la conversazione
		
		
		
		String output="quello che mettera' l'utente oppure messaggio predefinito";
		//sarebbe opportuno salvare il risultato in una tabella del db
		return output;
	}
	
	private void saveFeedback(String feedback) {
		//metodo per salvare il feedback in un database
	}

}
