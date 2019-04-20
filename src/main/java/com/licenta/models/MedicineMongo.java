package com.licenta.models;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@TypeDef(name="hstore",typeClass = PostgreSQLHStoreType.class)
public class MedicineMongo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Type(type="hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> prospect=new HashMap<>();

    @Column(name="companyCUI")
    private String companyCUI;
}
