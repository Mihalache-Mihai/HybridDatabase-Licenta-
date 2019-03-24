package com.licenta.repository;

import com.licenta.models.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserMongoRepository extends MongoRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
