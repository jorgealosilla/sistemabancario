package br.com.banco.sistemabancario.model.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "CHEQUES_ESPECIAIS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChequeEspecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ID_CONTAS")
    private Conta conta;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @Column(name = "LIMITE")
    private BigDecimal limite;
}
