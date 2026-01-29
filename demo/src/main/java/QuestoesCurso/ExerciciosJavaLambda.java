package QuestoesCurso;

import java.util.ArrayList;
import java.util.List;

public class ExerciciosJavaLambda {
    public static void main(String[] args) {
       LambdaSoma soma = (a,b) -> a + b;
       System.out.println(soma.soma(3,4));

    //1 - Crie uma expressão lambda que multiplique dois números inteiros. A expressão deve ser implementada dentro de uma
    // interface funcional com o método multiplicacao(int a, int b).
       Multiplicacao multiplicacao =  (a,b) -> a*b;
       System.out.println(multiplicacao.multiplicacao(3,4));

    //2 - Implemente uma expressão lambda que verifique se um número é primo.
       NumberPrimo numberPrimo = (n) ->{
           if(n <= 1) return false;
           for(int i = 2; i <= n / 2; i++){
               if(n % i == 0) return false;
           }
           return true;
       };
       System.out.println(numberPrimo.primo(10));

       ConverteMinuscula converteMinuscula = letra ->  letra.toUpperCase();
       System.out.println(converteMinuscula.converteLetraMinuscula("a"));


    //4 - Crie uma expressão lambda que verifique se uma string é um palíndromo. A expressão deve ser implementada dentro de
    //uma interface funcional com o método boolean verificarPalindromo(String str). Dica: utilize o método reverse da classe StringBuilder.

       Palindromo ehPalindromo = verificarSePalindromo -> {
           verificarSePalindromo = verificarSePalindromo.toLowerCase();
           String palavraInvertida = "";
           for(int i = verificarSePalindromo.length() - 1; i >= 0; i--){
               palavraInvertida += verificarSePalindromo.charAt(i);
           }
           return palavraInvertida.equals(verificarSePalindromo);
       };

    //Outra forma de fazer
        Palindromo ehPalindromo2 = str -> {
            str = str.toLowerCase();

            String invertida = new StringBuilder(str).reverse().toString();

            return str.equals(invertida);
        };
       System.out.println(ehPalindromo.Epalindromo("natan"));
       System.out.println(ehPalindromo2.Epalindromo("ovo"));
       System.out.println(ehPalindromo.Epalindromo("jhonatan"));
       System.out.println(ehPalindromo2.Epalindromo("Raiana"));

//        5 - Implemente uma expressão lambda que recebe uma lista de inteiros e retorna uma nova lista onde cada número foi
//        multiplicado por 3. Dica: a função replaceAll, das Collections, recebe uma interface funcional como parâmetro, assim como vimos na função forEach.
       List<Integer> numerosInteiros = new ArrayList<>();
       numerosInteiros.add(1);
       numerosInteiros.add(2);
       numerosInteiros.add(3);
       numerosInteiros.add(4);

       numerosInteiros.replaceAll(inteiro -> inteiro * 3);
       System.out.println(numerosInteiros);
//        6 - Crie uma expressão lambda que ordene uma lista de strings em ordem alfabética. Dica: a função sort, das Collections,
//        recebe uma interface funcional como parâmetro, assim como vimos na função forEach.
        List<String> ordemAlfabetica = new ArrayList<>();
        ordemAlfabetica.add("CASA");
        ordemAlfabetica.add("ABACATE");
        ordemAlfabetica.add("CARRO");
        ordemAlfabetica.add("DINHEIRO");
        ordemAlfabetica.add("ARCO");

        ordemAlfabetica.sort((a,b) -> a.compareTo(b));
        System.out.println(ordemAlfabetica);
//        7 - Crie uma função lambda que recebe dois números e divide o primeiro pelo segundo. A função deve lançar uma exceção
//        de tipo ArithmeticException se o divisor for zero.
        Divisao divida = (a,b) -> {
            if(b == 0|| a == 0) {
                throw new ArithmeticException("Divisão por zero não é permitido");
            }
            return a/b;
        };

        System.out.println(divida.divisao(4,2));
        System.out.println(divida.divisao(3,4));
        System.out.println(divida.divisao(0,4));
        System.out.println(divida.divisao(1,0));
    }
}
