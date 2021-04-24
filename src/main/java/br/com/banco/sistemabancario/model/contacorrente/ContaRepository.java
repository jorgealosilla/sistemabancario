package br.com.banco.sistemabancario.model.contacorrente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Long> {

    @Query(value = "select c from Conta c where c.cliente.id = :idCliente")
    Optional<Conta> findContaByIdCliente(final Long idCliente);

    @Query(value = "select count(*) > 0 from Conta c where c.cliente.id = :idCliente")
    boolean existisContaByIdCliente(final Long idCliente);
}
