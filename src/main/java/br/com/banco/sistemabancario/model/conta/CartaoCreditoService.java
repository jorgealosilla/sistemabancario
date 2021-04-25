package br.com.banco.sistemabancario.model.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartaoCreditoService {

    @Value("${constants.conta.limites.faixa-1.limite-cartao-credito}")
    private String limiteCartaoFaixa1;
    @Value("${constants.conta.limites.faixa-2.limite-cartao-credito}")
    private String limiteCartaoFaixa2;
    @Value("${constants.conta.limites.faixa-3.limite-cartao-credito}")
    private String limiteCartaoFaixa3;
    @Value("${constants.conta.limites.faixa-4.limite-cartao-credito}")
    private String limiteCartaoFaixa4;

    @Autowired
    private CartaoCreditoRepository repository;
    @Autowired
    private FaixaScoreHelper faixaScoreHelper;

    public CartaoCredito criaOuAtualizaCartaoCredito(final Conta conta) {
        if (repository.existsCartaoCreditoByIdConta(conta.getId()))
            return atualizaCartaoCredito(conta);
        return criaCartaoCredito(conta);
    }

    private CartaoCredito criaCartaoCredito(Conta conta) {
        if (conta.getScoreCliente() > 0) {
            CartaoCredito cartaoCredito = CartaoCredito.builder()
                    .conta(conta)
                    .limite(getLimiteCartaoCredito(conta.getScoreCliente()))
                    .build();
            return repository.save(cartaoCredito);
        } else {
            return null;
        }
    }

    private CartaoCredito atualizaCartaoCredito(final Conta conta) {
        Optional<CartaoCredito> optionalCartaoCredito = repository.findCartaoCreditoByIdConta(conta.getId());
        CartaoCredito cartaoCredito = optionalCartaoCredito.get();
        return repository.save(
                CartaoCredito.builder()
                        .id(cartaoCredito.getId())
                        .conta(conta)
                        .limite(getLimiteCartaoCredito(conta.getScoreCliente()))
                        .build()
        );
    }

    private BigDecimal getLimiteCartaoCredito(final int scoreCliente) {
        FaixaScore faixaScore = faixaScoreHelper.getFaixaByScore(scoreCliente);
        switch (faixaScore) {
            case FAIXA1:
                return new BigDecimal(limiteCartaoFaixa1);
            case FAIXA2:
                return new BigDecimal(limiteCartaoFaixa2);
            case FAIXA3:
                return new BigDecimal(limiteCartaoFaixa3);
            case FAIXA4:
                return new BigDecimal(limiteCartaoFaixa4);
            default:
                return BigDecimal.ZERO;
        }
    }
}
