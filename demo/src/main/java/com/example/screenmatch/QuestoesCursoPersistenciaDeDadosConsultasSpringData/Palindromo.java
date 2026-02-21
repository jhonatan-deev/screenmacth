package com.example.screenmatch.QuestoesCursoPersistenciaDeDadosConsultasSpringData;
//4 - Implemente um método que verifica se uma frase é um palíndromo. Um palíndromo é uma palavra/frase que, quando
// lida de trás pra frente, é igual à leitura normal. Um exemplo:
public class Palindromo {
    public static void main(String[] args) {
        System.out.println(ehPalindromo("socorram me subi no onibus em marrocos"));
        System.out.println(ehPalindromo("Java"));
        System.out.println(ehPalindromo("OvO"));
        System.out.println(ehPalindromo("Raiana"));

    }
    public static boolean ehPalindromo(String palavra) {
        String textoRecebido = palavra.toLowerCase().replaceAll(" ", "");
        String textoInvertido = new StringBuilder(textoRecebido).reverse().toString();
        if(textoInvertido.equals(textoRecebido) == true) {
            return true;
        }else{
            return false;
        }
    };
}
