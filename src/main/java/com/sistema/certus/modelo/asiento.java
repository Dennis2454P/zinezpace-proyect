package com.sistema.certus.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class asiento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
