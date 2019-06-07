package com.licenta.repository;

import com.licenta.models.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PrescriptionMongoRepository extends MongoRepository<Prescription,String> {
    List<Prescription> findAllByPrescriptionSeriesAndCNP(String prescriptionSeries, String CNP);
    Prescription findByPrescriptionSeries(String prescriptionSeries);

    List<Prescription> findAllByNameContaining(String name);
}
