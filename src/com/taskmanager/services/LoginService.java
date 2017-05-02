/**
 * 
 */
package com.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.data.LoginStatusEnum;
import com.taskmanager.data.UserDetails;
import com.taskmanager.repository.UserRepository;

/**
 * @author dwarakak
 *
 */
@Service
public class LoginService {

	@Autowired
	UserRepository userRepo;

	public LoginStatusEnum validateCredentials(String userId, String passwd) {
		return userRepo.validateLoginCredentials(userId, passwd);
	}
	
	public UserDetails getUserRole(final String userId){
		return userRepo.getUserRole(userId);
	}
}
