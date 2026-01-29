package QuestoesStreamsFluxosEstatisticas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuadradoDosNumeros {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> resultado = numeros.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(resultado);
    }
}
