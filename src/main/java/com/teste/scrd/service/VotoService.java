package com.teste.scrd.service;

import com.teste.scrd.enums.Resposta;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.model.Voto;
import com.teste.scrd.repository.VotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    public Voto add(Voto voto) {
        return votoRepository.save(voto);
    }

    public String getResultado(Votacao votacao) {
        List<Voto> votoList = votoRepository.findByVotacaoIdVotacao(votacao.getIdVotacao())
                .orElseThrow(()-> new RuntimeException("Votacao não encontrada") );

        Integer countSim = 0;
        Integer countNao = 0;

        for(Voto voto: votoList){
            if(voto.getVoto().equals(Resposta.SIM))
                countSim += 1;
            else
                countNao += 1;
        }

        if (countSim > countNao)
            return "Sim ganhou - Resultado: SIM= " + countSim + " NÂO= " + countNao;
        else
            return "Não Ganhou - Resultado: SIM= " + countSim + " NÂO= " + countNao;
    }
}
