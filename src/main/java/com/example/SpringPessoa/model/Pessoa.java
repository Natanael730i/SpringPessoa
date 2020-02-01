package com.example.SpringPessoa.model;

import com.example.SpringPessoa.utils.ValidaCpf;
import lombok.Data;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Pessoa")
public class Pessoa {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true,nullable = false)
    private ObjectId id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "anoNascimeto", nullable = false)
    private LocalDate anoNascimento;
    @JoinColumn(name = "idEndereco", unique = true,nullable = false )
    private ObjectId idEndereco;

    public void setCpf(String cpf) {
        boolean cpfTeste = ValidaCpf.isCPF(cpf);
        if(cpfTeste){
            this.cpf = cpf;
        }else{
            throw new RuntimeException("CPF invalido!");
        }

    }
}
