//package br.com.banco.sistemabancario.model;
//
//import br.com.banco.sistemabancario.api.ClienteResource;
//import br.com.banco.sistemabancario.api.dto.ClienteDto;
//import br.com.banco.sistemabancario.model.cliente.Cliente;
//import br.com.banco.sistemabancario.model.cliente.ClienteService;
//import br.com.banco.sistemabancario.model.cliente.TipoPessoa;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.xml.ws.Response;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = ClienteResource.class)
//@AutoConfigureMockMvc
//public class ClienteResourceTest {

//    @Autowired
//    private ClienteResource clienteResource;
//    @Autowired
//    private MockMvc mockMvc;

//    @MockBean
//    private ClienteService clienteService;
//    @MockBean
//    private ClienteDto.RepresentationBuilder representationBuilder;
//
//    private ClienteDto createEntity(){
//        final String nome = "Jorge Alosilla";
//        final TipoPessoa tipoPessoa = TipoPessoa.FISICA;
//        final String cpf = "70406802033";
//        final String cnpj = "70406802033";
//
//        ClienteDto dto = ClienteDto.builder()
//                .nome(nome)
//                .tipo(tipoPessoa)
//                .cpf(cpf)
//                .cnpj(cnpj)
//                .build();
//
//        return dto;
//    }
//
//    @Test
//    public void shouldCreateEntity() throws Exception {
//        ResponseEntity responseEntity = clienteResource.create(createEntity());
//
//        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        Assert.assertTrue(responseEntity.getBody() instanceof ClienteDto);
//    }
//
//    @Test
//    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
//        String cliente = "  {\n" +
//                "    \"nome\": \"Southsystem\",\n" +
//                "    \"tipo\": \"JURIDICA\",\n" +
//                "    \"cnpj\": \"79316368000009\"\n" +
//                "  }";
//        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
//                .content(cliente)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//    }
//}
