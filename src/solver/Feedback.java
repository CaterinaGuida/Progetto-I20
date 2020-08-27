package solver;

import daos.FeedbackDAO;

public class Feedback {
	private boolean satisfied;
	private String suggestion;
	private String mail;
	private String product_id;
	
	Feedback(boolean sat, String sugg, String mail, String product_id) {
		this.satisfied=sat;
		this.suggestion=sugg;
		this.mail=mail;
		FeedbackDAO fbDAO = new  FeedbackDAO();
		fbDAO.saveFeedback(suggestion, mail, product_id, satisfied);
		}


}
