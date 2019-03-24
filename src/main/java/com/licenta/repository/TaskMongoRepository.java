package com.licenta.repository;

import com.licenta.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskMongoRepository extends MongoRepository<Task, Long> {
    public Task findByDescription(String description);
}
