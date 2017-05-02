/**
 * 
 */
package com.taskmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.data.TaskDetails;
import com.taskmanager.data.UserDetails;
import com.taskmanager.repository.TaskDaoRepository;
import com.taskmanager.repository.TaskDaoSequenceRepository;

/**
 * @author dwarakak
 *
 */
@Service
public class TaskService {

	@Autowired
	private TaskDaoRepository daoRepository;
	
	@Autowired
	private TaskDaoSequenceRepository daoSequenceRepository;

	public List<TaskDetails> findTaskList(final UserDetails userDetails) {
		return daoRepository.getTaskList(userDetails);
	}

	public TaskDetails getTaskList(final Long taskId) {
		return daoRepository.getTask(taskId);
	}

	public void createTask(final TaskDetails details) {
		Long taskId = daoSequenceRepository.generateTaskId();
		details.setTaskId(taskId);
		daoRepository.createTask(details);
	}
	
	public void updateTask(final TaskDetails details) {
		daoRepository.updateTask(details);
	}

	public void deleteTask(final Long taskId) {
		daoRepository.removeTask(taskId);
	}
	
	/**
	 * @return
	 */
	public List<TaskDetails> cleanUpTask(){
		return daoRepository.getAllCompletedTaskList();
	}
}
