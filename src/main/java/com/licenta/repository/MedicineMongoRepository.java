package com.licenta.repository;

import com.licenta.models.Company;
import com.licenta.models.Medicine;
import com.licenta.models.MedicineMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface MedicineMongoRepository extends MongoRepository<MedicineMongo, Long> {
}
