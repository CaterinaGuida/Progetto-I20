package dBManagement;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import daos.FeedbackDAO;

@SuppressWarnings("serial")
public class FeedbackPanel extends JPanel {
	JTextArea result;
	JTable tabellaFeedback;
	public FeedbackPanel() {
		result=new JTextArea();
		this.setBackground(Color.white);
		
		String[] columnNames = {"Mail",
                "Descrizione",
                "Codice Prodotto",
                "Soddifazione"};
		tabellaFeedback=new JTable(FeedbackDAO.getAllFeedback(),columnNames);
		tabellaFeedback.getColumnModel().getColumn(1).setPreferredWidth(400);
		JScrollPane scpn= new JScrollPane(tabellaFeedback);
		this.setLayout(new BorderLayout());
		add(scpn, BorderLayout.CENTER);
		
	}

}
