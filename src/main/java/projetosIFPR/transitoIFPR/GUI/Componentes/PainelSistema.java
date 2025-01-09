package projetosIFPR.transitoIFPR.GUI.Componentes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;
import projetosIFPR.transitoIFPR.estado.GUIController;

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
            

            // assumir que não é nulo
            while (rs.next()) {

                JPanel interior = new JPanel(new MigLayout(
                        "w 100%!, insets 3%",
                        "[shrink 0][grow][shrink 0]",
                        "12"
                ));

                // fazer tabela com os valores da tabela rs.getString(0)

                String nomeTabela = rs.getString(1);
                String colunaID;

                ResultSet linhas = BD.query("SELECT * FROM transito." + nomeTabela, new Object[] {});

                JTable tabela = new JTable();

                if (linhas == null) {
                    continue;
                }

                DefaultTableModel tableModel = new DefaultTableModel();
                ResultSetMetaData metaData = linhas.getMetaData();

                int colunas = metaData.getColumnCount();
                String[] nomeColunas = new String[colunas];
                //System.out.println("Nome da tabela: " + nomeTabela);

                // criar combobox aqui para utilizar o próximo loop
                JComboBox<String> colunaEscolhida = new JComboBox<>();

                for (int i = 1; i <= colunas; i++) {
                    tableModel.addColumn(metaData.getColumnName(i));
                    colunaEscolhida.addItem(metaData.getColumnName(i));
                    nomeColunas[i-1] = metaData.getColumnName(i);
                }

                colunaID = metaData.getColumnName(1);


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
                tabela.setDefaultEditor(Object.class, null);

                JTextField busca = new JTextField();
                interior.add(new JLabel("Busca: "));
                interior.add(busca, "span 2, grow, wrap");

                interior.add(new JLabel("Coluna à buscar: "));
                interior.add(new JPanel());
                interior.add(colunaEscolhida, "grow, wrap");


                KeyListener buscaListener = new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char tecla = e.getKeyChar();
                        String textoBuscar = busca.getText();
                        if (tecla != '\b') {
                            textoBuscar += tecla;
                        }
                        //System.out.println(textoBuscar);

                        try {
                            ResultSet update = BD.query("SELECT * FROM transito." + nomeTabela +
                                    " WHERE " + colunaEscolhida.getSelectedItem() + " LIKE ?", new Object[] {textoBuscar + "%"});

                            int colunasUpdate = update.getMetaData().getColumnCount();
                            DefaultTableModel updateTableModel = new DefaultTableModel();

                            for (int i = 1; i <= colunasUpdate; i++) {
                                updateTableModel.addColumn(update.getMetaData().getColumnName(i));
                            }

                            while (update.next()) {
                                Object[] dados = new Object[colunasUpdate];

//                                System.out.print("Objetos: {");
                                for (int i = 1; i <= colunasUpdate; i++) {
                                    dados[i-1] = update.getObject(i);
//                                    System.out.print(dados[i-1].toString() + ((i == colunasUpdate) ? "" : ", "));
                                }
//                                System.out.println('}');
                                updateTableModel.addRow(dados);
                            }

                            tabela.setModel(updateTableModel);
                            tabela.setAutoCreateRowSorter(true);

                        } catch (SQLException ex) {
                            System.out.println(ex.getErrorCode());
                        }

                    }

                    public void keyPressed(KeyEvent e) {}
                    public void keyReleased(KeyEvent e) {}
                };

                busca.addKeyListener(buscaListener);


                interior.add(new JScrollPane(tabela), "grow, span, wrap, h 350!");

                tabelaAtiva.addTab(nomeTabela, interior);

                JButton inserirAtualizar = new JButton("Inserir/Atualizar");
                JButton deletar = new JButton("Deletar");
                deletar.setBackground(new Color(0xff6666));
                deletar.setForeground(new Color(0xeeeeee));
                deletar.setFont(deletar.getFont().deriveFont(Font.BOLD));

                interior.add(inserirAtualizar, "w 60%!, align center, span, wrap");
                interior.add(deletar, "w 60%!, align center, span, wrap");

                inserirAtualizar.addActionListener(e-> {
                    JDialog popupFrame = new JDialog(GUIController.getAtivo(), true);
                    popupFrame.toFront();
                    popupFrame.setMinimumSize(new Dimension(360, 480));
                    JPanel popup = new JPanel(new MigLayout(
                            "w 100%!, h 100%!, insets 10%",
                            "[shrink 0][grow][shrink 0]",
                            "12"
                    ));

                    popupFrame.setContentPane(popup);
                    Map<String, JTextField> input = new LinkedHashMap<>();

                    for (int i = 0; i < nomeColunas.length; i++) {
                        String coluna = nomeColunas[i];
                        popup.add(new JLabel(coluna + ": "));
                        JTextField textField = new JTextField();
                        textField.setPreferredSize(new Dimension(240, 24));
                        popup.add(textField, "grow, span 2, wrap");
                        input.put(coluna, textField);
                    }


                    JButton finalizarDialogo = new JButton("Inserir/Atualizar");

                    popup.add(finalizarDialogo, "span, w 65%!, h 24!, align center");

                    popupFrame.pack();

                    finalizarDialogo.addActionListener( e2 -> {

                        Object[] dados = new Object[colunas];
                        int i = 0;
                        for (Map.Entry<String, JTextField> entry : input.entrySet()) {
                            if (entry.getValue().getText().isEmpty()) {
                                popupFrame.dispose();
                                return;
                            }
                            dados[i] = entry.getValue().getText();
                            i++;
                        }

                        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

                        StringBuilder query = new StringBuilder("INSERT INTO ");
                        query.append(nomeTabela).append(" (");

                        String[] colunasNomes = input.keySet().toArray(new String[0]);
                        for (int j = 0; j < colunasNomes.length; j++) {
                            query.append(colunasNomes[j]);
                            if (j < colunasNomes.length - 1) {
                                query.append(", ");
                            }
                        }
                        query.append(") VALUES (");

                        for (int j = 0; j < colunasNomes.length; j++) {
                            query.append("?");
                            if (j < colunasNomes.length - 1) {
                                query.append(", ");
                            }
                        }
                        query.append(")");


                        int linhaSelecionada = tabela.getSelectedRow();
                        if (linhaSelecionada != -1) {
                            Object[] dadosAntigos = new Object[colunas];
                            for (int j = 0; j < colunas; j++) {
                                dadosAntigos[j] = model.getValueAt(linhaSelecionada, j);
                                model.setValueAt(dados[j], linhaSelecionada, j);
                            }

                            StringBuilder delete = new StringBuilder();
                            for (int j = 0; j < colunasNomes.length; j++) {

                                delete.append(colunasNomes[j]).append(" = ").append("?");
                                if (j < colunasNomes.length - 1) {
                                    delete.append(" AND ");
                                }
                            }
                            BD.execute(
                                    "DELETE FROM " + nomeTabela + " WHERE " + delete.toString(),
                                    dadosAntigos
                            );

                            BD.execute(query.toString(), dados);
                        } else {
                            model.addRow(dados);
                            BD.execute(query.toString(), dados);
                        }

                        popupFrame.dispose();
                    });

                    popupFrame.setVisible(true);
                });

                deletar.addActionListener(e -> {
                    int rowIdx = tabela.getSelectedRow();
                    String ID = (String) tabela.getModel().getValueAt(rowIdx,  0);

                    int resultado = JOptionPane.showConfirmDialog (null, "Deseja deletar o(s) registro(s) identificado(s) por " + ID + "?","Deletar", JOptionPane.YES_NO_OPTION);
                    if (resultado != JOptionPane.YES_OPTION) {
                        return;
                    }

                    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                    model.removeRow(rowIdx);
                    BD.execute(
                    "DELETE FROM " + nomeTabela + " WHERE " + colunaID + " = ?",
                        new Object[] {ID}
                    );
                    tabela.setModel(model);
                });


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
