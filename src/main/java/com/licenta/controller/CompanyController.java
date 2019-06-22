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
    public Company addCompany(@RequestBody Company company) {
        long start_time = System.nanoTime();
        companyRepository.save(company);

        Company companyReturn = new Company();
        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time company insert is: " + Double.toString(elapsedTimeInSecond));
        companyReturn.setResponseTime(Double.toString(elapsedTimeInSecond));
        log.info("Company added successfully!");
        return companyReturn;
    }

    @GetMapping("/insert7k")
    public Company populate() {
        Company companyReturn = new Company();
        long start_time = System.nanoTime();
        for (int i = 0; i < 7000; i++) {
            Company c = new Company();
            String CUI = "1239987776622444" + i;
            String name = "mockCompany";
            List<Medicine> medicines = new ArrayList<>();
            c.setCUI(CUI);
            c.setCompanyName(name);
            c.setMedicines(medicines);
            companyRepository.save(c);
            log.info("Company saved: " + CUI);
        }
        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time for insert was: " + Double.toString(elapsedTimeInSecond) + " seconds");
        companyReturn.setResponseTime("Time company insert is: " + Double.toString(elapsedTimeInSecond));
        return companyReturn;

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
    public Company editCompany(@PathVariable long id, @RequestBody Company company) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        Assert.notNull(existingCompany, "Company not found");
        long start_time = System.nanoTime();


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
        Company companyReturn = new Company();
        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time company update is: " + Double.toString(elapsedTimeInSecond));
        companyReturn.setResponseTime(Double.toString(elapsedTimeInSecond));
        log.info("Company updated successfully!");

        return companyReturn;
    }


    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable long id) {
        Company c = new Company();
        long start_time = System.nanoTime();
        companyRepository.findById(id).ifPresent(companyRepository::delete);
        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time delete company is: " + Double.toString(elapsedTimeInSecond));
        String responseTime = Double.toString(elapsedTimeInSecond);

        c.setResponseTime(responseTime);
        log.info("Company with id " + id + " deleted successfully!");
        return c;
    }

    @GetMapping("/{name}")
    public List<Company> findAllCompaniesContaining(@PathVariable String name) {
        return companyRepository.findAllByCompanyNameContaining(name);
    }

    @GetMapping("/byID/{id}")
    public Company getCompanyByID(@PathVariable long id) {
        return companyRepository.getById(id);
    }
}
