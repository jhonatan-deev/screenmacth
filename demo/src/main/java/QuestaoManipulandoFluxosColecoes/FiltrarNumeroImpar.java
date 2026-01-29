package QuestaoManipulandoFluxosColecoes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FiltrarNumeroImpar {
    public static void main(String[] args) {
        //3 - Dada a lista de números inteiros abaixo, filtre os números ímpares, multiplique cada um por 2 e colete os resultados em uma nova lista.
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> resultados = numeros.stream()
                .filter(numero -> numero % 2 != 0)
                .peek(numero -> System.out.println("Ímpares: " + numero))
                .map(numero -> numero * 2)
                .collect(Collectors.toList());

        System.out.println(resultados);


    }
}
