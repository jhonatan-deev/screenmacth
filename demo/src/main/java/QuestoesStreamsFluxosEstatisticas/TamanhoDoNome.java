package QuestoesStreamsFluxosEstatisticas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TamanhoDoNome {
    public static void main(String[] args) {
        //2 -Dada a lista de palavras (strings) abaixo, agrupe-as pelo seu tamanho. No código a seguir,
        // há um exemplo prático do resultado esperado.
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");

        Map<Integer, List<String>> resultados = palavras.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Palavras: " + resultados);

    }
}
