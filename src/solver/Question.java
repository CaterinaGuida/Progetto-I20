package solver;
import java.util.ArrayList;

import daos.AnswerDAO;
import daos.QuestionDAO;

public class Question {
	private String code,product;
	private String text;
	private ArrayList<Answer> options;
	private String prod_id;
	
	public Question(String code) {
		this.code=code;
		options=new ArrayList<>();
		//QUI CI SARA' IL METODO CHE PRENDE LE INFO DAL DB
		//STESSA COSA CON LE OPTIONS
	}
	public String getProduct() {
		return product;
	}
	public void setProduct() {
		this.product= new QuestionDAO().getProduct(code);
	}
	public void setProduct(String prod) {
		this.product= prod;
	}
	public String getCode() {
		return code;
		//mi da il codice della domanda
	}

	public String getText() {
		return text;
		//mi da il testo della domada

	}

	public ArrayList<Answer> getOptions() {
		return options;
		//mi da l'array delle opzioni

	}
	
	public String getProdID() {
		return prod_id;
		//mi da il prouduct id
	}

	public void setCode(String code) {
		this.code = code;
		//mi rende possibile modificare il codice
	}

	public void setText() {
		QuestionDAO qstDAO = new QuestionDAO();
		String text = qstDAO.getText(this.code);
		this.text=text;
	}
	public void setText(String txt) {
		this.text=txt;
	}
	public void addOption(Answer a) {
		this.options.add(a);
	}
	public void setOptions() {
		AnswerDAO answer=new AnswerDAO();
		this.options=answer.getAnswer(this.code);
	}
	
	public void setProdID() {
		//chiama il metodo di question dao che mi da il prod id
	}

}
