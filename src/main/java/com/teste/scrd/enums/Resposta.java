package com.teste.scrd.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public enum Resposta {

    SIM("Sim"),
    NAO("Não");

    private final String Label;

    Resposta (String label) {this.Label=label;}

}
