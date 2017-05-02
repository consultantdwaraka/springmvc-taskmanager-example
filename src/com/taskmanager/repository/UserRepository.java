/**
 * 
 */
package com.taskmanager.repository;

import java.util.List;

import com.taskmanager.data.LoginStatusEnum;
import com.taskmanager.data.UserDetails;

/**
 * @author dwarakak
 *
 */
public interface UserRepository {

	public void createUser(final UserDetails userDetails);

	public List<UserDetails> getUserList();

	public void deleteUser(final String userDetails);

	public LoginStatusEnum validateLoginCredentials(final String userId,
			final String passwd);

	public UserDetails getUserRole(final String userId);
	
	public UserDetails getUserDetails(final String userId);
	
	public void updateUser(final UserDetails userDetails);

}
