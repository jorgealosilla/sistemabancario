package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.conta.CartaoCredito;
import br.com.banco.sistemabancario.model.conta.ChequeEspecial;
import br.com.banco.sistemabancario.model.conta.Conta;
import br.com.banco.sistemabancario.model.conta.TipoConta;
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
public class ContaDtoTest {


    @Autowired
    private ContaDto.RepresentationBuilder representationBuilder;

    @Test
    public void testToRepresentation() {
        final Long idCliente = 1L;

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getId()).thenReturn(idCliente);

        //atributos Conta
        final Long idConta = 2L;
        final String agencia = "7777";
        final String numero = "1234-5";
        final TipoConta tipoConta = TipoConta.CORRENTE;

        final Long idCartao = 3L;
        final CartaoCredito cartaoMock = mock(CartaoCredito.class);
        when(cartaoMock.getId()).thenReturn(idCartao);

        final Long idChequeEspecial = 4L;
        final ChequeEspecial chequeEspecialMock = mock(ChequeEspecial.class);
        when(chequeEspecialMock.getId()).thenReturn(idChequeEspecial);

        Conta contaMock = mock(Conta.class);
        when(contaMock.getId()).thenReturn(idConta);
        when(contaMock.getTipoConta()).thenReturn(tipoConta);
        when(contaMock.getAgencia()).thenReturn(agencia);
        when(contaMock.getNumero()).thenReturn(numero);
        when(contaMock.getCliente()).thenReturn(clienteMock);
        when(contaMock.getCartaoCredito()).thenReturn(cartaoMock);
        when(contaMock.getChequeEspecial()).thenReturn(chequeEspecialMock);

        ContaDto contaDto = representationBuilder.toRepresentation(contaMock);

        Assert.assertEquals(idCliente, contaDto.getClienteDto().getId());
        Assert.assertEquals(idCartao, contaDto.getCartaoCreditoDto().getId());
        Assert.assertEquals(idChequeEspecial, contaDto.getChequeEspecialDto().getId());
        Assert.assertEquals(tipoConta, contaDto.getTipoConta());
        Assert.assertEquals(agencia, contaDto.getAgencia());
        Assert.assertEquals(numero, contaDto.getNumero());
        Assert.assertEquals(idConta, contaDto.getId());

    }
}
