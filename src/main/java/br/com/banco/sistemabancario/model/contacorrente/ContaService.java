package br.com.banco.sistemabancario.model.contacorrente;

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

    @Value("${constants.conta.agencia}")
    private String agencia;


    public Conta criaOuAtualizaConta(final Cliente cliente) {
        if (contaRepository.existisContaByIdCliente(cliente.getId()))
            return atualizaConta(cliente);
        return criaConta(cliente);
    }

    private Conta criaConta(Cliente cliente) {
        Conta conta = Conta.builder()
                .cliente(cliente)
                .numero(gerarNumeroConta())
                .agencia(agencia)
                .tipoConta(cliente.getTipoPessoa().equals(TipoPessoa.FISICA) ? TipoConta.CORRENTE : TipoConta.EMPRESARIAL)
                .build();
        return contaRepository.save(conta);
    }

    private Conta atualizaConta(final Cliente cliente) {
        Optional<Conta> optionalConta = contaRepository.findContaByIdCliente(cliente.getId());
        Conta conta = optionalConta.get();
        return contaRepository.save(
                Conta.builder()
                        .id(conta.getId())
                        .cliente(cliente)
                        .numero(conta.getNumero())
                        .agencia(agencia)
                        .tipoConta(cliente.getTipoPessoa().equals(TipoPessoa.FISICA) ? TipoConta.CORRENTE : TipoConta.EMPRESARIAL)
                        .build()
        );
    }

    private String gerarNumeroConta() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
