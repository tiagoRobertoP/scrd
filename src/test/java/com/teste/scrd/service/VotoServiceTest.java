package com.teste.scrd.service;

import com.teste.scrd.enums.Resposta;
import com.teste.scrd.exceptions.VotoException;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.model.Voto;
import com.teste.scrd.repository.VotoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VotoServiceTest {

    private VotoService service;

    @Mock
    private VotoRepository repository;

    @Before
    public void setup() {
        this.service = new VotoService(this.repository);
    }

    private List<Voto> createValidVotoList() {
       Voto voto1 =  Voto.builder()
                .voto(Resposta.SIM)
                .build();

        Voto voto2 =  Voto.builder()
                .voto(Resposta.SIM)
                .build();
        Voto voto3 =  Voto.builder()
                .voto(Resposta.NAO)
                .build();

        return Arrays.asList(voto1, voto2, voto3);
    }

    private Votacao createValidVotacao() {
        return Votacao.builder()
                .idVotacao(1L)
                .tempoLimiteMinutos(1)
                .build();
    }

    @Test
    public void testGetResultado() throws VotoException {
        List<Voto> votoList = createValidVotoList();
        Votacao votacaoData = createValidVotacao();
        this.repository = Mockito.mock(VotoRepository.class);
        Mockito.when(this.repository.findByVotacaoIdVotacao(1L)).thenReturn(Optional.of(votoList));

        this.service = new VotoService(this.repository);
        String  resultado = this.service.getResultado(votacaoData);

        Assert.assertEquals(resultado, "Sim ganhou - Resultado: SIM= " + 2 + " NÂO= " + 1);
    }


}
