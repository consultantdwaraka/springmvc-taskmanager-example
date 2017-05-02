/**
 * 
 */
package com.taskmanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.taskmanager.data.TaskSequence;

/**
 * @author dwarakak
 *
 */
@Repository
public class TaskDaoSequenceRepositoryImpl implements TaskDaoSequenceRepository {

	@Autowired
	private MongoOperations mongoOperations;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskmanager.repository.TaskDaoSequenceRepository#generateTaskId()
	 */
	@Override
	public Long generateTaskId() {
		Query query = new Query(Criteria.where("autoId").is("TaskSequence"));

		Update update = new Update();
		update.inc("taskId", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		TaskSequence seqId = mongoOperations.findAndModify(query, update,
				options, TaskSequence.class);

		return seqId.getTaskId();
	}
}
