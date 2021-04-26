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

        contaRepository.save(conta);
        Conta contaDb = contaRepository.findById(conta.getId()).get();

        ChequeEspecial chequeEspecial = chequeEspecialService.criaOuAtualizaChequeEspecial(contaDb, cliente.getScore());
        contaDb.adicionaChequeEspecial(chequeEspecial);

        if (faixaScoreHelper.getFaixaByScore(cliente.getScore()).geraCartaoCredito()) {
            CartaoCredito cartaoCredito = cartaoCreditoService.criaOuAtualizaCartaoCredito(contaDb, cliente.getScore());
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
                .chequeEspecial(conta.getChequeEspecial())
                .cartaoCredito(conta.getCartaoCredito())
                .tipoConta(cliente.getTipoPessoa().equals(TipoPessoa.FISICA) ? TipoConta.CORRENTE : TipoConta.EMPRESARIAL)
                .build();
    }

    private String gerarNumeroConta() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public Iterable<Conta> findAll() {
        return contaRepository.findAll();
    }

    public Optional<Conta> findById(final Long id) {
        return contaRepository.findById(id);
    }
}
