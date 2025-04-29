package ar.edu.mercadogratis.domain.model.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

public class Precio {
    private final BigDecimal valor;

    public Precio(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo ni nulo.");
        }
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Precio precio = (Precio) o;
        return Objects.equals(valor, precio.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
