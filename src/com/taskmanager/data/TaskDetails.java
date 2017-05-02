/**
 * 
 */
package com.taskmanager.data;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author dwarakak Task data container.
 */
@Document(collection = "TaskDetails")
public class TaskDetails {

	@Id
	private Long taskId;

	private String taskType;

	private String taskName;

	private String taskDescription;
	
	private String taskCreatedBy;

	private Double taskCost;
	
	private Double taskEstimatedCost;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date taskCreationDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date taskStartDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date taskCompletionDate;

	private String taskStatus;

	private String reasonForDelayOrPreCompletion;

	private String assignedTo;

	private String customerName;

	private String customerPhoneNumber;

	private String customerEmail;

	/**
	 * @return
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return
	 */
	public String getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * @param taskDescription
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	/**
	 * @param customerPhoneNumber
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	/**
	 * @return
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return
	 */
	public Date getTaskCreationDate() {
		return taskCreationDate;
	}

	/**
	 * @param taskCreationDate
	 */
	public void setTaskCreationDate(Date taskCreationDate) {
		this.taskCreationDate = taskCreationDate;
	}

	/**
	 * @return
	 */
	public Date getTaskStartDate() {
		return taskStartDate;
	}

	/**
	 * @param taskStartDate
	 */
	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	/**
	 * @return
	 */
	public Date getTaskCompletionDate() {
		return taskCompletionDate;
	}

	/**
	 * @param taskCompletionDate
	 */
	public void setTaskCompletionDate(Date taskCompletionDate) {
		this.taskCompletionDate = taskCompletionDate;
	}

	/**
	 * @return
	 */
	public String getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * @return
	 */
	public String getReasonForDelayOrPreCompletion() {
		return reasonForDelayOrPreCompletion;
	}

	/**
	 * @param reasonForDelayOrPreCompletion
	 */
	public void setReasonForDelayOrPreCompletion(
			String reasonForDelayOrPreCompletion) {
		this.reasonForDelayOrPreCompletion = reasonForDelayOrPreCompletion;
	}

	/**
	 * @return
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public Double getTaskCost() {
		return taskCost;
	}

	public void setTaskCost(Double taskCost) {
		this.taskCost = taskCost;
	}

	public Double getTaskEstimatedCost() {
		return taskEstimatedCost;
	}

	public void setTaskEstimatedCost(Double taskEstimatedCost) {
		this.taskEstimatedCost = taskEstimatedCost;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskDetails [taskId=" + taskId + ", taskType=" + taskType
				+ ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", taskCreatedBy=" + taskCreatedBy
				+ ", taskCost=" + taskCost + ", taskEstimatedCost="
				+ taskEstimatedCost + ", taskCreationDate=" + taskCreationDate
				+ ", taskStartDate=" + taskStartDate + ", taskCompletionDate="
				+ taskCompletionDate + ", taskStatus=" + taskStatus
				+ ", reasonForDelayOrPreCompletion="
				+ reasonForDelayOrPreCompletion + ", assignedTo=" + assignedTo
				+ ", customerName=" + customerName + ", customerPhoneNumber="
				+ customerPhoneNumber + ", customerEmail=" + customerEmail
				+ "]";
	}
	
	
}
