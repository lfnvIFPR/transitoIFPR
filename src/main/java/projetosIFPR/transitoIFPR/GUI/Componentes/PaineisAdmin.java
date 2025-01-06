package projetosIFPR.transitoIFPR.GUI.Componentes;

import java.awt.*;

public class PaineisAdmin implements OpcaoLista {

    @Override
    public String getNome() {
        return "ADMIN";
    }

    @Override
    public boolean possuiComponente() {
        return false;
    }

    @Override
    public Component getComponente() {
        return null;
    }
}
