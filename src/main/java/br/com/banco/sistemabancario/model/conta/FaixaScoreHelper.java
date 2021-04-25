package br.com.banco.sistemabancario.model.conta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class FaixaScoreHelper {
    
    @Value("${constants.conta.limites.faixa-1.score}")
    private String scoreFaixa1;
    @Value("${constants.conta.limites.faixa-2.score}")
    private String scoreFaixa2;
    @Value("${constants.conta.limites.faixa-3.score}")
    private String scoreFaixa3;
    @Value("${constants.conta.limites.faixa-4.score}")
    private String scoreFaixa4;

    public FaixaScore getFaixaByScore(final int scoreCliente) {
        if (Pattern.compile(scoreFaixa1).matcher(String.valueOf(scoreCliente)).matches())
            return FaixaScore.FAIXA1;
        if (Pattern.compile(scoreFaixa2).matcher(String.valueOf(scoreCliente)).matches())
            return FaixaScore.FAIXA2;
        if (Pattern.compile(scoreFaixa3).matcher(String.valueOf(scoreCliente)).matches())
            return FaixaScore.FAIXA3;
        if (Pattern.compile(scoreFaixa4).matcher(String.valueOf(scoreCliente)).matches())
            return FaixaScore.FAIXA4;
        return FaixaScore.FAIXA0;
    }
}
