package QuestaoManipulandoFluxosColecoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeNumeros {
    public static void main(String[] args) {
        //1 - Dada a lista de números inteiros abaixo, filtre apenas os números pares e imprima-os.
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        numeros.stream()
                .filter( numero -> numero % 2 ==0)
                .forEach(System.out::println);

    }
}
