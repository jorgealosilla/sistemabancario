package br.com.banco.sistemabancario.model.cliente;

import br.com.banco.sistemabancario.model.conta.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaService contaService;

    public Cliente save(final Cliente cliente) {
        validate(cliente);
        Cliente c = clienteRepository.save(cliente);

        //TODO: Implementar criação da conta assincrona
        c.adicionaConta(contaService.criaOuAtualizaConta(c));
        return clienteRepository.save(c);
    }

    public Cliente update(final Cliente cliente) {
        validate(cliente);
        //TODO: Implementar criação da conta assincrona
        cliente.adicionaConta(contaService.criaOuAtualizaConta(cliente));
        return clienteRepository.save(cliente);
    }

    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(final Long id) {
        return clienteRepository.findById(id);
    }

    public void delete(final Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            clienteRepository.delete(optionalCliente.get());
        }
    }

    public void validate(final Cliente cliente) {

        if (cliente.getTipoPessoa().equals(TipoPessoa.FISICA)) {
            if (Objects.isNull(cliente.getCpf())) {
                throw new ValidationException("cpf deve ser informado");
            }
            if (Objects.nonNull(cliente.getCnpj())) {
                throw new ValidationException("cnpj é incompatível com o tipo de pessoa física");
            }
        } else if (cliente.getTipoPessoa().equals(TipoPessoa.JURIDICA)) {
            if (Objects.isNull(cliente.getCnpj())) {
                throw new ValidationException("cnpj deve ser informado");
            }
            if (Objects.nonNull(cliente.getCpf())) {

                throw new ValidationException("cpf é incompatível com o tipo de pessoa jurídica");
            }

        }
    }
}
