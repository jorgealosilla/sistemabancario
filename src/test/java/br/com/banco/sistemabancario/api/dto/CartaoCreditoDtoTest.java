package br.com.banco.sistemabancario.api.dto;


import br.com.banco.sistemabancario.model.conta.CartaoCredito;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartaoCreditoDtoTest {
    @Autowired
    private CartaoCreditoDto.RepresentationBuilder representationBuilder;

    @Test
    public void testToRepresentation() {
        final Long id = 1L;
        final BigDecimal limite = BigDecimal.TEN;

        final CartaoCredito cartaoCreditoMock = mock(CartaoCredito.class);
        when(cartaoCreditoMock.getId()).thenReturn(id);
        when(cartaoCreditoMock.getLimite()).thenReturn(limite);

        CartaoCreditoDto cartaoCreditoDto = representationBuilder.toRepresentation(cartaoCreditoMock);

        Assert.assertEquals(id, cartaoCreditoDto.getId());
        Assert.assertEquals(limite, cartaoCreditoDto.getLimite());
    }
}
