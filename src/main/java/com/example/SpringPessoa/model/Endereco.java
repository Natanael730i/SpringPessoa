package com.example.SpringPessoa.model;

import lombok.Data;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Endereco")
@Data
@IdClass(Endereco.class)
public class Endereco implements Serializable {


    @Id
    @ManyToOne(targetEntity = Pessoa.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEndereco", unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private ObjectId id;
    @Column(name = "logradouro",nullable = false)
    private String logradouro;
    @Column(name = "bairro",nullable = false)
    private String bairro;
    @Column(name = "numero", nullable = false)
    private Integer numero;
}

