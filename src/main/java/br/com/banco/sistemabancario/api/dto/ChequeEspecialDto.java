package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.api.util.AbstractRepresentationBuilder;
import br.com.banco.sistemabancario.model.conta.ChequeEspecial;
import lombok.*;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChequeEspecialDto {

    private Long id;

    private Boolean ativo;

    private BigDecimal limite;

    @Component
    public static final class RepresentationBuilder extends AbstractRepresentationBuilder<ChequeEspecial, ChequeEspecialDto, ChequeEspecial.ChequeEspecialBuilder> {

        @Override
        public ChequeEspecial fromRepresentation(final ChequeEspecialDto dto, final ChequeEspecial.ChequeEspecialBuilder builder) {
            throw new NotImplementedException();
        }

        @Override
        public ChequeEspecialDto toRepresentation(final ChequeEspecial entity) {
            if (Objects.isNull(entity)) {
                return null;
            }
            return ChequeEspecialDto.builder()
                    .id(entity.getId())
                    .ativo(entity.getAtivo())
                    .limite(entity.getLimite())
                    .build();
        }
    }
}
