package projetosIFPR.transitoIFPR.GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelPrincipal extends JFrame {

    private JPanel barraLateral;

    public PainelPrincipal() {
        super("Sistema de trânsito - IFPR");

        URL iconeURL = getClass().getClassLoader().getResource("png/icon.png");
        ImageIcon icone = new ImageIcon(iconeURL);
        setIconImage(icone.getImage());

        gerarLayout();
    }

    private void gerarLayout() {
        JLabel texto = new JLabel("Olá mundo!");
        add(texto, BorderLayout.CENTER);

        barraLateral = new JPanel();
        barraLateral.setPreferredSize(new Dimension(200, 10));

        String[][] botoes = { {"Usuário"}, {"Administrador"}};

        barraLateral.setBackground(new Color(0x2d2f30));
        add(barraLateral, BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
    }


}
