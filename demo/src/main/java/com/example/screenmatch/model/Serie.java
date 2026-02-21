package com.example.screenmatch.model;

import com.example.screenmatch.services.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
public class Serie {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column(unique = true)
   private String titulo;
   private Integer totalTemporadas;
   private Double avaliacao;
   @Enumerated(EnumType.STRING)
   private Categoria genero;
   private String sinopse;           // original em inglês
   private String sinopseTraduzida;  // versão em português
   private String atores;
   private String poster;

   @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Episodios> episodios = new ArrayList<>();

   public Serie() {}// a jpa exige que tenha em construtor padrao desse ou vai gerar esse erro: No default constructor for entity 'com.example.screenmatch.model.Serie'
   public Serie(DadosSerie dadosSerie) {
      this.titulo = dadosSerie.titulo();
      this.totalTemporadas = dadosSerie.totalTemporadas();
      this.avaliacao = Double.valueOf(dadosSerie.avaliacao());
      this.genero = Categoria.obterCategoria(dadosSerie.genero().split(",")[0].trim());
      this.atores = dadosSerie.atores();
      this.poster = dadosSerie.poster();

      // texto original em inglês
      this.sinopse = dadosSerie.sinopse();

      // tradução usando MyMemory
      this.sinopseTraduzida = ConsultaMyMemory.obterTraducao(this.sinopse);
   }



   @Override
   public String toString() {
      return "Serie{" +
              "id=" + id +
              ", titulo='" + titulo + '\'' +
              ", totalTemporadas=" + totalTemporadas +
              ", avaliacao=" + avaliacao +
              ", genero=" + genero +
              ", sinopseTraduzida='" + sinopseTraduzida + '\'' +
              ", atores='" + atores + '\'' +
              ", poster='" + poster + '\'' +
              ", quantidadeEpisodios=" + (episodios != null ? episodios.size() : 0) +
              '}';
   }

   public List<Episodios> getEpsodios() {
      return episodios;
   }

   public void setEpisodios(List<Episodios> episodiosList) {
      episodiosList.forEach(e -> e.setSerie(this));
      this.episodios = episodiosList;
   }

   public long getId() {
      return id;
   }

   public void setGenero(Categoria genero) {
      this.genero = genero;
   }

   public void setSinopse(String sinopse) {
      this.sinopse = sinopse;
   }

   public void setSinopseTraduzida(String sinopseTraduzida) {
      this.sinopseTraduzida = sinopseTraduzida;
   }

   public void setAtores(String atores) {
      this.atores = atores;
   }

   public void setPoster(String poster) {
      this.poster = poster;
   }

   public void setId(long id) {
      this.id = id;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public void setTotalTemporadas(Integer totalTemporadas) {
      this.totalTemporadas = totalTemporadas;
   }

   public void setAvaliacao(Double avaliacao) {
      this.avaliacao = avaliacao;
   }

   public String getTitulo() {
      return titulo;
   }

   public Integer getTotalTemporadas() {
      return totalTemporadas;
   }

   public Double getAvaliacao() {
      return avaliacao;
   }

   public String getSinopse() {
      return sinopse;
   }

   public String getSinopseTraduzida() {
      return sinopseTraduzida;
   }

   public String getAtores() {
      return atores;
   }

   public String getPoster() {
      return poster;
   }

   public Categoria getGenero() {
      return genero;
   }
}
