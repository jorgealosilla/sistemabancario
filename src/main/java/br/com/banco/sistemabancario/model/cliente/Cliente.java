package br.com.banco.sistemabancario.model.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CLIENTES")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @NotNull
    @Size(max = 1)
    @Column(name = "TIPO_PESSOA", columnDefinition = "VARCHAR2", length = 1)
    private TipoPessoa tipoPessoa;

    @Column(name = "CPF", length = 11)
    @Size(min = 11, max = 11)
    private String cpf;

    @Column(name = "CNPJ", length = 14)
    @Size(min = 14, max = 14)
    private String cnpj;

    @Column(name = "SCORE", length = 1)
    @Size(max = 1)
    private int score;

    public static class BuilderUpdate extends ClienteBuilder {
        public ClienteBuilder from(Cliente cliente) {
            id(cliente.getId());
            return this;
        }

    }

}
