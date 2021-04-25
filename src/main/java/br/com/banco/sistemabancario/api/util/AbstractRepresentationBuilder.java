package br.com.banco.sistemabancario.api.util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class AbstractRepresentationBuilder<T, DTO, B> {

    protected abstract T fromRepresentation(DTO dto, B builder);

    protected abstract DTO toRepresentation(T t);

    public List<DTO> toRepresentation(Iterable<T> lista) {
        final List<DTO> listaDto = new ArrayList<>();
        lista.forEach(registro -> {
            listaDto.add(this.toRepresentation(registro));
        });
        return listaDto;
    }
}
