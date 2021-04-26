package br.com.banco.sistemabancario.model.conta;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conta", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private ChequeEspecial chequeEspecial;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conta", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private CartaoCredito cartaoCredito;

    public void adicionaChequeEspecial(ChequeEspecial chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public void adicionaCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }
}