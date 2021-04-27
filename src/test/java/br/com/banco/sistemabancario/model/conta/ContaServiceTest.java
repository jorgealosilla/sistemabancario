package br.com.banco.sistemabancario.model.conta;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.cliente.TipoPessoa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContaServiceTest {

    @MockBean
    private ContaRepository contaRepository;
    @MockBean
    private ChequeEspecialService chequeEspecialService;
    @MockBean
    private CartaoCreditoService cartaoCreditoService;

    @Autowired
    private ContaService service;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;
    @Captor
    private ArgumentCaptor<Conta> contaArgumentCaptor;

    @Test
    public void testSave() {

        final int scoreCliente = 6;

        Cliente cliente = mock(Cliente.class);
        when(cliente.getScore()).thenReturn(scoreCliente);
        when(cliente.getTipoPessoa()).thenReturn(TipoPessoa.FISICA);

        Conta contaMock = mock(Conta.class);

        when(contaRepository.save(any())).thenReturn(contaMock);
        when(contaRepository.existsContaByIdCliente(any())).thenReturn(false);
        when(contaRepository.findById(any())).thenReturn(Optional.of(contaMock));

        ChequeEspecial chequeEspecialMock = mock(ChequeEspecial.class);
        when(chequeEspecialService.criaOuAtualizaChequeEspecial(contaMock, scoreCliente)).thenReturn(chequeEspecialMock);

        Conta conta = service.criaOuAtualizaConta(cliente);

        verify(contaRepository, VerificationModeFactory.times(2)).save(any());
        verify(contaRepository, VerificationModeFactory.times(1)).existsContaByIdCliente(any());
        verify(chequeEspecialService, VerificationModeFactory.times(1)).criaOuAtualizaChequeEspecial(any(), anyInt());
        verify(cartaoCreditoService, VerificationModeFactory.times(1)).criaOuAtualizaCartaoCredito(any(), anyInt());

    }

    @Test
    public void testUpdate() {

        final int scoreCliente = 1;

        Cliente cliente = mock(Cliente.class);
        when(cliente.getScore()).thenReturn(scoreCliente);
        when(cliente.getTipoPessoa()).thenReturn(TipoPessoa.FISICA);

        Conta contaMock = mock(Conta.class, RETURNS_MOCKS);

        when(contaRepository.existsContaByIdCliente(any())).thenReturn(true);
        when(contaRepository.findContaByIdCliente(any())).thenReturn(Optional.of(contaMock));
        when(contaRepository.save(any())).thenReturn(contaMock);
        when(contaRepository.findById(any())).thenReturn(Optional.of(contaMock));

        ChequeEspecial chequeEspecialMock = mock(ChequeEspecial.class);
        when(chequeEspecialService.criaOuAtualizaChequeEspecial(contaMock, scoreCliente)).thenReturn(chequeEspecialMock);

        service.criaOuAtualizaConta(cliente);

        verify(contaRepository, VerificationModeFactory.times(2)).save(any());
        verify(contaRepository, VerificationModeFactory.times(1)).existsContaByIdCliente(anyLong());
        verify(chequeEspecialService, VerificationModeFactory.times(1)).criaOuAtualizaChequeEspecial(any(), anyInt());
        verifyNoInteractions(cartaoCreditoService);

    }

    @Test
    public void testfindAll() {
        service.findAll();
        verify(contaRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void testfindById() {
        final Long id = 1L;
        service.findById(id);
        verify(contaRepository, VerificationModeFactory.times(1)).findById(longArgumentCaptor.capture());
        Assert.assertEquals(id, longArgumentCaptor.getValue());
    }
}
