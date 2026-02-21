package com.example.screenmatch.QuestoesCursoPersistenciaDeDadosConsultasSpringData;
//8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR.
// Cada valor deve ter um código numérico e uma descrição associados. Adicione métodos para acessar o código e a descrição.
// Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e conseguir descrevê-los melhor.
public enum CodigoErro {
    NOT_FOUND(404, "Não Encontrado."),
    BAD_REQUEST(400, "Requisição inválida."),
    INTERNAL_SERVER_ERROR(500, "Erro do Servidor Interno.");

    private int codigo;
    private String mensagem;

    CodigoErro(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getMensagem() {
        return mensagem;
    }
//Para chamar o método:
//
//System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
//System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválida
}
