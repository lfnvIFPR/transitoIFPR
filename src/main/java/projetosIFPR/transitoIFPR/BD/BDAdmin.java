package projetosIFPR.transitoIFPR.BD;
import java.time.LocalDate;
import projetosIFPR.transitoIFPR.cripto.Hash;

// registro da tabela de administradores
// poderia ser um public record, se não fosse pelo fato que geramos um salt para criptografar a senha;
public class BDAdmin {
    public final String hashSenha;
    public final LocalDate ultimaDataAtividade;
    public final String salt;

    public BDAdmin(String senha, LocalDate ultimaDataAtividade) {
        this.salt = Hash.randomString(8);
        this.hashSenha = Hash.hashString(senha + this.salt);
        this.ultimaDataAtividade = ultimaDataAtividade;
    }

}