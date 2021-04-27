package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.cliente.TipoPessoa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteDtoTest {


    @Autowired
    private ClienteDto.RepresentationBuilder representationBuilder;

    @Test
    public void testFromRepresentation() {
        final String nome = "Jorge Alosilla";
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = "70406802033";
        final String cnpj = "70406802033";

        ClienteDto dto = ClienteDto.builder()
                .nome(nome)
                .tipo(tipoPessoa)
                .cpf(cpf)
                .cnpj(cnpj)
                .build();

        Cliente cliente = representationBuilder.fromRepresentation(dto, Cliente.builder());

        Assert.assertNull(cliente.getId());
        Assert.assertEquals(nome, cliente.getNome());
        Assert.assertEquals(tipoPessoa, cliente.getTipoPessoa());
        Assert.assertEquals(cpf, cliente.getCpf());
        Assert.assertEquals(cnpj, cliente.getCnpj());
    }


    @Test
    public void testToRepresentation() {
        final Long id = 1L;
        final String nome = "Jorge Alosilla";
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = "70406802033";
        final String cnpj = "70406802033";
        final int score = 3;

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getId()).thenReturn(id);
        when(clienteMock.getNome()).thenReturn(nome);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCpf()).thenReturn(cpf);
        when(clienteMock.getCnpj()).thenReturn(cnpj);
        when(clienteMock.getScore()).thenReturn(score);

        ClienteDto clienteDto = representationBuilder.toRepresentation(clienteMock);

        Assert.assertEquals(id, clienteDto.getId());
        Assert.assertEquals(nome, clienteDto.getNome());
        Assert.assertEquals(tipoPessoa, clienteDto.getTipo());
        Assert.assertEquals(cpf, clienteDto.getCpf());
        Assert.assertEquals(cnpj, clienteDto.getCnpj());
        Assert.assertEquals(score, clienteDto.getScore());

    }
}
