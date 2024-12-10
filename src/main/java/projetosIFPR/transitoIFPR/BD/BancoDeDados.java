package projetosIFPR.transitoIFPR.BD;

import java.time.LocalDate;
import java.util.*;
import static java.util.Map.entry;

public class BancoDeDados {


    // Emulação de um banco de dados, até agora. Mude para SQL depois;
    // A String isolada representa a PK da tabela;
    private static Map<String, BDAdmin> tabelaAdmin = Map.ofEntries(
            entry("A000000000000001", new BDAdmin("OlaMundo", LocalDate.now())),
            entry("A000000000000002", new BDAdmin("senhasenha", LocalDate.now())),
            entry("A000000000000003", new BDAdmin("testando123", LocalDate.now())),
            entry("A000000000000004", new BDAdmin("=!Simbolos!=", LocalDate.now()))
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

}
