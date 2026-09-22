# Teste prático de Java — Iniflex

Projeto desenvolvido para o teste prático de programação da Iniflex. O programa cadastra os funcionários informados na tabela do enunciado e demonstra operações com listas, herança, `LocalDate`, `BigDecimal`, streams e agrupamento em `Map`.

## Requisitos

- Java 21 ou superior
- Maven

## Executar

Na raiz do projeto, compile com:

```bash
mvn clean package
```

Para executar o programa após a compilação:

```bash
java -cp target/classes org.example.Principal
```

Também é possível executar a classe `org.example.Principal` diretamente pela IDE.

## O que o programa faz

1. Cria objetos `Funcionario` na ordem e com os dados da tabela do teste.
2. Remove João da lista.
3. Imprime os funcionários com data no formato `dd/MM/yyyy` e valores no padrão brasileiro.
4. Aplica aumento de 10%, arredondando os salários para duas casas decimais.
5. Agrupa e imprime os funcionários por função em um `Map<String, List<Funcionario>>`.
6. Lista aniversariantes dos meses 10 e 12.
7. Encontra o funcionário mais velho e calcula sua idade com base na data atual.
8. Imprime os nomes em ordem alfabética.
9. Soma os salários após o aumento.
10. Calcula quantos salários mínimos de R$ 1.212,00 cada funcionário recebe.

## Estrutura

```text
src/main/java/org/example/
├── Funcionario.java  # Funcionário estende Pessoa
├── Main.java         # Encaminha a execução para Principal
├── Pessoa.java       # Nome e data de nascimento
└── Principal.java    # Cadastro e operações do exercício
```

O projeto não possui testes automatizados próprios; `mvn test` valida a compilação e executa a fase de testes do Maven.
