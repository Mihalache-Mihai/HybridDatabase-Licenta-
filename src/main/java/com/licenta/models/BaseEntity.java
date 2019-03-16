package com.licenta.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<ID> implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private ID id;

    public BaseEntity(){}

    public BaseEntity(ID id)
    {
        this.id = id;
    }

    /**
     * @return the ID of the current entity.
     */
    public ID getId() {
        return id;
    }

    /**
     * @param id the new ID of the entity.
     */
    public void setId(ID id) {
        this.id = id;
    }


    /**
     * @return a string containing the id of the entity.
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
