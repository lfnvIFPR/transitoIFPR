package projetosIFPR.transitoIFPR.GUI.Componentes;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import android.net.sip.SipException;
import net.miginfocom.swing.MigLayout;
import projetosIFPR.transitoIFPR.Administrador;
import projetosIFPR.transitoIFPR.Condutor;
import projetosIFPR.transitoIFPR.Fiscalizador;
import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;
import projetosIFPR.transitoIFPR.estado.EstadoApp;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            "[shrink 0][shrink 0][grow]"
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
        fisico.add(sep, "w 85%!, align center, wrap");

        JPanel infoEspecifico = new JPanel(new MigLayout(
            "h 300:n, wrap 3",
            "[shrink 0][grow][shrink 0]",
            "[]"
        ));
        
        try {
            
            BancoDeDados BD = new BancoDeDados();
            
            
            if (usr instanceof Condutor) {
                
                ResultSet rs = BD.query(
                    "SELECT data_cnh, data_nascimento, pontuacao_atual FROM condutor WHERE numero_CNH = ?",
                    new Object[] {usr.getNome()}
                );
                
                rs.next();
                LocalDate cnh = rs.getDate(1).toLocalDate();
                LocalDate nascimento = rs.getDate(2).toLocalDate();
                int pontos = rs.getInt(3);
                
                DateTimeFormatter fmt2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                
                // System.out.println(cnh);
                // System.out.println(nascimento);
                // System.out.println(pontos);

                listar(infoEspecifico, "Data da CNH: ", cnh.format(fmt2));

                listar(infoEspecifico, "Data de nascimento: ", nascimento.format(fmt2) + 
                " (" + (nascimento.until(LocalDate.now()).getYears()) + " anos)"
                );

                listar(infoEspecifico, "Pontuação atual: ", pontos + " pontos");

                rs = BD.query(
                    "SELECT COUNT(*) FROM multa WHERE cnh_responsavel = ?",
                    new Object[] {usr.getNome()}
                );

                rs.next();

                int multas = rs.getInt(1);

                listar(infoEspecifico, "Número de multas registradas:", String.valueOf(multas));
                
            }
            
            
        } catch (SQLException ex) {
            

            System.out.println("Erro no usuario");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        
        fisico.add(infoEspecifico, "w 85%!, align center, grow");


    }


    // assumindo pai tem MigLayout
    // [shrink 0][grow][shrink 0]

    private void listar(JPanel pai, String nome, String valor) {
        pai.add(new JLabel(nome), "align leading");
        pai.add(new JPanel());
        pai.add(new JLabel(valor), "align trailing, wrap");
        pai.add(new JSeparator(), "span, wrap");
    }


}
