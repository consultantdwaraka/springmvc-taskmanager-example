/**
 * 
 */
package com.taskmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taskmanager.data.UserDetails;
import com.taskmanager.repository.UserRepository;

/**
 * @author dwarakak
 *
 */
@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @return
	 */
	public List<UserDetails> getUserList() {
		return userRepository.getUserList();
	}

	public UserDetails getUserDetails(final String userId) {
		return userRepository.getUserDetails(userId);
	}

	/**
	 * @param userDetails
	 */
	public void createUser(final UserDetails userDetails) {
		userRepository.createUser(userDetails);
	}

	public void deleteUser(final String userDetails) {
		userRepository.deleteUser(userDetails);
	}
	
	public void updateUser(final UserDetails userDetails){
		userRepository.updateUser(userDetails);
	}

}
