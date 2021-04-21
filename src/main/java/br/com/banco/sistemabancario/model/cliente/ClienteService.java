package br.com.banco.sistemabancario.model.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(final @Valid Cliente cliente) {
        validate(cliente);
        return clienteRepository.save(cliente);
    }

    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(final Long id) {
        return clienteRepository.findById(id);
    }

    public void deleteById(final Long id) {
        clienteRepository.deleteById(id);
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
