package solver.solver;
import java.util.ArrayList;

public class Question {
	private String code,product;
	private String text;
	private ArrayList<Answer> options;
	
	public Question(String code) {
		this.code=code;
		options=new ArrayList<>();
	}
	
	public String getProduct() {
		return product;
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
	public void setCode(String code) {
		this.code = code;
		//mi rende possibile modificare il codice
	}

	public void setText(String txt) {
		this.text=txt;
	}
	public void addOption(Answer a) {
		this.options.add(a);
	}
	

	public String toString() {
		String s=this.code+" "+text;
		for(Answer a:options)
			s+="\n"+a.toString();
		return s;
	}
}
