package br.com.banco.sistemabancario.model.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(final Cliente cliente) {
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
}
