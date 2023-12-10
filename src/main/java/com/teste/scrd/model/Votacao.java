package com.teste.scrd.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="votacao")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Votacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_votacao")
    private Long idVotacao;

    @DateTimeFormat(style = "yyyy-MM-dd hh:mm:ss")
    @Column(name="abertura")
    private LocalDateTime abertura;

    @Column(name="tempo_limite_minutos")
    private int tempoLimiteMinutos;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_assembleia")
    private Assembleia assembleia;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_pauta")
    private Pauta pauta;
}
