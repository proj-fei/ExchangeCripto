-- Ativa a extensão pgcrypto para permitir o uso de gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,                              -- Identificador único gerado automaticamente
    name VARCHAR(100) NOT NULL,                        -- Nome do usuário
    cpf VARCHAR(11) NOT NULL UNIQUE,                   -- CPF (único)
    password VARCHAR(255) NOT NULL,                    -- Senha
    isAdmin SMALLINT DEFAULT 0                         -- Admin 0 = False, 1 = True
);

CREATE TABLE wallets (
    id SERIAL PRIMARY KEY,                              -- Identificador único gerado automaticamente
    balance NUMERIC(15, 2) DEFAULT 0,                  -- Saldo em dinheiro (real)
    userId INTEGER,                                    -- Chave estrangeira para Users
    CONSTRAINT fk_user_wallet FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE currency (
    id SERIAL PRIMARY KEY,                              -- Identificador único gerado automaticamente
    name VARCHAR(50) NOT NULL,                         -- Nome da moeda (ex: BTC, ETH, XRP)
    acronym VARCHAR(10) NOT NULL,                      -- Sigla da moeda (ex: BTC, ETH, XRP)
    quotation NUMERIC(15, 6) NOT NULL,                 -- Cotação da moeda
    taxC INTEGER NOT NULL,                             -- Taxa de compra
    taxV INTEGER NOT NULL                              -- Taxa de venda
);

CREATE TABLE wallet_currency_balances (
    id SERIAL PRIMARY KEY,                              -- Identificador único gerado automaticamente
    walletId INTEGER NOT NULL,                         -- Chave estrangeira para Wallets
    currencyId INTEGER NOT NULL,                       -- Chave estrangeira para Currency
    balance NUMERIC(15, 6) DEFAULT 0,                  -- Saldo da moeda específica na carteira
    CONSTRAINT fk_wallet_currency FOREIGN KEY (walletId) REFERENCES wallets(id) ON DELETE CASCADE,  -- Se a wallet for excluída, os saldos de moedas também serão excluídos
    CONSTRAINT fk_currency FOREIGN KEY (currencyId) REFERENCES currency(id) ON DELETE CASCADE       -- Se a moeda for excluída, os saldos relacionados também serão excluídos
);

CREATE TABLE extracts (
    id SERIAL PRIMARY KEY,                              -- Identificador único gerado automaticamente
    operation VARCHAR(50) NOT NULL,                    -- Operação
    value NUMERIC(15, 6) NOT NULL,                     -- Valor da operação
    tax NUMERIC(15, 2),                                -- Taxa associada à operação
    quotation NUMERIC(15, 6),                          -- Cotação da moeda no momento da operação
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- Data e hora da operação
    currencyId INTEGER NOT NULL,                        -- Chave estrangeira para Currency
    walletId INTEGER NOT NULL,                          -- Chave estrangeira para Wallets
    CONSTRAINT fk_currency_extract FOREIGN KEY (currencyId) REFERENCES currency(id) ON DELETE CASCADE,   -- Se a moeda for excluída, o extrato será excluído
    CONSTRAINT fk_wallet_extract FOREIGN KEY (walletId) REFERENCES wallets(id) ON DELETE CASCADE         -- Se a wallet for excluída, o extrato será excluído
);

CREATE TABLE quote_history (
    id SERIAL PRIMARY KEY,                             -- Identificador único gerado automaticamente
    value NUMERIC(15, 6) NOT NULL,                     -- Valor da moeda
    quotation NUMERIC(15, 6),                          -- Cotação da moeda no momento da operação
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- Data e hora da operação
    currencyId INTEGER NOT NULL,                       -- Chave estrangeira para Currency
    CONSTRAINT fk_currency_extract FOREIGN KEY (currencyId) REFERENCES currency(id) ON DELETE cascade   -- Se a moeda for excluída, o histórico será excluído
)