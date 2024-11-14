package model;

import java.math.BigDecimal;

public interface Tarifa {
    BigDecimal taxarCompra(BigDecimal value);
    BigDecimal taxarVenda(BigDecimal value);
}
