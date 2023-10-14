INSERT INTO tb_users(id, name, email, username, role, password_hash)
VALUES ("0615fd18-87ec-4fbb-9a85-2477566c5874", "Joao Gabriel Carvalho", "emailtestejoao@gmail.com", "joaoteste123", "ROLE_ADMIN", "$2a$12$3GGiz4GbjsSRH0ZtpuJP5.WkJt1qLdXP79WRCTn67G6RJ0sllI26i");

INSERT INTO tb_user_info(id, user_agent, internet_protocol, locale, user_system_id)
VALUES ("1ee3c8ce-712e-444f-a214-9f050c126d3c", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/118.0", "127.0.0.1", "pt_BR", "0615fd18-87ec-4fbb-9a85-2477566c5874");

UPDATE tb_users
SET user_info_system_id = "1ee3c8ce-712e-444f-a214-9f050c126d3c"
WHERE id = "0615fd18-87ec-4fbb-9a85-2477566c5874";


