package br.com.banco.sistemabancario.api;

import br.com.banco.sistemabancario.api.dto.ClienteDto;
import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.cliente.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/clientes")
@Api(tags = {"Cadastro de Clientes"})
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "O id informado não foi encontrado"),
        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado"),
})
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteDto.RepresentationBuilder representationBuilder;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca o cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente do id informado")
    })
    public @ResponseBody
    ResponseEntity find(@PathVariable(value = "id") final Long id) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        if (!optionalCliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(optionalCliente.get()));
    }

    @GetMapping
    @ApiOperation(value = "Busca todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes")
    })
    public @ResponseBody
    ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(clienteService.findAll()));
    }

    @PostMapping
    @ApiOperation(value = "Cadastra o cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o cliente cadastrado")
    })
    public @ResponseBody
    ResponseEntity create(@RequestBody @Valid final ClienteDto dto) {
        Cliente cliente = representationBuilder.fromRepresentation(dto, Cliente.builder());
        clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(representationBuilder.toRepresentation(cliente));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza o cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente atualizado")
    })
    public @ResponseBody
    ResponseEntity update(@PathVariable(value = "id") final Long id, @RequestBody final ClienteDto dto) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        if (!optionalCliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Cliente cliente = representationBuilder.fromRepresentation(dto, Cliente.builder().id(id));
        cliente = clienteService.update(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Exclui o cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "O cliente foi excluído")
    })
    public ResponseEntity delete(@PathVariable(value = "id") final Long id) {
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
