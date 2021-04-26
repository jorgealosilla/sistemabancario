package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.api.util.AbstractRepresentationBuilder;
import br.com.banco.sistemabancario.model.cliente.Cliente;
import br.com.banco.sistemabancario.model.cliente.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Random;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Long id;
    @Size(max = 200)
    private String nome;
    private TipoPessoa tipo;
    @Size(min = 11, max = 11)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;
    @Size(min = 14, max = 14)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cnpj;

    private int score;

    @Service
    public static final class RepresentationBuilder extends AbstractRepresentationBuilder<Cliente, ClienteDto, Cliente.ClienteBuilder> {

        @Override
        public Cliente fromRepresentation(final ClienteDto dto, final Cliente.ClienteBuilder builder) {
            // TODO: Ajustar para não gerar novamente o scoreautomatico no update
            int score = new Random().nextInt(10);
            return builder
                    .nome(dto.getNome())
                    .tipoPessoa(dto.getTipo())
                    .cpf(dto.getCpf())
                    .cnpj(dto.getCnpj())
                    .score(score)
                    .build();
        }

        @Override
        public ClienteDto toRepresentation(final Cliente entity) {
            return ClienteDto.builder()
                    .id(entity.getId())
                    .nome(entity.getNome())
                    .tipo(entity.getTipoPessoa())
                    .cpf(entity.getCpf())
                    .cnpj(entity.getCnpj())
                    .score(entity.getScore())
                    .build();
        }
    }
}
