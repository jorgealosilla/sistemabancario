package br.com.banco.sistemabancario.model.conta;

public enum TipoConta {
    CORRENTE("C", "Conta Corrente"),
    EMPRESARIAL("E", "Conta Empresarial");

    private final String value;
    private final String description;

    TipoConta(final String value, final String description) {
        this.value = value;
        this.description = description;
    }
}
