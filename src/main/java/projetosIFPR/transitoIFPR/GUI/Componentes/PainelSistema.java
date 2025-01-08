package projetosIFPR.transitoIFPR.GUI.Componentes;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat.Style;
import java.util.function.ObjIntConsumer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;

public class PainelSistema implements OpcaoLista {

    String nome;
    JPanel fisico;


    public PainelSistema(String nome) {
        this.nome = nome;
        gerarLayout();
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return "Remover, atualizar e obter informações deste sistema.";
    }

    @Override
    public boolean possuiComponente() {
        return true;
    }

    @Override
    public Component getComponente() {
        return fisico;
    }

    public void gerarLayout() {
        fisico = new JPanel(new MigLayout(
            "w 100%!, insets 3%, wrap 1",
            "[grow]",
            ""
            )
        );


        JTabbedPane tabelaAtiva = new JTabbedPane();

        
        try {
            BancoDeDados BD = new BancoDeDados();
            ResultSet rs = BD.query("SELECT table_name FROM information_schema.tables WHERE table_schema = 'transito' ", new Object[]{});

            JPanel interior = new JPanel(new MigLayout(
                "w 100%!, insets 3%",
                "",
                ""
            ));
            

            // assumir que não é nulo
            while (rs.next()) {
                
                // fazer tabela com os valores da tabela rs.getString(0)

                String nomeTabela = rs.getString(1);

                ResultSet linhas = BD.query("SELECT * FROM transito." + nomeTabela, new Object[] {});

                JTable tabela = new JTable();

                if (linhas == null) {
                    continue;
                }

                DefaultTableModel tableModel = new DefaultTableModel();
                ResultSetMetaData metaData = linhas.getMetaData();

                int colunas = metaData.getColumnCount();

                System.out.println("Nome da tabela: " + nomeTabela);

                for (int i = 1; i <= colunas; i++) {
                    tableModel.addColumn(metaData.getColumnName(i));
                }

                while (linhas.next()) {
                    Object[] dados = new Object[colunas];
                    for (int i = 1; i <= colunas; i++) {
                        dados[i-1] = linhas.getObject(i);
                    }

                    tableModel.addRow(dados);
                }


                tabela.setModel(tableModel);
                // ???? 
                tabela.setAutoCreateRowSorter(true);
                
                tabelaAtiva.addTab(nomeTabela, new JScrollPane(tabela));
            }




            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("PainelSistema");
        }


        JLabel titulo = new JLabel("Sistema");
        titulo.putClientProperty("FlatLaf.styleClass", "h1");
        fisico.add(titulo, "align left, growx");

        fisico.add(tabelaAtiva, "grow, align center");
        

    }
    
}
