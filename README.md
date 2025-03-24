![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)  

# Desafio Resolvido - Itaú Unibanco - Desafio de Programação

Objetivo:
Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos.  
A API foi desenvolvida com Java e Spring Boot.

Desafio resolvido em aula da Javanauta aqui neste [Video](https://www.youtube.com/watch?v=9xrx1pxZEGU&t=127s).   
Proposta completa do desafio pode ser encontrada [aqui](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior).   

Desenvolvimento complementar [aqui](https://www.youtube.com/watch?v=jEUdgT-qpKo).   
Desevolvido neste:
- Testes
- Tratamento de erros com GlobalExceptionHandler
- Observabilidade

##  Como Configurar o Projeto

1. Clone o Repositório

2. Compile o Projeto

```bash
 mvn clean install
```

3. Execute o Projeto

```bash
mvn spring-boot:run
```
4. Como Rodar em um Container (Opcional)

4.1. Crie a Imagem Docker
Certifique-se de que o Docker está instalado e execute:

```bash
docker build -t api-transacoes 
```

4.2. Execute o Container

```bash
docker run -p 8080:8080 api-transacoes
```

### 2.2. Endpoints da API

A seguir os endpoints que estão presentes na API e a funcionalidade esperada de cada um deles.  
Disponível o Swagger após executar o projeto em 'http://localhost:8080/swagger-ui.html'

#### 2.2.1. Receber Transações: `POST /transacao`

Este é o endpoint que recebe as Transações. Cada transação consiste de um `valor` e uma `dataHora` de quando ela aconteceu:

```json
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

Os campos no JSON acima significam o seguinte:

| Campo      | Significado                                                   | Obrigatório? |
|------------|---------------------------------------------------------------|--------------|
| `valor`    | **Valor em decimal com ponto flutuante** da transação         | Sim          |
| `dataHora` | **Data/Hora no padrão ISO 8601** em que a transação aconteceu | Sim          |


A API só aceitará transações que:

1. Tenham os campos `valor` e `dataHora` preenchidos
2. A transação **NÃO DEVE** acontecer no futuro
3. A transação **DEVE** ter acontecido a qualquer momento no passado
4. A transação **NÃO DEVE** ter valor negativo
5. A transação **DEVE** ter valor igual ou maior que `0` (zero)

Como resposta, espera-se que este endpoint responda com:

- `201 Created` sem nenhum corpo
  - A transação foi aceita (ou seja foi validada, está válida e foi registrada)
- `422 Unprocessable Entity` sem nenhum corpo
  - A transação **não** foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que `0`)
- `400 Bad Request` sem nenhum corpo
  - A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)

#### 2.2.2. Limpar Transações: `DELETE /transacao`

Este endpoint simplesmente **apaga todos os dados de transações** que estejam armazenados.

Como resposta, espera-se que este endpoint responda com:

- `200 OK` sem nenhum corpo
  - Todas as informações foram apagadas com sucesso

#### 2.2.3. Calcular Estatísticas: `GET /estatistica`

Este endpoint deve retornar estatísticas das transações que **aconteceram nos últimos 60 segundos (1 minuto)**. As estatísticas que devem ser calculadas são:

```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```

Os campos no JSON acima significam o seguinte:

|  Campo  | Significado                                                   | Obrigatório? |
|---------|---------------------------------------------------------------|--------------|
| `count` | **Quantidade de transações** nos últimos 60 segundos          | Sim          |
| `sum`   | **Soma total do valor** transacionado nos últimos 60 segundos | Sim          |
| `avg`   | **Média do valor** transacionado nos últimos 60 segundos      | Sim          |
| `min`   | **Menor valor** transacionado nos últimos 60 segundos         | Sim          |
| `max`   | **Maior valor** transacionado nos últimos 60 segundos         | Sim          |

Como resposta, espera-se que este endpoint responda com:

- `200 OK` com os dados das estatísticas
  - Um JSON com os campos `count`, `sum`, `avg`, `min` e `max` todos preenchidos com seus respectivos valores
  - **Atenção!** Quando não houverem transações nos últimos 60 segundos considere todos os valores como `0` (zero)

![image](https://github.com/user-attachments/assets/af281441-7205-4126-8623-032397a8d17b)
