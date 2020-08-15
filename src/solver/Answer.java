package solver;

public class Answer {
	private String code;
	private String text;

	public Answer(String code){
		this.code=code;
		//DA MODIFICARE INSERENDO IL METODO CHE LEGGE DA DB
	}

	public String getCode() {
		return code;
		//mi da il codice dell'opzione, quello che l'utente dovr� inserire per selezionarla
	}

	public String getText() {
		return text;
		//mi da il testo dell'opzione
	}

	public void setCode(String code) {
		this.code = code;
		//possibilia' di modificare il codice (forse inutile)
	}

	public void setText(String text) {
		this.text = text;
		//possibilita di modifica il testo (forse inutile)
	}

}
