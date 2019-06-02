package com.licenta.repository;

import com.licenta.models.MedicineMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicineMongoRepository extends MongoRepository<MedicineMongo, String> {
    List<MedicineMongo> findByNameContaining (String name);
}
