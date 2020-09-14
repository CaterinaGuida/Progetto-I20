package dBManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import daos.SolutionDAO;

public class AddSolution implements ActionListener{
	private JTextArea description,code,product;
	private SolutionDAO solution;
	public AddSolution(JTextArea description,JTextArea code,JTextArea product) {
	this.code=code;
	this.description=description;
	this.product=product;
	this.solution=new SolutionDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(description.getText().length()>1) {
			solution.addSolution(code.getText(), description.getText(), product.getText());
			code.setText("");
			description.setText("");
			product.setText("");
		}
		
	}

}
