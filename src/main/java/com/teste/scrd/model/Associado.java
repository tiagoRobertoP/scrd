package com.teste.scrd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="associado")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_associado")
    private Long idAssociado;

    @Column(name="nome")
    private String nome;

    @Column(name="cpf")
    private String cpf;
}
