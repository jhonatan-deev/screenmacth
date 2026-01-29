package QuestaoManipulandoFluxosColecoes;

import java.util.Arrays;
import java.util.List;

public class MinusculaParaMaiuscula {

    public static void main(String[] args) {
        //2 - Dada a lista de strings abaixo, converta todas para letras maiúsculas e imprima-as.
        List<String> palavras = Arrays.asList("java", "stream", "lambda");
        palavras.stream().map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
