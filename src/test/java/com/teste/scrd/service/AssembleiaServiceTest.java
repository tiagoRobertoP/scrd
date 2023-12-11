package com.teste.scrd.service;

import com.teste.scrd.model.Assembleia;
import com.teste.scrd.repository.AssembleiaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class AssembleiaServiceTest {

    private AssembleiaService service;

    @Mock
    private AssembleiaRepository repository;

    @Before
    public void setup() {
        this.service = new AssembleiaService(repository);
    }

    private Assembleia createValidAssembleia() {
        return Assembleia.builder()
                .idAssembleia(1L)
                .descricao("Assembleia 1")
                .build();
    }

    @Test
    public void testAdd() {
        Assembleia assembleiaData = createValidAssembleia();

        this.repository = Mockito.mock(AssembleiaRepository.class);

        Mockito.when(this.repository.save(assembleiaData)).thenReturn(assembleiaData);

        this.service = new AssembleiaService(this.repository);
        Assembleia assembleia =  this.service.add(assembleiaData);

        Assert.assertEquals(assembleiaData.getIdAssembleia() , assembleia.getIdAssembleia());

    }



}
