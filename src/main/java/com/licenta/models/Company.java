package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="companyName",unique = true)
    private String companyName;

    @Column
    @JsonIgnore
    @OneToMany(
            mappedBy = "company"
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
    )
    private List<Medicine> medicines = new ArrayList<>();

    @Override
    public String toString() {
        return "";
    }
}
