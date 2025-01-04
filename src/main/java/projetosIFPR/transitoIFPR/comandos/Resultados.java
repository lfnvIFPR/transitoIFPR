package projetosIFPR.transitoIFPR.comandos;

public class Resultados {

    boolean sucesso = true;
    Object[] retorno;
    
    public Resultados(boolean sucesso, Object[] retorno) {
        this.sucesso = sucesso;
        this.retorno = retorno;
    }

}
