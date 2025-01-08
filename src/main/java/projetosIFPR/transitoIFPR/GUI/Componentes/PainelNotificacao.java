package projetosIFPR.transitoIFPR.GUI.Componentes;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class PainelNotificacao implements OpcaoLista {

    private JPanel fisico;
    private String nomeDoBotao;

    public PainelNotificacao(String nomeDoBotao) {
        this.nomeDoBotao = nomeDoBotao;
        gerarLayout();
    }

    @Override
    public String getNome() {
        return nomeDoBotao;
    }

    @Override
    public String getDescricao() {
        return "Ver notificações recentes e atualizações importantes.";
    }

    @Override
    public boolean possuiComponente() {
        return true;
    }

    @Override
    public Component getComponente() {
        return fisico;
    }

    private void gerarLayout() {
        fisico = new JPanel(new MigLayout("inset 10"));
    }
}
