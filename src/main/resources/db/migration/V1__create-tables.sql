
-- Tabela de Roles
CREATE TABLE tb_role (
    role_id BIGINT PRIMARY KEY,
    authority VARCHAR(255) NOT NULL
);

-- Tabela de Users
CREATE TABLE tb_user (
    user_id BINARY(16) PRIMARY KEY, -- UUID
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Tabela de associação User <-> Role (muitos-para-muitos)
CREATE TABLE tb_user_role (
    user_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES tb_role(role_id)
);

-- Tabela de Tweets
CREATE TABLE tweet (
    tweet_id BINARY(16) PRIMARY KEY, -- UUID
    user_id BINARY(16) NOT NULL,
    tweet_text VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    CONSTRAINT fk_tweet_user FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);
