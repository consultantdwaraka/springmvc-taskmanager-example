/**
 * 
 */
package com.taskmanager.data.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.taskmanager.data.TaskDetails;

/**
 * @author dwarakak
 *
 */
@Component
public class TaskValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return TaskDetails.class.isAssignableFrom(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmpty(arg1, "taskName", "required.task.name");
		ValidationUtils.rejectIfEmpty(arg1, "taskDescription",
				"required.task.description");
		ValidationUtils.rejectIfEmpty(arg1, "taskType", "required.task.nature");
		//ValidationUtils.rejectIfEmpty(arg1, "taskCost", "required.task.cost");
		
		ValidationUtils.rejectIfEmpty(arg1, "taskStartDate",
				"required.task.startdate");
		ValidationUtils.rejectIfEmpty(arg1, "taskCompletionDate",
				"required.task.enddate");
		ValidationUtils.rejectIfEmpty(arg1, "taskStatus",
				"required.task.status");
		ValidationUtils.rejectIfEmpty(arg1, "assignedTo",
				"required.task.assignTo");
		ValidationUtils.rejectIfEmpty(arg1, "customerName",
				"required.task.customername");
		ValidationUtils.rejectIfEmpty(arg1, "customerPhoneNumber",
				"required.task.customernumber");
		ValidationUtils.rejectIfEmpty(arg1, "customerEmail",
				"required.task.customeremail");
		TaskDetails taskDetails = (TaskDetails) arg0;

		if (taskDetails.getTaskStartDate() != null
				&& taskDetails.getTaskCompletionDate() != null) {
			int dateCompare = taskDetails.getTaskStartDate().compareTo(
					taskDetails.getTaskCompletionDate());
			if (dateCompare > 0) {
				arg1.rejectValue("taskCompletionDate", "required.task.enddategrtrthanstartdate");
			}
		}
		/* Validate customer phone number. */
		final Double taskCost = taskDetails.getTaskCost();
		if (taskCost != null) {
			try {
				Double.parseDouble(taskCost.toString());
			} catch (Exception exception) {
				arg1.rejectValue("taskCost", "required.task.providetaskcost ");
			}
		}
		
		/** else{
			ValidationUtils.rejectIfEmpty(arg1, "taskCost", "required.task.cost");
		} **/
		
		/* Validate customer phone number. */
		final String customerPhoneNumber = taskDetails.getCustomerPhoneNumber();
		if (!StringUtils.isEmpty(customerPhoneNumber)) {
			try {
				Long.parseLong(customerPhoneNumber);
			} catch (Exception exception) {
				arg1.rejectValue("customerPhoneNumber", "required.task.providevalidphonenumer");
			}
		}
	}

}
