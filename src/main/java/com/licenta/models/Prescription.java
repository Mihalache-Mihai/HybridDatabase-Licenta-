package com.licenta.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long id;


}
