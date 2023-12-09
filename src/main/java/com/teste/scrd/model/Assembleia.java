package com.teste.scrd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="assembleia")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assembleia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_assembleia")
    private Long idAssembleia;

    @Column(name="descricao")
    private String descricao;
}
