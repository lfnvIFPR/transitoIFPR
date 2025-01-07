package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.comandos.Comando;

import java.util.List;

public interface IUsuario {
    String getNome();
    String[] getComandosAcessiveis();
    void configurarComando(Comando comandoAtivo);
    void executarComando();

}
