package dBManagement.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import daos.ProductDAO;

public class AddProduct implements ActionListener {
	JTextArea name,code;
	ProductDAO dbProduct;
	public AddProduct(JTextArea name,JTextArea code) {
		this.code=code;
		this.name=name;
		dbProduct=new ProductDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(name.getText().length()>1 && code.getText().length()>1) {
			dbProduct.addProduct(name.getText(), code.getText());
			name.setText("");
			code.setText("");
		}
	}

}
