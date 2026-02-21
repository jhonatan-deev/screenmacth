package com.example.screenmatch.QuestoesCursoPersistenciaDeDadosConsultasSpringData;
//6 - Crie um enum Mes que represente os meses do ano. Adicione um método que retorna o número de dias de cada mês,
// considerando anos não bissextos
public enum Mes {
    JANEIRO(31),
    FEVEREIRO(28),
    MARCO(31),
    ABRIL(30),
    MAIO(31),
    JUNHO(30),
    JULHO(31),
    AGOSTO(31),
    SETEMBRO(30),
    OUTUBRO(31),
    NOVEMBRO(30),
    DEZEMBRO(31);

    private int dias;

    Mes(int dias){
        this.dias = dias;
    }

    public int getDias(){
        return dias;
    }
}
//ENUM é um tipo especial de classe que representa um conjunto fixo de valores.
//Ele é usado quando algo só pode assumir algumas opções específicas e conhecidas.
//
//Exemplos:
//- Meses do ano
//- Dias da semana
//- Status de pedido (ABERTO, PAGO, CANCELADO)
//- Tipo de usuário (ADMIN, USER, GUEST)
//Cada valor do enum é um objeto
//----------------------------------------
//Quando você escreve:
//
//JANEIRO(31),
//FEVEREIRO(28)
//
//O Java está criando objetos, como se fosse:
//
//Mes JANEIRO = new Mes(31);
//Mes FEVEREIRO = new Mes(28);
//
//Ou seja:
//Cada item do enum é uma instância (objeto) da classe Mes.

//Para chamar o método:
//
//System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
//System.out.println(Mes.JULHO.getNumeroDeDias()); // 31