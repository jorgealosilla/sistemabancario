package br.com.banco.sistemabancario.api.dto;

import br.com.banco.sistemabancario.api.util.AbstractRepresentationBuilder;
import br.com.banco.sistemabancario.model.conta.CartaoCredito;
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
public class CartaoCreditoDto {

    private Long id;

    private BigDecimal limite;

    @Component
    public static final class RepresentationBuilder extends AbstractRepresentationBuilder<CartaoCredito, CartaoCreditoDto, CartaoCredito.CartaoCreditoBuilder> {

        @Override
        public CartaoCredito fromRepresentation(final CartaoCreditoDto dto, final CartaoCredito.CartaoCreditoBuilder builder) {
            throw new NotImplementedException();
        }

        @Override
        public CartaoCreditoDto toRepresentation(final CartaoCredito entity) {
            if(Objects.isNull(entity)){
                return null;
            }
            return CartaoCreditoDto.builder()
                    .id(entity.getId())
                    .limite(entity.getLimite())
                    .build();
        }
    }
}
