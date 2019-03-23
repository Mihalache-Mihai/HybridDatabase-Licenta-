package com.licenta.models;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity(name = "Task")
@Table(name ="task")
@TypeDef(name="hstore",typeClass = PostgreSQLHStoreType.class)
public class Task implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long ID;


    @Column(name="description")
    private String description;


    @Type(type="hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> properties = new HashMap<>();

    protected Task() { }

    public Task(String description) {
        this.description = description;
    }
}