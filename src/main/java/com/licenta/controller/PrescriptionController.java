package com.licenta.controller;

import com.licenta.models.Prescription;
import com.licenta.repository.PrescriptionMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

//    @PostMapping("/add")
//    public void populate(){
//        for(int i=0;i<20;i++){
//            String prescriptionSeries = "21012"+i;
//            String county = "brasov";
//            String locality = "mockLocality"+i;
//            String CNP = "12345"+i;
//            String name= "mihai";
//            String residence = "mockResidence"+i;
//            String diagnosis = "mockDiagnosis";
//            String medicines = "mockMedicines"+i;
//            Prescription p= new Prescription();
//            p.setPrescriptionSeries(prescriptionSeries);
//            p.setResidence(residence);
//            p.setName(name);
//            p.setCounty(county);
//            p.setLocality(locality);
//            p.setCNP(CNP);
//            p.setDiagnosis(diagnosis);
//            p.setMedicines(medicines);
//
//            prescriptionMongoRepository.save(p);
//            log.info("prescription saved"+locality);
//        }
//        for(int i=0;i<2000000;i++){
//            String prescriptionSeries = "3"+i;
//            String county = "mockCounty"+i;
//            String locality = "mockLocality"+i;
//            String CNP = "12345"+i;
//            String name= "mockName";
//            String residence = "mockResidence"+i;
//            String diagnosis = "mockDiagnosis";
//            String medicines = "mockMedicines"+i;
//            Prescription p= new Prescription();
//            p.setPrescriptionSeries(prescriptionSeries);
//            p.setResidence(residence);
//            p.setName(name);
//            p.setCounty(county);
//            p.setLocality(locality);
//            p.setCNP(CNP);
//            p.setDiagnosis(diagnosis);
//            p.setMedicines(medicines);
//
//            prescriptionMongoRepository.save(p);
//            log.info("prescription saved"+county);
//        }
//
//
//    }


    @GetMapping("/insert100k")
    public Prescription populate() {
        Prescription prescriptionReturn = new Prescription();
        long start_time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String prescriptionSeries = "presentationTest" + i;
            String county = "cluj";
            String locality = "mockLocality" + i;
            String CNP = "12345" + i;
            String name = "mihai";
            String residence = "mockResidence" + i;
            String diagnosis = "mockDiagnosis";
            String medicines = "mockMedicines" + i;
            Prescription p = new Prescription();
            p.setPrescriptionSeries(prescriptionSeries);
            p.setResidence(residence);
            p.setName(name);
            p.setCounty(county);
            p.setLocality(locality);
            p.setCNP(CNP);
            p.setDiagnosis(diagnosis);
            p.setMedicines(medicines);

            prescriptionMongoRepository.save(p);
            log.info("prescription saved" + locality);
        }
        long end_time = System.nanoTime();
        long duration = end_time - start_time;
        log.info("Time is: " + Long.toString(duration));

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        prescriptionReturn.setResponseTime("Time for insert was:" + Double.toString(elapsedTimeInSecond)+" seconds");
        return prescriptionReturn;

    }

    @PostMapping
    public Prescription addPrescription(@RequestBody Prescription prescription) {
        Prescription p = new Prescription();
        Assert.notNull(prescription, "Prescription is null");
        long start_time = System.nanoTime();
        prescriptionMongoRepository.insert(prescription);


        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time prescription update is: " + Double.toString(elapsedTimeInSecond));
        p.setResponseTime(Double.toString(elapsedTimeInSecond));
        log.info("Prescription saved successfully!");
        return p;
    }

    @GetMapping("/{name}")
    public List<Prescription> getPrescriptionByNameContaining(@PathVariable String name) {
        return prescriptionMongoRepository.findAllByNameContaining(name);
    }

    @GetMapping
    public Iterable<Prescription> getPrescriptions() {
        return prescriptionMongoRepository.findAll();
    }


    @PutMapping(value = "/{series}")
    public void editPrescription(@PathVariable String series, @RequestBody Prescription prescription) {
        long start_time = System.nanoTime();
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

        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time prescription update is: " + Double.toString(elapsedTimeInSecond));
        log.info("Prescription saved successfully!");
    }


    @DeleteMapping("/{series}")
    public Prescription deletePrescription(@PathVariable String series) {
        Prescription prescriptionReturn = new Prescription();
        long start_time = System.nanoTime();

        Prescription p = prescriptionMongoRepository.findByPrescriptionSeries(series);
        if (p != null) {
            prescriptionMongoRepository.delete(p);
        }
        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time prescription delete is: " + Double.toString(elapsedTimeInSecond));
        p.setResponseTime(Double.toString(elapsedTimeInSecond));
        log.info("Prescription deleted successfully!");
        return prescriptionReturn;
    }

//    @RequestMapping("/byName/{name}")
//    public List<Prescription> findAllByCounty(@PathVariable String name){
//        List<Prescription> prescriptionList = new ArrayList<>();
//        long start_time = System.nanoTime();
//        prescriptionList = prescriptionMongoRepository.findAllByName(name);
//        long end_time = System.nanoTime();
//        long duration = end_time-start_time;
//
//        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
//        log.info("Time prescription is: "+ Double.toString(elapsedTimeInSecond));
//
//        return prescriptionList;
//    }

    @RequestMapping("/{name}/{county}")
    public List<Prescription> findAllByPrescriptionAndCNP(@PathVariable String name, @PathVariable String county) {
        List<Prescription> list = new ArrayList<>();
        long start_time = System.nanoTime();
        list = prescriptionMongoRepository.findPrescriptionByNameAndCounty(name, county);
        long end_time = System.nanoTime();
        long duration = end_time - start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time medicines is: " + Double.toString(elapsedTimeInSecond));

        list.get(0).setResponseTime(Double.toString(elapsedTimeInSecond));
        return list;
    }

    @GetMapping("/bySeries/{series}")
    public Prescription getPrescriptionBySeries(@PathVariable String series) {
        return prescriptionMongoRepository.findByPrescriptionSeries(series);
    }
}
