package com.licenta.controller;

import com.licenta.models.Company;
import com.licenta.models.Medicine;
import com.licenta.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public void addCompany(@RequestBody Company company) {
        companyRepository.save(company);
        log.info("Company added successfully!");
    }

    @PostMapping("/insert")
    public void populate(){

        for(int i=0;i<300000;i++){
            Company c = new Company();
            String CUI = "123998777662"+i;
            String name="mockCompany";
            List<Medicine> medicines = new ArrayList<>();
            c.setCUI(CUI);
            c.setCompanyName(name);
            c.setMedicines(medicines);
            companyRepository.save(c);
            log.info("Company saved: " +CUI);
        }

    }

//    @PostMapping("/add")
//    public void populate(){
//
//        for(int i=0;i<300000;i++){
//            Company c = new Company();
//            String CUI = "1123123123123"+i;
//            String name="mockCompany";
//            List<Medicine> medicines = new ArrayList<>();
//            c.setCUI(CUI);
//            c.setCompanyName(name);
//            c.setMedicines(medicines);
//            companyRepository.save(c);
//            log.info("Company saved: " +CUI);
//        }

 //   }

    @GetMapping
    public Iterable<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editCompany(@PathVariable long id, @RequestBody Company company) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        Assert.notNull(existingCompany, "Company not found");
        if (company.getCompanyName() != null) {
            existingCompany.setCompanyName(company.getCompanyName());
            log.info("Company name updated!");
        }
        if (company.getCUI() != null) {
            existingCompany.setCUI(company.getCUI());
            log.info("Company CUI updated!");
        }
        if (company.getMedicines() != null) {
            existingCompany.setMedicines(company.getMedicines());
            log.info("Company Medicines updated!");
        }
        companyRepository.save(existingCompany);
        log.info("Company updated successfully!");
    }


    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyRepository.findById(id).ifPresent(companyRepository::delete);
        log.info("Company with id " + id + " deleted successfully!");
    }

    @GetMapping("/{name}")
    public List<Company> findAllCompaniesContaining(@PathVariable  String name){
        return companyRepository.findAllByCompanyNameContaining(name);
    }

    @GetMapping("/byID/{id}")
    public Company getCompanyByID(@PathVariable long id){
        return companyRepository.getById(id);
    }
}
