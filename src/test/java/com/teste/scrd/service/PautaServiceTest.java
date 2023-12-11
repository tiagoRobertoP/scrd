package com.teste.scrd.service;

import com.teste.scrd.exceptions.PautaException;
import com.teste.scrd.model.Pauta;
import com.teste.scrd.repository.PautaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class PautaServiceTest {

    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Before
    public void setup(){
        this.service = new PautaService(repository);
    }

    private Pauta createValidPauta() {
        return Pauta.builder()
                .idPauta(1L)
                .descricao("Pauta 1")
                .build();
    }

    @Test
    public void testDelete() throws PautaException {
        Pauta pautaData = createValidPauta();

        this.repository = Mockito.mock(PautaRepository.class);
        Mockito.when(this.repository.deleteByIdPauta(1L)).thenReturn(Optional.ofNullable(pautaData));

        this.service = new PautaService(this.repository);
        Boolean deleted = this.service.delete(1L);

        Assert.assertTrue(deleted);
    }

}
