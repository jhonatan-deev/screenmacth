package com.example.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(
        name = "episodios",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"titulo", "temporada", "numero_ep", "serie_id"}
        )
)
public class Episodios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEp;
    private Double avaliacao;
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;
    public Episodios() {}

    public Episodios(Integer numeroTemporada, DadosEpisodio dadosEpisodio, Serie serie) {
        this.temporada =  numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEp = dadosEpisodio.numeroEp();

        try{
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        }catch (NumberFormatException ex){
            this.avaliacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataDeLancamento());
        }catch (DateTimeParseException ex){
            this.dataLancamento = null;
        }
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Temporada " + temporada +
                " | Ep: " + numeroEp +
                " | Titulo: " + titulo +
                " | Nota: " + avaliacao +
                " | Data: " + dataLancamento;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroEp() {
        return numeroEp;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroEp(Integer numeroEp) {
        this.numeroEp = numeroEp;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
