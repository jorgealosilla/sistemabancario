package br.com.banco.sistemabancario.model.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ChequeEspecialService {

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
    @Autowired
    private FaixaScoreHelper faixaScoreHelper;

    public ChequeEspecial criaOuAtualizaChequeEspecial(final Conta conta, final int scoreCliente) {
        if (repository.existsChequeEspecialByIdConta(conta.getId()))
            return atualizaChequeEspecial(conta, scoreCliente);
        return criaChequeEspecial(conta, scoreCliente);
    }

    private ChequeEspecial criaChequeEspecial(final Conta conta, final int scoreCliente) {
        ChequeEspecial chequeEspecial = ChequeEspecial.builder()
                .conta(conta)
                .ativo(isAtivo(scoreCliente))
                .limite(getLimiteChequeEspecial(scoreCliente))
                .build();
        return repository.save(chequeEspecial);
    }

    private ChequeEspecial atualizaChequeEspecial(final Conta conta, final int scoreCliente) {
        Optional<ChequeEspecial> optionalChequeEspecial = repository.findChequeEspecialByIdConta(conta.getId());
        ChequeEspecial chequeEspecial = optionalChequeEspecial.get();
        return repository.save(
                ChequeEspecial.builder()
                        .id(chequeEspecial.getId())
                        .conta(conta)
                        .ativo(isAtivo(scoreCliente))
                        .limite(getLimiteChequeEspecial(scoreCliente))
                        .build()
        );
    }

    private boolean isAtivo(final int scoreCliente){
        FaixaScore faixaScore = faixaScoreHelper.getFaixaByScore(scoreCliente);
        return faixaScore.ativaLimiteChequeEspecial();
    }

    private BigDecimal getLimiteChequeEspecial(final int scoreCliente) {
        FaixaScore faixaScore = faixaScoreHelper.getFaixaByScore(scoreCliente);
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
}
