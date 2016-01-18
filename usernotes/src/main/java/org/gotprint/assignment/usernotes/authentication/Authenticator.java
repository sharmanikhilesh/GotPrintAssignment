package org.gotprint.assignment.usernotes.authentication;

import java.util.StringTokenizer;

import org.glassfish.jersey.internal.util.Base64;
import org.gotprint.assignment.usernotes.entity.UserEntity;
import org.gotprint.assignment.usernotes.dbservice.UserService;



public class Authenticator {
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";	
	
	public static boolean authorizeUser(String authValue, String email){
		if(authValue == null || authValue.equals(""))
			return false;
		authValue = authValue.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		if(authValue.equals(""))
			return false;
		String decodedString = Base64.decodeAsString(authValue);
		StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
		String useremail = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		if(useremail.equalsIgnoreCase(email)){
			if(authenticateUser(useremail, password))
				return true;
		}
		return false;
	}
	
	public static boolean authenticateUser(String email, String password){
		UserEntity user = new UserService().getUserByEmail(email);
		if(user != null)
			return user.getPassword().equals(password);
		return false;
	}
}
