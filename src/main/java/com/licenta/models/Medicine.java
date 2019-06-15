package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@TypeDef(name="hstore",typeClass = PostgreSQLHStoreType.class)

public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name="stock")
    private Integer stock;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine )) return false;
        return id != null && id.equals(((Medicine) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }
    @Override
    public String toString(){
        return "";
    }

}

