package br.com.banco.sistemabancario.model.conta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChequeEspecialRepository extends CrudRepository<ChequeEspecial, Long> {

    @Query(value = "select c from ChequeEspecial c where c.conta.id = :idConta")
    Optional<ChequeEspecial> findChequeEspecialByIdConta(final Long idConta);

    @Query(value = "select count(*) > 0 from ChequeEspecial c where c.conta.id = :idConta")
    boolean existsChequeEspecialByIdConta(final Long idConta);
}
