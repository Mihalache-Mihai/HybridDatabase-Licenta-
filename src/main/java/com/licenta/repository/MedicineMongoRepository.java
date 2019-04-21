package com.licenta.repository;

import com.licenta.models.MedicineMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicineMongoRepository extends MongoRepository<MedicineMongo, String> {
}
