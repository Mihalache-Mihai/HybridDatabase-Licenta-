package com.licenta.utils;

import com.licenta.models.Medicine;
import com.licenta.models.MedicineMongo;

public class Utils {
    public MedicineMongo toMongoMedicine(Medicine medicine) {
        MedicineMongo medicineMongo = new MedicineMongo();
        medicineMongo.setId(medicine.getId().toString());
        medicineMongo.setName(medicine.getName());
        medicineMongo.setStock(medicine.getStock());
        medicineMongo.setProspect(medicine.getProspect());
        return medicineMongo;
    }
}
