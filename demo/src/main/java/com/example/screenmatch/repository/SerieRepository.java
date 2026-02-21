package com.example.screenmatch.repository;

import com.example.screenmatch.model.Categoria;
import com.example.screenmatch.model.Episodios;
import com.example.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);
    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String autor, Double avaliacao);
    List<Serie> findTop5ByOrderByAvaliacaoDesc();
    List<Serie> findByGenero(Categoria categoria);
    @Query(
            value = "SELECT * FROM serie s " +
                    "WHERE s.total_temporadas <= :totalTemporadas " +
                    "AND s.avaliacao >= :avaliacao",
            nativeQuery = true
    )
    List<Serie> seriesPorTemporada(Integer totalTemporadas, Double avaliacao);
//  List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer totalTemporadas, Double avaliacao);
    @Query(value = "SELECT e FROM  Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodios> episodiosPorTrechoEpisodio(String trechoEpisodio);
    @Query(value = "SELECT e FROM Serie s join s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodios> topEpisodiosPorSerie(Serie serie);
    @Query(value = "SELECT e FROM Serie s join s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodios> episodiosPorSerieEAno(Serie serie, int anoLancamento);

}
