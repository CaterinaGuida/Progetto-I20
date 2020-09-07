package webui.server;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import webui.server.exceptions.ConflictingSessionException;

public class CookieParser {
	public static String getValueFrom(Cookie[] cookieList, String id) {
		
		for(Cookie c : cookieList) {
			if(c.getName().equals(id)) {
				return c.getValue();
			}
		}
		return null;
	}
	
	public static Cookie genCookie(String value, String id) {
		return new Cookie(id, value);
	}
}


