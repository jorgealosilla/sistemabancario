package br.com.banco.sistemabancario.model.conta;

public enum FaixaScore {
    FAIXA0,
    FAIXA1,
    FAIXA2,
    FAIXA3,
    FAIXA4;

    public boolean geraCartaoCredito() {
        return !this.equals(FAIXA0) && !this.equals(FAIXA1);
    }

    public boolean ativaLimiteChequeEspecial() {
        return !this.equals(FAIXA0) && !this.equals(FAIXA1);
    }
}
