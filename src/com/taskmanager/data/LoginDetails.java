/**
 * 
 */
package com.taskmanager.data;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dwarakak
 *
 */
@Document(collection="MemberCredentials")
public class LoginDetails {
	
	private String userId;
	
	private String passwd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
