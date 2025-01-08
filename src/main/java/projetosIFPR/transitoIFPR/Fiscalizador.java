package projetosIFPR.transitoIFPR;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Fiscalizador implements IUsuario {
    private String ID;
    private LocalDateTime ultimoLogin;

    private String[] comandosAcessiveis = new String[]{"FISCAL", "COMUM"};

    public Fiscalizador(String ID, LocalDateTime ultimoLogin) {
        this.ID = ID;
        this.ultimoLogin = ultimoLogin;
    }


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
