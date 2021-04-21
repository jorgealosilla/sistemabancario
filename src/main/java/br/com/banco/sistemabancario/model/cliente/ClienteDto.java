package br.com.banco.sistemabancario.model.cliente;

import br.com.banco.sistemabancario.api.util.AbstractRepresentationBuilder;
import lombok.Getter;
import lombok.Setter;
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
public class ClienteDto {
    private Long id;
    @Size(max = 200)
    private String nome;
    private TipoPessoa tipo;
    @Size(max = 11)
    private String cpf;
    @Size(max = 14)
    private String cnpj;
    @Size(max = 2)
    private int score;

    private ClienteDto() {
    }

    public static class Builder {

        private ClienteDto entityDto;

        private Builder() {
            entityDto = new ClienteDto();
        }

        public Builder id(Long id) {
            entityDto.setId(id);
            return this;
        }

        public Builder nome(String nome) {
            entityDto.setNome(nome);
            return this;
        }

        public Builder tipo(TipoPessoa tipoPessoa) {
            entityDto.setTipo(tipoPessoa);
            return this;
        }

        public Builder cpf(String cpf) {
            entityDto.setCpf(cpf);
            return this;
        }

        public Builder cnpj(String cnpj) {
            entityDto.setCnpj(cnpj);
            return this;
        }

        public Builder score(int score) {
            entityDto.setScore(score);
            return this;
        }

        public ClienteDto build() {
            return entityDto;
        }
    }

    @Service
    public static final class RepresentationBuilder extends AbstractRepresentationBuilder<Cliente, ClienteDto, Cliente.ClienteBuilder> {

        public static Builder create() {
            return new Builder();
        }
        
        @Override
        public Cliente fromRepresentation(final ClienteDto dto, final Cliente.ClienteBuilder builder) {
            int score = new Random().nextInt(11);
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
            return create()
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
