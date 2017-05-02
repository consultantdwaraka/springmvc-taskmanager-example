/**
 * 
 */
package com.taskmanager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.taskmanager.data.LoginStatusEnum;
import com.taskmanager.data.UserDetails;

/**
 * @author dwarakak
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private MongoOperations mongoOperations;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskmanager.repository.UserRepository#createUser()
	 */
	@Override
	public void createUser(final UserDetails userDetails) {
		mongoOperations.save(userDetails);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskmanager.repository.UserRepository#getUserList()
	 */
	@Override
	public List<UserDetails> getUserList() {
		List<UserDetails> usersList = mongoOperations
				.findAll(UserDetails.class);
		return usersList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.UserRepository#deleteUser(com.taskmanager.
	 * data.UserDetails)
	 */
	@Override
	public void deleteUser(String userDetails) {
		mongoOperations.remove(new Query(Criteria.where("empId")
				.is(userDetails)), UserDetails.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.LoginRepository#validateLoginCredentials(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public LoginStatusEnum validateLoginCredentials(String userId, String passwd) {
		final UserDetails loginDetails = mongoOperations.findOne(new Query(
				Criteria.where("empId").is(userId)), UserDetails.class);
		if (loginDetails != null && "Active".equals(loginDetails.getEmpStatus())) {
			if (loginDetails != null && userId.equals(loginDetails.getEmpId())
					&& passwd.equals(loginDetails.getEmpPwd())) {
				return LoginStatusEnum.ACTIVE;
			} else {
				return LoginStatusEnum.INVALID_CREDENTIALS;
			}
		} else {
			return LoginStatusEnum.INACTIVE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.LoginRepository#getUserRole(java.lang.String)
	 */
	@Override
	public UserDetails getUserRole(String userId) {
		UserDetails userDetails = mongoOperations.findOne(new Query(Criteria
				.where("empId").is(userId)), UserDetails.class);
		return userDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.UserRepository#getUserDetails(java.lang.String
	 * )
	 */
	@Override
	public UserDetails getUserDetails(String userId) {
		final UserDetails userDetails = mongoOperations.findOne(new Query(
				Criteria.where("empId").is(userId)), UserDetails.class);
		return userDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.UserRepository#updateUser(com.taskmanager.
	 * data.UserDetails)
	 */
	@Override
	public void updateUser(UserDetails userDetails) {
		final Query findQuery = new Query(Criteria.where("empId").is(
				userDetails.getEmpId()));

		Update updateQuery = new Update();
		updateQuery.set("empId", userDetails.getEmpId());
		updateQuery.set("empName", userDetails.getEmpName());
		updateQuery.set("empPwd", userDetails.getEmpPwd());
		updateQuery.set("empRole", userDetails.getEmpRole());
		updateQuery.set("empStatus", userDetails.getEmpStatus());
		updateQuery.set("contactNo", userDetails.getContactNo());
		updateQuery.set("contactAddress", userDetails.getContactAddress());

		mongoOperations
				.findAndModify(findQuery, updateQuery, UserDetails.class);
	}

}
