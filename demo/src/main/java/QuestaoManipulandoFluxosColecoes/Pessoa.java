package QuestaoManipulandoFluxosColecoes;

public class Pessoa {
    //6 - Dado um objeto Pessoa com os campos nome e idade, filtre as pessoas com mais de 18 anos, extraia os nomes e
    //imprima-os em ordem alfabética. A classe Pessoa está definida abaixo.

    String nome;
    int idade;

    Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}
