package projetosIFPR.transitoIFPR.GUI;

import projetosIFPR.transitoIFPR.GUI.Componentes.*;
import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.comandos.SairComando;
import projetosIFPR.transitoIFPR.estado.EstadoApp;
import projetosIFPR.transitoIFPR.utilidade.Palavras;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

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
        pack();
        repaint();
    }

    private void gerarLayout() {
        setLayout(new MigLayout(
                "insets 0, w 1000, h 800",
                "[20%][80%]",
                "[grow]"
        ));

        JPanel header = new JPanel(new MigLayout("insets 1%"));
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(0x2d2f30));
        add(header, "dock north");

        JLabel ifpr = new JLabel("IFPR");
        ifpr.putClientProperty("FlatLaf.styleClass", "h1");
        header.add(ifpr);

        conteudoPrimario = new JPanel(new CardLayout());
        conteudoPrimario.setOpaque(false);

        barraLateral = new JPanel();
        barraLateral.setLayout(new MigLayout("wrap 1, inset 1%"));
        barraLateral.setBackground(new Color(0x2d2f30));

        add(barraLateral, "grow");
        add(conteudoPrimario, "grow");

        OpcaoLista[][] botoes = {
            {
                new PaineisComum(),
                new PainelUsuario("Usuário"),
                new PainelNotificacao("Notificações"),
                new BotaoComando(new SairComando(), "Sair",
                        "Encerrar sessão e voltar para a tela de log in.",
                        true)
            },
            {
                new PaineisAdmin(), 
                new PainelSistema("Sistema")
            }
        };

        IUsuario usuarioLogado = EstadoApp.getUsuarioLogado();

        String[] opcoes = usuarioLogado.getComandosAcessiveis();

        for (OpcaoLista[] categoria : botoes) {
            
            OpcaoLista tipoPainel = categoria[0];
            String nomeCategoria = tipoPainel.getNome();

            boolean categoriaValida = false;
            for (String opcao : opcoes) {
                if (opcao.compareTo(nomeCategoria) == 0) {
                    categoriaValida = true;
                    break;
                }
            }
            
            if (!categoriaValida) {
                continue;
            }

            for (int i = 0; i < categoria.length; i++) {
                OpcaoLista opcao = categoria[i];

                if (opcao.possuiComponente()) {
                    JButton botao = new JButton(opcao.getNome());
                    botao.setToolTipText(opcao.getDescricao());
                    barraLateral.add(botao, "gapleft 8%, w 80%!, align center");
                    conteudoPrimario.add(opcao.getComponente(), opcao.getNome());
                    botao.addActionListener(e -> {
                        CardLayout cl = (CardLayout) conteudoPrimario.getLayout();
                        cl.show(conteudoPrimario, opcao.getNome());
                        conteudoPrimario.repaint();
                    });
                } else {

                    if (!(opcao instanceof BotaoExecutor)) {
                        JLabel categoriaLabel = new JLabel(Palavras.capitalizar(nomeCategoria));
                        barraLateral.add(categoriaLabel, "gapleft 4%, w 80%!");
                        continue;
                    }

                    JButton botao = new JButton(opcao.getNome());
                    botao.setToolTipText(opcao.getDescricao());

                    if (((BotaoExecutor) opcao).botaoImportante()) {
                        botao.setBackground(new Color(0xff6666));
                        botao.setForeground(new Color(0xeeeeee));
                        botao.setFont(botao.getFont().deriveFont(Font.BOLD));
                    }

                    barraLateral.add(botao, "gapleft 8%, w 80%!, align center");
                    botao.addActionListener(e -> {
                        BotaoExecutor opcaoExecutora = (BotaoExecutor) opcao;
                        opcaoExecutora.executarComando();
                    });

                }
            }


        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }


}
