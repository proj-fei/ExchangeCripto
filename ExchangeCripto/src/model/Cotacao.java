package model;

import java.math.BigDecimal;

public interface Cotacao {
    void updateCotacao();
    BigDecimal calcularRealToCripto(BigDecimal value);
    BigDecimal calcularCriptoToReal(BigDecimal value);
}
