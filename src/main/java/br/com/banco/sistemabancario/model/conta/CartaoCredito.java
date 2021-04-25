package br.com.banco.sistemabancario.model.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "CARTOES_CREDITOS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ID_CONTAS")
    private Conta conta;

    @Column(name = "LIMITE")
    private BigDecimal limite;
}
