package com.licenta.repository;

import com.licenta.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

public interface TaskMongoRepository extends MongoRepository<Task, Long> {
    public Task findByDescription(String description);
}
