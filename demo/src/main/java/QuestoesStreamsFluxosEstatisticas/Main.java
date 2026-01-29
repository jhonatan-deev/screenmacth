package QuestoesStreamsFluxosEstatisticas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Lista de produtos de exemplo
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        // ========================================
        // 6 - Dada a lista de produtos acima, agrupe-os por categoria em um Map<String, List<Produto>>.
        // ========================================

        // stream(): inicia o processamento da lista, permitindo usar operações funcionais (filter, map, collect, etc.)
        // collect(): método terminal que transforma o Stream em uma coleção (List, Map, Set, etc.)
        // groupingBy(): agrupa os elementos do Stream por alguma chave (aqui: categoria do produto)
        Map<String, List<Produto>> produtosPorCategoria =
                produtos.stream()
                        .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println("=== Questão 6 ===");
        System.out.println(produtosPorCategoria);

        // ========================================
        // 7 - Dada a lista de produtos acima, conte quantos produtos há em cada categoria e armazene em um Map<String, Long>.
        // ========================================

        // counting(): conta quantos elementos existem em cada grupo
        // groupingBy(Produto::getCategoria, counting()): chave = categoria, valor = quantidade de produtos
        Map<String, Long> quantidadeDeProdutos = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));

        System.out.println("=== Questão 7 ===");
        System.out.println(quantidadeDeProdutos);

        // ========================================
        // 8 - Dada a lista de produtos acima, encontre o produto mais caro de cada categoria e armazene em um Map<String, Optional<Produto>>.
        // ========================================

        // maxBy(Comparator.comparing(Produto::getPreco)): retorna o produto de maior preço de cada grupo
        // Optional<Produto>: usado pois pode não haver elementos no grupo
        Map<String, Optional<Produto>> produtoMaisCaro = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparing(Produto::getPreco))
                ));

        System.out.println("=== Questão 8 ===");
        System.out.println(produtoMaisCaro);

        // ========================================
        // 9 - Dada a lista de produtos acima, calcule o total dos preços dos produtos em cada categoria e armazene em um Map<String, Double>.
        // ========================================

        // summingDouble(Produto::getPreco): soma todos os preços de cada grupo
        // groupingBy(Produto::getCategoria, summingDouble(...)): chave = categoria, valor = soma dos preços
        Map<String, Double> precoTotal = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.summingDouble(Produto::getPreco)));

        System.out.println("=== Questão 9 ===");
        System.out.println(precoTotal);

        // ========================================
        // 10 - RESUMO COMPLETO POR CATEGORIA (usando todos os métodos juntos)
        // Agrupar produtos por categoria e calcular:
        // 1) Lista de produtos
        // 2) Quantidade de produtos
        // 3) Produto mais caro
        // 4) Soma total dos preços
        // ========================================

        // collectingAndThen(): transforma o resultado de um Collector aplicando uma função final
        Map<String, Map<String, Object>> resumoCategorias = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                lista -> {
                                    Map<String, Object> info = new HashMap<>();
                                    info.put("produtos", lista); // lista de produtos
                                    info.put("quantidade", lista.size()); // quantidade de produtos
                                    info.put("maisCaro", lista.stream()
                                            .max(Comparator.comparing(Produto::getPreco))
                                            .orElse(null)); // produto mais caro
                                    info.put("precoTotal", lista.stream()
                                            .mapToDouble(Produto::getPreco)
                                            .sum()); // soma dos preços
                                    return info;
                                }
                        )
                ));

        System.out.println("=== Questão 10 ===");
        System.out.println(resumoCategorias);
    }
}



//
//## 🔹 Lista completa de métodos usados nas questões
//
//| Método / Collector                                      | O que faz                                                                                                           | Como foi usado                                                                                                                       |
//| ------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
//| **`stream()`**                                          | Inicia o processamento de uma coleção, permitindo usar operações funcionais como `filter`, `map`, `collect`, etc.   | Em todas as questões: transforma a `List<Produto>` em um `Stream<Produto>` para poder usar operações de Stream.                      |
//| **`collect()`**                                         | Método terminal do Stream que transforma o Stream em uma coleção (`List`, `Map`, `Set`, etc.).                      | Em todas as questões: usado para gerar o Map final (`groupingBy`) ou listas.                                                         |
//| **`Collectors.groupingBy(Function)`**                   | Agrupa elementos do Stream em um Map usando a função como chave. O valor padrão é uma lista dos elementos do grupo. | Questão 6: agrupou produtos por categoria → Map<String, List<Produto>>                                                               |
//| **`Collectors.groupingBy(Function, Collector)`**        | Agrupa elementos do Stream por chave e aplica outro Collector para definir o valor do Map.                          | Questões 7, 8, 9 e 10. Ex.: `counting()`, `maxBy()`, `summingDouble()`, `collectingAndThen()`.                                       |
//| **`Collectors.counting()`**                             | Conta a quantidade de elementos em cada grupo.                                                                      | Questão 7: Map<String, Long> → quantidade de produtos por categoria.                                                                 |
//| **`Collectors.maxBy(Comparator)`**                      | Retorna o maior elemento de cada grupo, de acordo com o comparador. O resultado é um `Optional<T>`.                 | Questão 8 e Questão 10: produto mais caro por categoria.                                                                             |
//| **`Comparator.comparing(Function)`**                    | Cria um comparador com base no valor retornado pela função, usado para ordenação ou para `maxBy`.                   | Questão 8 e 10: comparar produtos pelo preço (`Produto::getPreco`).                                                                  |
//| **`Collectors.summingDouble(ToDoubleFunction)`**        | Soma valores double de cada grupo, retornando um `Double`.                                                          | Questão 9 e 10: soma total de preços por categoria.                                                                                  |
//| **`mapToDouble(ToDoubleFunction)`**                     | Transforma um Stream de objetos em um DoubleStream, permitindo operações numéricas (`sum`, `average`, etc.).        | Questão 10: soma dos preços da lista de produtos.                                                                                    |
//| **`Collectors.collectingAndThen(Collector, Function)`** | Aplica um Collector e depois transforma o resultado aplicando uma função final.                                     | Questão 10: transformou a lista de produtos de cada categoria em um Map contendo lista, quantidade, produto mais caro e preço total. |
//| **`Optional.orElse(T)`**                                | Retorna o valor do Optional se presente, caso contrário retorna o valor fornecido.                                  | Questão 10: garantiu que, se não houver produto no grupo, retorne `null` em vez de Optional vazio.                                   |
//
//---
//
//Se você quiser, posso fazer **um quadro visual com todos os métodos aplicados ao mesmo exemplo**, mostrando **cada produto entrando no Map**, cada Collector atuando (`counting`, `maxBy`, `summingDouble`) e como o `collectingAndThen` cria o resumo final da questão 10.
//
//Isso deixa **muito fácil de memorizar e visualizar** o fluxo do Stream.
//
//Quer que eu faça esse quadro visual?