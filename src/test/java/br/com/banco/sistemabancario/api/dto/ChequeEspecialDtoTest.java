package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.model.conta.ChequeEspecial;
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
public class ChequeEspecialDtoTest {

    @Autowired
    private ChequeEspecialDto.RepresentationBuilder representationBuilder;

    @Test
    public void testToRepresentation() {
        final Long id = 1L;
        final boolean ativo = true;
        final BigDecimal limite = BigDecimal.TEN;

        final ChequeEspecial chequeEspecialMock = mock(ChequeEspecial.class);
        when(chequeEspecialMock.getId()).thenReturn(id);
        when(chequeEspecialMock.getAtivo()).thenReturn(ativo);
        when(chequeEspecialMock.getLimite()).thenReturn(limite);

        ChequeEspecialDto chequeEspecialDto = representationBuilder.toRepresentation(chequeEspecialMock);

        Assert.assertEquals(id, chequeEspecialDto.getId());
        Assert.assertEquals(ativo, chequeEspecialDto.getAtivo());
        Assert.assertEquals(limite, chequeEspecialDto.getLimite());
    }
}
