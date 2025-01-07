package projetosIFPR.transitoIFPR.GUI;

import projetosIFPR.transitoIFPR.Autenticador;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;
import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.estado.EstadoApp;
import projetosIFPR.transitoIFPR.estado.GUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class TelaLogin extends JFrame {

    private JTextField ID;
    private JPasswordField senha;
    private JButton login;

    public TelaLogin() {
        super("Sistema de trânsito - IFPR");

        URL iconeURL = getClass().getClassLoader().getResource("png/icon.png");
        ImageIcon icone = new ImageIcon(iconeURL);
        setIconImage(icone.getImage());

        gerarLayout();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BancoDeDados bd = new BancoDeDados();
                Autenticador entrada = new Autenticador();
                IUsuario usuario = entrada.gerarUsuario(ID.getText(), String.valueOf(senha.getPassword()), bd);

                if (usuario != null) {
                    String[] comandos = usuario.getComandosAcessiveis();
                    StringBuilder sb = new StringBuilder();
                    for (String comando: comandos) {
                        sb.append(comando);
                        if (!Objects.equals(comando, comandos[comandos.length - 1])) {
                            sb.append(", ");
                        }

                    }
                    JOptionPane.showMessageDialog(null, "Olá, usuário @" + usuario.getNome() + "!\n Você possui os comandos: " + sb.toString());
                    EstadoApp.setUsuarioLogado(usuario);
                    GUIController.configurarAtivo(new PainelPrincipal());
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    senha.setText(null);
                    ID.setText(null);
                }
            }
        });

    }

    private void gerarLayout() {
        JPanel main = new JPanel();
        setLayout(null);
        setSize(850, 700);
        main.setLayout(new GridBagLayout());

        JPanel secundario = new JPanel(new GridLayout(0, 1));

        secundario.setSize(450, 10);
        secundario.setBackground(new Color(0x36393b));

        JPanel primario = new JPanel();
        primario.setSize(300, 10);
        primario.setLayout(new GridLayout(4, 1));
        primario.setBackground(new Color(0x2d2f30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 0.4;
        main.add(primario, gbc);


        gbc.weightx = 0.6;
        gbc.gridx = 1;
        main.add(secundario, gbc);

        JLabel loginLabel = new JLabel("Login");

        loginLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 24));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel idLabel = new JLabel("ID:");
        JLabel senhaLabel = new JLabel("Senha:");

        idLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
        senhaLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));

        ID = new JTextField();
        ID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));

        senha = new JPasswordField();
        senha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));

        JPanel idPanel = new JPanel(new GridLayout(1,0));
        JPanel senhaPanel = new JPanel(new GridLayout(1,0));

        idPanel.setOpaque(false);
        senhaPanel.setOpaque(false);

        primario.add(encapsular(loginLabel));
        primario.add(encapsular(idPanel));
        primario.add(encapsular(senhaPanel));

        JPanel botaoPanel = new JPanel();
        botaoPanel.setOpaque(false);
        botaoPanel.setLayout(new BoxLayout(botaoPanel, BoxLayout.Y_AXIS));
        primario.add(encapsular(botaoPanel));

        login = new JButton("Entrar");
        login.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));


        botaoPanel.add(login, BorderLayout.CENTER);
        botaoPanel.add(Box.createVerticalGlue());

        ID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));

        idPanel.add(encapsular(idLabel));
        senhaPanel.add(encapsular(senhaLabel));

        idPanel.add(centralizar(ID, idPanel));
        senhaPanel.add(centralizar(senha, senhaPanel));

        URL clipartURL = getClass().getClassLoader().getResource("png/clipart.png");
        ImageIcon clipartImg = new ImageIcon(clipartURL);
        clipartImg = new ImageIcon(
                clipartImg.getImage()
                        .getScaledInstance(360, 360, Image.SCALE_SMOOTH));
        JLabel clipart = new JLabel(clipartImg);
        clipart.setVerticalAlignment(SwingConstants.CENTER);

        secundario.add(clipart);

        setContentPane(main);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    JPanel centralizar(Component c, Component container) {

        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(Box.createVerticalGlue());
        painel.add(c, BorderLayout.CENTER);
        painel.add(Box.createVerticalGlue());


        return painel;
    }

    JPanel encapsular(Component c) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setOpaque(false);

        JPanel marginN = new JPanel();
        JPanel marginW = new JPanel();
        JPanel marginS = new JPanel();
        JPanel marginE = new JPanel();

        marginN.setOpaque(false);
        marginW.setOpaque(false);
        marginS.setOpaque(false);
        marginE.setOpaque(false);

        marginN.setPreferredSize(new Dimension(8,8));
        marginW.setPreferredSize(new Dimension(8,8));
        marginS.setPreferredSize(new Dimension(8,8));
        marginE.setPreferredSize(new Dimension(8,8));


        painel.add(marginW, BorderLayout.WEST);
        painel.add(marginE, BorderLayout.EAST);
        painel.add(marginN, BorderLayout.NORTH);
        painel.add(marginS, BorderLayout.SOUTH);


        painel.add(c, BorderLayout.CENTER);
        return painel;
    }
}
