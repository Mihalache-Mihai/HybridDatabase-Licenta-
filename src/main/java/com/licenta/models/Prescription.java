package com.licenta.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Map;

@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String prescriptionSeries;

    private String locality;

    private String county;

    private String CNP;

    private String name;

    private String residence;

    private String diagnosis;

    private Map<String, String> medicines;
}
