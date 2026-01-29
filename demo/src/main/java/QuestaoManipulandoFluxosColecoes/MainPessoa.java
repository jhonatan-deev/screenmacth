package QuestaoManipulandoFluxosColecoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPessoa {
    public static void main(String[] args) {
        //6 - Dado um objeto Pessoa com os campos nome e idade, filtre as pessoas com mais de 18 anos, extraia os nomes
        // e imprima-os em ordem alfabética. A classe Pessoa está definida abaixo.
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19)
        );

        List<String> resultados = pessoas.stream()
                .filter(p -> p.idade >= 18)
                .peek(p -> System.out.println("filtra nome: " + p.nome))
                .map(p -> p.nome)
                .sorted()
                .peek( p -> System.out.println("Pecorre o nome para ordem"))
                .collect(Collectors.toList());
        System.out.println(resultados);




    }
}
