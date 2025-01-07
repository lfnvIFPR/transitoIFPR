package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.comandos.Comando;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Administrador implements IUsuario {
    private Comando comandoAtivo;
    private String ID;
    private LocalDateTime ultimoLogin;

    private String[] comandosAcessiveis = new String[]{"ADMIN", "FISCAL", "COMUM"};

    public Administrador(String ID, LocalDateTime ultimoLogin) {
        this.ID = ID;
        this.ultimoLogin = ultimoLogin;
    }

    public void configurarComando(Comando comandoAtivo) { this.comandoAtivo = comandoAtivo; }
    public void executarComando() { this.comandoAtivo.executar(); }



    public String getNome() {
        return this.ID;
    }

    public LocalDateTime getUltimoLogin() {
        return this.ultimoLogin;
    }

    public String[] getComandosAcessiveis() {
        return Arrays.copyOf(this.comandosAcessiveis, this.comandosAcessiveis.length);
    }
}
