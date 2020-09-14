package dBManagement;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class AttentionPanel extends JPanel{
	JTextArea testo;
	public static final String text="tutti i prodotti , domande e soluzioni vengono identificati in maniera univoca tramite id ocodice.\n prestare molta attenzione ad iserire i codici,\n"
			+ "il codice della prima domanda di ogni prodotto deve essere composto da 0 + il codice del prodotto.\n "
			+ "il codice delle domande successive si ottiene concatenando il codice della domanda presente e il numero della risposta.\n"
			+ "il codice della soluzione si ottiene concatenando il codice della domanda precedente ,il numero della risposta e S.\n"
			+ "per maggiori informazioni consultare la documentazione presente nel sito github.com/IngSW-unipv/Progetto-I20/wiki";
	public AttentionPanel() {
		Font font=new Font("TimesRoman", Font.PLAIN, 18);
		testo=new JTextArea();
		testo.setText(text);
		testo.setFont(font);
		testo.setForeground(Color.BLUE);		
		this.setBackground(Color.WHITE);
		add(testo);

	}

}
