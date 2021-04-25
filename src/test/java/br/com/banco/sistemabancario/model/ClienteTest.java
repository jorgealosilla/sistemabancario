package br.com.banco.sistemabancario.model;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.cliente.TipoPessoa;
import org.junit.Assert;
import org.junit.Test;

public class ClienteTest {

    @Test
    public void shouldBuildClientePF() {
        final String nome = "Jorge Alosilla";
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = "70406802033";

        final Cliente cliente = Cliente.builder()
                .nome(nome)
                .tipoPessoa(tipoPessoa)
                .cpf(cpf)
                .build();

        Assert.assertNull(cliente.getId());
        Assert.assertNotNull(cliente.getScore());
        Assert.assertTrue(cliente.getScore() >= 0);
        Assert.assertTrue(cliente.getScore() <= 10);
        Assert.assertEquals(nome, cliente.getNome());
        Assert.assertEquals(tipoPessoa, cliente.getTipoPessoa());
        Assert.assertEquals(cpf, cliente.getCpf());

    }

    @Test
    public void shouldBuildClientePJ() {
        final String nome = "Empresa xpto";
        final TipoPessoa tipoPessoa = TipoPessoa.JURIDICA;
        final String cpf = "70406802033";

        final Cliente cliente = Cliente.builder()
                .nome(nome)
                .tipoPessoa(tipoPessoa)
                .cpf(cpf)
                .build();

        Assert.assertNull(cliente.getId());
        Assert.assertNotNull(cliente.getScore());
        Assert.assertTrue(cliente.getScore() >= 0);
        Assert.assertTrue(cliente.getScore() <= 10);
        Assert.assertEquals(nome, cliente.getNome());
        Assert.assertEquals(tipoPessoa, cliente.getTipoPessoa());
        Assert.assertEquals(cpf, cliente.getCpf());

    }
}
