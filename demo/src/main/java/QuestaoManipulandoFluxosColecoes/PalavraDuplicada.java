package QuestaoManipulandoFluxosColecoes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PalavraDuplicada {
    public static void main(String[] args) {
        //4 - Dada a lista de strings abaixo, remova as duplicatas (palavras que aparecem mais de uma vez) e imprima o resultado.
        List<String> palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        List<String> resultados = palavras.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(resultados);
    }
}
