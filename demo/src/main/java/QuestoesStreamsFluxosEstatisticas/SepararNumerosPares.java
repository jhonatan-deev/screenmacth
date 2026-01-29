package QuestoesStreamsFluxosEstatisticas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SepararNumerosPares {
    public static void main(String[] args) {
        //5 - Dada uma lista de números inteiros, separe os números pares dos ímpares.
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        List<Integer> impares = numeros.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());

        System.out.println("Numeros pares: " + pares + ", " +  "Numeros impares: " + impares);


    }
}
