package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.comandos.Comando;

import java.util.Arrays;

public class Condutor implements IUsuario {
    private Comando comandoAtivo;
    private String ID;

    private String[] comandosAcessiveis = new String[]{"CONDUTOR", "COMUM"};

    public Condutor(String ID) {
        this.ID = ID;
    }

    public void configurarComando(Comando comandoAtivo) { this.comandoAtivo = comandoAtivo; }
    public void executarComando() { this.comandoAtivo.executar(); }



    public String getNome() {
        return this.ID;
    }
    public String[] getComandosAcessiveis() {
        return Arrays.copyOf(this.comandosAcessiveis, this.comandosAcessiveis.length);
    }
}
