package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Medicine> medicines = new ArrayList<>();

    private String responseTime;

    @Override
    public String toString() {
        return "";
    }
}
