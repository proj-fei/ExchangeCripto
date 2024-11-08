package model;

import java.math.BigDecimal;

public class Bitcoin extends Coin {

    public Bitcoin(int id, int taxVenda, int taxCompra, String name, String acronym, BigDecimal quotation, BigDecimal balance) {
        super(id, taxVenda, taxCompra, name, acronym, quotation, balance);
    }
    
}

// As classe de Bitcoin, Ehtereum e Ripple mantém a mesma estrutura de Coin e não possuem diferença, já que no projeto
// utilizamos polimorfismo tratamos todas as moedas de forma genérica então elas são classes para representar as principais
// moedas do sistema.