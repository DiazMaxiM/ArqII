package ar.edu.mercadogratis.domain.model.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String valor;
    private static final Pattern PATTERN = Pattern.compile("^\\S+@\\S+\\.\\S+$");

    public Email(String valor) {
        if (valor == null || !PATTERN.matcher(valor).matches()) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(valor, email.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
