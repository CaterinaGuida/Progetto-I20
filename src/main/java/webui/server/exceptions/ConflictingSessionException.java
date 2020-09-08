package webui.server.exceptions;

public class ConflictingSessionException extends Exception{
	private static String genErrorMessage(String sid) {
		return "Your session id: "+sid+"is already in use";
	}
	
	public ConflictingSessionException(String sid) {
		super(genErrorMessage(sid));
	}

}
