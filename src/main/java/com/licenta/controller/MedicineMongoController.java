package com.licenta.controller;

import com.licenta.models.MedicineMongo;
import com.licenta.repository.MedicineMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicineMongo")
public class MedicineMongoController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final MedicineMongoRepository medicineMongoRepository;

    public MedicineMongoController(MedicineMongoRepository medicineMongoRepository) {
        this.medicineMongoRepository = medicineMongoRepository;
    }

    @RequestMapping("/{name}")
    public List<MedicineMongo> findAllByName(@PathVariable String name){
        //return medicineMongoRepository.findByNameContaining(name);

        List<MedicineMongo> medicinesList = new ArrayList<>();
        long start_time = System.nanoTime();
        medicinesList = medicineMongoRepository.findByNameContaining(name);
        long end_time = System.nanoTime();
        long duration = end_time-start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time non relational is: "+ Double.toString(elapsedTimeInSecond));

        return medicinesList;

    }

    @GetMapping("/byID/{id}")
    public MedicineMongo getMongoMedicineByID(@PathVariable long id){
        String idMongo = Long.toString(id);
        return medicineMongoRepository.getMedicineMongoById(idMongo);
    }

    @GetMapping("/{name}/{stock}")
    public List<MedicineMongo> findAllMedicinesByNameAndStock(@PathVariable String name, @PathVariable Integer stock){
        List<MedicineMongo> medicinesList = new ArrayList<>();
        long start_time = System.nanoTime();
        medicinesList = medicineMongoRepository.findAllByNameAndStock(name,stock);
        long end_time = System.nanoTime();
        long duration = end_time-start_time;
        log.info("Time is: "+ Long.toString(duration));

        return medicinesList;

    }
}
