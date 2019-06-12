package com.licenta.controller;

import com.licenta.models.Prescription;
import com.licenta.repository.PrescriptionMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    private static final Logger log = LoggerFactory.getLogger(PrescriptionController.class);

    private final PrescriptionMongoRepository prescriptionMongoRepository;

    @Autowired
    public PrescriptionController(PrescriptionMongoRepository prescriptionMongoRepository) {
        this.prescriptionMongoRepository = prescriptionMongoRepository;
    }

    @PostMapping
    public void addPrescription(@RequestBody Prescription prescription) {
        Assert.notNull(prescription, "Prescription is null");
        prescriptionMongoRepository.insert(prescription);
        log.info("Prescription saved successfully!");
    }

    @GetMapping("/{name}")
    public List<Prescription> getPrescriptionByNameContaining(@PathVariable String name){
        return prescriptionMongoRepository.findAllByNameContaining(name);
    }

    @GetMapping
    public Iterable<Prescription> getPrescriptions() {
        return prescriptionMongoRepository.findAll();
    }


    @PutMapping(value = "/{series}")
    public void editPrescription(@PathVariable String series, @RequestBody Prescription prescription) {
      //  String idMongo = String.valueOf(id);
        Prescription existingPrescription = prescriptionMongoRepository.findByPrescriptionSeries(series);
        Assert.notNull(prescription, "Prescription not found");
        if (prescription.getCNP() != null) {
            existingPrescription.setCNP(prescription.getCNP());
            log.info("Prescription CNP updated successfully!");
        }
        if (prescription.getCounty() != null) {
            existingPrescription.setCounty(prescription.getCounty());
            log.info("Prescription county updated successfully!");
        }
        if (prescription.getLocality() != null) {
            existingPrescription.setLocality(prescription.getLocality());
            log.info("Prescription locality updated successfully!");
        }
        if (prescription.getDiagnosis() != null) {
            existingPrescription.setDiagnosis(prescription.getDiagnosis());
            log.info("Prescription diagnosis updated successfully!");
        }
        if (prescription.getMedicines() != null) {
            existingPrescription.setMedicines(prescription.getMedicines());
            log.info("Prescription medicines updated successfully!");
        }
        if (prescription.getName() != null) {
            existingPrescription.setName(prescription.getName());
            log.info("Prescription name updated successfully!");
        }
        if (prescription.getResidence() != null) {
            existingPrescription.setResidence(prescription.getResidence());
            log.info("Prescription residence updated successfully!");
        }
        if (prescription.getPrescriptionSeries() != null) {
            existingPrescription.setPrescriptionSeries(prescription.getPrescriptionSeries());
            log.info("Prescription series updated successfully!");
        }
        prescriptionMongoRepository.save(existingPrescription);
        log.info("Prescription saved successfully!");
    }


    @DeleteMapping("/{series}")
    public void deletePrescription(@PathVariable String series) {
//        Iterable<Prescription> list = prescriptionMongoRepository.findAll();
//        for (Prescription p : list) {
//            if (p.getPrescriptionSeries().equals(prescriptionSeries)) {
//                prescriptionMongoRepository.delete(p);
//                break;
//            }
//        }
        Prescription p = prescriptionMongoRepository.findByPrescriptionSeries(series);
        if(p!=null){
            prescriptionMongoRepository.delete(p);
        }

        log.info("Prescription deleted successfully!");
    }


    @RequestMapping("/{name}/{county}")
    public List<Prescription> findAllByPrescriptionAndCNP(@PathVariable String name, @PathVariable String county){
        return prescriptionMongoRepository.findPrescriptionByNameAndCounty(name,county);
    }

    @GetMapping("/bySeries/{series}")
    public Prescription getPrescriptionBySeries(@PathVariable String series){
        return prescriptionMongoRepository.findByPrescriptionSeries(series);
    }
}
