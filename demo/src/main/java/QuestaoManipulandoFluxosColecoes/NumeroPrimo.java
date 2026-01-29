package QuestaoManipulandoFluxosColecoes;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NumeroPrimo {
    //5 - Dada a lista de sublistas de números inteiros abaixo, extraia todos os números primos em uma única lista e os ordene em ordem crescente.

    public static boolean isPrimo(int numero) {
        if(numero <= 1) return false;

        for(int i = 2; i < numero; i++){
            if(numero % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );

        List<Integer> numerosPrimo = new ArrayList<>();
        for(List<Integer> lista : listaDeNumeros) {
            for(int numero : lista) {
                if(isPrimo(numero)){
                    numerosPrimo.add(numero);
                }
            }
        }
        Collections.sort(numerosPrimo);
        System.out.println(numerosPrimo);

    }
}
