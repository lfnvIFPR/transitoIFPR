package projetosIFPR.transitoIFPR;
import java.time.LocalDateTime;

public interface IUsuario {
    String getNome();
    LocalDateTime getUltimoLogin();
    String[] getComandosAcessiveis();

}
