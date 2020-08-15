package solver;

public class Test {

	public static void main(String[] args) {
		Question q=new Question("F12");
		q.setText();
		System.out.println(q.getText());
		q.setOptions();
		for(Answer a:q.getOptions())
			System.out.println(a.toString());
	}

}
