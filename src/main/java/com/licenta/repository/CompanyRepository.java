package com.licenta.repository;

import com.licenta.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyByCompanyName(String name);
    Company getById(long id);
    List<Company> findAllByCompanyNameContaining(String name);
}
