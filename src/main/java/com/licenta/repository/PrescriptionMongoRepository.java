package com.licenta.repository;

import com.licenta.models.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PrescriptionMongoRepository extends MongoRepository<Prescription,String> {
    @Query(value = "{'prescriptionSeries' : ?0 }",delete = true)
    Prescription deletePrescriptionByPrescriptionSeries(String prescriptionSeries);
}
