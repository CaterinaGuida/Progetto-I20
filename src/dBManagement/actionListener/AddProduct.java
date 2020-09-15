package dBManagement.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import daos.ProductDAO;

public class AddProduct implements ActionListener {
	JTextArea name,code,product;
	ProductDAO dbProduct;
	public AddProduct(JTextArea name,JTextArea code, JTextArea product) {
		this.code=code;
		this.name=name;
		this.product=product;
		dbProduct=new ProductDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dbProduct=new ProductDAO();
		if(name.getText().length()>1) {
			dbProduct.addProduct(name.getText(), code.getText());
			name.setText("");
			code.setText("");
			product.setText(dbProduct.getAllProduct());
		}
		
	}

}
