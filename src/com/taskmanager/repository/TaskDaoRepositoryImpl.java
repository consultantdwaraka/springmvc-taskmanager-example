/**
 * 
 */
package com.taskmanager.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.taskmanager.data.TaskDetails;
import com.taskmanager.data.UserDetails;

/**
 * @author dwarakak
 *
 */
@Repository
public class TaskDaoRepositoryImpl implements TaskDaoRepository {

	@Autowired
	private MongoOperations mongoOperations;

	/**
	 * Create Task.
	 */
	@Override
	public void createTask(final TaskDetails details) {
		mongoOperations.save(details);
	}

	/**
	 * Update Task.
	 */
	@Override
	public void updateTask(final TaskDetails details) {
		final TaskDetails existingTaskDetails = getTask(details.getTaskId());

		Query findQuery = new Query(Criteria.where("taskId").is(
				details.getTaskId()));

		Update updateQuery = new Update();
		updateQuery.set("taskId", details.getTaskId());
		updateQuery.set("taskType", details.getTaskType());
		updateQuery.set("taskName", details.getTaskName());
		updateQuery.set("taskDescription", details.getTaskDescription());
		if (existingTaskDetails != null) {
			updateQuery.set("taskCreationDate",
					existingTaskDetails.getTaskCreationDate());
		}
		updateQuery.set(
				"taskEstimatedCost",
				details.getTaskEstimatedCost() == null ? 0.0 : details
						.getTaskEstimatedCost());
		updateQuery.set("taskCost", details.getTaskCost() == null ? 0.0
				: details.getTaskCost());
		updateQuery.set("taskStartDate", details.getTaskStartDate());
		updateQuery.set("taskCompletionDate", details.getTaskCompletionDate());
		updateQuery.set("taskStatus", details.getTaskStatus());
		updateQuery.set("reasonForDelayOrPreCompletion",
				details.getReasonForDelayOrPreCompletion());
		updateQuery.set("assignedTo", details.getAssignedTo());
		updateQuery.set("customerName", details.getCustomerName());
		updateQuery
				.set("customerPhoneNumber", details.getCustomerPhoneNumber());
		updateQuery.set("customerEmail", details.getCustomerEmail());

		mongoOperations
				.findAndModify(findQuery, updateQuery, TaskDetails.class);

	}

	/**
	 * Remove Task.
	 */
	@Override
	public void removeTask(final Long taskId) {
		mongoOperations.remove(new Query(Criteria.where("taskId").is(taskId)),
				TaskDetails.class);
	}

	/**
	 * Get Task List.
	 */
	@Override
	public List<TaskDetails> getTaskList(final UserDetails userDetails) {
		final List<TaskDetails> taskLsit = new ArrayList<TaskDetails>();
		if ("Boss".equals(userDetails.getEmpRole())) {
			taskLsit.addAll(mongoOperations
					.find(new Query(), TaskDetails.class));
		}else if ("Manager".equals(userDetails.getEmpRole())) {
			taskLsit.addAll(mongoOperations
					.find(new Query(), TaskDetails.class));
		} else if ("Administrator".equals(userDetails.getEmpRole())) {
			taskLsit.addAll(mongoOperations.find(
					new Query(new Criteria().orOperator(
							Criteria.where("taskCreatedBy").is(
									userDetails.getEmpId()),
							Criteria.where("assignedTo").is(
									userDetails.getEmpId()))),
					TaskDetails.class));
		} else if ("User".equals(userDetails.getEmpRole())) {
			taskLsit.addAll(mongoOperations.find(
					new Query(Criteria.where("assignedTo").is(
							userDetails.getEmpId())), TaskDetails.class));
		}
		return taskLsit;
	}

	/**
	 * @return
	 */
	@Override
	public List<TaskDetails> getAllCompletedTaskList() {
		final List<TaskDetails> taskLsit = new ArrayList<TaskDetails>();
		taskLsit.addAll(mongoOperations.find(
				new Query(new Criteria().orOperator(Criteria
						.where("taskStatus").is("Completed"),
						Criteria.where("taskStatus").is("Approved"), Criteria
								.where("taskStatus").is("Removed"))),
				TaskDetails.class));
		return taskLsit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.TaskDaoRepository#getTask(java.lang.String)
	 */
	@Override
	public TaskDetails getTask(Long taskId) {
		final TaskDetails taskDetails = mongoOperations.findOne(new Query(
				Criteria.where("taskId").is(taskId)), TaskDetails.class);
		return taskDetails;
	}

}
