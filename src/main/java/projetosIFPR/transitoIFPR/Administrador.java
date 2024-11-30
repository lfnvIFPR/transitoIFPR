package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.comandos.Comando;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Administrador implements IUsuario {
    private Comando comandoAtivo;
    private String ID;

    private ArrayList<String> comandosAcessiveis = new ArrayList<String>(
            Arrays.asList(
                    new String[]{"ADMIN", "FISCAL", "COMUM"}
            )
    );

    public Administrador(String ID) {
        this.ID = ID;
    }

    public void configurarComando(Comando comandoAtivo) { this.comandoAtivo = comandoAtivo; }
    public void executarComando() { this.comandoAtivo.executar(); }



    public String getNome() {
        return this.ID;
    }
    public List<String> getComandosAcessiveis() {
        return List.copyOf(comandosAcessiveis);
    }
}
