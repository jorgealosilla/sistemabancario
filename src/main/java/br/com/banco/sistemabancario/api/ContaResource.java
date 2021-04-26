package br.com.banco.sistemabancario.api;

import br.com.banco.sistemabancario.api.dto.ContaDto;
import br.com.banco.sistemabancario.model.conta.Conta;
import br.com.banco.sistemabancario.model.conta.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(value = "/contas")
public class ContaResource {
    @Autowired
    private ContaService contaService;
    @Autowired
    private ContaDto.RepresentationBuilder representationBuilder;

    @GetMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity find(@PathVariable(value = "id") final Long id) {
        Optional<Conta> optionalConta = contaService.findById(id);
        if (!optionalConta.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(optionalConta.get()));
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(contaService.findAll()));
    }
}
