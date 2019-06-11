package com.licenta.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Data
@Document
@CompoundIndexes({
        @CompoundIndex(name = "prescription_series", def="{'prescriptionSeries': 1, 'CNP': 1}")
})
public class Prescription implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column(name="series",unique = true)
    private String prescriptionSeries;

    private String locality;

    private String county;

    private String CNP;

    private String name;

    private String residence;

    private String diagnosis;

    private String medicines;
}
