package com.teste.scrd.model;

import com.teste.scrd.enums.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="voto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_voto")
    private Long idVoto;

    @Column(name="voto")
    @Enumerated(EnumType.STRING)
    private Resposta voto;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_associado")
    private Associado associado;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_votacao")
    private Votacao votacao;

}
