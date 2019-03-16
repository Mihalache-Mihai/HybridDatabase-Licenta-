package com.licenta.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Task extends BaseEntity<Long> implements Serializable{

    @Column(name="description")
    private String description;

    protected Task() { }

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}