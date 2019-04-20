package com.licenta.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Company implements Serializable{

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="cui",unique = true)
    private String CUI;

    @Column(name="companyName")
    private String companyName;

    @Column
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Medicine> medicines = new ArrayList<>();

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
        medicine.setCompany(this);
    }

    public void removeMedicine(Medicine medicine) {
        medicines.remove(medicine);
        medicine.setCompany(null);
    }

    @Override
    public String toString() {
        return "";
    }
}
