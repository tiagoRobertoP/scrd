package com.teste.scrd.service;

import com.teste.scrd.exceptions.VotacaoException;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.repository.VotacaoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class VotacaoServiceTest {

    private VotacaoService service;

    @Mock
    private VotacaoRepository repository;

    @Before
    public void setup() {
        this.service = new VotacaoService(this.repository);
    }

    private Votacao createValidVotacao() {
        return Votacao.builder()
                .idVotacao(1L)
                .tempoLimiteMinutos(1)
                .build();
    }

    @Test
    public void testgetById () throws VotacaoException {
        Votacao votacaoData = createValidVotacao();
        this.repository = Mockito.mock(VotacaoRepository.class);
        Mockito.when(this.repository.findByIdVotacao(1L)).thenReturn(Optional.ofNullable(votacaoData));

        this.service = new VotacaoService(this.repository);
        Votacao votacao = this.service.getById(1L);

        Assert.assertEquals(votacaoData.getIdVotacao(), votacao.getIdVotacao());
    }
}
