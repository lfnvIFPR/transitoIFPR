package projetosIFPR.transitoIFPR.GUI.Componentes;

import java.awt.*;

public class PaineisComum implements OpcaoLista {

    @Override
    public String getNome() {
        return "COMUM";
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
