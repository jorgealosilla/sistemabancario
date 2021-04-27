package br.com.banco.sistemabancario.model.cliente;

import br.com.banco.sistemabancario.model.conta.Conta;
import br.com.banco.sistemabancario.model.conta.ContaService;
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

import javax.validation.ValidationException;
import java.util.Optional;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ContaService contaService;

    @Autowired
    private ClienteService service;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;
    @Captor
    private ArgumentCaptor<Cliente> clienteArgumentCaptor;

    @Test
    public void testSave() {

        final Long id = 1L;
        final String nome = "Jorge Alosilla";
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = "70406802033";
        final int score = 3;

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getId()).thenReturn(id);
        when(clienteMock.getNome()).thenReturn(nome);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCpf()).thenReturn(cpf);
        when(clienteMock.getScore()).thenReturn(score);

        when(clienteRepository.save(any())).thenReturn(clienteMock);

        Conta contaMock = mock(Conta.class);
        when(contaService.criaOuAtualizaConta(clienteMock)).thenReturn(contaMock);

        service.save(clienteMock);

        verify(clienteRepository, VerificationModeFactory.times(2)).save(any());

    }

    @Test
    public void testUpdate() {

        final Long id = 1L;
        final String nome = "Jorge Alosilla";
        final TipoPessoa tipoPessoa = TipoPessoa.JURIDICA;
        final String cnpj = "73720495000105";
        final int score = 3;

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getId()).thenReturn(id);
        when(clienteMock.getNome()).thenReturn(nome);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCnpj()).thenReturn(cnpj);
        when(clienteMock.getScore()).thenReturn(score);

        doCallRealMethod().when(clienteMock).adicionaConta(any());

        Assert.assertNull(clienteMock.getConta());

        when(clienteRepository.save(any())).thenReturn(clienteMock);

        Conta contaMock = mock(Conta.class);
        when(contaService.criaOuAtualizaConta(clienteMock)).thenReturn(contaMock);

        service.update(clienteMock);

        verify(clienteMock, VerificationModeFactory.times(1)).adicionaConta(contaMock);
        verify(clienteRepository, VerificationModeFactory.times(1)).save(any());
    }

    @Test
    public void testfindAll() {
        service.findAll();
        verify(clienteRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void testfindById() {
        final Long id = 1L;
        service.findById(id);
        verify(clienteRepository, VerificationModeFactory.times(1)).findById(longArgumentCaptor.capture());
        Assert.assertEquals(id, longArgumentCaptor.getValue());
    }

    @Test
    public void testDelete() {

        final Long id = 1L;
        Cliente cliente = mock(Cliente.class);
        when(cliente.getId()).thenReturn(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        service.delete(id);

        verify(clienteRepository, VerificationModeFactory.times(1)).findById(longArgumentCaptor.capture());
        verify(clienteRepository, VerificationModeFactory.times(1)).delete(clienteArgumentCaptor.capture());
        Assert.assertEquals(id, longArgumentCaptor.getValue());
        Assert.assertEquals(cliente, clienteArgumentCaptor.getValue());

    }


    @Test
    public void testValidatePFSemCpf() {
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = null;

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCpf()).thenReturn(cpf);

        try {
            service.validate(clienteMock);
            fail();
        } catch (ValidationException ve) {
            Assert.assertEquals("cpf deve ser informado", ve.getMessage());
        }
    }

    @Test
    public void testValidatePJSemCnpj() {
        final TipoPessoa tipoPessoa = TipoPessoa.JURIDICA;
        final String cnpj = null;

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCnpj()).thenReturn(cnpj);

        try {
            service.validate(clienteMock);
            fail();
        } catch (ValidationException ve) {
            Assert.assertEquals("cnpj deve ser informado", ve.getMessage());
        }
    }

    @Test
    public void testValidatePFComCpfECnpj() {
        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
        final String cpf = "91822074029";
        final String cnpj = "73720495000105";

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCpf()).thenReturn(cpf);
        when(clienteMock.getCnpj()).thenReturn(cnpj);

        try {
            service.validate(clienteMock);
            fail();
        } catch (ValidationException ve) {
            Assert.assertEquals("cnpj é incompatível com o tipo de pessoa física", ve.getMessage());
        }
    }

    @Test
    public void testValidatePJComCnpjECpf() {
        final TipoPessoa tipoPessoa = TipoPessoa.JURIDICA;
        final String cpf = "91822074029";
        final String cnpj = "73720495000105";

        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getTipoPessoa()).thenReturn(tipoPessoa);
        when(clienteMock.getCpf()).thenReturn(cpf);
        when(clienteMock.getCnpj()).thenReturn(cnpj);

        try {
            service.validate(clienteMock);
            fail();
        } catch (ValidationException ve) {
            Assert.assertEquals("cpf é incompatível com o tipo de pessoa jurídica", ve.getMessage());
        }
    }
}
