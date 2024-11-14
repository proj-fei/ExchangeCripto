-- Inserindo um usuário admin
INSERT INTO users (name, cpf, password, isAdmin)
VALUES ('Admin User', '00000000000', '1234', 1);

-- Inserindo moedas na tabela currency
INSERT INTO currency (name, acronym, quotation, taxC, taxV)
VALUES 
    ('Bitcoin', 'BTC', 50000.000000, 2, 3),
    ('Ethereum', 'ETH', 3000.000000, 1, 2),
    ('Ripple', 'XRP', 1.000000, 1, 1);
