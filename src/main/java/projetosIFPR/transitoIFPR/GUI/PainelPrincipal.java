package projetosIFPR.transitoIFPR.GUI;

import projetosIFPR.transitoIFPR.GUI.Componentes.TextoDatalist;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelPrincipal extends JFrame {

    private JPanel barraLateral;
    private JPanel conteudoPrimario;

    public PainelPrincipal() {
        super("Sistema de trânsito - IFPR");

        URL iconeURL = getClass().getClassLoader().getResource("png/icon.png");
        ImageIcon icone = new ImageIcon(iconeURL);
        setIconImage(icone.getImage());

        gerarLayout();
    }

    private void gerarLayout() {
        conteudoPrimario = new JPanel(new CardLayout());
        conteudoPrimario.setOpaque(false);
//        add(conteudoPrimario, BorderLayout.CENTER);


        add(new TextoDatalist(new String[] {"123", "456"}), BorderLayout.CENTER);

        barraLateral = new JPanel();
        barraLateral.setPreferredSize(new Dimension(200, 10));

        String[][] botoes = { {"Usuário"}, {"Administrador"}};

        barraLateral.setBackground(new Color(0x2d2f30));
        add(barraLateral, BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
    }


}
