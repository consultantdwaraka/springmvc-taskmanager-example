/**
 * 
 */
package com.taskmanager.data.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.taskmanager.data.UserDetails;

/**
 * @author dwarakak
 * User validator.
 */
@Component
public class UserValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return UserDetails.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmpty(arg1, "empId", "required.emp.empid");
		ValidationUtils.rejectIfEmpty(arg1, "empName", "required.emp.empname");
		ValidationUtils.rejectIfEmpty(arg1, "empPwd", "required.emp.emppwd");
		ValidationUtils.rejectIfEmpty(arg1, "empRole", "required.emp.emprole");
		ValidationUtils.rejectIfEmpty(arg1, "empStatus", "required.emp.empstatus");
		ValidationUtils.rejectIfEmpty(arg1, "contactNo", "required.emp.empcontactno");
		ValidationUtils.rejectIfEmpty(arg1, "contactAddress", "required.emp.empcontactaddress");
		
		final UserDetails userDetails = (UserDetails) arg0;
		
		/* Validate customer phone number. */
		final String customerNumber = userDetails.getContactNo();
		if (!StringUtils.isEmpty(customerNumber)) {
			try {
				Long.parseLong(customerNumber);
			} catch (Exception exception) {
				arg1.rejectValue("contactNo", "required.task.providevalidphonenumer");
			}
		}
		
	}

}
