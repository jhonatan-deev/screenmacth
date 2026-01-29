package com.example.screenmatch.principal;

import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
import com.example.screenmatch.model.Epsodios;
import com.example.screenmatch.services.ConsumoApi;
import com.example.screenmatch.services.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=304ca963";

    public void exibirMenu(){
        System.out.println("Digite o nome da serie para buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace( " ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
//        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i = 1; i <= dados.totalTemporadas(); i++){
			json = consumo.obterDados( ENDERECO + nomeSerie.replace( " ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//			System.out.println(dadosTemporada);
			temporadas.add(dadosTemporada);
		}

//        for(int i = 0 ; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodes();
//            for(int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
           // melhorando o código acima são iguais
//        temporadas.forEach(todasAsTemporadas -> todasAsTemporadas.episodes().forEach(e-> System.out.println(e.titulo())));

        List<Epsodios> epsodios = temporadas.stream()
                .flatMap(temporada -> temporada.episodes().stream()
                        .map(episodio -> new Epsodios(temporada.numeroSeason(), episodio))
                ).collect(Collectors.toList());
        epsodios.forEach(System.out::println);
//        System.out.println("Digite o nome do episodio para buscar: ");
//        var trechoDoTitulo = leitura.nextLine();
//        Optional<Epsodios> episodioBuscado = epsodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoDoTitulo))
//                .findFirst();
//        if(episodioBuscado.isPresent()){
//            System.out.println("Episódio Encontrado: " + episodioBuscado.get().getTemporada());
//        }else{
//            System.out.println("Nenhum episodio encontrado");
//        }

//        System.out.println("Digit a data do episode que você busca: ");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        epsodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getDataLancamento() +
//                        " Episódio: " + e.getTitulo() +
//                        " Data de Lançamento " + e.getDataLancamento().format(formatter))
//                );

        Map<Integer, Double> avaliacoesPorTemporada = epsodios.stream()
                .filter(epsodio -> epsodio.getAvaliacao() > 0.0)
                .collect(
                        Collectors.groupingBy(Epsodios::getTemporada,
                        Collectors.averagingDouble(Epsodios::getAvaliacao)

                        ));
                System.out.println(avaliacoesPorTemporada);
                DoubleSummaryStatistics est = epsodios.stream()
                        .filter(epsodio -> epsodio.getAvaliacao() > 0.0)
                        .collect(Collectors.summarizingDouble(Epsodios::getAvaliacao));
                System.out.println("Média: " + est.getAverage() + ", Quantidade de votos: " + est.getCount());
                System.out.println("Avaliação mais baixa: " + est.getMin() + " Avaliação mais alta:t " + est.getMax());
    }
}
