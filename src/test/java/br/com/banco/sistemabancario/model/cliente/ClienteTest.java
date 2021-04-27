package br.com.banco.sistemabancario.model.cliente;

import br.com.banco.sistemabancario.model.conta.Conta;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ClienteTest {

    @Test
    public void shouldBuild() {
        final String nome = "Jorge Alosilla";
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = "70406802033";
        final String cnpj = "70406802033";

        final Cliente cliente = Cliente.builder()
                .nome(nome)
                .tipoPessoa(tipoPessoa)
                .cpf(cpf)
                .cnpj(cnpj)
                .build();

        Assert.assertNull(cliente.getId());
        Assert.assertNotNull(cliente.getScore());
        Assert.assertTrue(cliente.getScore() >= 0);
        Assert.assertTrue(cliente.getScore() <= 10);
        Assert.assertEquals(nome, cliente.getNome());
        Assert.assertEquals(tipoPessoa, cliente.getTipoPessoa());
        Assert.assertEquals(cpf, cliente.getCpf());
        Assert.assertEquals(cnpj, cliente.getCnpj());

        final Conta conta = mock(Conta.class);
        cliente.adicionaConta(conta);

        Assert.assertEquals(cliente.getConta(), conta);
    }


}
