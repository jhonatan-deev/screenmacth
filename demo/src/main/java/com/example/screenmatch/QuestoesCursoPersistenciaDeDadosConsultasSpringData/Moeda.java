package com.example.screenmatch.QuestoesCursoPersistenciaDeDadosConsultasSpringData;
//7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL. Cada moeda deve ter uma taxa de conversão para reais.
//Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.
public enum Moeda {
    DOLAR(5.44),
    EURO(3.53),
    REAL(1);

    private final double taxa;

    Moeda(double taxa){
        this.taxa = taxa;
    }

    public double converterPara(double valorEmReais) {
        return valorEmReais / taxa;
    };

    //Para chamar o método:
    //
    //System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
    //System.out.println(Moeda.EURO.converterPara(100)); // 18.18 (aproximado)
}
