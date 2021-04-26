package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.api.util.AbstractRepresentationBuilder;
import br.com.banco.sistemabancario.model.conta.Conta;
import br.com.banco.sistemabancario.model.conta.TipoConta;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaDto {
    private Long id;

    @Size(min = 6, max = 6)
    private String numero;

    @NotNull
    private String agencia;

    @NotNull
    private TipoConta tipoConta;

    @NotNull
    private ClienteDto clienteDto;

    private ChequeEspecialDto chequeEspecialDto;

    private CartaoCreditoDto cartaoCreditoDto;

    @Component
    public static final class RepresentationBuilder extends AbstractRepresentationBuilder<Conta, ContaDto, Conta.ContaBuilder> {

        @Autowired
        ClienteDto.RepresentationBuilder clienteRepresentationBuilder;
        @Autowired
        ChequeEspecialDto.RepresentationBuilder chequeEspecialRepresentationBuilder;
        @Autowired
        CartaoCreditoDto.RepresentationBuilder cartaoCreditoRepresentationBuilder;

        @Override
        public Conta fromRepresentation(final ContaDto dto, final Conta.ContaBuilder builder) {
            throw new NotImplementedException();
        }

        @Override
        public ContaDto toRepresentation(final Conta entity) {
            return ContaDto.builder()
                    .id(entity.getId())
                    .clienteDto(clienteRepresentationBuilder.toRepresentation(entity.getCliente()))
                    .agencia(entity.getAgencia())
                    .numero(entity.getNumero())
                    .tipoConta(entity.getTipoConta())
                    .chequeEspecialDto(chequeEspecialRepresentationBuilder.toRepresentation(entity.getChequeEspecial()))
                    .cartaoCreditoDto(cartaoCreditoRepresentationBuilder.toRepresentation(entity.getCartaoCredito()))
                    .build();
        }
    }
}
