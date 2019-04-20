package com.licenta.repository;

import com.licenta.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.Path;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "company")
public interface CompanyRepository extends CrudRepository<Company,Long> {
}
