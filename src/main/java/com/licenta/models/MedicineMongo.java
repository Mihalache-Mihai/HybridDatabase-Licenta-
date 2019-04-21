package com.licenta.models;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@TypeDef(name="hstore",typeClass = PostgreSQLHStoreType.class)
public class MedicineMongo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private Map<String,String> prospect=new HashMap<>();

    private String companyCUI;
}
