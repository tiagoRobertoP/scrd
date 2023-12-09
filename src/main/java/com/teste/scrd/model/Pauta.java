package com.teste.scrd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="pauta")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pauta")
    private Long idPauta;

    @Column(name="descricao")
    private String descricao;
}
