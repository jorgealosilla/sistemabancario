package br.com.banco.sistemabancario.model.contacorrente;

import br.com.banco.sistemabancario.model.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONTAS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 6, max = 6)
    @Column(name = "NUMERO", length = 6, nullable = false)
    private String numero;

    @NotNull
    @Column(name = "AGENCIA")
    private String agencia;

    @NotNull
    @Column(name = "TIPO_CONTA")
    private TipoConta tipoConta;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ID_CLIENTES")
    private Cliente cliente;

}