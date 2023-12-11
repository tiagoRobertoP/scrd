package com.teste.scrd.service;

import com.teste.scrd.exceptions.AssociadoException;
import com.teste.scrd.model.Associado;
import com.teste.scrd.repository.AssociadoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class AssociadoServiceTest {

    private AssociadoService service;
    @Mock
    private AssociadoRepository repository;
    @Before
    public void setup(){
        this.service = new AssociadoService(repository);
    }

    private Associado createValidAssociado(){
        return Associado.builder()
                .idAssociado(1L)
                .nome("José")
                .cpf("1")
                .build();
    }
    @Test
    public void testDelete() throws AssociadoException {
        Associado associado01 = createValidAssociado();

        this.repository = Mockito.mock(AssociadoRepository.class);
        Mockito.when(repository.deleteByIdAssociado(1L)).thenReturn(Optional.ofNullable(associado01));

        this.service = new AssociadoService(repository);
        boolean deleted  = this.service.delete(1L);

        Assert.assertTrue(deleted);
    }
}
