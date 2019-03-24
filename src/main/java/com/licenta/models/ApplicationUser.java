package com.licenta.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class ApplicationUser implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long ID;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

}
