package com.licenta.repository;

import com.licenta.models.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicineMongoRepository extends MongoRepository<Medicine, Long> {
}
