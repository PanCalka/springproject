package com.PanCalka.Recipe.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private String uom;
}
