package projetosIFPR.transitoIFPR.GUI.Componentes;

import net.miginfocom.swing.MigLayout;
import projetosIFPR.transitoIFPR.Condutor;
import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;
import projetosIFPR.transitoIFPR.estado.EstadoApp;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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
        fisico = new JPanel(new MigLayout(
            "w 100%!, insets 3%, wrap 1",
            "[grow]",
            ""
            )
        );

        JLabel titulo = new JLabel("Notificações");
        titulo.putClientProperty("FlatLaf.styleClass", "h1");
        fisico.add(titulo, "align left, growx");


        JPanel listaNotificacoes = new JPanel(new MigLayout(
            "w 100%!, wrap 1",
            "10[grow]10",
            "[]"
        ));

        fisico.add(listaNotificacoes, "grow, align center");


        IUsuario usr = EstadoApp.getUsuarioLogado();


        try {
            BancoDeDados BD = new BancoDeDados();
            if (usr instanceof Condutor) {

                ResultSet rs = BD.query("SELECT * FROM multa " +
                    "INNER JOIN autuacao ON multa.id_autuacao_respondida=autuacao.id_autuacao " + 
                    "WHERE cnh_responsavel = ? AND autuacao.data_ocorrido = ? " + 
                    "ORDER BY id_multa DESC", 
                    new Object[] {usr.getNome(), usr.getUltimoLogin()}
                );
                
        
                if (rs == null || !rs.next()) {
                    
                    JLabel msg = new JLabel("Não há notificações disponíveis.");
                    msg.setHorizontalTextPosition(SwingConstants.CENTER);
                    msg.setHorizontalAlignment(SwingConstants.CENTER);;
                    msg.setForeground(new Color(0x999999));
                    msg.setFont(msg.getFont().deriveFont(Font.ITALIC));
                    listaNotificacoes.add(msg, "align center, growx");

                } else {

                }

                
            } else {
                ResultSet rs = BD.query("SELECT * FROM resposta " +
                    "INNER JOIN autuacao ON resposta.id_autuacao=autuacao.id_autuacao " + 
                    "WHERE autuacao.data_ocorrido = ? " + 
                    "ORDER BY id_resposta DESC", 
                    new Object[] {usr.getUltimoLogin()}
                );

                if (rs == null || !rs.next()) {
                    
                    JLabel msg = new JLabel("Não há notificações disponíveis.");
                    msg.setHorizontalTextPosition(SwingConstants.CENTER);
                    msg.setHorizontalAlignment(SwingConstants.CENTER);;
                    msg.setForeground(new Color(0x999999));
                    msg.setFont(msg.getFont().deriveFont(Font.ITALIC));
                    listaNotificacoes.add(msg, "align center, growx");

                } else {


                }

            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
}
