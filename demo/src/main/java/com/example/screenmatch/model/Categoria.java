package com.example.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance" , "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    ANIMACAO("Animation", "Animação"),
    AVENTURA("Adventure", "Aventura"),
    FANTASIA("Fantasy", "Fantasia"),
    SUSPENSE("Thriller",  "Suspense"),
    FICCAO_CIENTIFICA("Sci-Fi", "Ficção"),
    HORROR("Horror", "Terror"),
    OUTROS("Others", "Outros");

    private String categoriaOmdb;
    private String categoriaEmPortugues;

    Categoria(String categoriaOmdb, String categoriaEmPortugues) {

        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEmPortugues = categoriaEmPortugues;
    }

    public static Categoria obterCategoria(String textoBuscaCategoria) {
        for (Categoria categorias : Categoria.values()) {
            if (textoBuscaCategoria.equalsIgnoreCase(categorias.categoriaOmdb)) {
                return categorias;
            }
        }
        throw new IllegalArgumentException("Nenhum categoria encontrada!");
    }
    public static Categoria categoriaPortugues(String textoBuscaCategoria) {
        for (Categoria categorias : Categoria.values()) {
            if (textoBuscaCategoria.equalsIgnoreCase(categorias.categoriaEmPortugues)) {
                return categorias;
            }
        }
        throw new IllegalArgumentException("Nenhum categoria encontrada! pt-br");
    }

}
