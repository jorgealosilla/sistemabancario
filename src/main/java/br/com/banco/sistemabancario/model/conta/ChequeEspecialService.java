package br.com.banco.sistemabancario.model.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ChequeEspecialService {

    @Value("${constants.conta.limites.faixa-1.score}")
    private String scoreFaixa1;
    @Value("${constants.conta.limites.faixa-2.score}")
    private String scoreFaixa2;
    @Value("${constants.conta.limites.faixa-3.score}")
    private String scoreFaixa3;
    @Value("${constants.conta.limites.faixa-4.score}")
    private String scoreFaixa4;

    @Value("${constants.conta.limites.faixa-1.limite-cheque-especial}")
    private String limiteChequeFaixa1;
    @Value("${constants.conta.limites.faixa-2.limite-cheque-especial}")
    private String limiteChequeFaixa2;
    @Value("${constants.conta.limites.faixa-3.limite-cheque-especial}")
    private String limiteChequeFaixa3;
    @Value("${constants.conta.limites.faixa-4.limite-cheque-especial}")
    private String limiteChequeFaixa4;

    @Autowired
    private ChequeEspecialRepository repository;

    public ChequeEspecial criaOuAtualizaChequeEspecial(final Conta conta) {
        if (repository.existsChequeEspecialByIdConta(conta.getId()))
            return atualizaChequeEspecial(conta);
        return criaChequeEspecial(conta);
    }

    private ChequeEspecial criaChequeEspecial(Conta conta) {
        ChequeEspecial chequeEspecial = ChequeEspecial.builder()
                .conta(conta)
                .ativo(conta.getScoreCliente() > 0)
                .limite(getLimiteChequeEspecial(conta.getScoreCliente()))
                .build();
        return repository.save(chequeEspecial);
    }

    private ChequeEspecial atualizaChequeEspecial(final Conta conta) {
        Optional<ChequeEspecial> optionalChequeEspecial = repository.findChequeEspecialByIdConta(conta.getId());
        ChequeEspecial chequeEspecial = optionalChequeEspecial.get();
        return repository.save(
                ChequeEspecial.builder()
                        .id(chequeEspecial.getId())
                        .conta(conta)
                        .ativo(conta.getScoreCliente() > 0)
                        .limite(getLimiteChequeEspecial(conta.getScoreCliente()))
                        .build()
        );
    }

    private BigDecimal getLimiteChequeEspecial(final int scoreCliente) {
        FaixaScore faixaScore = getFaixaByScore(scoreCliente);
        switch (faixaScore) {
            case FAIXA1:
                return new BigDecimal(limiteChequeFaixa1);
            case FAIXA2:
                return new BigDecimal(limiteChequeFaixa2);
            case FAIXA3:
                return new BigDecimal(limiteChequeFaixa3);
            case FAIXA4:
                return new BigDecimal(limiteChequeFaixa4);
            default:
                return BigDecimal.ZERO;
        }
    }

    private FaixaScore getFaixaByScore(final int scoreCliente) {
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

    public enum FaixaScore {
        FAIXA0,
        FAIXA1,
        FAIXA2,
        FAIXA3,
        FAIXA4
    }
}
