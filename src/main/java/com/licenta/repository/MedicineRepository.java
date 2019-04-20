package com.licenta.repository;

import com.licenta.models.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "medicine")
public interface MedicineRepository extends CrudRepository<Medicine, Long> {
}
