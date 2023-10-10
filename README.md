## Demanda – Versão 1.0.0

### Informações sobre a demanda

O objetivo será desenvolver um sistema de gerenciamento para estacionamento. Após levantamento
de requisitos junto ao cliente, listaremos as especificações a serem desenvolvidas pela equipe de
back-end. Importante salientar que o sistema será uma API Rest com autenticação por Json Web
Token (JWT).

        O sistema deverá possuir uma documentação dos recursos disponíveis pela API, para servir de apoio a equipe de front-end.

1. Requisitos e Configurações
- A API deverá ser configurada com o Timezone do país
- A API deverá ser configurada com o Locale do país
- A API deverá usar um sistema de auditoria
- Registrar a data de criação e última modificação dos registros
- Registrar o usuário que criou e o último que modificou um registro
- Configurar o acesso a uma base de dados para o ambiente de desenvolvimento


2. Requisitos / Usuários

- O usuário deverá ter um e-mail, o qual será usado como username (deve ser único)
- O usuário deverá ter uma senha de 6 caracteres
- O usuário deverá ter um perfil de administrador ou cliente.
- O usuário será criado sem a necessidade de autenticação.
- O usuário poderá ser localizado pelo identificador gerado.
- O administrador, quando autenticado, poderá recuperar qualquer usuário pelo id.
- O cliente, quando autenticado, poderá somente recuperar seus próprios dados de usuário.
- O usuário poderá alterar a senha
- Apenas quando estiver autenticado e somente o próprio usuário terá essa autorização.
- O administrador poderá listar todos os usuários quando estiver autenticado.
- Documentar todos os recursos criados.
- Realizar testes de integração do tipo ponto a ponto (end-to-end) dos recursos criados.


3. Requisitos / Autenticação

- Implementar um sistema de segurança e autenticação com JSON Web Token
- Documentar o recurso de autenticação.
- Realizar testes sobre o sistema de autenticação.


4. Requisitos / Clientes

- O cadastro de cliente só será possível depois de realizado o cadastro como usuário. 
- Um usuário poderá ter apenas um único cadastro como cliente e um cliente poderá estar vinculado a um único usuário.
- Todas as ações necessitam de autenticação
- O cadastro vai conter dados como nome completo
- O cadastro vai conter um cpf (deve ser único)
- O cadastro deve estar vinculado ao usuário.
- O cliente deverá realizar um cadastro com o nome completo e cpf.
- Apenas o cliente está autorizado.
- Um cliente poderá ser localizado pelo identificador gerado no cadastro.
- Apenas o administrador está autorizado.
- Um administrador poderá listar todos os clientes.
- O cliente poderá recuperar os próprios dados via token.
- Documentar todos os recursos criados.
- Realizar testes de integração do tipo ponto a ponto (end-to-end) dos recursos criados.


5. Requisitos / Vagas

- Todas ações requerem autenticação e são restritas ao administrador.
- Cada vaga deverá conter um código único que não deve ser o id.
- Cada vaga deverá conter um status de livre ou ocupada.
- Uma vaga poderá ser localizada pelo código.
- Documentar todos os recursos criados.
- Realizar testes de integração do tipo ponto a ponto (end-to-end) dos recursos criados.


6. Requisitos / Estacionamentos

- Recurso será responsável por controlar as entradas e saídas de veículos.
- Apenas clientes cadastrados podem estacionar.
- Para estacionar o cliente deve fornecer o CPF cadastrado.
- Os requisitos a seguir requerem autenticação e são restritos ao administrador
- Ao estacionar um veículo as seguintes informações devem ser armazenadas: placa, marca, modelo, cor, data de entrada, cpf.
- O processo de registro de um estacionamento deverá gerar um número de recibo único que será armazenado junto ao demais dados de entrada.
- Ao deixar o estacionamento as seguintes informações devem ser registradas: data de saída, valor do período estacionado e valor do desconto
- Uma vaga de estacionamento poderá ser ocupada por muitos veículos, desde que, não ao mesmo tempo.
- O desconto deve ser concedido sempre após o cliente completar 10 estacionamentos (entrada e saída).
- A porcentagem de desconto será de 30% sobre o valor a pagar do próximo estacionamento completado (entrada e saída)
- O estacionamento deverá ser vinculado a uma vaga e ao cliente proprietário do veículo.
- Ao retirar o veículo o cliente deverá informar o número do recibo gerado na data de entrada
   - Os requisitos a seguir requerem autenticação e são acessíveis pelo administrador e/ou cliente
   - Apenas um administrador poderá realizar a operação de check-in.
   - Apenas um administrador poderá realizar a operação de check-out.
   - Administrador e cliente possuem permissão para localizar os dados de entrada no estacionamento pelo número do recibo.
   - O administrador poderá listar os estacionamentos pelo CPF do cliente.
   - O cliente poderá listar apenas os seus próprios dados de estacionamentos.
   - O custo do estacionamento:
1. Primeiros 15 Minutos – R$ 5.00
2. Primeiros 60 Minutos – R$ 9.25
3. A partir dos primeiros 60 Minutos iniciais, inclua no custo de R$ 9.25 a cobrança adicional de R$ 1.75 para cada faixa de 15 Minutos adicionais aos primeiros 60 minutos


### Use a tabela de cálculos para entender o processo
           Tabela de cálculos (exemplos):
           Entrada Saída Valor
              01-03-2023 13:00 01-03-2023 13:15 R$ 5.00
              01-03-2023 13:00 01-03-2023 14:00 R$ 9,25
              01-03-2023 13:00 01-03-2023 14:30 R$ 12,75 (9,25 + 1,75 * 2)
              01-03-2023 13:00 01-03-2023 15:00 R$ 16,25 (9,25 + 1,75 * 4)
              01-03-2023 13:00 01-03-2023 15:10 R$ 18,00 (9,25 + 1,75 * 5)

- Realizar testes de integração do tipo ponto a ponto (end-to-end) dos recursos criados.
- Relatório em Pdf.
- O cliente poderá gerar um relatório em Pdf com sua lista de estacionamentos.