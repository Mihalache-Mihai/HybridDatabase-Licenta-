package com.licenta.controller;

import com.licenta.models.Company;
import com.licenta.models.Medicine;
import com.licenta.models.MedicineMongo;
import com.licenta.repository.CompanyRepository;
import com.licenta.repository.MedicineMongoRepository;
import com.licenta.repository.MedicineRepository;
import com.licenta.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private static final Logger log = LoggerFactory.getLogger(MedicineController.class);
    private Utils utils = new Utils();

    private final MedicineRepository medicineRepository;

    private final MedicineMongoRepository medicineMongoRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public MedicineController(MedicineRepository medicineRepository, MedicineMongoRepository medicineMongoRepository,CompanyRepository companyRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineMongoRepository = medicineMongoRepository;
        this.companyRepository = companyRepository;
    }


    @PostMapping("/add")
    public void populate(){
            Company c = companyRepository.getById(11229);
        for(int i=0;i<30;i++){
            Medicine m = new Medicine();
            String name= "test medicine" +i;
            m.setName(name);
            m.setCompany(c);
            Integer stock = 2019;
            m.setStock(stock);
            medicineRepository.save(m);
            MedicineMongo mm = utils.toMongoMedicine(m);
            medicineMongoRepository.save(mm);
            log.info("Medicine saved: "+  name);
        }
        for(int i=0;i<30000;i++){
            Medicine m = new Medicine();
            String name= "Mock data4" +i;
            m.setName(name);
            m.setCompany(c);
            Integer stock = 2019;
            m.setStock(stock);
            medicineRepository.save(m);
            MedicineMongo mm = utils.toMongoMedicine(m);
            medicineMongoRepository.save(mm);
            log.info("Medicine saved: "+  name);
        }
    }

    @PostMapping
    public void addMedicine(@RequestBody Medicine medicine) {
        String name=medicine.getName();
        String newName=name.toLowerCase();
        medicine.setName(newName);
        if(medicine.getCompany().getCompanyName()!=null){
            Company medicineCompany = companyRepository.findCompanyByCompanyName(medicine.getCompany().getCompanyName());
            medicine.setCompany(medicineCompany);
        }

        medicineRepository.save(medicine);
        log.info("Medicine saved successfully!");

        MedicineMongo medicineMongo = utils.toMongoMedicine(medicine);
        medicineMongoRepository.insert(medicineMongo);
        log.info("Medicine Mongo saved successfully!");
    }

    @GetMapping
    public Iterable<Medicine> getMedicines() {
        return medicineRepository.findAll();
    }

    @GetMapping(value = "/byID/{id}")
    public Medicine getMedicineByID(@PathVariable long id){
        Medicine medicine = medicineRepository.getMedicineById(id);
        medicine.getCompany();
        return medicine;
    }

    @PutMapping(value = "/{id}")
    public void editMedicine(@PathVariable long id, @RequestBody Medicine medicine) {
        Medicine existingMedicine = medicineRepository.findById(id).orElse(null);
        Assert.notNull(existingMedicine,"Medicine not found");
        Assert.notNull(medicine, "Invalid input!");
        if(medicine.getName()!=null){
            existingMedicine.setName(medicine.getName());
            log.info("Medicine name updated successfully");
        }
        if (medicine.getCompany() != null) {
            existingMedicine.setCompany(medicine.getCompany());
            log.info("Medicine company updated successfully!");
        }
        if(medicine.getStock() !=null){
            existingMedicine.setStock(medicine.getStock());
            log.info("Medicine stock updated");
        }
        medicineRepository.saveAndFlush(existingMedicine);
        log.info("Medicine updated successfully!");

        MedicineMongo medicineMongo = utils.toMongoMedicine(existingMedicine);
        medicineMongoRepository.save(medicineMongo);
        log.info("Medicine mongo updated successfully!");
    }


    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable long id) {
        medicineRepository.findById(id).ifPresent(medicineRepository::delete);
        log.info("Medicine with id "+id+" deleted successfully");

        String idMongo = String.valueOf(id);
        medicineMongoRepository.findById(idMongo).ifPresent(medicineMongoRepository::delete);
        log.info("Medicine mongo with id "+id+" deleted successfully");
    }

    @RequestMapping("/{name}")
    public List<Medicine> findByName(@PathVariable String name){
        //String newName = name.toLowerCase();
        //return medicineRepository.findByNameContaining(name);

        List<Medicine> medicinesList = new ArrayList<>();
        long start_time = System.nanoTime();
        medicinesList = medicineRepository.findByNameContaining(name);
        long end_time = System.nanoTime();
        long duration = end_time-start_time;
        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time relational is: "+ Double.toString(elapsedTimeInSecond));
        return medicinesList;
    }

    @GetMapping("/findAllBy/{CUI}")
    public List<Medicine> findAllByCompanyCUIOrderByAsc(@PathVariable String CUI){
        //return medicineRepository.findAllByCompany_CUIOrderByNameAsc(CUI);

        List<Medicine> list = new ArrayList<>();
        long start_time = System.nanoTime();
        list = medicineRepository.findAllByCompany_CUIOrderByNameAsc(CUI);
        long end_time = System.nanoTime();
        long duration = end_time-start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time medicines is: "+ Double.toString(elapsedTimeInSecond));


        return list;
    }


}
