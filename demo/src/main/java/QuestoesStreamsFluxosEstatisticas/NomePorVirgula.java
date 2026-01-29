package QuestoesStreamsFluxosEstatisticas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NomePorVirgula {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
        //3 - Dada a lista de nomes abaixo, concatene-os separados por vírgula. No código a seguir, há um exemplo
        // prático do resultado esperado.
        String resultado = nomes.stream()
                .collect(Collectors.joining(", "));

        System.out.println("\"" + resultado + "\"");
    }
}