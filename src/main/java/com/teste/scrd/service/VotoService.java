package com.teste.scrd.service;

import com.teste.scrd.enums.Errors;
import com.teste.scrd.enums.Resposta;
import com.teste.scrd.exceptions.VotoException;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.model.Voto;
import com.teste.scrd.repository.VotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VotoService {

    private VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto add(Voto voto) {
        return votoRepository.save(voto);
    }

    public String getResultado(Votacao votacao) throws VotoException {
        List<Voto> votoList = votoRepository.findByVotacaoIdVotacao(votacao.getIdVotacao())
                .orElseThrow(()-> new VotoException(Errors.VOTO_NOT_FOUND) );

        Integer countSim = 0;
        Integer countNao = 0;

        for(Voto voto: votoList){
            if(voto.getVoto().equals(Resposta.SIM))
                countSim += 1;
            else
                countNao += 1;
        }

        if(countSim == countNao)
            return "Empatou - Resultado: SIM= " + countSim + " NÂO= " + countNao;

        if (countSim > countNao)
            return "Sim ganhou - Resultado: SIM= " + countSim + " NÂO= " + countNao;
        else
            return "Não Ganhou - Resultado: SIM= " + countSim + " NÂO= " + countNao;
    }
}
