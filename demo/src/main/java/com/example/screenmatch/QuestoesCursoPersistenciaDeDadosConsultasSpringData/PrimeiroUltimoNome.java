package com.example.screenmatch.QuestoesCursoPersistenciaDeDadosConsultasSpringData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. O método deve
// retornar o primeiro e o último nome após remover os espaços desnecessários.
public class PrimeiroUltimoNome {
    public static void main(String[] args) {
        System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
        System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"


    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
       String[] partes = nomeCompleto.trim().split(" ");
       List<String> nomes = Arrays.asList(partes);
       nomes.stream()
               .filter(n -> !n.isBlank())
               .collect(Collectors.joining(" "));

       if(nomes.size() == 1){
           return nomes.get(0);
       }
       return nomes.get(0) + " " + nomes.get(nomes.size() - 1);
    }

}
