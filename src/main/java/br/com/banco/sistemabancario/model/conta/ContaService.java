package br.com.banco.sistemabancario.model.conta;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.cliente.TipoPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ChequeEspecialService chequeEspecialService;
    @Autowired
    private CartaoCreditoService cartaoCreditoService;
    @Autowired
    private FaixaScoreHelper faixaScoreHelper;

    @Value("${constants.conta.agencia}")
    private String agencia;

    public Conta criaOuAtualizaConta(final Cliente cliente) {
        Conta conta;
        if (contaRepository.existsContaByIdCliente(cliente.getId())) {
            conta = atualizaConta(cliente);
        } else {
            conta = criaConta(cliente);
        }

        Conta contaDb = contaRepository.save(conta);

        ChequeEspecial chequeEspecial = chequeEspecialService.criaOuAtualizaChequeEspecial(contaDb);
        contaDb.adicionaChequeEspecial(chequeEspecial);

        if (faixaScoreHelper.getFaixaByScore(conta.getScoreCliente()).geraCartaoCredito()) {
            CartaoCredito cartaoCredito = cartaoCreditoService.criaOuAtualizaCartaoCredito(contaDb);
            contaDb.adicionaCartaoCredito(cartaoCredito);
        }

        return contaRepository.save(contaDb);
    }

    private Conta criaConta(Cliente cliente) {
        return Conta.builder()
                .cliente(cliente)
                .numero(gerarNumeroConta())
                .agencia(agencia)
                .tipoConta(cliente.getTipoPessoa().equals(TipoPessoa.FISICA) ? TipoConta.CORRENTE : TipoConta.EMPRESARIAL)
                .build();
    }

    private Conta atualizaConta(final Cliente cliente) {
        Optional<Conta> optionalConta = contaRepository.findContaByIdCliente(cliente.getId());
        Conta conta = optionalConta.get();
        return Conta.builder()
                .id(conta.getId())
                .cliente(cliente)
                .numero(conta.getNumero())
                .agencia(agencia)
                .tipoConta(cliente.getTipoPessoa().equals(TipoPessoa.FISICA) ? TipoConta.CORRENTE : TipoConta.EMPRESARIAL)
                .build();
    }

    private String gerarNumeroConta() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
