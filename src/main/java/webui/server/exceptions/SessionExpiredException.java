package webui.server.exceptions;

public class SessionExpiredException extends Exception{
	private static final String ERROR_MESSAGE = "Session has expired";
	
	public SessionExpiredException() {
		super(ERROR_MESSAGE);
	}
}
