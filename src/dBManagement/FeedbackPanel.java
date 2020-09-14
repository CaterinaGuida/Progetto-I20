package dBManagement;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import daos.FeedbackDAO;

@SuppressWarnings("serial")
public class FeedbackPanel extends JPanel {
	JTextArea result;
	public FeedbackPanel() {
		result=new JTextArea();
		this.setBackground(Color.white);
		result.setText(FeedbackDAO.getAllFeedback());
		add(result);
		
	}

}
