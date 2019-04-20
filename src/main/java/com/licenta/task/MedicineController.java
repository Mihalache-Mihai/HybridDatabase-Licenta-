package com.licenta.task;

import com.licenta.models.Company;
import com.licenta.models.Medicine;
import com.licenta.models.MedicineMongo;
import com.licenta.models.Task;
import com.licenta.repository.CompanyRepository;
import com.licenta.repository.MedicineMongoRepository;
import com.licenta.repository.MedicineRepository;
import com.licenta.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private static final Logger log = LoggerFactory.getLogger(MedicineController.class);
    private Utils utils = new Utils();

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MedicineMongoRepository medicineMongoRepository;

    public MedicineController(){}

    @PostMapping
    public void addMedicine(@RequestBody Medicine medicine) {
        Long companyId = medicine.getCompany().getId();
        Company company = companyRepository.findById(companyId).orElse(null);
        try{
            company.addMedicine(medicine);
        }
        catch(Exception ex){
            log.error("Company does not exist!");
        }
        medicineRepository.save(medicine);


        MedicineMongo medicineMongo = utils.toMongoMedicine(medicine);
        medicineMongoRepository.insert(medicineMongo);

    }

    @GetMapping
    public Iterable<Medicine> getMedicines() {
        return medicineRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editMedicine(@PathVariable long id, @RequestBody Medicine medicine) {
        Medicine existingMedicine = medicineRepository.findById(id).get();
//        Assert.notNull(existingTask, "Task not found");
//        existingTask.setDescription(task.getDescription());
//        existingTask.setProperties(task.getProperties());
        medicineRepository.save(existingMedicine);
    }



    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        Medicine medicineToDelete = medicineRepository.findById(id).get();
        medicineRepository.delete(medicineToDelete);
    }


}
