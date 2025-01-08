package projetosIFPR.transitoIFPR.GUI.Componentes;

import projetosIFPR.transitoIFPR.comandos.Comando;

public interface BotaoExecutor {
    void configurarComando(Comando comandoAtivo);
    void executarComando();
    boolean botaoImportante();
}
