package projetosIFPR.transitoIFPR.GUI.Componentes;

import javax.swing.*;
import java.awt.*;

public class PainelUsuario implements OpcaoLista {

    private JPanel fisico;
    private String nomeDoBotao;

    public PainelUsuario(String nomeDoBotao) {
        this.nomeDoBotao = nomeDoBotao;
        gerarLayout();
    }

    @Override
    public String getNome() {
        return nomeDoBotao;
    }

    @Override
    public boolean possuiComponente() {
        return false;
    }

    @Override
    public Component getComponente() {
        return null;
    }

    private void gerarLayout() {
        fisico = new JPanel();

    }



}
