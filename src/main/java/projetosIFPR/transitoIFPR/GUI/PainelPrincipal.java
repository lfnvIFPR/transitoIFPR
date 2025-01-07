package projetosIFPR.transitoIFPR.GUI;

import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.GUI.Componentes.OpcaoLista;
import projetosIFPR.transitoIFPR.GUI.Componentes.PaineisComum;
import projetosIFPR.transitoIFPR.GUI.Componentes.PainelUsuario;
import projetosIFPR.transitoIFPR.estado.EstadoApp;

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
        add(conteudoPrimario, BorderLayout.CENTER);



        barraLateral = new JPanel();
        barraLateral.setPreferredSize(new Dimension(200, 10));
        barraLateral.setLayout(new BoxLayout(barraLateral, BoxLayout.Y_AXIS));

        OpcaoLista[][] botoes = { {new PaineisComum(), new PainelUsuario("Usuário"), new PainelUsuario("abbaba")}};

        IUsuario usuarioLogado = EstadoApp.getUsuarioLogado();

        String[] opcoes = usuarioLogado.getComandosAcessiveis();

        for (OpcaoLista[] categoria : botoes) {
            
            OpcaoLista tipoPainel = categoria[0];
            String nomeCategoria = tipoPainel.getNome();

            boolean categoriaAtiva = false;
            for (String opcao : opcoes) {
                if (opcao.compareTo(nomeCategoria) == 0) {
                    categoriaAtiva = true;
                    break;
                }
            }
            
            if (!categoriaAtiva) {
                continue;
            }

            for (int i = 1; i < categoria.length; i++) {
                OpcaoLista painel = categoria[i];
                JButton botao = new JButton(painel.getNome());
                barraLateral.add(botao);
                conteudoPrimario.add(painel.getComponente(), painel.getNome());
                botao.addActionListener(e -> {
                    CardLayout cl = (CardLayout) conteudoPrimario.getLayout();
                    cl.show(conteudoPrimario, painel.getNome());
                });
            }


        }


        barraLateral.setBackground(new Color(0x2d2f30));
        add(barraLateral, BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
    }


}
