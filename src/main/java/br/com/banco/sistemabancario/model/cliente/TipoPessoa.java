package br.com.banco.sistemabancario.model.cliente;

public enum TipoPessoa {
    FISICA("F", "Física"),
    JURIDICA("J", "Jurídica");

    private final String value;
    private final String description;

    TipoPessoa(final String value, final String description) {
        this.value = value;
        this.description = description;
    }
}
