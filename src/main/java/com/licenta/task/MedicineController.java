package com.licenta.task;

import com.licenta.models.Medicine;
import com.licenta.models.Task;
import com.licenta.repository.MedicineMongoRepository;
import com.licenta.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineMongoRepository medicineMongoRepository;

    public MedicineController(){}

    @PostMapping
    public void addMedicine(@RequestBody Medicine medicine) {
        medicineRepository.save(medicine);
        medicineMongoRepository.save(medicine);
        //taskMongoRepository.save(task);
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
