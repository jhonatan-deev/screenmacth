package com.example.screenmatch.principal;

import com.example.screenmatch.config.OmdbConfig;
import com.example.screenmatch.model.*;
import com.example.screenmatch.repository.SerieRepository;
import com.example.screenmatch.services.ConsumoApi;
import com.example.screenmatch.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {

    private final ConsumoApi consumo;
    private final ConverteDados conversor;
    private final SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();
    private final OmdbConfig config;
    private Optional<Serie> serieBuscada;
    private final Scanner leitura = new Scanner(System.in);

    public Principal(
            ConsumoApi consumo,
            ConverteDados conversor,
            SerieRepository repositorio,
            OmdbConfig config
    ) {
        this.consumo = consumo;
        this.conversor = conversor;
        this.repositorio = repositorio;
        this.config = config;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                1 - Buscar série
                2 - Buscar episódios
                3 - Buscar Série por Titulo
                4 - Listar séries buscadas
                5 - Buscar Serie por ator
                6 - Top 5 Melhores Series
                7 - Buscar Series de uma Categoria
                8 - Series por Maximo de Temporadas e Mínimo de Avaliação
                9 - Buscar Episódio por trecho de titulo
                10 - Top 5 Episódios por Serie
                11 - Buscar Episódios por Serie e ano de Lançamento
                0 - Sair
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarSerie();
                case 2 -> buscarEpisodio();
                case 3 -> buscarSeriePorTiulo();
                case 4 -> listaSeriesBuscadas();
                case 5 -> buscarSeriePorAtor();
                case 6 -> buscarTop5Serie();
                case 7 -> buscarSeriePorCategoria();
                case 8 -> buscarSeriesComMaximoDeTemporadasMinimoDeAvaliacao();
                case 9 -> buscarEpisodioPorTrechoTitulo();
                case 10 -> topEpisodiosPorSerie();
                case 11 -> buscarEpisodioDepoisDeUmaData();
            }
        }
    }

    private void buscarSerie() {
        System.out.println("Digite o nome da série:");
        String nomeSerie = leitura.nextLine();

        String url = config.getUrl() + "?t=" + nomeSerie.replace(" ", "+") + "&apikey=" + config.getApiKey();

        String json = consumo.obterDados(url);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(serie);
    }

    private void buscarEpisodio() {
        listaSeriesBuscadas();

        System.out.println("Digite o nome da série que deseja buscar os episódios:");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(
                        config.getUrl() + "?t=" + nomeSerie.replace(" ", "+") + "&Season=" + i + "&apikey=" + config.getApiKey()
                );
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);

                if (dadosTemporada.episodios() != null) {
                    temporadas.add(dadosTemporada);
                }
            }

            temporadas.forEach(System.out::println);

            List<Episodios> episodios = temporadas.stream()
                    .flatMap(dadosTemp -> dadosTemp.episodios().stream()
                            .map(ep -> new Episodios(dadosTemp.numero(), ep, serieEncontrada)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);

            System.out.println("Episódios carregados com sucesso!");
        } else {
            System.out.println("Nenhuma série encontrada com esse nome!");
        }
    }


    public void buscarSeriePorTiulo(){
        System.out.println("Digite o nome da Serie que você deseja buscar:");
        String serieParaBuscar = leitura.nextLine();
        serieBuscada = repositorio.findByTituloContainingIgnoreCase(serieParaBuscar);
        if (serieBuscada.isPresent()) {
            System.out.println(serieBuscada.get());
        }else{
            System.out.println("Serie não encontrada!");
        }
    }

    private void buscarSeriePorAtor(){
        System.out.println("Digite o nome do ator: ");
        String nomeAtor = leitura.nextLine();
        System.out.println("Digite apartir da nota que deseja buscar: ");
        double notaAtor = leitura.nextDouble();
        List<Serie> atorBuscado = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,  notaAtor);
        if (!atorBuscado.isEmpty()) {
            System.out.println("Séries em que " + nomeAtor + " trabalhou: ");
            atorBuscado.forEach(
                    s ->  System.out.println( s.getTitulo() + " Avaliação " + s.getAvaliacao()));
        }else {
            System.out.println("Ator não encontrado!");
        }
    }

    public void buscarTop5Serie() {
        List<Serie> top5Series = repositorio.findTop5ByOrderByAvaliacaoDesc();

        System.out.println("\n🏆 TOP 5 SÉRIES MAIS BEM AVALIADAS");
        System.out.println("====================================");
        int posicao = 1;
        for (Serie serie : top5Series) {
            System.out.printf(
                    "%dº - %s | ⭐ %.1f | Gênero: %s%n",
                    posicao++,
                    serie.getTitulo(),
                    serie.getAvaliacao(),
                    serie.getGenero()
            );
        }

        System.out.println("====================================\n");
    }

    private void listaSeriesBuscadas() {
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorCategoria(){
        System.out.println("Digite o nome da categoria que deseja buscar: ");
        String nomeCategoria = leitura.nextLine();
        Categoria categoria = Categoria.categoriaPortugues(nomeCategoria);
        List<Serie> categoriaEncontrada = repositorio.findByGenero(categoria);
        System.out.println("Séries com essa categoria: ");
        categoriaEncontrada.forEach(System.out::println);
        System.out.println("-------------------------------------");
    }

    public void buscarSeriesComMaximoDeTemporadasMinimoDeAvaliacao(){
        System.out.println("Digite a quantidade de temporadas Maxima: ");
        int numeroMaximoTemporadas = leitura.nextInt();
        System.out.println("Digite a nota de avaliação minima: ");
        double notaMinima = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> seriesEncontradas = repositorio.seriesPorTemporada(numeroMaximoTemporadas, notaMinima);
        System.out.println("Séries encontradas: ");
        seriesEncontradas.forEach(System.out::println);
    }
    public void buscarEpisodioPorTrechoTitulo(){
        System.out.println("Digite o nome do serie: ");
        String nomeEpisodio = leitura.nextLine();
        List<Episodios> episodiosEncontrados = repositorio.episodiosPorTrechoEpisodio(nomeEpisodio);
        episodiosEncontrados.forEach(System.out::println);

    }

    public void topEpisodiosPorSerie(){
        buscarSeriePorTiulo();
        if(serieBuscada.isPresent()){
            Serie serie = serieBuscada.get();
            List<Episodios> topEpisodios = repositorio.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(System.out::println);
        }
    }

    public void buscarEpisodioDepoisDeUmaData(){
        buscarSeriePorTiulo();
        if(serieBuscada.isPresent()){
            Serie serie = serieBuscada.get();
            System.out.println("Digite o ano limite de lançamento: ");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();
            List<Episodios> episodiosAno = repositorio.episodiosPorSerieEAno(serie, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }
}
