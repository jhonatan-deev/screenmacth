package com.example.screenmatch.QuestoesCursoPersistenciaDeDadosConsultasSpringData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Imagine que você tem uma lista de strings. Algumas das strings são números, mas outras não. Queremos converter a lista
// de strings para uma lista de números. Se a conversão falhar, você deve ignorar o valor. Por exemplo, na lista a seguir, a saída deve ser [10, 20]:
public class SepararDiferentesDados {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("10", "abc", "20", "30x");
        List<Integer> numerosEncontrados = input.stream()
                .map(n -> {
                    try{
                        return Integer.parseInt(n);
                    }catch (Exception e){
                        return null;
                    }
                })
                .filter(n -> n != null)
                .collect(Collectors.toList());
        System.out.println(numerosEncontrados);
    }
}
