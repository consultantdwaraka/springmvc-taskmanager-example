/**
 * 
 */
package com.taskmanager.repository;

import java.util.List;

import com.taskmanager.data.TaskDetails;
import com.taskmanager.data.UserDetails;

/**
 * @author dwarakak
 *
 */
public interface TaskDaoRepository{
	
	public void createTask(final TaskDetails details);
	
	public void updateTask(final TaskDetails details);
	
	public void removeTask(final Long taskId);
	
	public List<TaskDetails> getTaskList(final UserDetails userDetails);
	
	public TaskDetails getTask(final Long taskId);
	
	public List<TaskDetails> getAllCompletedTaskList();

}
