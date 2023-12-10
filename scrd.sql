CREATE DATABASE scrd;

use scrd;

CREATE TABLE associado (
    id_associado int NOT NULL AUTO_INCREMENT,
    nome varchar(255),
    cpf varchar(255),
    CONSTRAINT PK_associado PRIMARY KEY (id_associado)
);

CREATE TABLE assembleia (
    id_assembleia int NOT NULL AUTO_INCREMENT,
    descricao varchar(255),
    CONSTRAINT PK_assembleia PRIMARY KEY (id_assembleia)
);

CREATE TABLE pauta (
    id_pauta int NOT NULL AUTO_INCREMENT,
    descricao varchar(255),
    CONSTRAINT PK_pauta PRIMARY KEY (id_pauta)
);

CREATE TABLE votacao (
    id_votacao int NOT NULL AUTO_INCREMENT,
    tempo_limite_minutos int,
    abertura datetime,
    id_assembleia int,
    id_pauta int,
    CONSTRAINT PK_votacao PRIMARY KEY (id_votacao),
    CONSTRAINT FK_votacaoAssembleia FOREIGN KEY (id_assembleia) REFERENCES assembleia(id_assembleia),
    CONSTRAINT FK_votacaoPauta FOREIGN KEY (id_pauta) REFERENCES pauta(id_pauta)
);

CREATE TABLE voto (
    id_voto int NOT NULL AUTO_INCREMENT,
    voto varchar(255),
    id_associado int,
    id_votacao int,
    CONSTRAINT PK_voto PRIMARY KEY (id_voto),
    CONSTRAINT FK_votoAssociado FOREIGN KEY (id_associado) REFERENCES associado(id_associado),
    CONSTRAINT FK_votoVotacao FOREIGN KEY (id_votacao) REFERENCES votacao(id_votacao)
);


select * from voto;