package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.comandos.Comando;

import java.time.LocalDateTime;

public interface IUsuario {
    String getNome();
    LocalDateTime getUltimoLogin();
    String[] getComandosAcessiveis();
    void configurarComando(Comando comandoAtivo);
    void executarComando();

}
