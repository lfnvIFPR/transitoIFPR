package projetosIFPR.transitoIFPR.comandos;

import projetosIFPR.transitoIFPR.GUI.TelaLogin;
import projetosIFPR.transitoIFPR.estado.EstadoApp;
import projetosIFPR.transitoIFPR.estado.GUIController;

public class SairComando implements Comando {

    @Override
    public Resultados executar() {
        EstadoApp.resetUsuarioLogado();
        GUIController.configurarAtivo(new TelaLogin());
        return new Resultados(true, null);
    }
}
