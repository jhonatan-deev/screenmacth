package QuestoesStreamsFluxosEstatisticas;

import java.util.Arrays;
import java.util.List;

public class MaiorNumero {
    public static void main(String[] args) {
        //1 - Dada a lista de números inteiros a seguir, encontre o maior número dela.
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        numeros.stream()
                .max(Integer::compareTo)
                .ifPresent(System.out::println);
    }
}
