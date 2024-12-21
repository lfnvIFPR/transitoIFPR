package projetosIFPR.transitoIFPR.BD;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import static java.util.Map.entry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;

public class BancoDeDados {

    private Connection conn;

    // Emulação de um banco de dados, até agora. Mude para SQL depois;
    // A String isolada representa a PK da tabela;
    private static Map<String, BDAdmin> tabelaAdmin = Map.ofEntries(
            entry("A000000000000001", new BDAdmin("OlaMundo", LocalDateTime.now())),
            entry("A000000000000002", new BDAdmin("senhasenha", LocalDateTime.now())),
            entry("A000000000000003", new BDAdmin("testando123", LocalDateTime.now())),
            entry("A000000000000004", new BDAdmin("=!Simbolos!=", LocalDateTime.now()))
    );

    private static Map<String, BDCondutor> tabelaCondutor = Map.ofEntries(
            entry("123456789", new BDCondutor("senha", LocalDate.now(), LocalDate.now(), 0, LocalDate.now())),
            entry("987654321", new BDCondutor("!", LocalDate.now(), LocalDate.now(), 4, LocalDate.now())),
            entry("001122334", new BDCondutor("hahahehe", LocalDate.now(), LocalDate.now(), 32, LocalDate.now())),
            entry("700000007", new BDCondutor("numDaSorte", LocalDate.now(), LocalDate.now(), 1, LocalDate.now()))
    );

    private static Map<String, BDFiscalizador> tabelaFiscal = Map.ofEntries(
            entry("F000000000000001", new BDFiscalizador("fiscal1", "Radar Automatico", LocalDate.now())),
            entry("F100000000000000", new BDFiscalizador("!=##$%54", "Agente Fiscal", LocalDate.now()))
    );


    public static boolean IDValido(String ID) {
        return tabelaAdmin.containsKey(ID) ||
                tabelaCondutor.containsKey(ID) ||
                tabelaFiscal.containsKey(ID);
    }

    public static BDAdmin obterRegistroA(String key) {
        if (tabelaAdmin.containsKey(key)) return tabelaAdmin.get(key);
        return null;
    }
    public static BDFiscalizador obterRegistroF(String key) {
        if (tabelaFiscal.containsKey(key)) return tabelaFiscal.get(key);
        return null;
    }
    public static BDCondutor obterRegistroC(String key) {
        if (tabelaCondutor.containsKey(key)) return tabelaCondutor.get(key);
        return null;
    }

    public BancoDeDados() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        }
        
        
        try {
            
            this.conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/transito", "admin", "60fe74406e7f353ed979f350f2fbb6a2e8690a5fa7d1b0c32983d1d8b3f95f67");
        
                for (Map.Entry<String, BDAdmin> entry : tabelaAdmin.entrySet()) {
                    String key = entry.getKey();
                    BDAdmin val = entry.getValue();

                    ArrayList<Object> params = val.export();
                    params.addFirst(key);


                    query("INSERT INTO administrador VALUES (?, ?, ?, ?);", params);
                }
                                        

            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void query(String stmt, ArrayList<Object> params) {
        PreparedStatement prep;
        try {
            prep = this.conn.prepareStatement(stmt);

            for(int i = 1; i < params.size(); i++) {
                Object param = params.get(i);
                if (param.getClass() == int.class) {
                    prep.setInt(i, (int) param);
                } else if (param.getClass() == String.class) {
                    prep.setString(i, (String) param);
                } else if (param.getClass() == LocalDateTime.class) {
                    LocalDateTime ldt = (LocalDateTime) param;
                    Timestamp ts = new Timestamp(ldt.toEpochSecond(ZoneOffset.UTC));
                    prep.setTimestamp(i, ts);
                }
            }

            prep.execute();
            
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
