package com.licenta.controller;

import com.licenta.models.MedicineMongo;
import com.licenta.repository.MedicineMongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicineMongo")
public class MedicineMongoController {

    private final MedicineMongoRepository medicineMongoRepository;

    public MedicineMongoController(MedicineMongoRepository medicineMongoRepository) {
        this.medicineMongoRepository = medicineMongoRepository;
    }

    @RequestMapping("/{name}")
    public List<MedicineMongo> findAllByName(@PathVariable String name){
        return medicineMongoRepository.findByNameContaining(name);
    }
}
