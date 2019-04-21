package com.licenta.controller;

import com.licenta.models.Medicine;
import com.licenta.models.MedicineMongo;
import com.licenta.repository.MedicineMongoRepository;
import com.licenta.repository.MedicineRepository;
import com.licenta.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private static final Logger log = LoggerFactory.getLogger(MedicineController.class);
    private Utils utils = new Utils();

    private final MedicineRepository medicineRepository;

    private final MedicineMongoRepository medicineMongoRepository;

    @Autowired
    public MedicineController(MedicineRepository medicineRepository, MedicineMongoRepository medicineMongoRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineMongoRepository = medicineMongoRepository;
    }

    @PostMapping
    public void addMedicine(@RequestBody Medicine medicine) {
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

    @PutMapping(value = "/{id}")
    public void editMedicine(@PathVariable long id, @RequestBody Medicine medicine) {
        Medicine existingMedicine = medicineRepository.findById(id).orElse(null);
        Assert.notNull(medicine, "Medicine not found!");
        if (medicine.getCompany() != null) {
            existingMedicine.setCompany(medicine.getCompany());
            log.info("Medicine company updated successfully!");
        }
        if (medicine.getProspect() != null) {
            existingMedicine.setProspect(medicine.getProspect());
            log.info("Medicine prospect updated successfully!");
        }
        medicineRepository.save(existingMedicine);
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

}
