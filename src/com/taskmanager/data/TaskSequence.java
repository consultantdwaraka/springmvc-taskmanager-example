/**
 * 
 */
package com.taskmanager.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dwarakak
 *
 */
@Document(collection="TaskSequence")
public class TaskSequence {

	@Id
	private String autoId;
	
	private Long taskId;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	
	
}
