/**
 * 
 */
package com.taskmanager.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.taskmanager.data.TaskDetails;

/**
 * @author dwarakak
 *
 */
@Component
public class DaemonService {

	private static final Logger logger = LoggerFactory
			.getLogger(DaemonService.class);

	@Autowired
	private TaskService taskService;

	@Scheduled(cron = "0 0 0 * * *")
	public void cleanUpOldTasks() {
		logger.info("cleanUpOldTasks(): cleaning all the old tasks. "
				+ new Date());
		final List<TaskDetails> taskDetailsList = taskService.cleanUpTask();
		for (TaskDetails taskDetails : taskDetailsList) {
			Date taskCreationDate = taskDetails.getTaskCreationDate();
			long daysDiff = calculateDaysDiff(taskCreationDate);
			if (daysDiff > 180) {
				Long taskId = taskDetails.getTaskId();
				logger.info("cleanUpOldTasks(): Deleting task id. " + taskId);
				taskService.deleteTask(taskId);
				logger.info("cleanUpOldTasks(): Deleted task id. " + taskId);
			}
		}
	}

	/**
	 * @param creationDate
	 * @return
	 */
	private long calculateDaysDiff(Date creationDate) {
		Date currentDate = new Date();
		long daysDiff = currentDate.getTime() - creationDate.getTime();
		return TimeUnit.DAYS.convert(daysDiff, TimeUnit.MILLISECONDS);
	}
}
