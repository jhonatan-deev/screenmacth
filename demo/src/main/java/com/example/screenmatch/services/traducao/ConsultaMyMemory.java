package com.example.screenmatch.services.traducao;

import com.example.screenmatch.services.ConsumoApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaMyMemory {

    public static String obterTraducao(String texto) {
        try {
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8);
            String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

            String url = "https://api.mymemory.translated.net/get?q="
                    + textoCodificado
                    + "&langpair="
                    + langpair;

            ConsumoApi consumo = new ConsumoApi();
            String json = consumo.obterDados(url);

            ObjectMapper mapper = new ObjectMapper();
            DadosTraducao dados = mapper.readValue(json, DadosTraducao.class);

            return dados.dadosResposta().textoTraduzido();

        } catch (Exception e) {
            System.out.println("⚠️ Erro ao traduzir: " + e.getMessage());
            return texto;
        }
    }
}
