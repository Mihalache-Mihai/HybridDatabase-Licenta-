package com.licenta.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Credentials credentials;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email",unique = true)
    private String email;

    @Column(name="salary")
    private Integer salary;

    @Column(name="cnp")
    private String cnp;
}
