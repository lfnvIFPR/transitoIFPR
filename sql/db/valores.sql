-- Crie o usuário java;
-- Cria valores iniciais;

CREATE USER IF NOT EXISTS 'java'@'localhost' IDENTIFIED BY 'javaConnector';
GRANT ALL PRIVILEGES ON transito.* TO 'java'@'localhost';
FLUSH PRIVILEGES;

INSERT IGNORE INTO administrador (id_admin, ultima_data_atividade, hash_senha, salt)
VALUES
    -- Senhas:
    -- 001 => OlaMundo
    -- 002 => senhasenha
    -- 003 => testando123
    -- 004 => =!Simbolos!=

    ('A000000000000001', CURRENT_TIMESTAMP, '244fd056d280e03fd2513f83618f9ad2ed015adf53accaab883147b57d7d8149', '11111111'),
    ('A000000000000002', CURRENT_TIMESTAMP, '5d20e6390471756538b90d3a92b0504410d8811f8bf95e3536c50b9a5c78d76e', '22222222'),
    ('A000000000000003', CURRENT_TIMESTAMP, 'a9b20f6a915e63627fc0a2ef8e643dde629aafb0b7832263d04d4c599773e548', 'abcdefgh'),
    ('A000000000000004', CURRENT_TIMESTAMP, '710935108c1524469b87d7927f8c24452b6a86108dd2040af7675b606292eb67', '12345678');

INSERT IGNORE INTO agente_fiscalizador (id_agente, nome, ultima_data_atividade, hash_senha, salt)
VALUES
    -- Senhas:
    -- 001 => fiscal1
    -- 002 => !=##$%54
    ('A000000000000001', 'Agente Fiscal 1', CURRENT_TIMESTAMP, 'a4d394f43361e834ee5c0149c7be89d1dc972b12e84c22cb1ad0f0dec5b2118b', '11111111'),
    ('A000000000000002', 'Radar Automático', CURRENT_TIMESTAMP, '369c78ff678fca74d8bea93397e58e509588020d9d270b9e64feee05e6999d70', '22222222');

INSERT IGNORE INTO condutor (numero_CNH, data_cnh, data_nascimento, ultima_data_atividade, hash_senha, salt)
VALUES
    -- Senhas:
    -- 123... => senha
    -- 987... => !
    -- 0011.. => hahahehe
    -- 70..07 => numDaSorte
    ('123456789', CURRENT_DATE, {d'2001-03-11'}, CURRENT_TIMESTAMP, 'b0c518b1f728db6f5c7b1d3b75effc87c5019f6de25a2017a117ea511e770ed5', '11111111'),
    ('987654321', CURRENT_DATE, {d'1989-09-01'}, CURRENT_TIMESTAMP, 'ee6f2068ccc8deb3d23b4fc13222f0b8bfaccc2983112e6e032270f36b6ca52b', '22222222'),
    ('001122334', CURRENT_DATE, {d'1974-08-16'}, CURRENT_TIMESTAMP, 'd471916374c79801334656436e108af83e19abe9c3ff07342b71561376e4ea85', 'abcdefgh'),
    ('700000007', CURRENT_DATE, {d'2004-01-30'}, CURRENT_TIMESTAMP, '2f57edb32869d05198a34ee7d8033786187661c61b5c58186d331a4d417be17c', '12345678');
