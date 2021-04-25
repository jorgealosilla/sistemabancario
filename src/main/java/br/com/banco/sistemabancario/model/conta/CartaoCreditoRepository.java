package br.com.banco.sistemabancario.model.conta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, Long> {

    @Query(value = "select c from CartaoCredito c where c.conta.id = :idConta")
    Optional<CartaoCredito> findCartaoCreditoByIdConta(final Long idConta);

    @Query(value = "select count(*) > 0 from CartaoCredito c where c.conta.id = :idConta")
    boolean existsCartaoCreditoByIdConta(final Long idConta);
}
