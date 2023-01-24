# CompraProd
Api de compra de produto

Para Efetuar Uma compra  cadastre um usuario

```bash
--Usuario '{
  "nome": "string",
  "cpf": "string",
  "email": "string"
}'
```
 e um produto 
 
 ```bash
--Produto'{
  "nomeProduto": "string",
  "quantidade": 0,
  "preco": 0,
  "marca": "string"
}'
```

depois em v1/venda digite o número de id do usuario e id do produto que foram cadastrados como mostra o exemplo abaixo

```bash
--Venda '{
  "usuarioId": 1,
  "produtoId": 1
}'
```

Toda vez que um produto é comprado sua quantidade é abaixada chegando a 0 o sistema solta uma exceção.

Link para o Swagger: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
  
Link para o console do H2: http://localhost:8080/h2-console/login.jsp?jsessionid=b0e55238a7e4f90c6cc2c9569e248eb2

Usuario: sa
Senha: 


Links importantes: 
Para gerar o CPF: https://www.4devs.com.br/gerador_de_cpf
