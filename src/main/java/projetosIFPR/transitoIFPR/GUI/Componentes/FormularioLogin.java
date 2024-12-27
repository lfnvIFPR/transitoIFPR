package projetosIFPR.transitoIFPR.GUI.Componentes;

import projetosIFPR.transitoIFPR.Autenticador;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;
import projetosIFPR.transitoIFPR.IUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import com.formdev.flatlaf.FlatDarculaLaf;

public class FormularioLogin extends JPanel {
    private JPanel mainPanel;
    private JTextField ID;
    private JPasswordField senha;
    private JButton limparButton;
    private JButton entrarButton;

    public FormularioLogin() {
        FlatDarculaLaf.setup();
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // Label "Login"
        JLabel loginLabel = new JLabel("Login");

        mainPanel.add(loginLabel);

        // Sub-grid for "ID" and "Senha"
        JPanel formPanel = new JPanel();

        // Label "ID"
        JLabel idLabel = new JLabel("ID");
        formPanel.add(idLabel);

        // TextField for "ID"
        ID = new JTextField();
        ID.setPreferredSize(new Dimension(150, 24));
        formPanel.add(ID);

        // Label "Senha"
        JLabel senhaLabel = new JLabel("Senha");
        formPanel.add(senhaLabel);

        // PasswordField for "Senha"
        senha = new JPasswordField();
        senha.setPreferredSize(new Dimension(150, 24));
        formPanel.add(senha);

        // Add formPanel to mainPanel
        mainPanel.add(formPanel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        entrarButton = new JButton("Entrar");
        limparButton = new JButton("Limpar");
        buttonPanel.add(entrarButton);
        buttonPanel.add(limparButton);

        // Add buttonPanel to mainPanel
        mainPanel.add(buttonPanel);

        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autenticador entrada = new Autenticador();
                BancoDeDados bd = new BancoDeDados();
                IUsuario a = entrada.gerarUsuario(ID.getText(), Arrays.toString(senha.getPassword()), bd);
                if (a != null) {
                    JOptionPane.showMessageDialog(null, "Logado com o usuário @" + a.getNome());
                }
            }
        });
    }
}
