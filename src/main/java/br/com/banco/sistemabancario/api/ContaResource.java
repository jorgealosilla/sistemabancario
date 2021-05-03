package br.com.banco.sistemabancario.api;

import br.com.banco.sistemabancario.api.dto.ContaDto;
import br.com.banco.sistemabancario.model.conta.Conta;
import br.com.banco.sistemabancario.model.conta.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = {"Contas dos clientes"})
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "O id informado não foi encontrado"),
        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado"),
})
public class ContaResource {
    @Autowired
    private ContaService contaService;
    @Autowired
    private ContaDto.RepresentationBuilder representationBuilder;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca a conta pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta pelo id informado")
    })
    public @ResponseBody
    ResponseEntity find(@PathVariable(value = "id") final Long id) {
        Optional<Conta> optionalConta = contaService.findById(id);
        if (!optionalConta.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(optionalConta.get()));
    }

    @GetMapping
    @ApiOperation(value = "Busca todas as contas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de contas")
    })
    public @ResponseBody
    ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(representationBuilder.toRepresentation(contaService.findAll()));
    }
}
