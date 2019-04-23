package com.licenta.repository;

import com.licenta.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "company", collectionResourceRel = "company",itemResourceRel = "company")
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
