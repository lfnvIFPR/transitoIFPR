package projetosIFPR.transitoIFPR.BD;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class BancoDeDados {

    private Connection conn;

    public boolean IDValido(String ID) {

        String sql = """
            SELECT 1
            FROM administrador WHERE id_admin = ?
            UNION
            SELECT 1
            FROM condutor WHERE numero_CNH = ?
            UNION
            SELECT 1
            FROM agente_fiscalizador WHERE id_agente = ?
            LIMIT 1
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Define o mesmo parâmetro para as 3 tabelas
            stmt.setString(1, ID);
            stmt.setString(2, ID);
            stmt.setString(3, ID);

            // Executa a consulta
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    public BDAdmin obterRegistroA(String key) {
        String sql = "SELECT * FROM administrador WHERE id_admin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, key);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashSenha = rs.getString("hash_senha");
                    Timestamp ultimaDataAtividade = rs.getTimestamp("ultima_data_atividade");
                    String salt = rs.getString("salt");

                    return new BDAdmin(hashSenha, ultimaDataAtividade.toLocalDateTime(), salt);
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public BDFiscalizador obterRegistroF(String key) {
        String sql = "SELECT * FROM agente_fiscalizador WHERE id_agente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, key);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String hashSenha = rs.getString("hash_senha");
                    String salt = rs.getString("salt");
                    Timestamp ultimaDataAtividade = rs.getTimestamp("ultima_data_atividade");

                    return new BDFiscalizador(hashSenha, nome, ultimaDataAtividade.toLocalDateTime(), salt);
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    // Método para obter um registro da tabela condutor
    public BDCondutor obterRegistroC(String key) {
        String sql = "SELECT * FROM condutor WHERE numero_CNH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, key);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashSenha = rs.getString("hash_senha");
                    Date dataCnh = rs.getDate("data_cnh");
                    Date dataNascimento = rs.getDate("data_nascimento");
                    int pontosCNH = rs.getInt("pontuacao_atual");
                    Timestamp ultimaDataAtividade = rs.getTimestamp("ultima_data_atividade");
                    String salt = rs.getString("salt");

                    return new BDCondutor(hashSenha, dataCnh.toLocalDate(), dataNascimento.toLocalDate(), pontosCNH, ultimaDataAtividade.toLocalDateTime(), salt);
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public BancoDeDados() {
        try {
            
            this.conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/transito", "java", "javaConnector");
//
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT table_name\n" +
//                "FROM information_schema.tables\n" +
//                "WHERE table_type='BASE TABLE'\n" +
//                "      AND table_schema = 'transito'");
//
//            while (rs.next()) {
//                String tableName = rs.getString(1);
//                System.out.println("Table " + tableName);
//            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public ResultSet query(String stmt, Object[] params) {
        PreparedStatement prep;
        try {
            prep = this.conn.prepareStatement(stmt);

            for(int i = 0; i < params.length; i++) {
                Object param = params[i];
                if (param.getClass() == int.class) {
                    prep.setInt(i + 1, (int) param);
                } else if (param.getClass() == String.class) {
                    prep.setString(i + 1, (String) param);
                } else if (param.getClass() == LocalDateTime.class) {
                    LocalDateTime ldt = (LocalDateTime) param;
                    Timestamp ts = Timestamp.valueOf(ldt);
                    prep.setTimestamp(i + 1, ts);
                }
            }

            ResultSet rs = prep.executeQuery();
            return rs;
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("Query: " + stmt);
        }

        return null;
    }

}
