package projetosIFPR.transitoIFPR.GUI.Componentes;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import projetosIFPR.transitoIFPR.Administrador;
import projetosIFPR.transitoIFPR.Fiscalizador;
import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.estado.EstadoApp;

import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

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
    public String getDescricao() {
        return "Visualiza informações sobre o seu cadastro no sistema.";
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
        fisico = new JPanel(new MigLayout("insets 3%, wrap 1"));


        JLabel titulo = new JLabel("Usuário");
        titulo.putClientProperty("FlatLaf.styleClass", "h1");
        fisico.add(titulo, "align left, growx");

        JPanel usuarioPanel = new JPanel(new MigLayout(
            "w 100%!",
            "[shrink 0]10[grow]",
            "[]"
        ));

        fisico.add(usuarioPanel, "align label center, grow");

        IUsuario usr = EstadoApp.getUsuarioLogado();
        String icone = 
        (usr instanceof Administrador) ? 
            "png/admin.png" : 
        ((usr instanceof Fiscalizador) ? 
            "png/fiscal.png" : 
        "png/condutor.png");

        URL imagem = getClass().getClassLoader().getResource(icone);
        ImageIcon iconImage = new ImageIcon(imagem);
        JLabel iconLabel = new JLabel(iconImage);

        usuarioPanel.add(iconLabel);

        JPanel info = new JPanel(new MigLayout(
            "inset 2%, w 100%",
            "[shrink 0][grow][shrink 0]",
            "[][]"
        ));

        info.add(new JLabel("@" + usr.getNome()));
        String tipo = 
        (usr instanceof Administrador) ? 
            "Administrador" : 
        ((usr instanceof Fiscalizador) ? 
            "Fiscalizador" : 
        "Condutor");
        info.add(new JPanel());
        
        info.add(new JLabel(tipo), "wrap");
        LocalDateTime ultimoLogin = usr.getUltimoLogin();
        DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);

        info.add(new JLabel("Último login: " + ultimoLogin.format(fmt)), "span");

        usuarioPanel.add(info, "wrap, grow");

        JSeparator sep = new JSeparator();
        sep.setPreferredSize(new Dimension(2, 2));
        fisico.add(sep, "w 85%!, align center");
        



    }



}
