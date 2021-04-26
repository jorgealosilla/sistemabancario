package br.com.banco.sistemabancario.api;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.api.dto.ClienteDto;
import br.com.banco.sistemabancario.model.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteDto.RepresentationBuilder representationBuilder;

    @GetMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity find(@PathVariable(value = "id") final Long id) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        if (!optionalCliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(optionalCliente.get()));
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(clienteService.findAll()));
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity create(@RequestBody @Valid final ClienteDto dto) {
        Cliente cliente = representationBuilder.fromRepresentation(dto, Cliente.builder());
        clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(representationBuilder.toRepresentation(cliente));
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity create(@PathVariable(value = "id") final Long id, @RequestBody final ClienteDto dto) {
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        if (!optionalCliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Cliente cliente = representationBuilder.fromRepresentation(dto, Cliente.builder().id(id));
        cliente = clienteService.update(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") final Long id) {
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
