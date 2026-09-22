package org.example;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat MOEDA = NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                funcionario("Maria", "18/10/2000", "2009.44", "Operador"),
                funcionario("João", "12/05/1990", "2284.38", "Operador"),
                funcionario("Caio", "02/05/1961", "9836.14", "Coordenador"),
                funcionario("Miguel", "14/10/1988", "19119.88", "Diretor"),
                funcionario("Alice", "05/01/1995", "2234.68", "Recepcionista"),
                funcionario("Heitor", "19/11/1999", "1582.72", "Operador"),
                funcionario("Arthur", "31/03/1993", "4071.84", "Contador"),
                funcionario("Laura", "08/07/1994", "3017.45", "Gerente"),
                funcionario("Heloísa", "24/05/2003", "1606.85", "Eletricista"),
                funcionario("Helena", "02/09/1996", "2799.93", "Gerente")
        ));

        funcionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("Funcionários:");
        funcionarios.forEach(Principal::imprimirFuncionario);

        funcionarios.forEach(f -> f.setSalario(
                f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, java.math.RoundingMode.HALF_UP)));
        System.out.println("\nApós aumento de 10%:");
        funcionarios.forEach(Principal::imprimirFuncionario);

        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("\nFuncionários por função:");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(Principal::imprimirFuncionario);
        });

        System.out.println("\nAniversariantes dos meses 10 e 12:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(DATA)));

        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento)).orElseThrow();
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\nMais velho: " + maisVelho.getNome() + " - " + idade + " anos");

        System.out.println("\nOrdem alfabética:");
        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        BigDecimal total = funcionarios.stream().map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nSoma dos salários: R$ " + moeda(total));

        System.out.println("\nSalários mínimos por funcionário:");
        funcionarios.forEach(f -> System.out.printf("%s: %.2f salários mínimos%n", f.getNome(),
                f.getSalario().divide(SALARIO_MINIMO, 2, java.math.RoundingMode.HALF_UP).doubleValue()));
    }

    private static Funcionario funcionario(String nome, String nascimento, String salario, String funcao) {
        return new Funcionario(nome, LocalDate.parse(nascimento, DATA), new BigDecimal(salario), funcao);
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.printf("Nome: %s | Nascimento: %s | Salário: R$ %s | Função: %s%n",
                f.getNome(), f.getDataNascimento().format(DATA), moeda(f.getSalario()), f.getFuncao());
    }

    private static String moeda(BigDecimal valor) {
        MOEDA.setMinimumFractionDigits(2);
        MOEDA.setMaximumFractionDigits(2);
        return MOEDA.format(valor);
    }
}
